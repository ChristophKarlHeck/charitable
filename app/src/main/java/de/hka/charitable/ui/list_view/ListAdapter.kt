package de.hka.charitable.ui.list_view

import android.content.Context
import android.view.LayoutInflater.*
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList;
import de.hka.charitable.R;
import de.hka.charitable.database.Meal;

class ListAdapter(context: Context, mealArrayList: ArrayList<Meal>) :
    ArrayAdapter<Meal>(context, R.layout.list_item, R.id.empty, mealArrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val meal = getItem(position)
        var view = convertView;
        if (convertView == null)
            view = from(context).inflate(R.layout.list_item, parent, false);
        val previewImage = view?.findViewById<ImageView>(R.id.previewImage);
        val foodName = view?.findViewById<TextView>(R.id.foodName);
        val description = view?.findViewById<TextView>(R.id.description);
        val price = view?.findViewById<TextView>(R.id.price);
        foodName?.text = meal?.name;
        description?.text = meal?.description;
        price?.text = meal?.price;
        meal?.imagePath?.let { previewImage?.setImageResource(it.toInt()) }
        return super.getView(position, view, parent);
    }

}