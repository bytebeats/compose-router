package me.bytebeats.compose.router.savedstate

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import me.bytebeats.compose.router.BUNDLE_KEY

/**
 * Created by bytebeats on 2021/10/12 : 19:21
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */

@Composable
fun BundleScope(
    savedState: Bundle?,
    children: @Composable (Bundle) -> Unit
) {
    BundleScope(
        defaultBundle = savedState ?: Bundle(),
        autoDispose = true,
        children = children
    )
}

@Composable
fun BundleScope(
    key: String,
    children: @Composable (Bundle) -> Unit
) {
    BundleScope(key = key, autoDispose = true, children = children)
}

/**
 * Scopes a new Bundle with [key] under ambient [LocalSavedState] and provides it
 * to [children].
 */
@Composable
fun BundleScope(
    key: String = BUNDLE_KEY,
    defaultBundle: Bundle = Bundle(),
    autoDispose: Boolean = true,
    children: @Composable (Bundle) -> Unit
) {
    val upstream = LocalSavedState.current
    val downstream = upstream.getBundle(key) ?: defaultBundle
    SideEffect {
        upstream.putBundle(key, downstream)
    }
    if (autoDispose) {
        DisposableEffect(key1 = Unit, effect = { onDispose { upstream.remove(key) } })
    }

    CompositionLocalProvider(LocalSavedState provides downstream) {
        children(downstream)
    }
}