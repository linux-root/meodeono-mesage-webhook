package controller

import app.AppConfig
import groovy.transform.InheritConstructors
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.http.HttpServerResponse
import io.vertx.ext.web.RoutingContext
import vertx.VertxController

/**
 * Created by Khanhdb@eway.vn on 6/25/17.
 */
@InheritConstructors
class GET_MessageTransporter extends VertxController<AppConfig>{

    @Override
    void validate(RoutingContext context) {

    }

    @Override
    void handle(RoutingContext context, HttpServerRequest request, HttpServerResponse response) {
        response.setStatusCode(200)
        response.end('OK')
    }
}
