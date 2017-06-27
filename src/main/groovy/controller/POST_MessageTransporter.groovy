package controller

import app.AppConfig
import groovy.transform.InheritConstructors
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import okhttp3.*
import vertx.VertxController

/**
 * Created by Khanhdb@eway.vn on 6/25/17.
 */
@InheritConstructors
class POST_MessageTransporter extends VertxController<AppConfig>{

    static final ACCESS_TOKEN = "EAAYmqgM3dSUBAA1DYF94arInGTSEcmLAuKHbhAw3tjXgc0B2iC8ZAGUiOb8aSZBk9Sgl6GQ8eu9P1vHaKzVYeyCRBLholHK88vmkEKkIITHZB6W9s5Nr6eMUUhvYk8lHhq4VWS7qm1ZBngYZCmDV2GR3zSv2V6yVktJVvPGD6dG8xLovM9p0cTJrMAh2ZBZAgATq85gtT7y0AZDZD"
    @Override
    void validate(RoutingContext context) {

    }

    @Override
    void handle(RoutingContext context, HttpServerRequest request, HttpServerResponse response) {
        def message = parseMessage(context)
        response.setStatusCode(200)
        def id = new JsonObject().put('id', message.sender)
        def reply = new JsonObject().put('text', message.message)
        def m = new JsonObject().put('recipient', id).put('message', reply)
        sendMessage(m)
        response.end()
    }

    static void sendMessage(JsonObject message) {
        def constantURL = "https://graph.facebook.com/v2.9/me/messages"
        def access_token = "?access_token=${ACCESS_TOKEN}"

        OkHttpClient httpClient = new OkHttpClient()
        MediaType JSON = MediaType.parse("application/json; charset=utf-8")
        RequestBody body = RequestBody.create(JSON, message.toString())
        Request request = new Request.Builder()
                          .url(constantURL + access_token)
                          .post(body)
                          .build()
        Response response = httpClient.newCall(request).execute()
        JsonObject object = new JsonObject(response.body().string())
    }
}
