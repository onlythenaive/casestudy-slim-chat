package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;

@Service
public class ContactFacadeBean extends GenericDomainComponentBean implements ContactFacade {

    @Autowired
    private ContactProjector contactProjector;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact acceptContactById(String id) {
        Account account = authenticated();
        ContactEntity entity = this.contactRepository.findById(id).orElseThrow(this::contactNotFound);
        if (!entity.getAcceptorId().equals(account.getId())) {
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
        Account account = authenticated();
        ContactEntity entity = this.contactRepository.findById(id).orElseThrow(this::contactNotFound);
        if (!entity.getAcceptorId().equals(account.getId()) && !entity.getInitiatorId().equals(account.getId())) {
            throw insufficientPrivileges();
        }
        if (!entity.isAccepted()) {
            this.contactRepository.deleteById(id);
            return Optional.empty();
        }
        if (entity.getAcceptorId().equals(account.getId())) {
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
        Account account = authenticated();
        String acceptorId = invoice.getAcceptorId();
        ensureUniqueness(account.getId(), acceptorId);
        ContactEntity entity = ContactEntity.builder()
                .id(uuid())
                .initiatorId(account.getId())
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
        Account account = authenticated();
        return this.contactRepository.findAllByAcceptorIdOrInitiatorIdAndAccepted(account.getId(), account.getId(),true)
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
        Account account = authenticated();
        return this.contactRepository.findAllByAcceptorIdAndAccepted(account.getId(), false)
                .stream()
                .map(this::project)
                .collect(Collectors.toList());
    }

    private Collection<Contact> findAllInitiatedContacts(boolean accepted) {
        Account account = authenticated();
        return this.contactRepository.findAllByInitiatorIdAndAccepted(account.getId(), accepted)
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
