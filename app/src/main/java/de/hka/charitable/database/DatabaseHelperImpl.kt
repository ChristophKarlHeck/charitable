package de.hka.charitable.database

import android.util.Log

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getMeals(): List<Meal> = appDatabase.mealDao().getAll()

    // return a meal by id from the database
    fun getMeal(uid: Int): Meal {
       appDatabase.mealDao().getAll().forEach{
           if(it.uid==uid)
           {
               return it
           }
       }
        return Meal("","","","","","","","")
    }

    // add rating to a meal from the database, which is found by the id
    suspend fun updateRatingMeal(uid: Int, rating: String){
        val meal = getMeal(uid)
        Log.d(meal.toString(), meal.toString())
        if(meal.name!="")
        {
            deleteMeal(meal)
            meal.rating = rating
            insertMeal(meal)
        }
    }

    // delete a meal from the database
    fun deleteMeal(meal: Meal) = appDatabase.mealDao().deleteMeal(meal)

    // add a meal to the database
    override suspend fun insertMeal(meal: Meal) = appDatabase.mealDao().insertMeal(meal)
}