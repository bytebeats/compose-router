package me.bytebeats.compose.router.savedstate

import android.os.Bundle
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import me.bytebeats.compose.router.BUNDLE_KEY

/**
 * Created by bytebeats on 2021/10/12 : 10:46
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */

private val rootSavedState = Bundle()

val LocalSavedState: ProvidableCompositionLocal<Bundle> = compositionLocalOf { rootSavedState }

fun Bundle.saveLocal() {
    putBundle(BUNDLE_KEY, rootSavedState)
}