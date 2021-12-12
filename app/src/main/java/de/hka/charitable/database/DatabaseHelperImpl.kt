package de.hka.charitable.database

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getMeals(): List<Meal> = appDatabase.mealDao().getAll()

    override suspend fun insertMeal(meal: Meal) = appDatabase.mealDao().insertMeal(meal)
}