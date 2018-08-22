package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupInvoice {

    private String caption;
    private Collection<String> participantIds;
}
