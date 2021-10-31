package com.metinvandar.yourservice.domain.models

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.metinvandar.yourservice.R

enum class ServiceItemType(val value: String) {

    MODIFICATION("Tadilat") {
        override fun image(context: Context): Drawable? {
            return ContextCompat.getDrawable(context, R.drawable.ic_modification)
        }
    },
    CLEANING("Temizlik") {
        override fun image(context: Context): Drawable? {
            return ContextCompat.getDrawable(context, R.drawable.ic_cleaning)
        }
    },
    TRANSPORTATION("Nakliyat") {
        override fun image(context: Context): Drawable? {
            return ContextCompat.getDrawable(context, R.drawable.ic_transportation)
        }
    },
    REPAIR("Tamir") {
        override fun image(context: Context): Drawable? {
            return ContextCompat.getDrawable(context, R.drawable.ic_repair)
        }
    },
    PRIVATE_LESSON("Özel Ders") {
        override fun image(context: Context): Drawable? {
            return ContextCompat.getDrawable(context, R.drawable.ic_private_lesson)
        }
    },
    WEDDING_ORGANIZATION("Düğün Organizasyon") {
        override fun image(context: Context): Drawable? {
            return ContextCompat.getDrawable(context, R.drawable.ic_wedding)
        }
    },
    HEALTH_EDUCATION("Sağlık Eğitimi") {
        override fun image(context: Context): Drawable? {
            return ContextCompat.getDrawable(context, R.drawable.ic_health)
        }
    },
    OTHER("Diğer") {
        override fun image(context: Context): Drawable? {
            return ContextCompat.getDrawable(context, R.drawable.ic_other)
        }
    };

    companion object {
        fun fromValue(value: String): ServiceItemType {
            return values().find { it.value == value } ?: OTHER
        }
    }

    abstract fun image(context: Context): Drawable?
}
