package app

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import groovy.util.logging.Slf4j
import org.apache.commons.lang3.Validate
import vertx.VertxConfig

/**
 * Created by Khanhdb@eway.vn on 6/25/17.
 */
@Slf4j
class AppConfig extends VertxConfig {

    @JsonProperty('page_access_token')
    String pageAccessToken

    static AppConfig newInstance(File appConfigFile) throws IOException {
        Validate.isTrue(appConfigFile.exists(), "AppConfigFile not exists: ${appConfigFile.getAbsolutePath()}")
        log.warn('@Loading app.AppConfig')

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory()).with {
            propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
            disable DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
        }

        AppConfig appConfig = objectMapper.readValue(appConfigFile, AppConfig.class)
        appConfig.properties.each { Validate.notNull(it.value, "${it.key} must not be null") }
        return appConfig

    }
}
