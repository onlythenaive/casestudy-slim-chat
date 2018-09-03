package com.onlythenaive.casestudy.slimchat.service.core;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionDescriptor;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionDescriptorService;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;

@ControllerAdvice
public class RootExceptionHandler extends GenericComponentBean {

    @Autowired
    private ExceptionDescriptorService exceptionDescriptorService;

    @ExceptionHandler(OperationException.class)
    public Object handleOperationException(OperationException exception) {
        logger().debug("Operation exception occurred", exception);
        ExceptionDescriptor descriptor = this.exceptionDescriptorService.extract(exception);
        return ResponseEntity
                .status(descriptor.getCategory().getDefaultHttpStatus())
                .body(descriptor);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Object handleNoHandlerFoundException(Exception exception, HttpServletRequest request) {
/*
        // LOG
        String uri = request.getRequestURI();
        if (uri.startsWith("/ui")) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        */
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
}
