package org.d3if3121.tellink.data.retrofit


import org.d3if3121.tellink.data.model.Response
import org.d3if3121.tellink.data.repository.interfaces.ProjectListByNimResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/project/{nim}")
    fun getProjectsByNim(
        @Path("nim") nim : String
    ): Response<ProjectListByNimResponse>
}