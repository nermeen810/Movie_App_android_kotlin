package com.nermeen.movie_app.data.dataSource.localDataSource

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson

class AppTypeConverter {

        @TypeConverter
        fun StringToListOFInt(data: String?): List<Int>? {
            return Gson().fromJson(data, Array<Int>::class.java).toList()
        }

        @TypeConverter
        fun ListOFIntToString(data: List<Int>?): String? {
            return Gson().toJson(data)
        }

}