package com.example.combinedrecycleview.model

import com.google.gson.annotations.SerializedName

data class JobsResponse(
    @SerializedName("content") val content: Content,
    @SerializedName("empty") val empty: Boolean,
    @SerializedName("first") val first: Boolean,
    @SerializedName("last") val last: Boolean,
    @SerializedName("responseCode") val responseCode: String,
    @SerializedName("responseDescription") val responseDescription: String,
    @SerializedName("responseTime") val responseTime: String,
)

data class Content(
    @SerializedName("empty") val empty: Boolean,
    @SerializedName("errorContent") val errorContent: Boolean,
    @SerializedName("first") val first: Boolean,
    @SerializedName("jobs") val jobs: List<Job>,
    @SerializedName("last") val last: Boolean,
    @SerializedName("properties") val properties: Properties,
    @SerializedName("number") val number: Int,
    @SerializedName("numberOfElements") val numberOfElements: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("totalElements") val totalElements: Int,
)

data class Job(
    @SerializedName("company") val company: String,
    @SerializedName("companyMobile") val companyMobile: String,
    @SerializedName("contact") val contact: String,
    @SerializedName("description") val description: String,
    @SerializedName("employer") val employer: String,
    @SerializedName("experience") val experience: String,
    @SerializedName("id") val id: Int,
    @SerializedName("jobIconURL") val jobIconURL: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("locationId") val locationId: Int?,
    @SerializedName("salary") val salary: String,
    @SerializedName("sublocation") val sublocation: String?,
    @SerializedName("sublocationLatLng") val sublocationLatLng: String?,
    @SerializedName("sublocationShort") val sublocationShort: String?,
    @SerializedName("title") val title: String,
    @SerializedName("skills") val skills: List<JobSkill.Skill>,
    @SerializedName("enSkills") val enSkills: String?,
    @SerializedName("hotJobTitle") val hotJobTitle: String,
    @SerializedName("applied") val appliedJob: Boolean,
    @SerializedName("hotJob") val hotJob: Boolean,
    @SerializedName("openings") val openings: Integer?,
    @SerializedName("locationLatLng") val locationLatLng: String?,
    @SerializedName("accomodation") val accomodation: String?,
    @SerializedName("food") val food: String?,
    @SerializedName("leavePolicy") val leavePolicy: String?,
    @SerializedName("jobsNearMe") val jobsNearMe: String?,
)

data class Properties(
    @SerializedName("availaleJobsNearMe") val availaleJobsNearMe: Boolean
)
