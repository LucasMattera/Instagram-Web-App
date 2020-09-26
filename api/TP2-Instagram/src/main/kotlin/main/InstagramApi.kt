import controller.PostController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path
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

        app.routes {
            path("/posts") {
                get(postController::getPosts)
                path(":id") {
                    get(postController::getPost)
                }
            }
        }
    }

}


fun main(args: Array<String>) {
    InstagramApi().start()
}
