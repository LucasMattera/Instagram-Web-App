import controller.PostController
import controller.UserController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.util.RouteOverviewPlugin
import org.unq.ui.bootstrap.getInstagramSystem

class InstagramApi {

    fun start() {
        val app = Javalin.create() {
            it.defaultContentType = "application/json"
            it.registerPlugin(RouteOverviewPlugin("/routes"))
        }

        app.start(7000)
        val instagramSystem = getInstagramSystem()
        val postController = PostController(instagramSystem)
        val usrController = UserController(instagramSystem)

        app.routes {
            path("posts") {
                get(postController::getPosts)

                path(":id") {
                    get(postController::getPost)
                }
            }

            path("login") {
                post(usrController::loginUser)
            }
        }
    }

}


fun main(args: Array<String>) {
    InstagramApi().start()
}
