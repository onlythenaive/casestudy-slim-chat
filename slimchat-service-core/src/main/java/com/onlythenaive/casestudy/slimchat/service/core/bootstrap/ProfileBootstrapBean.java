package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileRepository;

@BootstrapComponent
@DependsOn("securityAccountBootstrapBean")
public class ProfileBootstrapBean extends GenericBootstrapBean {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    protected String getBootstrapName() {
        return "user profiles";
    }

    @Override
    protected void executeBootstrap() {

    }
}
