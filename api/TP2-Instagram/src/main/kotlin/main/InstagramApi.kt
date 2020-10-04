import controller.PostController
import controller.UserController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.util.RouteOverviewPlugin
import main.IgRoles
import main.InstagramAccessManager
import org.unq.ui.bootstrap.getInstagramSystem


class InstagramApi {

    fun start() {
        val instagramSystem = getInstagramSystem()
        val postController = PostController(instagramSystem)
        val userController = UserController(instagramSystem)

        val app = Javalin.create() {
            it.defaultContentType = "application/json"
            it.registerPlugin(RouteOverviewPlugin("/routes"))
            it.accessManager(InstagramAccessManager(instagramSystem))
        }

        app.start(7000)

        app.routes {
            path("post") {
                path(":id") {
                    get(postController::getPost, setOf(IgRoles.USER))
                }
            }

            path("login") {
                post(userController::login, setOf(IgRoles.ANYONE))
            }
        }
    }
}



fun main(args: Array<String>) {
    InstagramApi().start()
}
