package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Chat message invoice.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageInvoice {

    private String text;
    private String threadId;
}
