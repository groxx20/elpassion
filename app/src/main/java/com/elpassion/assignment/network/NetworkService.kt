package com.elpassion.assignment.network

import com.elpassion.assignment.dto.ResponseDtoRepos
import com.elpassion.assignment.dto.ResponseDtoUser
import com.elpassion.assignment.model.Person
import com.elpassion.assignment.model.Repository
import com.elpassion.assignment.model.Star
import com.elpassion.assignment.model.User
import com.elpassion.assignment.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by pavel on 23/1/18.
 */
interface NetworkService {


    @GET(Constants.API_SEARCH_USERS)
    fun getUsers(
            @Query("q") q: String,
    @Query("page") page: Int,
    @Query("per_page") per_page: Int): Observable<ResponseDtoUser<User>>

    @GET(Constants.API_SEARCH_REPOS)
    fun getRepos(
            @Query("q") q: String,
            @Query("page") page: Int,
            @Query("per_page") per_page: Int): Observable<ResponseDtoRepos<Repository>>


    @GET(Constants.API_SPECIFIC_USER + "/{login}")
     fun getSpecificPerson(@Path("login") name: String): Observable<Person>

    @GET(Constants.API_SPECIFIC_USER + "/{login}/" + Constants.API_REPOS)
    fun getSpecificPersonStars(@Path("login") name: String): Observable<List<Star>>

}

