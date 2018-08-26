package com.onlythenaive.casestudy.slimchat.service.core.view.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.proposal.ProposalFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.proposal.ProposalInvoice;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/proposals")
public class ProposalActionControllerBean extends GenericViewControllerBean {

    @Autowired
    private ProposalFacade proposalFacade;

    @PostMapping("/create")
    public ModelAndView create(ProposalActionForm form) {
        ProposalInvoice invoice = ProposalInvoice.builder()
                .acceptorId(form.getAcceptorId())
                .initiatorId(form.getInitiatorId())
                .text(form.getText())
                .build();
        this.proposalFacade.create(invoice);
        return redirectToView(form.getViewToRedirect());
    }

    @PostMapping("/accept")
    public ModelAndView accept(ProposalActionForm form) {
        this.proposalFacade.accept(form.getProposalId());
        return redirectToView(form.getViewToRedirect());
    }

    @PostMapping("/cancel")
    public ModelAndView cancel(ProposalActionForm form) {
        this.proposalFacade.cancel(form.getProposalId());
        return redirectToView(form.getViewToRedirect());
    }

    @PostMapping("/reject")
    public ModelAndView reject(ProposalActionForm form) {
        this.proposalFacade.reject(form.getProposalId());
        return redirectToView(form.getViewToRedirect());
    }

    @Override
    protected String defaultViewName() {
        return "proposal-list";
    }
}
