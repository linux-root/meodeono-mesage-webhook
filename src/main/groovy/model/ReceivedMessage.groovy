package model

import groovy.json.JsonSlurper
import io.vertx.ext.web.RoutingContext

/**
 * Created by Khanhdb@eway.vn on 7/1/17.
 */
class ReceivedMessage extends Message{
    String id
    Long time
    String sender
    String recipient

    static ReceivedMessage newInstance(RoutingContext context) {
        def body = JsonSlurper.newInstance().parse(context.body?.bytes) as Map
        def entry = body.get('entry')
        def mapSentMessage = entry.get(0)
        ReceivedMessage m = new ReceivedMessage(
                id: mapSentMessage.get('id'),
                time: mapSentMessage.get('time'),
                sender: mapSentMessage.get('messaging').get(0).get('sender').get('id'),
                recipient: mapSentMessage.get('messaging').get(0).get('recipient').get('id'),
                message: mapSentMessage.get('messaging').get(0).get('message').get('text')
        )
        return m
    }
}
