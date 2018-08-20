package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

public interface MessageProjector {

    Message intoMessage(MessageEntity entity);
}
