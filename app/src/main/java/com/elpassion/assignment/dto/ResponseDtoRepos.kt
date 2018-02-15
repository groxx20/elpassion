package com.elpassion.assignment.dto

/**
 * Created by pavel on 10/2/18.
 */

class ResponseDtoRepos<TYPE> {


    var total_count: Int? = null
    var incomplete_results: Boolean? = null
    var items: List<TYPE>? = null

}