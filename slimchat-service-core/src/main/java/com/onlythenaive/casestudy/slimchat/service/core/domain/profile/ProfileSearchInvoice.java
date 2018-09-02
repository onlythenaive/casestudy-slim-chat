package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Profile search invoice.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileSearchInvoice {

    // TODO: add search & paging properties

    private Boolean connected;
    private String nameTemplate;
}
