package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Chat message search result.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageSearchResult {

    private Collection<Message> items;
}
