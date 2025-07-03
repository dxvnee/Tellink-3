package org.d3if3121.tellink.data.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3121.tellink.data.model.NimRequest
import org.d3if3121.tellink.data.model.Project
import org.d3if3121.tellink.data.model.response.ApiResponse
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.data.model.mahasiswa.MahasiswaLogin
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("api/login")
    suspend fun loginMahasiswa(@Body mahasiswa: MahasiswaLogin) : ApiResponse<Mahasiswa>

    @POST("api/register")
    suspend fun registerMahasiswa(@Body mahasiswa: Mahasiswa): ApiResponse<Unit>

    @GET("api/feeds")
    suspend fun getProjectWithMahasiswa(): ApiResponse<List<Project>>

    @POST("api/projectsbynim")
    suspend fun getProjectsByNim(@Body nim: NimRequest): ApiResponse<List<Project>>

    @POST("api/requestbynim")
    suspend fun getRequestsByNim(@Body nim: NimRequest): ApiResponse<List<Project>>

    @Multipart
    @POST("api/addproject")
    suspend fun addProject(
        @Part("project") project: RequestBody,
        @Part image: MultipartBody.Part?
    ): ApiResponse<Unit>
}