package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

/**
 * Chat history operations facade.
 *
 * @author Ilia Gubarev
 */
public interface HistoryFacade {

    /**
     * Gets an existing history by its ID.
     *
     * @param id the ID of a history.
     * @return the requested history.
     */
    History getById(String id);

    /**
     * Gets a search result among existing histories.
     *
     * @param searchInvoice a search invoice.
     * @return the search results.
     */
    HistorySearchResult getSearchResult(HistorySearchInvoice searchInvoice);

    /**
     * Clears an existing history.
     *
     * @param id the ID of teh history to be cleared.
     */
    void clear(String id);
}
