package de.hka.charitable.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MealDao {
    @Query("SELECT * FROM meal")
    fun getAll(): List<Meal>

    @Query("SELECT * FROM meal WHERE uid IN (:mealIds)")
    fun loadAllByIds(mealIds: IntArray): List<Meal>

    @Query("SELECT * FROM meal WHERE name LIKE :name ")
    fun findByName(name: String): List<Meal>

    @Insert
    fun insertMeal(meal: Meal)

    @Delete
    fun deleteMeal(meal: Meal)
}