package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    private String id;
    private Profile initiator;
    private Profile acceptor;
    private String introduction;
    private boolean accepted;
    private boolean pending;
    private boolean requested;
    private boolean own;
}
