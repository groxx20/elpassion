package com.elpassion.assignment.model


/**
 * Created by pavel on 15/2/18.
 */

data class Repository(val id: Long,
                      val name: String,
                      val owner: User
)