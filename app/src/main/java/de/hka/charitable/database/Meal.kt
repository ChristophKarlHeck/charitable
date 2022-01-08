package de.hka.charitable.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class Meal(
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "price") val price: String?,
    @ColumnInfo(name = "seats") val seats: String?,
    @ColumnInfo(name = "ingredients") val ingredients: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "charitable_organization") val charitableOrganization: String?,
    @ColumnInfo(name= "image_path") val imagePath: String?,
    @ColumnInfo(name= "rating") var rating: String?,
){
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}