package org.d3if3121.tellink.data.repository.interfaces

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3121.tellink.data.model.comment.Comment
import org.d3if3121.tellink.data.model.comment.CommentIdRequest
import org.d3if3121.tellink.data.model.comment.CommentRequest
import org.d3if3121.tellink.data.model.project.Project
import org.d3if3121.tellink.data.model.response.Response

typealias ProjectListResponse = Response<List<Project>>
typealias ProjectWithMahasiswaResponse = Response<List<Project>>

typealias ProjectIdResponse = Response<Project>

typealias AddProjectResponse = Response<String>
typealias EditProjectResponse = Response<String>
typealias DeleteProjectResponse = Response<String>

typealias AddRequestResponse = Response<String>
typealias AddAcceptResponse = Response<String>
typealias DeleteAcceptResponse = Response<String>
typealias DeleteRequestResponse = Response<String>
typealias UpdateProjectResponse = Response<String>

typealias LikeProjectResponse = Response<String>
typealias CommentProjectResponse = Response<List<Comment>>
typealias CommentReplyResponse = Response<List<Comment>>
typealias AddCommentResponse = Response<String>
typealias DeleteCommentResponse = Response<String>

interface ProjectListInterface {

    suspend fun addProject(projectPart: RequestBody, imageMultipart: MultipartBody.Part?): AddProjectResponse
    suspend fun editProject(id: RequestBody, projectPart: RequestBody, imageMultipart: MultipartBody.Part?): EditProjectResponse
    suspend fun updateProject(projectId: String, project: Project): UpdateProjectResponse
    suspend fun deleteProject(id: String): DeleteProjectResponse

    suspend fun getProjectById(id: String): ProjectIdResponse
    suspend fun getProjectWithMahasiswa(nim: String): ProjectWithMahasiswaResponse
    suspend fun getProjectByNim(nim: String): ProjectWithMahasiswaResponse
    suspend fun getRequestByNim(nim: String): ProjectWithMahasiswaResponse

    suspend fun addRequest(projectId: String, nim: String): AddRequestResponse
    suspend fun addAccept(projectId: String, nim: String): AddAcceptResponse
    suspend fun deleteAccept(projectId: String, nim: String): DeleteAcceptResponse
    suspend fun deleteRequest(projectId: String, nim: String): DeleteRequestResponse

    suspend fun likeProject(projectId: String, nim: String): LikeProjectResponse
    suspend fun getCommentByProjectId(projectId: String): CommentProjectResponse
    suspend fun getCommentReplyById(commentId: String): CommentReplyResponse
    suspend fun addComment(comment: CommentRequest): AddCommentResponse
    suspend fun deleteComment(commentId: String): DeleteCommentResponse

}