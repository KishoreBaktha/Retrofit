package com.example.retrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit.model.User
import kotlinx.android.synthetic.main.activity_main.textView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Retrofit Builder
        val retrofit=Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .build()

        //objects to call methods
        val jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi::class.java)


        //get user
        val myCall:Call<List<User>> = jsonPlaceholderApi.getUsers()
         myCall.enqueue(object:Callback<List<User>>{
             override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("error", t.message.toString())
             }

             override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
               val Users:List<User> = response.body() ?: emptyList()
                 val stringBuilder = StringBuilder()
                 for(user in Users){
                     stringBuilder.append(user.id)
                     stringBuilder.append("\t")
                     stringBuilder.append(user.username)
                     stringBuilder.append("\t")
                     stringBuilder.append(user.email_user)
                     stringBuilder.append("\t")
                     stringBuilder.append(user.name)
                     stringBuilder.append("\n")
                 }
                 textView.text = stringBuilder

             }
         })

         //add Users
        val users = User(2,"kb","kb@hotmail.com","random")
        val addCall = jsonPlaceholderApi.addUsers(users)

        addCall.enqueue(object:Callback<User>{
            override fun onFailure(call: Call<User>, t: Throwable) {
                 textView.text = t.message.toString()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                textView.text =  response.code().toString()
            }
        })

        //manipulate uri
       // val getUserWithIdTwo = jsonPlaceholderApi.manipulateUrl(2)
        val hashMap:HashMap<String,String> = HashMap<String,String>()
        hashMap.put("userId","2")
        val getUserWithIdTwo = jsonPlaceholderApi.manipulateUrl(hashMap)

        getUserWithIdTwo.enqueue(object:Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                textView.text = t.message.toString()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val Users:List<User> = response.body() ?: emptyList()
                val stringBuilder = StringBuilder()
                for(user in Users){
                    stringBuilder.append(user.id)
                    stringBuilder.append("\t")
                    stringBuilder.append(user.username)
                    stringBuilder.append("\t")
                    stringBuilder.append(user.email_user)
                    stringBuilder.append("\t")
                    stringBuilder.append(user.name)
                    stringBuilder.append("\n")
                }
                textView.text = stringBuilder
            }
        })


        //delete users

        val deleteUser=jsonPlaceholderApi.deleteUser(2)

        deleteUser.enqueue(object:Callback<Unit>{
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                textView.text = t.message.toString()
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                textView.text =  response.code().toString()
            }
        })


        //update users
        val updateUser=jsonPlaceholderApi.updateUser(2,users)

        updateUser.enqueue(object:Callback<User>{
            override fun onFailure(call: Call<User>, t: Throwable) {
                textView.text = t.message.toString()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                textView.text =  response.code().toString()
            }
        })

        //patch users  - update only values passed
        val patchUser=jsonPlaceholderApi.patchUser(2,users)

        patchUser.enqueue(object:Callback<User>{
            override fun onFailure(call: Call<User>, t: Throwable) {
                textView.text = t.message.toString()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                textView.text =  response.code().toString()
            }
        })

    }
}