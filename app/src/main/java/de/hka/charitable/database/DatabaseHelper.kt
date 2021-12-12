package de.hka.charitable.database

interface DatabaseHelper {

    suspend fun getMeals(): List<Meal>

    suspend fun insertMeal(meal: Meal)
}