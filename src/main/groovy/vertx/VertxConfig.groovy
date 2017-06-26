package vertx

import com.fasterxml.jackson.annotation.JsonProperty
import io.vertx.core.Vertx

/**
 * Created by Khanhdb@eway.vn on 6/25/17.
 */
class VertxConfig {

    @JsonProperty('http.port')
    int httpPort
    Vertx vertx = Vertx.vertx()
}
