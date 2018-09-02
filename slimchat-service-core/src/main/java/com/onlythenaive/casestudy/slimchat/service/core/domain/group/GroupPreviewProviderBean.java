package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.access.AccessLevel;

/**
 * Group preview provider implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class GroupPreviewProviderBean extends GenericComponentBean implements GroupPreviewProvider {

    @Autowired
    private GroupAccessor groupAccessor;

    @Autowired
    private GroupProjector groupProjector;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group getById(String id) {
        return this.groupRepository.findById(id)
                .map(entity -> {
                    this.groupAccessor.ensureAccess(entity, AccessLevel.VIEW);
                    return this.groupProjector.projectPreview(entity);
                })
                .orElse(null);
    }
}
