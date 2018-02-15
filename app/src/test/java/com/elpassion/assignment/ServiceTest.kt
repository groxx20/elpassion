package com.elpassion.assignment

import com.elpassion.assignment.dto.ResponseDtoRepos
import com.elpassion.assignment.dto.ResponseDtoUser
import com.elpassion.assignment.model.Person
import com.elpassion.assignment.model.Repository
import com.elpassion.assignment.model.Star
import com.elpassion.assignment.model.User
import com.elpassion.assignment.network.NetworkService
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import rx.observers.TestSubscriber

/**
 * Created by pavel on 15/2/18.
 */

class ServiceTest {

    private var retrofit: Retrofit? = null
    var mSubscriberUser: TestSubscriber<ResponseDtoUser<User>>? = null

    var mSubscriberRepos: TestSubscriber<ResponseDtoRepos<Repository>>? = null

    var mSubscriberPerson: TestSubscriber<Person>? = null

    var mSubscriberStars: TestSubscriber<List<Star>>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()


        mSubscriberUser = TestSubscriber()
        mSubscriberRepos = TestSubscriber()

        mSubscriberPerson = TestSubscriber()
        mSubscriberStars = TestSubscriber()

    }



    @Test
    fun service_Success_User() {

        val apiEndpoints = retrofit!!.create(NetworkService::class.java)

        apiEndpoints.getUsers("groxx20", 1, 10).subscribe(mSubscriberUser)

        mSubscriberUser!!.assertNoErrors()
        mSubscriberUser!!.assertCompleted()

    }

    @Test
    fun service_Success_Repos() {

        val apiEndpoints = retrofit!!.create(NetworkService::class.java)

        apiEndpoints.getRepos("groxx20", 1, 10).subscribe(mSubscriberRepos)

        mSubscriberRepos!!.assertNoErrors()
        mSubscriberRepos!!.assertCompleted()

    }

    @Test
    fun service_Success_Person() {

        val apiEndpoints = retrofit!!.create(NetworkService::class.java)

        apiEndpoints.getSpecificPerson("groxx20").subscribe(mSubscriberPerson)

        mSubscriberPerson!!.assertNoErrors()
        mSubscriberPerson!!.assertCompleted()

    }

    @Test
    fun service_Success_Stars() {

        val apiEndpoints = retrofit!!.create(NetworkService::class.java)

        apiEndpoints.getSpecificPersonStars("groxx20").subscribe(mSubscriberStars)

        mSubscriberStars!!.assertNoErrors()
        mSubscriberStars!!.assertCompleted()

    }


}