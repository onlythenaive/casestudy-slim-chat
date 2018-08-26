package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryFormInput {

    private String historyId;
    private String redirectUri;
}
