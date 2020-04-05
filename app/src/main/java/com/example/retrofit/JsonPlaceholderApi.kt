package com.example.retrofit

import com.example.retrofit.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface JsonPlaceholderApi {


    //https://jsonplaceholder.typicode.com/      +users

     @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("posts")  //posts?userId=2
   // fun manipulateUrl(@Query("userId") id: Int):Call<List<User>>
    fun manipulateUrl(@QueryMap map:Map<String,String>):Call<List<User>>

    @POST("posts")
    fun addUsers(@Body user: User): Call<User>

    @DELETE("posts3/{id}")
    fun deleteUser(@Path("id") id:Int):Call<Unit>

    @PUT("posts/{id}")
    fun updateUser(@Path("id") id:Int, @Body user: User) :Call<User>

    @PATCH("posts/{id}")
    fun patchUser(@Path("id") id:Int, @Body user: User) :Call<User>
}