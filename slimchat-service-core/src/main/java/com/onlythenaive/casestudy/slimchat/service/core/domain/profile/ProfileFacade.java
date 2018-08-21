package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

public interface ProfileFacade {

    Profile getProfileByAccountName(String accountName);

    Profile getProfileById(String id);

    Profile updateProfile(ProfileInvoice profile);
}
