package com.bankingapplication.rest.utils;

import com.bankingapplication.rest.enums.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpUtils class is used for getting header information from request
 *
 * @author Nitesh Kumar
 */
public class HttpUtils {
    private Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * Get header value from request
     *
     * @param header a valid {@link HttpHeaders}
     * @return
     */
    public static String getHeader(HttpHeaders header) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return request.getHeader(header.getValue());
        }
        return null;
    }
}
