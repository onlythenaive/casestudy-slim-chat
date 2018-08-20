package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

public interface ProfileService {

    Profile createProfile(Profile profile);

    Profile findProfileByNickname(String nickname);

    Profile getProfileById(String id);

    Profile updateProfile(String id, Profile profile);
}
