package model

import io.vertx.core.json.JsonObject

/**
 * Created by Khanhdb@eway.vn on 7/1/17.
 */
class SentMessage extends Message{

    String recipient
    JsonObject toJson() {
        def id = new JsonObject().put('id', this.recipient)
        def text = new JsonObject().put('text', this.message)
        def jsonMessage = new JsonObject().put('recipient', id).put('message', text)
        return jsonMessage
   }
}
