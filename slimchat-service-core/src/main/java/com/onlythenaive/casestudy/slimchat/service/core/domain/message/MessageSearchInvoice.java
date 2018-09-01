package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageSearchInvoice {

    private String groupId;
    private String profileId1;
    private String profileId2;
}
