package com.onlythenaive.casestudy.slimchat.service.core.view.proposal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.proposal.Proposal;
import com.onlythenaive.casestudy.slimchat.service.core.domain.proposal.ProposalFacade;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/proposals")
public class ProposalListViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private ProposalFacade proposalFacade;

    @GetMapping("/incoming")
    public ModelAndView getIncoming() {
        Collection<Proposal> proposals = this.proposalFacade.findIncoming();
        return defaultView(data(proposals));
    }

    @GetMapping("/outgoing")
    public ModelAndView getOutgoing() {
        Collection<Proposal> proposals = this.proposalFacade.findOutgoing();
        return defaultView(data(proposals));
    }

    @Override
    protected String defaultViewName() {
        return "proposal-list";
    }

    private Object data(Collection<Proposal> proposals) {
        Map<String, Object> data = new HashMap<>();
        data.put("proposals", proposals);
        return data;
    }
}
