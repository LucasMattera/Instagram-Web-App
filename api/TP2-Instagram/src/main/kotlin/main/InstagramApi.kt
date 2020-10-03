import controller.PostController
import controller.UserController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.core.util.RouteOverviewPlugin
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import token.NotValidToken
import token.TokenController

class InstagramAccesManager(val igSys: InstagramSystem) : AccessManager {

    val tknCtrl = TokenController()

    override fun manage(hndlr: Handler, ctx: Context, roles: MutableSet<Role>) {
        val token = ctx.header("Authorization")
        when{
            roles.contains(IgRoles.ANYONE) -> print("blabla")
            token === null -> throw UnauthorizedResponse()
            roles.contains(IgRoles.USER) ->{
                try{
                    val usrId = tknCtrl.validateToken(token)
                    igSys.getUser(usrId)
                    ctx.attribute("usrId", usrId)
                    hndlr.handle(ctx)
                } catch(e: NotValidToken){
                    throw  UnauthorizedResponse("not valid token")
                } catch(e: NotFound){
                    throw UnauthorizedResponse("usr not found")
                }
            }
        }
    }

}

enum class IgRoles: Role{
    ANYONE,
    USER
}

class InstagramApi {

    fun start() {
        val instagramSystem = getInstagramSystem()
        val postController = PostController(instagramSystem)
        val usrController = UserController(instagramSystem)

        val app = Javalin.create() {
            it.defaultContentType = "application/json"
            it.registerPlugin(RouteOverviewPlugin("/routes"))
            it.accessManager(InstagramAccesManager(instagramSystem))
        }

        app.start(7000)

        app.routes {
            path("posts") {
                get(postController::getPosts, setOf(IgRoles.USER))

                path(":id") {
                    get(postController::getPost, setOf(IgRoles.USER))
                }
            }

            path("login") {
                post(usrController::loginUser, setOf(IgRoles.ANYONE))
            }
        }
    }

}


fun main(args: Array<String>) {
    InstagramApi().start()
}
