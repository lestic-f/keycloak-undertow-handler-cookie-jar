package fr.hm.keycloak.undertow.handler.cookie.handler;

import java.util.regex.Pattern;

import fr.hm.keycloak.undertow.handler.cookie.util.SameSiteNoneIncompatibleClientChecker;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.ResponseCommitListener;
import io.undertow.server.handlers.Cookie;
import io.undertow.util.Headers;

public class SameSiteCookieHandler implements HttpHandler {

    protected static final String NONE = "None";

    private final HttpHandler next;
    private final String mode;
    private final Pattern cookiePattern;
    private final boolean isNone;

    public SameSiteCookieHandler(final HttpHandler next, final String mode) {
        this(next, mode, null);
    }

    public SameSiteCookieHandler(final HttpHandler next, final String mode, final String cookiePattern) {
        this(next, mode, cookiePattern, true);
    }

    public SameSiteCookieHandler(final HttpHandler next, final String mode, final String cookiePattern, final boolean caseSensitive) {
        this.next = next;
        this.mode = mode;
        this.cookiePattern = cookiePattern != null && !cookiePattern.isEmpty() ? Pattern.compile(cookiePattern, caseSensitive ? 0 : Pattern.CASE_INSENSITIVE) : null;
        this.isNone = NONE.equalsIgnoreCase(mode);
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        exchange.addResponseCommitListener(new ResponseCommitListener() {
            @Override
            public void beforeCommit(HttpServerExchange exchange) {
                final String userAgent = exchange.getRequestHeaders().getFirst(Headers.USER_AGENT);
                if (userAgent != null && !SameSiteNoneIncompatibleClientChecker.shouldSendSameSiteNone(userAgent)) {
                    return;
                }
                for (Cookie cookie : exchange.getResponseCookies().values()) {
                    if (cookiePattern == null
                            || cookiePattern.matcher(cookie.getName()).matches()) {
                        cookie.setSameSiteMode(mode);
                        if (isNone) {
                            cookie.setSecure(true);
                        }
                    }
                }
            }
        });
        next.handleRequest(exchange);
    }

}
