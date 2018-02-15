package com.elpassion.assignment.model

/**
 * Created by pavel on 15/2/18.
 */

data class ItemList(val id: Long,
                    val login: String,
                    val name: String,
                    val avatar_url: String,
                    val isPerson: Boolean
)