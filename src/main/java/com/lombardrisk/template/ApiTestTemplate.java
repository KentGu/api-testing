package com.lombardrisk.template;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ApiTestTemplate extends TestTemplate {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RequestSpecification request;
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        logger.info("Set response " + response);
        this.response = response;
    }

    public RequestSpecification getRequest() {
        return request;
    }

    public void setRequest(RequestSpecification request) {
        logger.info("Set request " + request);
        this.request = request;
    }

}
