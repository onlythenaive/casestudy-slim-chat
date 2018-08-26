package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.proposal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalFormInput {

    private String proposalId;
    private String acceptorId;
    private String initiatorId;
    private String text;
    private String redirectUri;
}
