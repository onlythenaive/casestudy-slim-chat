package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Connection proposal invoice.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalInvoice {

    private String text;
    private String initiatorId;
    private String acceptorId;
}
