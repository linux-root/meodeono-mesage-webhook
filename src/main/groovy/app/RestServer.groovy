package app

import controller.GET_MessageTransporter
import groovy.transform.InheritConstructors
import io.vertx.ext.web.Route
import io.vertx.ext.web.handler.CorsHandler
import vertx.VertxServer

import static io.netty.handler.codec.http.HttpMethod.DELETE
import static io.netty.handler.codec.http.HttpMethod.GET
import static io.netty.handler.codec.http.HttpMethod.OPTIONS
import static io.netty.handler.codec.http.HttpMethod.PATCH
import static io.netty.handler.codec.http.HttpMethod.POST
import static io.netty.handler.codec.http.HttpMethod.PUT

/**
 * Created by Khanhdb@eway.vn on 6/25/17.
 */
@InheritConstructors
class RestServer extends VertxServer<AppConfig>{
    @Override
    def setupRouter() {
        Route.metaClass.rightShift = { Class clazz ->
            return delegate.handler(clazz.newInstance(config))
        }
        route("/*").handler(CorsHandler.create("*").with {
            allowedMethods([OPTIONS, GET, POST, PUT, DELETE, PATCH] as Set)
            allowedHeaders(["Authorization", "Content-Type"] as Set)
        })
        get('/test') >> GET_MessageTransporter

    }
}
