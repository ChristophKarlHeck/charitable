package de.hka.charitable

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import de.hka.charitable.database.AppDatabase
import de.hka.charitable.database.DatabaseBuilder
import de.hka.charitable.database.DatabaseHelperImpl
import de.hka.charitable.database.Meal
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class CreateMealActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_meal)

        val imageView = findViewById<ImageView>(R.id.imageView2)
        imageView.setImageResource(R.drawable.ic_create_meal_spaghetti)

        // View Date and Change Date Button
        val changeDateButton = findViewById<Button>(R.id.button)
        val showDate = findViewById<TextView>(R.id.textView2)

        val sdf = SimpleDateFormat("dd.MM.yyyy")
        showDate.text = sdf.format(Date())

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        changeDateButton.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                    view, mYear, mMonth , mDay -> showDate.text=mDay.toString()+"."+(mMonth+1)+"."+mYear},year, month, day)
            dpd.show()
        }

        // Get value from Layout
        val editTextMeal = findViewById<EditText>(R.id.editTextGericht)
        val editTextPrice = findViewById<EditText>(R.id.editTextPreis)
        val editTextSeats = findViewById<EditText>(R.id.editTextPlaetze)
        val editTextIngredients = findViewById<EditText>(R.id.editTextZutaten)
        val editTextDescription = findViewById<EditText>(R.id.editTextBeschreibung)
        val editTextCharitableOrganization = findViewById<EditText>(R.id.editTextSpendenorganisation)
        val buttonCreateMeal = findViewById<Button>(R.id.buttonAnlegen)



        // Add Meal to Database
            buttonCreateMeal.setOnClickListener{
                println("Hallo")
                GlobalScope.launch{
                    val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
                    dbHelper.insertMeal(
                        Meal(5,
                            editTextMeal.text.toString(),
                            editTextPrice.text.toString(),
                            editTextSeats.text.toString(),
                            editTextIngredients.text.toString(),
                            editTextDescription.text.toString(),
                            editTextCharitableOrganization.text.toString(),
                        )
                    )
                println(dbHelper.getMeals())
            }

        }



    }
}

