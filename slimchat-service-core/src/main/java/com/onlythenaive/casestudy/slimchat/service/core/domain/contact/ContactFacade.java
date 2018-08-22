package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import java.util.Collection;
import java.util.Optional;

public interface ContactFacade {

    Contact acceptContactById(String id);

    Optional<Contact> cancelContactById(String id);

    Contact createContact(ContactInvoice invoice);

    Collection<Contact> findAllAcceptedContacts();

    Collection<Contact> findAllPendingContacts();

    Collection<Contact> findAllRequestedContacts();
}
