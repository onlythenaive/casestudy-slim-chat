package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

@RestController
@RequestMapping("/api/v1.0/profiles")
public class ProfileControllerBean extends GenericComponentBean {

    @Autowired
    private ProfileFacade profileFacade;

    @RequestMapping(path = "/{id}")
    public Profile getById(@PathVariable("id") String id) {
        return this.profileFacade.getById(id);
    }
}
