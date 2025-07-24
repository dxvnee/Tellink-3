package org.d3if3121.tellink.data.repository

import android.content.Context
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3121.tellink.data.model.LikeRequest
import org.d3if3121.tellink.data.model.comment.CommentIdRequest
import org.d3if3121.tellink.data.model.mahasiswa.NimRequest
import org.d3if3121.tellink.data.model.project.Project
import org.d3if3121.tellink.data.model.project.ProjectIdRequest
import org.d3if3121.tellink.data.model.response.Response
import org.d3if3121.tellink.data.repository.interfaces.ProjectListInterface
import org.d3if3121.tellink.data.retrofit.RetrofitInterface
import retrofit2.HttpException

class ProjectListRepository (
    private val projectRef: CollectionReference,
    private val mahasiswaRef: CollectionReference,
    @ApplicationContext private val context: Context
): ProjectListInterface {


    override suspend fun getProjectWithMahasiswa(nim: String) = try {
        val response = RetrofitInterface.api.getProjectWithMahasiswa(NimRequest(nim))

        if (response.success){
            Response.Success(response.data)
        } else {
            Response.Failure(Exception(response.message))
        }
    } catch (e: HttpException){
        Response.Failure(errorToErrorMessage(e))
    }

    override suspend fun getProjectByNim(nim: String) = try {
        val response = RetrofitInterface.api.getProjectsByNim(NimRequest(nim))

        if (response.success){
            Response.Success(response.data)
        } else {
            Response.Failure(Exception(response.message))
        }
    } catch (e: HttpException) {
        Response.Failure(errorToErrorMessage(e))
    }

    override suspend fun getRequestByNim(nim: String) = try {
        val response = RetrofitInterface.api.getRequestsByNim(NimRequest(nim))

        if (response.success){
            Response.Success(response.data)
        } else {
            Response.Failure(Exception(response.message))
        }
    } catch (e: HttpException) {
        Response.Failure(errorToErrorMessage(e))
    }

    override suspend fun likeProject(projectId: String, nim: String) = try {
        val response = RetrofitInterface.api.likeProject(LikeRequest(projectId, nim))

        if (response.success){
            Response.Success(response.message)
        } else {
            Response.Failure(Exception(response.message))
        }
    } catch (e: HttpException){
        Response.Failure(errorToErrorMessage(e))
    }

    override suspend fun getCommentByProjectId(projectId: String) = try {
        val response = RetrofitInterface.api.getCommentByProjectId(ProjectIdRequest(projectId))

        if (response.success){
            Response.Success(response.data)
        } else {
            Response.Failure(Exception(response.message))
        }
    } catch (e: HttpException){
        Response.Failure(errorToErrorMessage(e))
    }

    override suspend fun getCommentReplyById(commentId: String) = try {
        val response = RetrofitInterface.api.getCommentReplyById(CommentIdRequest(commentId))

        if (response.success){
            Response.Success(response.data)
        } else {
            Response.Failure(Exception(response.message))
        }
    } catch (e: HttpException){
        Response.Failure(errorToErrorMessage(e))
    }


    override suspend fun addRequest(projectId: String, nim: String) = try {
        val process = projectRef.document(projectId)

        process.update("requests", FieldValue.arrayUnion(nim))
            .await()

        val process2 = mahasiswaRef.whereEqualTo("nim", nim).get().await().documents.first()
        process2.reference.update("requests", FieldValue.arrayUnion(projectId)).await()

        Response.Success(projectId)
    } catch (e: Exception) {
        Response.Failure(e)
    }

    override suspend fun addAccept(projectId: String, nim: String) = try {
        val process = projectRef.document(projectId)

        process.update("accept", FieldValue.arrayUnion(nim))
            .await()

        val process2 = mahasiswaRef.whereEqualTo("nim", nim).get().await().documents.first()
        process2.reference.update("accept", FieldValue.arrayUnion(projectId)).await()

        //

        val process3 = projectRef.document(projectId)

        process3.update("requests", FieldValue.arrayRemove(nim))
            .await()

        val process4 = mahasiswaRef.whereEqualTo("nim", nim).get().await().documents.first()
        process4.reference.update("requests", FieldValue.arrayRemove(projectId)).await()


        Response.Success(projectId)
    } catch (e: Exception) {
        Response.Failure(e)
    }
    override suspend fun deleteAccept(projectId: String, nim: String) = try {
        val process = projectRef.document(projectId)

        process.update("accept", FieldValue.arrayRemove(nim))
            .await()

        val process2 = mahasiswaRef.whereEqualTo("nim", nim).get().await().documents.first()
        process2.reference.update("accept", FieldValue.arrayRemove(projectId)).await()

        //

        val process3 = projectRef.document(projectId)

        process3.update("requests", FieldValue.arrayUnion(nim))
            .await()

        val process4 = mahasiswaRef.whereEqualTo("nim", nim).get().await().documents.first()
        process4.reference.update("requests", FieldValue.arrayUnion(projectId)).await()

        Response.Success(projectId)
    } catch (e: Exception) {
        Response.Failure(e)
    }


    override suspend fun deleteRequest(projectId: String, nim: String) = try {
        val process3 = projectRef.document(projectId)

        process3.update("requests", FieldValue.arrayRemove(nim))
            .await()

        val process4 = mahasiswaRef.whereEqualTo("nim", nim).get().await().documents.first()
        process4.reference.update("requests", FieldValue.arrayRemove(projectId)).await()

        Response.Success(projectId)
    } catch (e: Exception) {
        Response.Failure(e)
    }


    override suspend fun addProject(projectPart: RequestBody, imageMultipart: MultipartBody.Part?) = try {
        val response = RetrofitInterface.api.addProject(projectPart, imageMultipart)

        if (response.success){
            Response.Success(response.message)
        } else {
            Response.Failure(Exception(response.message))
        }
    } catch (e: HttpException){
        Response.Failure(errorToErrorMessage(e))
    }

    override suspend fun editProject(id: RequestBody, projectPart: RequestBody, imageMultipart: MultipartBody.Part?) = try {
        val response = RetrofitInterface.api.editProject(projectPart, imageMultipart, id)

        if (response.success){
            Response.Success(response.message)
        } else {
            Response.Failure(Exception(response.message))
        }
    } catch (e: HttpException){
        Response.Failure(errorToErrorMessage(e))
    }

    override suspend fun getProjectById(id: String) = try {
        val response = RetrofitInterface.api.getProjectById(ProjectIdRequest(id))

        if (response.success){
            Response.Success(response.data)
        } else {
            Response.Failure(Exception(response.message))
        }
    } catch (e: HttpException){
        Response.Failure(errorToErrorMessage(e))
    }

    override suspend fun updateProject(projectId: String, project: Project) = try {
        val projectDocument = projectRef.document(projectId)

        projectDocument.update(
            mapOf(
                Project.TITLE to project.title,
                Project.DESC to project.desc,
                Project.TAG to project.tag,
            )
        ).await()

        Response.Success("Edit successful.")
    } catch (e: Exception){
        Response.Failure(Exception("No project found for the given NIM."))
    }


    override suspend fun deleteProject(id: String) = try {
        val response = RetrofitInterface.api.deleteProject(ProjectIdRequest(id))

        if (response.success){
            Response.Success(response.message)
        } else {
            Response.Failure(Exception(response.message))
        }
    } catch (e: HttpException){
        Response.Failure(errorToErrorMessage(e))
    }

}

