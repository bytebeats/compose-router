package me.bytebeats.compose.router

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

/**
 * Created by bytebeats on 2021/10/12 : 19:34
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class BackPressHandler(val id: String = "root") {
    var children = mutableListOf<() -> Boolean>()

    /**
     * The last lambda expression of [children] which return true
     */
    fun handle(): Boolean = children.reversed().any { it() }
}

val LocalBackPressHandler: ProvidableCompositionLocal<BackPressHandler> =
    compositionLocalOf { throw IllegalStateException("BackPressHandler has not been initialized yet.") }