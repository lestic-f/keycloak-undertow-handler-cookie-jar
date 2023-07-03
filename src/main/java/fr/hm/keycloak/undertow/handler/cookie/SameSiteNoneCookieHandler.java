package fr.hm.keycloak.undertow.handler.cookie;

import fr.hm.keycloak.undertow.handler.cookie.configuration.Configuration;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.CookieSameSiteMode;
import io.undertow.server.handlers.SameSiteCookieHandler;

public class SameSiteNoneCookieHandler extends SameSiteCookieHandler {

    public SameSiteNoneCookieHandler(HttpHandler next) {
        super(next, CookieSameSiteMode.NONE.toString(), getOrNull(Configuration.getProperties().getProperty("cookie.pattern")));
    }

    private static final String getOrNull(String value) {
        return value == null || value.isEmpty() ? null : value;
    }

}
