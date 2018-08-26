package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.proposal.ProposalFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.proposal.ProposalInvoice;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/proposals")
public class ProposalActionPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private ProposalFacade proposalFacade;

    @PostMapping("/create")
    public ModelAndView create(ProposalFormInput form) {
        ProposalInvoice invoice = ProposalInvoice.builder()
                .acceptorId(form.getAcceptorId())
                .initiatorId(form.getInitiatorId())
                .text(form.getText())
                .build();
        this.proposalFacade.create(invoice);
        return redirect(form.getRedirectUri());
    }

    @PostMapping("/accept")
    public ModelAndView accept(ProposalFormInput form) {
        this.proposalFacade.accept(form.getProposalId());
        return redirect(form.getRedirectUri());
    }

    @PostMapping("/cancel")
    public ModelAndView cancel(ProposalFormInput form) {
        this.proposalFacade.cancel(form.getProposalId());
        return redirect(form.getRedirectUri());
    }

    @PostMapping("/reject")
    public ModelAndView reject(ProposalFormInput form) {
        this.proposalFacade.reject(form.getProposalId());
        return redirect(form.getRedirectUri());
    }
}
