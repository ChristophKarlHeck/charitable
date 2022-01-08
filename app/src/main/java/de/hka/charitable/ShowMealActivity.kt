package de.hka.charitable

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.hka.charitable.database.DatabaseBuilder
import de.hka.charitable.database.DatabaseHelperImpl
import de.hka.charitable.ui.meine_angebote.MeineAngeboteFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

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
            findViewById<EditText>(R.id.bewertung).setText(intent.getStringExtra("rating"))
            intent.getStringExtra("imagePath")?.let {
                findViewById<ImageView>(R.id.previewImage).setImageResource(
                    it.toInt())
            };

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(findViewById<EditText>(R.id.bewertung).text.isNotEmpty())
        {
            val id = intent.getIntExtra("id",0);
            val dbhelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            GlobalScope.launch {
                if (id != 0) {
                    dbhelper.updateRatingMeal(
                        id,
                        findViewById<EditText>(R.id.bewertung).text.toString()
                    )
                }
            }
            Thread.sleep(100)
            MeineAngeboteFragment.refreshFunction.invoke();
        }
    }
}

