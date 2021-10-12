package me.bytebeats.compose.router

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Created by bytebeats on 2021/10/12 : 19:46
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class BackStack<T> internal constructor(
    val initialElement: T,
    private val onElementRemoved: ((Int) -> Unit)? = null
) {
    private var elements by mutableStateOf(listOf(initialElement))

    val lastIndex: Int get() = elements.lastIndex

    val size: Int get() = elements.size

    val last: T get() = elements.last()

    fun isEmpty(): Boolean = elements.isEmpty()

    fun push(element: T) {
        elements = elements + element
    }

    fun pushAndDropNested(element: T) {
        onElementRemoved?.invoke(lastIndex)
        push(element)
    }

    fun pop(): T? = if (size > 1) {//at least 1 element kept in elements
        onElementRemoved?.invoke(lastIndex)
        val last = last
        elements = elements - last
        last
    } else null

    fun replace(element: T) {
        onElementRemoved?.invoke(lastIndex)
        elements = elements - last + element
    }

    fun newRoot(element: T) {
        elements.indices.reversed().forEach { onElementRemoved?.invoke(it) }
        elements = listOf(element)
    }

}