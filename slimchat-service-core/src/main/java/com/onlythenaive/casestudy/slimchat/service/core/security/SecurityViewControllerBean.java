package com.onlythenaive.casestudy.slimchat.service.core.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.onlythenaive.casestudy.slimchat.service.core.generic.GenericControllerBean;

/**
 * Security view controller implementation.
 *
 * @author Ilia Guarev
 */
@RestController
public class SecurityViewControllerBean extends GenericControllerBean implements SecurityViewController {

    @Autowired
    private SecurityService securityService;

    @Override
    public String registrationPage() {
        return renderView("registration");
    }

    @Override
    public String createAccount(SecurityCredentials credentials) {
        String password = credentials.getPassword();
        String passwordDuplicate = credentials.getPasswordDuplicate();
        if (!password.equals(passwordDuplicate)) {
            return renderView("registration", mistakeContent("Passwords do not match"));
        }
        try {
            this.securityService.createAccount(credentials);
            return renderView("registration-complete");
        } catch (Exception e) {
            return renderView("registration", mistakeContent("Registration failed"));
        }
    }

    @Override
    public String loginPage() {
        return renderView("login");
    }

    @Override
    public String login(SecurityCredentials credentials) {
        try {
            this.securityService.login(credentials);
            return redirect("home");
        } catch (Exception e) {
            return renderView("login", mistakeContent("Login attempt failed"));
        }
    }

    @Override
    public String logout() {
        this.securityService.logout();
        return redirect("/view/login");
    }

    private Object mistakeContent(String message) {
        Map<String, String> content = new HashMap<>();
        content.put("mistake", message);
        return content;
    }
}
