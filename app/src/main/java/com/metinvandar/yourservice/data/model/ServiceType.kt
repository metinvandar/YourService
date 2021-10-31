package com.metinvandar.yourservice.data.model

import com.google.gson.annotations.SerializedName

enum class ServiceType {
    @SerializedName("Tadilat")
    MODIFICATION,
    @SerializedName("Temizlik")
    CLEANING,
    @SerializedName("Nakliyat")
    TRANSPORTATION,
    @SerializedName("Tamir")
    REPAIR,
    @SerializedName("Özel Ders")
    PRIVATE_LESSON,
    @SerializedName("Düğün Organizasyon")
    WEDDING_ORGANIZATION,
    @SerializedName("Sağlık Eğitimi")
    HEALTH_EDUCATION
}