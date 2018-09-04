package com.onlythenaive.casestudy.slimchat.service.core.utility.gravatar;

import org.springframework.lang.Nullable;

/**
 * Utility helper for Gravatars URLs
 *
 * @author Ilia Gubarev
 */
public final class Gravatars {

    private static final int SIZE_DEFAULT = 64;
    private static final int SIZE_MIN = 16;
    private static final int SIZE_MAX = 256;
    private static final String URL_PREFIX = "https://www.gravatar.com/avatar/";
    private static final String URL_TEMPLATE = URL_PREFIX + "%s?s=%s&default=identicon";
    private static final String URL_TEMPLATE_NONE = URL_PREFIX + "none?s=%s&default=mp";

    /**
     * Provides a Gravatar URL for specified hash.
     *
     * @param hash a hash to be used.
     * @param size a size of a Gravatar image.
     * @return the requested Gravatar URL.
     */
    public static String url(@Nullable String hash, int size) {
        int safeSize = size >= SIZE_MIN && size <= SIZE_MAX ? size : SIZE_DEFAULT;
        return hash != null ? String.format(URL_TEMPLATE, hash, safeSize) : String.format(URL_TEMPLATE_NONE, safeSize);
    }
}
