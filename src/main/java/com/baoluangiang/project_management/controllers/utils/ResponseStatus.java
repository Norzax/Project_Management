package com.baoluangiang.project_management.controllers.utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseStatus {
    private static final Map<Integer, HttpStatus> STATUS_MAP = new HashMap<>();

    private ResponseStatus(){
        // Do nothing
    }

    static {
        STATUS_MAP.put(100, HttpStatus.CONTINUE);
        STATUS_MAP.put(101, HttpStatus.SWITCHING_PROTOCOLS);
        STATUS_MAP.put(102, HttpStatus.PROCESSING);
        STATUS_MAP.put(200, HttpStatus.OK);
        STATUS_MAP.put(201, HttpStatus.CREATED);
        STATUS_MAP.put(202, HttpStatus.ACCEPTED);
        STATUS_MAP.put(203, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        STATUS_MAP.put(204, HttpStatus.NO_CONTENT);
        STATUS_MAP.put(205, HttpStatus.RESET_CONTENT);
        STATUS_MAP.put(206, HttpStatus.PARTIAL_CONTENT);
        STATUS_MAP.put(207, HttpStatus.MULTI_STATUS);
        STATUS_MAP.put(300, HttpStatus.MULTIPLE_CHOICES);
        STATUS_MAP.put(301, HttpStatus.MOVED_PERMANENTLY);
        STATUS_MAP.put(302, HttpStatus.FOUND);
        STATUS_MAP.put(303, HttpStatus.SEE_OTHER);
        STATUS_MAP.put(304, HttpStatus.NOT_MODIFIED);
        STATUS_MAP.put(307, HttpStatus.TEMPORARY_REDIRECT);
        STATUS_MAP.put(308, HttpStatus.PERMANENT_REDIRECT);
        STATUS_MAP.put(400, HttpStatus.BAD_REQUEST);
        STATUS_MAP.put(401, HttpStatus.UNAUTHORIZED);
        STATUS_MAP.put(402, HttpStatus.PAYMENT_REQUIRED);
        STATUS_MAP.put(403, HttpStatus.FORBIDDEN);
        STATUS_MAP.put(404, HttpStatus.NOT_FOUND);
        STATUS_MAP.put(405, HttpStatus.METHOD_NOT_ALLOWED);
        STATUS_MAP.put(406, HttpStatus.NOT_ACCEPTABLE);
        STATUS_MAP.put(407, HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
        STATUS_MAP.put(408, HttpStatus.REQUEST_TIMEOUT);
        STATUS_MAP.put(409, HttpStatus.CONFLICT);
        STATUS_MAP.put(410, HttpStatus.GONE);
        STATUS_MAP.put(411, HttpStatus.LENGTH_REQUIRED);
        STATUS_MAP.put(412, HttpStatus.PRECONDITION_FAILED);
        STATUS_MAP.put(415, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        STATUS_MAP.put(416, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
        STATUS_MAP.put(417, HttpStatus.EXPECTATION_FAILED);
        STATUS_MAP.put(422, HttpStatus.UNPROCESSABLE_ENTITY);
        STATUS_MAP.put(423, HttpStatus.LOCKED);
        STATUS_MAP.put(424, HttpStatus.FAILED_DEPENDENCY);
        STATUS_MAP.put(426, HttpStatus.UPGRADE_REQUIRED);
        STATUS_MAP.put(428, HttpStatus.PRECONDITION_REQUIRED);
        STATUS_MAP.put(429, HttpStatus.TOO_MANY_REQUESTS);
        STATUS_MAP.put(431, HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE);
        STATUS_MAP.put(451, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
        STATUS_MAP.put(500, HttpStatus.INTERNAL_SERVER_ERROR);
        STATUS_MAP.put(501, HttpStatus.NOT_IMPLEMENTED);
        STATUS_MAP.put(502, HttpStatus.BAD_GATEWAY);
        STATUS_MAP.put(503, HttpStatus.SERVICE_UNAVAILABLE);
        STATUS_MAP.put(504, HttpStatus.GATEWAY_TIMEOUT);
        STATUS_MAP.put(505, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        STATUS_MAP.put(506, HttpStatus.VARIANT_ALSO_NEGOTIATES);
        STATUS_MAP.put(507, HttpStatus.INSUFFICIENT_STORAGE);
        STATUS_MAP.put(508, HttpStatus.LOOP_DETECTED);
        STATUS_MAP.put(510, HttpStatus.NOT_EXTENDED);
        STATUS_MAP.put(511, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }

    public static HttpStatus set(int status) {
        return STATUS_MAP.getOrDefault(status, HttpStatus.OK);
    }
}
