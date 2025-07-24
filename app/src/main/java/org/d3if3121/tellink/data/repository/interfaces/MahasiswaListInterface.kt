package org.d3if3121.tellink.data.repository.interfaces

import kotlinx.coroutines.flow.Flow
import org.d3if3121.tellink.data.model.comment.Comment
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.data.model.mahasiswa.MahasiswaEdit
import org.d3if3121.tellink.data.model.mahasiswa.MahasiswaLogin
import org.d3if3121.tellink.data.model.response.Response


typealias MahasiswaListResponse = Response<List<Mahasiswa>>
typealias RegisterResponse = Response<String>
typealias AddUserResponse = Response<Mahasiswa>
typealias UpdateMahasiswaResponse = Response<String>
typealias DeleteMahasiswaResponse = Response<Void>
typealias MahasiswaByNimResponse = Response<Mahasiswa>

typealias MahasiswaLikeResponse = Response<List<Mahasiswa>>

typealias MahasiswaReqByProjectIdResponse = Response<List<Mahasiswa>>

typealias LoginResponse = Response<Mahasiswa>

interface MahasiswaListInterface {
    fun getMahasiswaList(): Flow<MahasiswaListResponse>

    fun addUser(mahasiswa: Mahasiswa): Flow<AddUserResponse>

    suspend fun updateMahasiswa(mahasiswa: MahasiswaEdit): UpdateMahasiswaResponse
    suspend fun deleteMahasiswa(id: String): DeleteMahasiswaResponse

    suspend fun registerMahasiswa(mahasiswa: Mahasiswa): RegisterResponse
    suspend fun loginMahasiswa(mahasiswa: MahasiswaLogin): LoginResponse

    suspend fun getMahasiswaByNim(nim: String): Mahasiswa
    suspend fun getMahasiswaLikeByProjectId(projectId: String): MahasiswaLikeResponse

    suspend fun getMahasiswaReqByProjectId(projectId: String): MahasiswaReqByProjectIdResponse

    suspend fun checkRequestProject(id: String, nim: String): Boolean
    suspend fun markProject(nim: String, projectId: List<String>)
}