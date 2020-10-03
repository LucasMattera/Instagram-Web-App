package token

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import javalinjwt.JWTGenerator
import javalinjwt.JWTProvider
import org.unq.ui.model.User

data class UserTokenAdapter(val id: String, val email: String, val password: String )

class NotValidToken : Exception("not valid token")

class TokenController {

    val algorithm: Algorithm = Algorithm.HMAC256("pass")

    var generator: JWTGenerator<UserTokenAdapter> = JWTGenerator<UserTokenAdapter> { user: UserTokenAdapter, alg: Algorithm? ->
        val token: JWTCreator.Builder = JWT.create()
            .withClaim("id", user.id)
        token.sign(alg)
    }

    var verifier: JWTVerifier = JWT.require(algorithm).build()

    var provider = JWTProvider(algorithm, generator, verifier)

    fun generateToken(usr: User): String {
        return provider.generateToken(usr)
    }

    fun validateToken(tkn: String): String {
        val decodeJWT = provider.validateToken(tkn)
        if (decodeJWT.isPresent()) return decodeJWT.get().getClaim("id").asString()
        throw NotValidToken()
    }
}
/*
fun main() {
    val tokenController = TokenController()

    //val token = tokenController.generateToken(UserTokenAdapter("u_1", "pepe", "asd"))

    tokenController.validateToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6InVfMSJ9.x8CQsP5TEVZ4fag36Z2mG6x5zm1c6ToOl42DkJGA9gw")
}
 */



