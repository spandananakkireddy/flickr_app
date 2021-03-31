package com.example.nike_assessment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Image : Serializable {

    @SerializedName("title")
    val title: String? = null

    @SerializedName("media")
    val media: Media? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("author")
    val author: String? = null
    override fun toString(): String {
        return "Image{" +
                "title='" + title + '\'' +
                ", media=" + media +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                '}'
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}