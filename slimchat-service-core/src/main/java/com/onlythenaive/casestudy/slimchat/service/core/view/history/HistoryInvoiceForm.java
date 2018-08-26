package com.onlythenaive.casestudy.slimchat.service.core.view.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryInvoiceForm {

    private String historyId;
    private String viewToRedirect;
}
