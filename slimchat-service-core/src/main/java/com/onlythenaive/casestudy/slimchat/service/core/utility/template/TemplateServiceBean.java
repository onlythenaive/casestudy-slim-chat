package com.onlythenaive.casestudy.slimchat.service.core.utility.template;

import java.io.IOException;
import java.util.Map;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.onlythenaive.casestudy.slimchat.service.core.generic.GenericComponentBean;

/**
 * Default template service implementation.
 * <p/>
 * Uses Handlebars template engine internally.
 *
 * @see Handlebars
 * @see Template
 *
 * @author Ilia Gubarev.
 */
@Service
public class TemplateServiceBean extends GenericComponentBean implements TemplateService {

    private Handlebars handlebars;

    @Override
    public String renderTemplate(String name, Map<String, Object> properties) {
        try {
            return template(name).apply(properties);
        } catch (IOException e) {
            throw wrappedException(e);
        }
    }

    @PostConstruct
    private void init() {
        TemplateLoader templateLoader = new ClassPathTemplateLoader("/templates/");
        this.handlebars = new Handlebars(templateLoader);
    }

    private Template template(String name) {
        try {
            // TODO: add configurable template caching (performance improvement)
            return this.handlebars.compile(name);
        } catch (IOException e) {
            throw wrappedException(e);
        }
    }
}
