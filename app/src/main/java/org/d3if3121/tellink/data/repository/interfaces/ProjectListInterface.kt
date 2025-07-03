package org.d3if3121.tellink.data.repository.interfaces

import android.content.Context
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3121.tellink.data.model.Project
import org.d3if3121.tellink.data.model.ProjectAdd
import org.d3if3121.tellink.data.model.response.Response
import java.io.File

typealias ProjectListResponse = Response<List<Project>>
typealias ProjectWithMahasiswaResponse = Response<List<Project>>

typealias ProjectListByNimResponse = Response<List<Project>>
typealias ProjectListUserResponse = Response<List<Project>>
typealias AddProjectResponse = Response<String>
typealias AddRequestResponse = Response<String>
typealias AddAcceptResponse = Response<String>
typealias DeleteAcceptResponse = Response<String>
typealias DeleteRequestResponse = Response<String>
typealias UpdateProjectResponse = Response<String>
typealias DeleteProjectResponse = Response<Void>

interface ProjectListInterface {
    fun getProjectList(): Flow<ProjectListResponse>
    fun getProjectListByNim(nim: String): Flow<ProjectListByNimResponse>
    fun getProjectListUser(id: String): Flow<ProjectListUserResponse>
    fun getRequestList(nim: String): Flow<ProjectListUserResponse>

    suspend fun addProject(projectPart: RequestBody, imageMultipart: MultipartBody.Part?): AddProjectResponse
    suspend fun updateProject(projectId: String, project: Project): UpdateProjectResponse
    suspend fun deleteProject(id: String): DeleteProjectResponse

    suspend fun getProjectById(nim: String): Project
    suspend fun getProjectWithMahasiswa(): ProjectWithMahasiswaResponse
    suspend fun getProjectByNim(nim: String): ProjectWithMahasiswaResponse
    suspend fun getRequestByNim(nim: String): ProjectWithMahasiswaResponse

    suspend fun addRequest(projectId: String, nim: String): AddRequestResponse
    suspend fun addAccept(projectId: String, nim: String): AddAcceptResponse
    suspend fun deleteAccept(projectId: String, nim: String): DeleteAcceptResponse
    suspend fun deleteRequest(projectId: String, nim: String): DeleteRequestResponse

}