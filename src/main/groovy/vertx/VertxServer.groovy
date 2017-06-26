package vertx

import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerOptions
import io.vertx.ext.web.Router

/**
 * Created by Khanhdb@eway.vn on 6/25/17.
 */

abstract class VertxServer <C extends VertxConfig> {

    @Delegate
    final Router router
    final HttpServer httpServer
    C config

    VertxServer(C config){
        this.config = config
        def httpServerOption = new HttpServerOptions(
                maxInitialLineLength: 4096*4,
                compressionSupported: true,
                maxHeaderSize: 8192 * 2
        )
        this.router = Router.router(config.vertx)
        this.httpServer = config.vertx.createHttpServer(httpServerOption)
        setupRouter()
    }
    void start(Handler<AsyncResult<HttpServer>> listenHandler){
       httpServer.requestHandler(router.&accept).listen(config.httpPort, {event ->
           listenHandler?.handle(event)
           if (event.succeeded()) {
            println("VertxServer started at http://127.0.0.1:${httpServer.actualPort()}")
           } else {
               stop()
               throw new RuntimeException("Unable to start VertxServer at http://127.0.0.1:${httpServer.actualPort()}")
           }
       })
    }

    void stop() {
        httpServer?.close()
        config.vertx?.close()
    }

    abstract setupRouter()

}
