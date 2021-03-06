package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountFacade;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;

@Controller
@RequestMapping("/ui/plain/registration")
public class RegistrationActionPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private AccountFacade accountFacade;

    @PostMapping
    public ModelAndView register(RegistrationFormInput form) {
        ensurePasswordDuplicated(form);
        this.accountFacade.create(form.toInvoice());
        return redirect("profiles/me");
    }

    private void ensurePasswordDuplicated(RegistrationFormInput form) {
        if (!form.isSecretDuplicated()) {
            throw OperationException.builder()
                    .category(ExceptionCategory.CONFLICT)
                    .comment("Password does not match with its duplicate")
                    .textcode("x.conflict.registration.password-not-duplicated")
                    .data(form.copyWithoutSecret())
                    .build();
        }
    }
}
