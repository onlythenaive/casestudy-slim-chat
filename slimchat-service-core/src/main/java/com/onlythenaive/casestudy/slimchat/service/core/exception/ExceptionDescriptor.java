package com.onlythenaive.casestudy.slimchat.service.core.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Exception descriptor.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDescriptor {

    private ExceptionCategory category;
    private Object data;
    private String message;
    private String messageLocalized;
    private String textcode;
}
