package com.sgnewtech.sff.sffsms.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class RequestT24OriginatedFilter extends OncePerRequestFilter {

    @Value("${app.whitelisted.ips}")
    private String [] ips;

    IpAddressMatcher ipMatcher;

    Logger logger = LoggerFactory.getLogger(RequestT24OriginatedFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info(String.format("The request machine IP is %s",getRemoteIP(request)));
        List<String> list = Arrays.asList(ips);
        logger.info("The allowed ips: "+list);
        if(ipContained(request)) {
            filterChain.doFilter(request,response);
        }
        else {
            logger.info(String.format("The request machine IP "+ getRemoteIP(request) +" is not allowed!"));
        }
    }

    private boolean ipContained(HttpServletRequest request) {
        boolean isTrusted = false;
        for (String s : ips) {
            ipMatcher = new IpAddressMatcher(s);
            if (ipMatcher.matches(request)) {
                // The given ip matches one of the proxies in our list
                isTrusted = true;
                break;
            }
        }
        return isTrusted;
    }

    private String getRemoteIP(HttpServletRequest request) {
        String xff = request.getHeader("X-Forwarded-For");
        if (xff != null) {
            return xff.split("[\\s,]+")[0];
        }
        return request.getRemoteAddr();
    }
}