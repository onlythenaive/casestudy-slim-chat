package com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.helpers;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.github.jknack.handlebars.Options;
import com.onlythenaive.casestudy.slimchat.service.core.utility.datetime.PrettyTimestamps;

/**
 * Template helper for rendering timestamps.
 *
 * @author Ilia Gubarev
 */
@Component
public class PrettyTimestampTemplateHelperBean extends GenericTemplateHelperBean<Instant> {

    @Override
    public String getName() {
        return "prettyTimestamp";
    }

    @Override
    public Object apply(Instant timestamp, Options options) {
        return PrettyTimestamps.pretty(timestamp);
    }
}
