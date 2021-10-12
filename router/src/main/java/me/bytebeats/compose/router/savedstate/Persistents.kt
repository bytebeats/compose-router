package me.bytebeats.compose.router.savedstate

import androidx.compose.runtime.*

/**
 * Created by bytebeats on 2021/10/12 : 10:51
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */

@Composable
fun persistentInt(key: String, defaultValue: Int = 0): MutableState<Int> {
    val bundle = LocalSavedState.current
    val state = remember {
        mutableStateOf(bundle.getInt(key, defaultValue))
    }
    SaveInt(key = key, value = state.value)
    return state
}

@Composable
private fun SaveInt(key: String, value: Int) {
    LocalSavedState.current.putInt(key, value)
}