package controller

import app.AppConfig
import groovy.transform.InheritConstructors
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.http.HttpServerResponse
import io.vertx.ext.web.RoutingContext
import vertx.VertxController

/**
 * Created by Khanhdb@eway.vn on 6/26/17.
 */
@InheritConstructors
class GET_VerifyWebhook extends VertxController<AppConfig>{
    @Override
    void validate(RoutingContext context) {

    }

    @Override
    void handle(RoutingContext context, HttpServerRequest request, HttpServerResponse response) {
        def challenge = request.getParam('hub.challenge')
        response.setStatusCode(200)
        response.end(challenge.toString())
    }
}
