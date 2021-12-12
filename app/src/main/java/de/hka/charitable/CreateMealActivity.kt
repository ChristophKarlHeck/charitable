package de.hka.charitable

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
    }
}
