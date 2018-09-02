package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistorySearchResult {

    private Collection<History> items;
}
