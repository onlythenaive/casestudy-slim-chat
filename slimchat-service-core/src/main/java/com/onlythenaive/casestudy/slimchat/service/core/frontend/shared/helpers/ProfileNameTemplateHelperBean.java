package com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.helpers;

import org.springframework.stereotype.Component;

import com.github.jknack.handlebars.Options;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileNames;

/**
 * Template helper for rendering profile display names.
 *
 * @author Ilia Gubarev
 */
@Component
public class ProfileNameTemplateHelperBean extends GenericTemplateHelperBean<Profile> {

    @Override
    public String getName() {
        return "profileName";
    }

    @Override
    public Object apply(Profile profile, Options options) {
        return ProfileNames.pretty(profile);
    }
}
