package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import com.onlythenaive.casestudy.slimchat.service.core.domain.chat.ChatIdWrapper;

/**
 * Chat message invoice.
 *
 * @author Ilia Gubarev
 */
public class MessageInvoiceWrapper {

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

    public String getGroupId() {
        return this.groupId;
    }

    public String getRecipientId() {
        return this.recipientId;
    }

    public MessageInvoice getInvoice() {
        return this.invoice;
    }
}
