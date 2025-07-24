package org.d3if3121.tellink.data.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3121.tellink.data.model.comment.Comment
import org.d3if3121.tellink.data.model.LikeRequest
import org.d3if3121.tellink.data.model.comment.CommentIdRequest
import org.d3if3121.tellink.data.model.mahasiswa.NimRequest
import org.d3if3121.tellink.data.model.project.Project
import org.d3if3121.tellink.data.model.project.ProjectIdRequest
import org.d3if3121.tellink.data.model.response.ApiResponse
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.data.model.mahasiswa.MahasiswaLogin
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    //AUTH
    @POST("api/login")
    suspend fun loginMahasiswa(@Body mahasiswa: MahasiswaLogin) : ApiResponse<Mahasiswa>

    @POST("api/register")
    suspend fun registerMahasiswa(@Body mahasiswa: Mahasiswa): ApiResponse<Unit>


    //CONTENT
    @POST("api/feeds")
    suspend fun getProjectWithMahasiswa(@Body nim: NimRequest): ApiResponse<List<Project>>

    @POST("api/projectsbynim")
    suspend fun getProjectsByNim(@Body nim: NimRequest): ApiResponse<List<Project>>

    @POST("api/requestbynim")
    suspend fun getRequestsByNim(@Body nim: NimRequest): ApiResponse<List<Project>>

    @POST("api/projectsbyid")
    suspend fun getProjectById(@Body id: ProjectIdRequest): ApiResponse<Project>

    @Multipart
    @POST("api/addproject")
    suspend fun addProject(
        @Part("project") project: RequestBody,
        @Part image: MultipartBody.Part?
    ): ApiResponse<Unit>

    @Multipart
    @POST("api/editproject")
    suspend fun editProject(
        @Part("project") project: RequestBody,
        @Part image: MultipartBody.Part?,
        @Part("id") id: RequestBody
    ): ApiResponse<Unit>

    @POST("api/deleteproject")
    suspend fun deleteProject(@Body id: ProjectIdRequest): ApiResponse<String>

    @POST("api/requestedbynim")
    suspend fun getMahasiswaReqByProjectId(@Body projectId: ProjectIdRequest): ApiResponse<List<Mahasiswa>>

    //LIKE
    @POST("api/likeproject")
    suspend fun likeProject(@Body id: LikeRequest): ApiResponse<String>

    @POST("api/mahasiswalike")
    suspend fun getMahasiswaLikeByProjectId(@Body id: ProjectIdRequest): ApiResponse<List<Mahasiswa>>

    //COMMENT
    @POST("api/addcomment")
    suspend fun addComment(@Body comment: Comment): ApiResponse<String>

    @POST("api/getcommentbyprojectid")
    suspend fun getCommentByProjectId(@Body id: ProjectIdRequest): ApiResponse<List<Comment>>

    @POST("api/getcommentreplybyid")
    suspend fun getCommentReplyById(@Body id: CommentIdRequest): ApiResponse<List<Comment>>

}