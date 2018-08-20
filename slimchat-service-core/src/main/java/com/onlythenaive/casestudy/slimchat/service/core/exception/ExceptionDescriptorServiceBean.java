package com.onlythenaive.casestudy.slimchat.service.core.exception;

import org.springframework.stereotype.Service;

/**
 * Exception descriptor service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ExceptionDescriptorServiceBean implements ExceptionDescriptorService {

    @Override
    public ExceptionDescriptor defaultDescriptor() {
        ExceptionDescriptor descriptor = ExceptionDescriptor.builder()
                .category(ExceptionCategory.UNKNOWN)
                .build();
        return enrich(descriptor);
    }

    @Override
    public ExceptionDescriptor extract(OperationException exception) {
        ExceptionDescriptor descriptor = ExceptionDescriptor.builder()
                .category(exception.getCategory())
                .data(exception.getData())
                .textcode(exception.getTextcode())
                .build();
        return enrich(descriptor);
    }

    @Override
    public ExceptionDescriptor extract(Exception exception) {
        return defaultDescriptor();
    }

    private ExceptionDescriptor enrich(ExceptionDescriptor descriptor) {
        String originalTextcode = descriptor.getTextcode();
        String textcode = originalTextcode != null ? originalTextcode : descriptor.getCategory().getDefaultTextcode();
        return ExceptionDescriptor.builder()
                .message(messageByTextcode(textcode))
                .messageLocalized(localizedMessageByTextcode(textcode))
                .textcode(textcode)
                .build();
    }

    private String messageByTextcode(String textcode) {
        // TODO: replace with the default localization
        return "Sample message for textcode " + textcode;
    }

    private String localizedMessageByTextcode(String textcode) {
        // TODO: replace with a custom localization
        return "Sample message (localized) for textcode " + textcode;
    }
}
