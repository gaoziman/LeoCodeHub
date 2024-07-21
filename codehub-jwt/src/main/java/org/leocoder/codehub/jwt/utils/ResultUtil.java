package org.leocoder.codehub.jwt.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.leocoder.codehub.common.utils.Result;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-21 20:06
 * @description : ResultUtil
 */
public class ResultUtil {

    /**
     * 成功响参
     *
     * @param response
     * @param result
     * @throws IOException
     */
    public static void ok(HttpServletResponse response, Result<?> result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        ObjectMapper mapper = new ObjectMapper();
        writer.write(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }

    /**
     * 失败响参
     *
     * @param response
     * @param result
     * @throws IOException
     */
    public static void fail(HttpServletResponse response, Result<?> result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        ObjectMapper mapper = new ObjectMapper();
        writer.write(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }

    /**
     * 失败响参
     *
     * @param response
     * @param status   可指定响应码，如 401 等
     * @param result
     * @throws IOException
     */
    public static void fail(HttpServletResponse response, int status, Result<?> result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        ObjectMapper mapper = new ObjectMapper();
        writer.write(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}
