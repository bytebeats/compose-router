package me.bytebeats.compose.router.app.composable

import androidx.compose.runtime.Composable
import me.bytebeats.compose.router.Router
import me.bytebeats.compose.router.app.entity.Student

/**
 * Created by bytebeats on 2021/10/13 : 17:30
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
interface Root {
    sealed class Routing {
        object SignedOut : Routing()
        data class SignedIn(val student: Student) : Routing()
    }

    companion object {
        @Composable
        fun Content(defaultRouting: Routing) {
            Router(defaultRouting = defaultRouting) { backStack ->
                when (backStack.last) {
                    is Routing.SignedOut -> {
                    }
                    is Routing.SignedIn -> {
                    }
                }
            }
        }
    }
}