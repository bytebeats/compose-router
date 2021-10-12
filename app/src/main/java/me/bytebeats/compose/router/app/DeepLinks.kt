package me.bytebeats.compose.router.app

import android.content.Intent
import android.net.Uri
import android.util.Log

/**
 * Created by bytebeats on 2021/10/12 : 21:15
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */

fun Intent.deepLinkRoute(): List<Any> = when (data?.host) {
    "go-to-university" -> parseUniversityDeepLink(data!!)
    null -> emptyList()
    else -> emptyList<Any>().apply { Log.d(TAG, "Unexpected deep link: $data") }
}

private fun parseUniversityDeepLink(data: Uri): List<Any> {
    return emptyList()
}