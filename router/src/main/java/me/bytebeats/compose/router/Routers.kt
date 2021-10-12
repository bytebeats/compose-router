package me.bytebeats.compose.router

import android.transition.SidePropagation
import androidx.compose.runtime.*
import me.bytebeats.compose.router.savedstate.BundleScope
import me.bytebeats.compose.router.savedstate.LocalSavedState

/**
 * Created by bytebeats on 2021/10/12 : 10:22
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */

private fun key(backStackIndex: Int) = "Key$backStackIndex"

private val backStackMap = mutableMapOf<Any, BackStack<*>>()

/**
 * Currently only used for deep link based Router.
 *
 * Can be set to store a list of Routing elements of different types.
 * The idea is that when we walk through this list in sequence - provided that the sequence
 * is correct - we can set the app into any state that is a combination of Routing on different levels.
 *
 */
val LocalRouter: ProvidableCompositionLocal<List<Any>> = compositionLocalOf { emptyList() }

/**
 * Adds back stack functionality with bubbling up fallbacks if the back stack cannot be popped
 * on this level.
 *
 * @param defaultRouting    The default routing to initialise the back stack with
 * @param children          The @Composable to wrap with this BackHandler. It will have access to the back stack.
 */
@Composable
inline fun <reified T> Router(
    defaultRouting: T,
    noinline children: @Composable (BackStack<T>) -> Unit
) {
    Router(contextId = T::class.java.name, defaultRouting = defaultRouting, children = children)
}

@Composable
fun <T> Router(
    contextId: String,
    defaultRouting: T,
    children: @Composable (BackStack<T>) -> Unit
) {
    val route = LocalRouter.current
    val routingFromLocal = route.firstOrNull() as? T
    val downstreamRoute = if (route.size > 1) route.takeLast(route.size - 1) else emptyList()

    val upstreamHandler = LocalBackPressHandler.current
    val localHandler = remember {
        BackPressHandler("${upstreamHandler.id}.$contextId")
    }
    val backStack = findBackStack(
        key = localHandler.id,
        defaultRouting = defaultRouting,
        override = routingFromLocal
    )
    val handleBackPressHere: () -> Boolean = { localHandler.handle() || backStack.pop() != null }

    SideEffect {
        upstreamHandler.children.add(handleBackPressHere)
    }

    DisposableEffect(key1 = Unit) { onDispose { upstreamHandler.children.remove(handleBackPressHere) } }

    @Composable
    fun Observe(body: @Composable () -> Unit) = body()

    // Not recomposing router on backstack operation
    Observe {
        BundleScope(key = key(backStack.lastIndex), autoDispose = false) {
            CompositionLocalProvider(
                LocalBackPressHandler provides localHandler,
                LocalRouter provides downstreamRoute
            ) {
                children(backStack)
            }
        }
    }
}

@Composable
private fun <T> findBackStack(key: String, defaultRouting: T, override: T?): BackStack<T> {
    val upstreamBundle = LocalSavedState.current
    val onElementRemoved: (Int) -> Unit = { upstreamBundle.remove(key(it)) }

    val existing = backStackMap[key] as BackStack<T>?
    return when {
        override != null -> BackStack(override as T, onElementRemoved)
        else -> existing ?: BackStack(defaultRouting, onElementRemoved)
    }.apply {
        backStackMap[key] = this
    }
}
