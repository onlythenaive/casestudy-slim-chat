package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import com.onlythenaive.casestudy.slimchat.service.core.domain.chat.ChatIdWrapper;

/**
 * Chat message invoice wrapper.
 *
 * @author Ilia Gubarev
 */
public class MessageInvoiceWrapper {

    /**
     * Creates a new wrapper from an invoice and the ID of its author.
     *
     * @param invoice an original invoice.
     * @param authorId the ID of the author.
     * @return a new created wrapper.
     */
    public static MessageInvoiceWrapper of(MessageInvoice invoice, String authorId) {
        MessageInvoiceWrapper invoiceWrapper = new MessageInvoiceWrapper();
        invoiceWrapper.invoice = invoice;
        ChatIdWrapper chatIdWrapper = ChatIdWrapper.parse(invoice.getChatId());
        invoiceWrapper.groupId = chatIdWrapper.getGroupId();
        String profileId1 = chatIdWrapper.getProfileId1();
        String profileId2 = chatIdWrapper.getProfileId2();
        invoiceWrapper.recipientId = authorId.equals(profileId1) ? profileId2 : profileId1;
        return invoiceWrapper;
    }

    private String groupId;
    private String recipientId;
    private MessageInvoice invoice;

    /**
     * Gets the ID of a group.
     *
     * @return the ID of a group.
     */
    public String getGroupId() {
        return this.groupId;
    }

    /**
     * Gets the ID of the recipient.
     *
     * @return the ID of the recipient.
     */
    public String getRecipientId() {
        return this.recipientId;
    }

    /**
     * Gets the original invoice.
     *
     * @return the original invoice.
     */
    public MessageInvoice getInvoice() {
        return this.invoice;
    }
}
