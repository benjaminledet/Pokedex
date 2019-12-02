package com.benjaminledet.pokedex.data.remote.response

import com.google.gson.annotations.SerializedName

data class AbilityResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("effect_entries")
    val effectEntries: List<EffectEntry>
)

data class EffectEntry(

    @SerializedName("effect")
    val effect: String

)