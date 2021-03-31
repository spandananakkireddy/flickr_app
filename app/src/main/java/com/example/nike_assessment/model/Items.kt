package com.example.nike_assessment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 *Class for the image items
 */
class Items : Serializable {

    @SerializedName("items")
    val items: List<Image>? = null
    override fun toString(): String {
        return "Items{" +
                "items=" + items +
                '}'
    }
}