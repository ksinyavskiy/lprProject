package com.training.lprProject.error.util;

import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class CustomExceptionHandlerUtil {

    private CustomExceptionHandlerUtil() {

    }

    public static void handleException(HttpServletResponse httpServletResponse, String exceptionMessage)
            throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.getWriter().write(
                new JSONObject()
                        .put("timestamp", formatter.format(LocalDateTime.now()))
                        .put("message", exceptionMessage)
                        .toString()
        );
    }
}
