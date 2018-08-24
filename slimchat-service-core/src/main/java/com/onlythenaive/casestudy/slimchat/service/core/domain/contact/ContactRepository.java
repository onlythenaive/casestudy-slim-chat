package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import java.util.Collection;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainRepository;

public interface ContactRepository extends DomainRepository<ContactEntity> {

    Collection<ContactEntity> findAllByAcceptorIdOrInitiatorIdAndAccepted(String acceptorId, String initiatorId, boolean accepted);

    Collection<ContactEntity> findAllByAcceptorIdAndAccepted(String acceptorId, boolean accepted);

    Collection<ContactEntity> findAllByInitiatorIdAndAccepted(String initiatorId, boolean accepted);

    boolean existsByInitiatorIdAndAcceptorId(String initiatorId, String acceptorId);
}
