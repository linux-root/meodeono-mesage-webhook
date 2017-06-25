package vertx

import io.vertx.core.Vertx

/**
 * Created by Khanhdb@eway.vn on 6/25/17.
 */
class VertxConfig {

    int httpPort
    Vertx vertx = Vertx.vertx()
}
