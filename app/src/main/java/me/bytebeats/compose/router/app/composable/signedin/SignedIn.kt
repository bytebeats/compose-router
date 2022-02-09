package me.bytebeats.compose.router.app.composable.signedin

/**
 * Created by bytebeats on 2021/10/13 : 19:05
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
interface SignedIn {
    sealed class Routing {
        object Profile: Routing()
        object News: Routing()
        object Gallery: Routing()
    }

    companion object {

    }
}