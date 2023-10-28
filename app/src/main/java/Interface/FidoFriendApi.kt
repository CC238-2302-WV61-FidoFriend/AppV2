package Interface

import Beans.LoginBody
import Beans.Pet
import Beans.PetRegisterRequest
import Beans.Profile
import Beans.RegisterBody
import Beans.Service
import Beans.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FidoFriendApi {
    @GET("/api/User/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @POST("/api/User/login")
    fun loginUser(@Body requestBody: LoginBody): Call<User>

    @POST("/api/User")
    fun registerUser(@Body requestBody: RegisterBody): Call<User>

    @POST("/api/User")
    fun editUser(@Body requestBody: Profile): Call<User>

    @POST("/api/Pet")
    fun registerPet(@Body requestBody: PetRegisterRequest): Call<Pet>

    @GET("/api/Service")
    fun getServices(): Call<List<Service>>
}