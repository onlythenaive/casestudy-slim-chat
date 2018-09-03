package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Chat history search result.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistorySearchResult {

    private Collection<History> items;
}
