package com.example.combinedrecycleview.model

import com.google.gson.annotations.SerializedName

data class JobSkill(
    @SerializedName("responseCode") val responseCode: String,
    @SerializedName("responseDescription") val responseDescription: String,
    @SerializedName("responseTime") val responseTime: String,
    @SerializedName("content") val content: Content,
) {
    data class Content(
        @SerializedName("errorContent") val errorContent: Boolean,
        @SerializedName("skills") val skills: List<Skill>,
        @SerializedName("popularSkills") val popularSkills: List<Skill>,
    )

    data class Skill(
        @SerializedName("id") val id: Long,
        @SerializedName("title") val title: String,
        @SerializedName("knTitle") val knTitle: String,
        @SerializedName("hiTitle") val hiTitle: String,
        @SerializedName("popular") val popular: Boolean,
        var isSelected: Boolean = false,
        @SerializedName("entryDateTime") val entryDateTime: String? = null,
        @SerializedName("updateDateTime") val updateDateTime: String? = null,
    )
}

