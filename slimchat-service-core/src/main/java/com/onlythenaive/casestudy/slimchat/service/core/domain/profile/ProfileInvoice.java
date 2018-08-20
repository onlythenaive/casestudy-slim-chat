package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileInvoice {

    private String id;

    private String accountName;
    private String email;
    private String firstname;
    private String lastname;
    private String status;
}
