package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;

@Service
public class ContactFacadeBean extends DomainComponentBean implements ContactFacade {

    @Autowired
    private ContactProjector contactProjector;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact acceptContactById(String id) {
        ContactEntity entity = this.contactRepository.findById(id).orElseThrow(this::contactNotFound);
        if (!entity.getAcceptorId().equals(principalId())) {
            throw insufficientPrivileges();
        }
        if (entity.isAccepted()) {
            throw contactAlreadyAccepted();
        }
        entity.setAccepted(true);
        this.contactRepository.save(entity);
        return project(entity);
    }

    @Override
    public Optional<Contact> cancelContactById(String id) {
        ContactEntity entity = this.contactRepository.findById(id).orElseThrow(this::contactNotFound);
        if (!entity.getAcceptorId().equals(principalId()) && !entity.getInitiatorId().equals(principalId())) {
            throw insufficientPrivileges();
        }
        if (!entity.isAccepted()) {
            this.contactRepository.deleteById(id);
            return Optional.empty();
        }
        if (entity.getAcceptorId().equals(principalId())) {
            entity.setAccepted(false);
            this.contactRepository.save(entity);
            return Optional.of(project(entity));
        } else {
            this.contactRepository.deleteById(id);
            return Optional.empty();
        }
    }

    @Override
    public Contact createContact(ContactInvoice invoice) {
        String acceptorId = invoice.getAcceptorId();
        ensureUniqueness(principalId(), acceptorId);
        ContactEntity entity = ContactEntity.builder()
                .id(uuid())
                .initiatorId(principalId())
                .acceptorId(acceptorId)
                .introduction(invoice.getIntroduction())
                .createdAt(now())
                .build();
        this.contactRepository.insert(entity);
        return project(entity);
    }

    private void ensureUniqueness(String initiatorId, String acceptorId) {
        if (this.contactRepository.existsByInitiatorIdAndAcceptorId(initiatorId, acceptorId)) {
            throw contactAlreadyExists();
        }
    }

    @Override
    public Collection<Contact> findAllAcceptedContacts() {
        return this.contactRepository.findAllByAcceptorIdOrInitiatorIdAndAccepted(principalId(), principalId(),true)
                .stream()
                .map(this::project)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Contact> findAllPendingContacts() {
        return findAllInitiatedContacts(false);
    }

    @Override
    public Collection<Contact> findAllRequestedContacts() {
        return this.contactRepository.findAllByAcceptorIdAndAccepted(principalId(), false)
                .stream()
                .map(this::project)
                .collect(Collectors.toList());
    }

    private Collection<Contact> findAllInitiatedContacts(boolean accepted) {
        return this.contactRepository.findAllByInitiatorIdAndAccepted(principalId(), accepted)
                .stream()
                .map(this::project)
                .collect(Collectors.toList());
    }

    private Contact project(ContactEntity entity) {
        return this.contactProjector.intoContact(entity);
    }

    private RuntimeException contactAlreadyAccepted() {
        return OperationException.builder()
                .category(ExceptionCategory.LOGIC)
                .textcode("x.logic.contact.already-accepted")
                .comment("Contact is already accepted")
                .build();
    }

    private RuntimeException contactAlreadyExists() {
        return OperationException.builder()
                .category(ExceptionCategory.LOGIC)
                .textcode("x.logic.contact.already-exists")
                .comment("Contact is already exists")
                .build();
    }

    private RuntimeException contactNotFound() {
        return OperationException.builder()
                .category(ExceptionCategory.LOGIC)
                .textcode("x.logic.contact.not-found")
                .comment("Contact not found")
                .build();
    }
}
