package com.onlythenaive.casestudy.slimchat.service.core.utility.principal;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Principal information.
 *
 * @author Ilia Gubarev
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Principal {

    private String id;
    private String name;
    private Collection<String> roles;
}
