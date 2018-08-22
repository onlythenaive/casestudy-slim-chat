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
public class ContactInvoice {

    private String acceptorId;
    private String introduction;
}
