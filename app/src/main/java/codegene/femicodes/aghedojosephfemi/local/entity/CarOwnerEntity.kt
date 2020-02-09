package codegene.femicodes.aghedojosephfemi.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "car_owners")
data class CarOwnerEntity(
    @PrimaryKey
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("first_name")
    val firstName: String,
    @field:SerializedName("last_name")
    val lastName: String,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("country")
    val country: String,
    @field:SerializedName("car_model")
    val carModel: String,
    @field:SerializedName("car_model_year")
    val carModelYear: String,
    @field:SerializedName("car_color")
    val carColor: String,
    @field:SerializedName("gender")
    val gender: String,
    @field:SerializedName("job_title")
    val jobTitle: String,
    @field:SerializedName("bio")
    val bio: String
) {
    override fun toString() = gender
}