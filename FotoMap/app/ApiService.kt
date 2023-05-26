import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("endpoint")
    fun postData(
        @Body requestData: RequestData
    ): Call<ApiResponse>

    @GET("endpoint")
    fun getLocations(): Call<List<Location>>
}
