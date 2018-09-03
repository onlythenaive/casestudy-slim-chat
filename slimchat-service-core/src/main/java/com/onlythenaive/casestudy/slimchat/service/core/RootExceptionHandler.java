package com.onlythenaive.casestudy.slimchat.service.core;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class RootExceptionHandler {

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
