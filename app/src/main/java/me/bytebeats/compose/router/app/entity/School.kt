package me.bytebeats.compose.router.app.entity

/**
 * Created by bytebeats on 2021/10/12 : 21:07
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
data class School(val name: String, val majors: List<Major>)

val schools = listOf(
    School("Software School", MutableList(5) { Major(it.toString()) }),
    School("Arts School", MutableList(5) { Major(it.toString()) }),
    School("Philosophy School", MutableList(5) { Major(it.toString()) }),
    School("Law School", MutableList(5) { Major(it.toString()) }),
    School("History School", MutableList(5) { Major(it.toString()) }),
    School("Electrical Engineering School", MutableList(5) { Major(it.toString()) }),
    School("Computer School", MutableList(10) { Major(it.toString()) })
)
