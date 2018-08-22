package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;

@Service
public class GroupProviderBean extends GenericDomainComponentBean implements GroupProvider {

    @Autowired
    private GroupProjector groupProjector;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group getGroup(String id) {
        return this.groupRepository.findById(id)
                .map(this::project)
                .orElseThrow(this::groupNotFound);
    }

    private Group project(GroupEntity entity) {
        return this.groupProjector.intoGroup(entity);
    }

    private OperationException groupNotFound() {
        return OperationException.builder()
                .comment("Group does not exist")
                .textcode("x.logic.group.not-found")
                .category(ExceptionCategory.LOGIC)
                .build();
    }
}
