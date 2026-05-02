package com.example.foodies.data.local_source

import androidx.room.TypeConverter
import com.example.foodies.domain.entities.Parameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromParameters(parameters: Parameters): String = gson.toJson(parameters)

    @TypeConverter
    fun toParameters(value: String): Parameters = gson.fromJson(value, Parameters::class.java)

    @TypeConverter
    fun fromStringList(value: List<String>): String = gson.toJson(value)

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }
}