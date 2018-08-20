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

    private String chatId;
    private String text;
}
