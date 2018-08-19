package com.onlythenaive.casestudy.slimchat.service.core.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Security view controller.
 * <p/>
 * Incorporates processing and rendering of security-related view requests.
 *
 * @author Ilia Guarev
 */
@RequestMapping("/view")
public interface SecurityViewController {

    /**
     * Returns an HTML view of the registration page.
     *
     * @return HTML response.
     */
    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    String registrationPage();

    /**
     * Creates a new account.
     *
     * @param credentials account credentials to be used.
     * @return HTML response.
     */
    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    String createAccount(SecurityCredentials credentials);

    /**
     * Returns an HTML view of the login page.
     *
     * @return HTML response.
     */
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    String loginPage();

    /**
     * Creates new security token and authentication.
     *
     * @param credentials account credentials to be used.
     * @return HTML response.
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    String login(SecurityCredentials credentials);

    /**
     * Removes current security token and authentication.
     *
     * @return HTML response.
     */
    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    String logout();
}
