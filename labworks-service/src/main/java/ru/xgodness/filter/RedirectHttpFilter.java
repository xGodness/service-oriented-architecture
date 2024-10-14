package ru.xgodness.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;

import java.net.URI;

@Log
@Provider
@PreMatching
public class RedirectHttpFilter implements ContainerRequestFilter {
    private static final String HTTPS_PORT = ":9172";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        URI requestUri = requestContext.getUriInfo().getRequestUri();

        if ("http".equalsIgnoreCase(requestUri.getScheme())) {
            log.info("Redirecting HTTP request: " + requestContext.getUriInfo().getRequestUri());
            String httpQuery = requestUri.getRawQuery();
            String httpsQuery = httpQuery == null ? "" : "?" + httpQuery;

            URI httpsUri = URI.create("https://" + requestUri.getHost() + HTTPS_PORT + requestUri.getRawPath() + httpsQuery);
            log.info("Redirecting to: " + httpsUri);
            requestContext.abortWith(Response.status(301).location(httpsUri).build());
        }
    }
}
