package com.example.dtabookstore.network

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://13.76.226.75/api/"

// moshi object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// retrofit object
val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


// interface MarsApiService
interface ApiService {


    @POST("dangky")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun dangKy(@Body userData: UserDangKy): Call<UserDangKyRespond>


    @POST("dangnhap")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun dangNhap(@Body userDangNhapData: UserDangNhap): Call<UserDangNhapRespond>


    // nhận danh sách toàn bộ sách
    @GET("products")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getDanhSachSach(): Call<MutableList<Book>>


    @POST("dathang")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun datHang(@Body donHang: DonHang): Call<DonHangRespond>


    @POST("getUserInformation")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUserInformation(@Body userId: UserProfile): Call<UserProfileRespond>


    @POST("updateUserInformation")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun postUpdateUserInformation(@Body userUpdateInformation: UserUpdateInformation): Call<UserUpdateInformationRespond>


    @POST("getUserOrders")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUserOrders(@Body userId: UserProfile): Call<MutableList<UserOrderRespond>>


    @POST("getOrderDetail")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getOrderDetail(@Body orderId: OrderDetailRequest): Call<OrderDetailRespond>


    @POST("deleteOrder")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun deleteOrder(@Body orderId: OrderDeleteRequest): Call<OrderDeleteRespond>


    @POST("getNotification")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getNotification(@Body userId: NotificationRequest): Call<MutableList<NotificationRespond>>


    @POST("timSachTheoTen")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun timSachTheoTen(@Body tuKhoa: TimSachRequest): Call<List<TimSachRespond>>

}

object Api {
    // lưu id của user đã đăng nhập
    lateinit var userId: String

    val retrofitService: ApiService by lazy {
        // MarsApiService : interface
        retrofit.create(ApiService::class.java)
    }
}