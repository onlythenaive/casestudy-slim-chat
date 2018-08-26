package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileUpdateInvoice;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileFormInput {

    private String id;
    private String email;
    private String firstname;
    private String lastname;
    private String status;
    private Boolean restricted;

    public ProfileUpdateInvoice toUpdateInvoice() {
        return ProfileUpdateInvoice.builder()
                .email(email)
                .firstname(firstname)
                .lastname(lastname)
                .status(status)
                .restricted(restricted)
                .build();
    }
}
