package app

/**
 * Created by Khanhdb@eway.vn on 6/25/17.
 */
class Runner {

    static void main(String[] args) {
        AppConfig config = AppConfig.newInstance(new File(System.getProperty("user.dir"), "conf/application.yml"))
        def server = new RestServer(config)
        server.start({event ->})
    }
}
