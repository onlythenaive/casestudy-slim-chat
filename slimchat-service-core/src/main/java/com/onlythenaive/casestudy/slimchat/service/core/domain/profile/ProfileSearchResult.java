package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Profile search result.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileSearchResult {

    private Collection<Profile> items;
    private Long total;
}
