package controller

import app.AppConfig
import groovy.transform.InheritConstructors
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.http.HttpServerResponse
import io.vertx.ext.web.RoutingContext
import model.ReceivedMessage
import model.SentMessage
import okhttp3.*
import org.apache.commons.lang3.Validate
import vertx.VertxController

/**
 * Created by Khanhdb@eway.vn on 6/25/17.
 */
@InheritConstructors
class POST_MessageTransporter extends VertxController<AppConfig>{

    @Override
    void validate(RoutingContext context) {

    }

    @Override
    void handle(RoutingContext context, HttpServerRequest request, HttpServerResponse response) {
        def message = ReceivedMessage.newInstance(context)
        response.setStatusCode(200)
        def m = new SentMessage(message: message.message, recipient: message.sender)
        sendMessage(m, config.pageAccessToken)
        response.end()
    }

    static sendMessage(SentMessage sentMessage, String ACCESS_TOKEN) {
        Validate.notNull(sentMessage, 'Message must be not null')
        def constantURL = "https://graph.facebook.com/v2.9/me/messages"
        def access_token_param = "?access_token=${ACCESS_TOKEN}"

        OkHttpClient httpClient = new OkHttpClient()
        MediaType JSON = MediaType.parse("application/json; charset=utf-8")
        RequestBody body = RequestBody.create(JSON, sentMessage.toJson().toString())
        Request request = new Request.Builder()
                .url(constantURL + access_token_param)
                .post(body)
                .build()
        //retry....
        Response response = httpClient.newCall(request).execute()
        println 'hl'
    }
}
