package me.bytebeats.compose.router.app.entity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Created by bytebeats on 2021/10/12 : 21:04
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class Student(university: String, major: String) {
    var university by mutableStateOf(university)
    var major by mutableStateOf(major)
}
