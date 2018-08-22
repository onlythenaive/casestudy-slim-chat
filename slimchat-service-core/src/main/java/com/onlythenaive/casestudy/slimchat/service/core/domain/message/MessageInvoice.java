package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageInvoice {

    private String text;
    private Boolean personal;
    private String recipientId;
    private String groupId;
}
