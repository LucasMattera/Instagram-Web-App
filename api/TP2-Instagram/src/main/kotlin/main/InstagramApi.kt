import controller.LoginController
import controller.PostController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.util.RouteOverviewPlugin
import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.UsedEmail

class InstagramApi {

    fun start() {
        val app = Javalin.create() {
            it.defaultContentType = "application/json"
            it.registerPlugin(RouteOverviewPlugin("/routes"))
        }

        app.start(7000)
        val instagramSystem = getInstagramSystem()
        val postController = PostController(instagramSystem)
        val loginController = LoginController(instagramSystem)

        app.routes {
            path("posts") {
                get(postController::getPosts)

                path(":id") {
                    get(postController::getPost)
                }
            }

            path("login") {
                post(loginController::loginUser)
            }
        }
    }

}


fun main(args: Array<String>) {
    InstagramApi().start()
}
