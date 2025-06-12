package org.d3if3121.tellink.data.retrofit


import org.d3if3121.tellink.data.model.Project
import org.d3if3121.tellink.data.model.response.ApiResponse
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.data.model.mahasiswa.MahasiswaLogin
import org.d3if3121.tellink.data.model.response.Response
import org.d3if3121.tellink.data.repository.interfaces.ProjectListByNimResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("api/project/{nim}")
    fun getProjectsByNim(
        @Path("nim") nim : String
    ): Response<ProjectListByNimResponse>

    @POST("api/login")
    suspend fun loginMahasiswa(@Body mahasiswa: MahasiswaLogin) : ApiResponse<Mahasiswa>

    @POST("api/register")
    suspend fun registerMahasiswa(@Body mahasiswa: Mahasiswa): ApiResponse<Unit>

    @GET("api/feeds")
    suspend fun getProjectWithMahasiswa(): ApiResponse<List<Project>>
}