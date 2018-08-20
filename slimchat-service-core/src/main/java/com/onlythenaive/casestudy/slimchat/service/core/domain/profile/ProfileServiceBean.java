package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class ProfileServiceBean implements ProfileService {

    private Map<String, Profile> profilesById;
    private Map<String, Profile> profilesByNickname;

    @Override
    public Profile createProfile(Profile profile) {
        Profile existingProfile = findProfileByNickname(profile.getNickname());
        if (existingProfile != null) {
            throw new RuntimeException("profile already exists");
        }
        profile.setId(UUID.randomUUID().toString());
        profile.setLastUpdatedAt(Instant.now());
        profile.setRegisteredAt(Instant.now());
        this.profilesById.put(profile.getId(), profile);
        this.profilesByNickname.put(profile.getNickname(), profile);
        return profile;
    }

    @Override
    public Profile findProfileByNickname(String nickname) {
        String normalizedNickname = nickname.toLowerCase();
        return this.profilesByNickname.get(nickname);
    }

    @Override
    public Profile getProfileById(String id) {
        Profile profile = this.profilesById.get(id);
        if (profile == null) {
            throw new RuntimeException("profile does not exist");
        }
        return profile;
    }

    @Override
    public Profile updateProfile(String id, Profile profile) {
        Profile existingProfile = getProfileById(id);
        existingProfile.setEmail(profile.getEmail());
        existingProfile.setFirstname(profile.getFirstname());
        existingProfile.setLastname(profile.getLastname());
        existingProfile.setLastUpdatedAt(Instant.now());
        return existingProfile;
    }

    @PostConstruct
    private void init() {
        this.profilesById = new HashMap<>();
        this.profilesByNickname = new HashMap<>();
    }
}
