package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<ContactEntity, String> {

    Collection<ContactEntity> findAllByAcceptorIdOrInitiatorIdAndAccepted(String acceptorId, String initiatorId, boolean accepted);

    Collection<ContactEntity> findAllByAcceptorIdAndAccepted(String acceptorId, boolean accepted);

    Collection<ContactEntity> findAllByInitiatorIdAndAccepted(String initiatorId, boolean accepted);

    boolean existsByInitiatorIdAndAcceptorId(String initiatorId, String acceptorId);
}
