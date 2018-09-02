package com.onlythenaive.casestudy.slimchat.service.core.frontend.shared;

import org.springframework.stereotype.Component;

import com.github.jknack.handlebars.Options;
import com.onlythenaive.casestudy.slimchat.service.core.configuration.web.GenericTemplateHelperBean;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;

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
        String firstname = profile.getFirstname();
        if (firstname == null) {
            return profile.getId();
        }
        String lastname = profile.getLastname();
        return lastname != null ? firstname + " " + lastname : firstname;
    }
}
