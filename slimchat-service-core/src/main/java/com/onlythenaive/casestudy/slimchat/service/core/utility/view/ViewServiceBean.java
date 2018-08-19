package com.onlythenaive.casestudy.slimchat.service.core.utility.view;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.generic.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.template.TemplateService;

/**
 * Default view service implementation.
 * <p/>
 * Uses template service internally.
 *
 * @see TemplateService
 *
 * @author Ilia Gubarev.
 */
@Service
public class ViewServiceBean extends GenericComponentBean implements ViewService {

    @Autowired
    private TemplateService templateService;

    @Override
    public String renderView(String name) {
        return renderViewTemplate(name, viewPropertiesGeneric());
    }

    @Override
    public String renderView(String name, Object content) {
        return renderViewTemplate(name, viewPropertiesWithContent(content));
    }

    private String renderViewTemplate(String viewName, Map<String, Object> properties) {
        return this.templateService.renderTemplate("views/" + viewName, properties);
    }

    private Map<String, Object> viewPropertiesWithContent(Object content) {
        Map<String, Object> viewProperties = viewPropertiesGeneric();
        viewProperties.put("content", content);
        return viewProperties;
    }

    private Map<String, Object> viewPropertiesGeneric() {
        Map<String, Object> viewProperties = new HashMap<>();
        // TODO: add some generic view properties (f.e. application version, current user, e.t.c)
        return viewProperties;
    }
}
