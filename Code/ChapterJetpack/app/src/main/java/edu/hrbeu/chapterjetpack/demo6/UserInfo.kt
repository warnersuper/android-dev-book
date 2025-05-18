package edu.hrbeu.chapterjetpack.demo6

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val created_at: String,
    var updated_at: String,
)