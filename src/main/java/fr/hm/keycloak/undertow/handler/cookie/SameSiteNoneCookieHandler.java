package fr.hm.keycloak.undertow.handler.cookie;

import fr.hm.keycloak.undertow.handler.cookie.configuration.Configuration;
import fr.hm.keycloak.undertow.handler.cookie.handler.SameSiteCookieHandler;
import io.undertow.server.HttpHandler;

public class SameSiteNoneCookieHandler extends SameSiteCookieHandler {

    public SameSiteNoneCookieHandler(HttpHandler next) {
        super(next, SameSiteCookieHandler.NONE, getOrNull(Configuration.getProperties().getProperty("cookie.pattern")));
    }

    private static final String getOrNull(String value) {
        return value == null || value.isEmpty() ? null : value;
    }

}
