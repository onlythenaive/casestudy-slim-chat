package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;

public interface MessageRepositoryExtension {

    Collection<MessageEntity> getLatestInChat(String observerId);
}
