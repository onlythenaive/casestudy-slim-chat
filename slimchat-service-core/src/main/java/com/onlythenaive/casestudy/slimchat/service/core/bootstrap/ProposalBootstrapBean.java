package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import java.util.Collection;

import org.springframework.context.annotation.DependsOn;

import com.onlythenaive.casestudy.slimchat.service.core.domain.proposal.ProposalEntity;

@BootstrapComponent
@DependsOn("securityAccountBootstrapBean")
public class ProposalBootstrapBean extends GenericEntityBootstrapBean<ProposalEntity> {

    @Override
    protected String getBootstrapName() {
        return "connection proposals";
    }

    @Override
    protected void executeBootstrap() {
        Collection<ProposalEntity> entities = parseList("/bootstrap/dev/proposals.json", ProposalEntity.class);
        entities.forEach(this::insertBootstrappedEntity);
    }
}
