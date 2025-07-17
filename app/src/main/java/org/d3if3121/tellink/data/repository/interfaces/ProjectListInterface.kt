package org.d3if3121.tellink.data.repository.interfaces

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3121.tellink.data.model.project.Project
import org.d3if3121.tellink.data.model.project.ProjectIdRequest
import org.d3if3121.tellink.data.model.response.Response

typealias ProjectListResponse = Response<List<Project>>
typealias ProjectWithMahasiswaResponse = Response<List<Project>>

typealias ProjectListByNimResponse = Response<List<Project>>
typealias ProjectListUserResponse = Response<List<Project>>
typealias ProjectIdResponse = Response<Project>

typealias AddProjectResponse = Response<String>
typealias EditProjectResponse = Response<String>
typealias DeleteProjectResponse = Response<String>

typealias AddRequestResponse = Response<String>
typealias AddAcceptResponse = Response<String>
typealias DeleteAcceptResponse = Response<String>
typealias DeleteRequestResponse = Response<String>
typealias UpdateProjectResponse = Response<String>

interface ProjectListInterface {
    fun getProjectList(): Flow<ProjectListResponse>
    fun getProjectListByNim(nim: String): Flow<ProjectListByNimResponse>
    fun getProjectListUser(id: String): Flow<ProjectListUserResponse>
    fun getRequestList(nim: String): Flow<ProjectListUserResponse>

    suspend fun addProject(projectPart: RequestBody, imageMultipart: MultipartBody.Part?): AddProjectResponse
    suspend fun editProject(id: RequestBody, projectPart: RequestBody, imageMultipart: MultipartBody.Part?): EditProjectResponse
    suspend fun updateProject(projectId: String, project: Project): UpdateProjectResponse
    suspend fun deleteProject(id: String): DeleteProjectResponse

    suspend fun getProjectById(id: String): ProjectIdResponse
    suspend fun getProjectWithMahasiswa(): ProjectWithMahasiswaResponse
    suspend fun getProjectByNim(nim: String): ProjectWithMahasiswaResponse
    suspend fun getRequestByNim(nim: String): ProjectWithMahasiswaResponse

    suspend fun addRequest(projectId: String, nim: String): AddRequestResponse
    suspend fun addAccept(projectId: String, nim: String): AddAcceptResponse
    suspend fun deleteAccept(projectId: String, nim: String): DeleteAcceptResponse
    suspend fun deleteRequest(projectId: String, nim: String): DeleteRequestResponse

}