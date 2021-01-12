package com.training.lprProject.error.handler;

import com.training.lprProject.error.util.CustomExceptionHandlerUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class CustomAccessDeniedExceptionHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException {

        CustomExceptionHandlerUtil.handleException(httpServletResponse,
                "User has no access to the requested resource: " + httpServletRequest.getRequestURI());
    }
}
