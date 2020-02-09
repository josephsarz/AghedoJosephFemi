package codegene.femicodes.aghedojosephfemi.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "filters")
data class FilterEntity(

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("start_year")
    val startYear: Int? = null,

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("countries")
    val countries: List<String?>? = null,

    @field:SerializedName("end_year")
    val endYear: Int? = null,

    @field:SerializedName("colors")
    val colors: List<String?>? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createStringArrayList(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(gender)
        parcel.writeValue(startYear)
        parcel.writeValue(id)
        parcel.writeStringList(countries)
        parcel.writeValue(endYear)
        parcel.writeStringList(colors)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilterEntity> {
        override fun createFromParcel(parcel: Parcel): FilterEntity {
            return FilterEntity(parcel)
        }

        override fun newArray(size: Int): Array<FilterEntity?> {
            return arrayOfNulls(size)
        }
    }
}