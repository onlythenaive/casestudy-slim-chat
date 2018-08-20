package com.onlythenaive.casestudy.slimchat.service.core.domain.chat;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatInvoice {

    private String caption;
    private Collection<String> adminIds;
    private Collection<String> participantIds;
}
