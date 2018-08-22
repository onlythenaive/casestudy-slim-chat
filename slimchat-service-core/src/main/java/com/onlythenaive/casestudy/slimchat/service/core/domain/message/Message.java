package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String id;
    private String text;
    private Profile author;
    private boolean personal;
    private Profile recipient;
    private Group group;
    private Instant createdAt;
}
