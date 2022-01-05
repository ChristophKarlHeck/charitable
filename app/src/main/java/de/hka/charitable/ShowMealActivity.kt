package de.hka.charitable

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShowMealActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_meal)
        var intent = this.intent;
        if (intent != null) {
            findViewById<TextView>(R.id.foodName).text = intent.getStringExtra("name").toString()
            findViewById<TextView>(R.id.price).text = intent.getStringExtra("price")
            findViewById<TextView>(R.id.seats).text = intent.getStringExtra("seats")
            findViewById<TextView>(R.id.ingredients).text = intent.getStringExtra("ingredients")
            findViewById<TextView>(R.id.description).text = intent.getStringExtra("description")
            findViewById<TextView>(R.id.charitableOrganization).text = intent.getStringExtra("charitable_organization")

        }

    }
}

