package de.hka.charitable.database

import android.util.Log

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getMeals(): List<Meal> = appDatabase.mealDao().getAll()

    suspend fun getMeal(uid: Int): Meal {
       var list = appDatabase.mealDao().getAll().forEach{
           if(it.uid==uid)
           {
               return it;
           }
       }
        return Meal("","","","","","","","");
    }

    suspend fun updateRatingMeal(uid: Int, rating: String){
        var meal = getMeal(uid);
        Log.d(meal.toString(), meal.toString())
        if(meal.name!="")
        {
            deleteMeal(meal);
            meal.rating = rating;
            insertMeal(meal);
        }
    }

    suspend fun deleteMeal(meal: Meal) = appDatabase.mealDao().deleteMeal(meal);

    override suspend fun insertMeal(meal: Meal) = appDatabase.mealDao().insertMeal(meal)
}