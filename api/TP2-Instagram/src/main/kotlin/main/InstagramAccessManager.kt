package main

import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import token.NotValidToken
import token.TokenController

class InstagramAccessManager(val igSys: InstagramSystem) : AccessManager {

    val tknCtrl = TokenController()

    override fun manage(handler: Handler, ctx: Context, roles: MutableSet<Role>) {
        val token = ctx.header("Authorization")
        when{
            roles.contains(IgRoles.ANYONE) -> handler.handle(ctx)
            token === null -> throw UnauthorizedResponse()
            roles.contains(IgRoles.USER) ->{
                try{
                    val usrId = tknCtrl.validateToken(token)
                    igSys.getUser(usrId)
                    ctx.attribute("usrId", usrId)
                    handler.handle(ctx)
                } catch(e: NotValidToken){
                    throw  UnauthorizedResponse("Not valid Token")
                } catch(e: NotFound){
                    throw UnauthorizedResponse("Not valid Token")
                }
            }
        }
    }

}

enum class IgRoles: Role {
    ANYONE,
    USER
}