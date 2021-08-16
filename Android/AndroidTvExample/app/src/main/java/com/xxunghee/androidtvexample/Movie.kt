package com.xxunghee.androidtvexample

import java.io.Serializable

/**
 * Movie class represents video entity with title, description, image thumbs and video url.
 *
 * Modified by xxunghee on 2021-08-16
 */
data class Movie(
    var id: Long = 0,
    var category: Int = 0,
    var title: String? = null,
    var description: String? = null,
    var backgroundImageUrl: String? = null,
    var cardImageUrl: String? = null,
    var videoUrl: String? = null,
    var studio: String? = null
) : Serializable {

    override fun toString(): String {
        return "Movie{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", backgroundImageUrl='" + backgroundImageUrl + '\'' +
                ", cardImageUrl='" + cardImageUrl + '\'' +
                '}'
    }

    companion object {
        internal const val serialVersionUID = 727566175075960653L
    }
}