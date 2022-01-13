package de.hka.charitable.ui.meine_angebote

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.hka.charitable.CreateMealActivity
import de.hka.charitable.R
import de.hka.charitable.ShowMealActivity
import de.hka.charitable.database.DatabaseBuilder
import de.hka.charitable.database.DatabaseHelperImpl
import de.hka.charitable.database.Meal
import de.hka.charitable.databinding.ActivityMainBinding
import de.hka.charitable.databinding.FragmentMeineAngeboteBinding
import de.hka.charitable.ui.list_view.ListAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class MeineAngeboteFragment : Fragment() {

    private lateinit var meineAngeboteViewModel: MeineAngeboteViewModel
    private var _binding: FragmentMeineAngeboteBinding? = null
    var adapter: ListAdapter? = null
    var currentView: View? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        meineAngeboteViewModel =
            ViewModelProvider(this).get(MeineAngeboteViewModel::class.java)
        _binding = FragmentMeineAngeboteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshFunction = { this.refresh() }
        currentView = view
        val list = this.getFoodListItems()
        val listOwnMeals = view.findViewById<ListView>(R.id.listMyMeals)
        val openCreateMealActivityButton =
            view.findViewById<FloatingActionButton>(R.id.addNewMealButton)
        // wait for the Database Request
        Thread.sleep(100)
        adapter = ListAdapter(
            view.context,
            list
        )
        listOwnMeals.adapter = adapter
        listOwnMeals.isClickable = true
        listOwnMeals.setOnItemClickListener { parent, view, position, id ->
            run {
                val meal = (listOwnMeals.getItemAtPosition(position) as Meal)
                val intent = Intent(view.context, ShowMealActivity::class.java)
                // Set data for the intent, so it can be used somewhere else
                intent.putExtra("name", meal.name)
                intent.putExtra("price", meal.price)
                intent.putExtra("seats", meal.seats)
                intent.putExtra("ingredients", meal.ingredients)
                intent.putExtra("description", meal.description)
                intent.putExtra("charitable_organization", meal.charitableOrganization)
                intent.putExtra("imagePath", meal.imagePath)
                intent.putExtra("rating", meal.rating)
                intent.putExtra("id", meal.uid)
                startActivity(intent)
            }
        }
        openCreateMealActivityButton.setOnClickListener {
            val intent = Intent(view.context, CreateMealActivity::class.java)
            startActivity(intent)
        }
        // connect buttons with the corresponding sorting function
        val sortByNameButton =
            view.findViewById<Button>(R.id.sortByName)
        val sortByPriceButton =
            view.findViewById<Button>(R.id.sortByPrice)
        sortByNameButton.setOnClickListener {
            this.refreshAndSortByName()
        }
        sortByPriceButton.setOnClickListener {
            this.refreshAndSortByPrice()
        }


    }

    // refresh the meal listView
    fun refresh() {
        Thread.sleep(50)
        val list = getFoodListItems()
        val listOwnMeals = currentView?.findViewById<ListView>(R.id.listMyMeals)
        adapter = currentView?.context?.let {
            ListAdapter(
                it,
                list
            )
        }
        listOwnMeals?.adapter = adapter
    }

    // return the meals for the listview
    private fun getFoodListItems(): ArrayList<Meal> {
        val list = ArrayList<Meal>()
        GlobalScope.launch {
            val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(requireContext()))
            dbHelper.getMeals().forEach {
                list.add(it)
            }
        }
        Thread.sleep(100)
        return list
    }

    // refresh the meal listView
    fun refreshAndSortByName() {
        Thread.sleep(50)
        val list = getFoodListItemsAndSortByName()
        val listOwnMeals = currentView?.findViewById<ListView>(R.id.listMyMeals)
        Thread.sleep(100)
        adapter = currentView?.context?.let {
            ListAdapter(
                it,
                list
            )
        }
        listOwnMeals?.adapter = adapter
    }

    // return the meals for the listview sorted by name
    private fun getFoodListItemsAndSortByName(): ArrayList<Meal> {
        var list = ArrayList<Meal>()
        GlobalScope.launch {
            val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(requireContext()))
            dbHelper.getMeals().forEach {
                list.add(it)
            }
            list = ArrayList<Meal>(list.sortedWith(compareBy { it.name }))
        }
        Thread.sleep(100)
        return list
    }

    // refresh the meal listView
    fun refreshAndSortByPrice() {
        Thread.sleep(50)
        val list = getFoodListItemsAndSortByPrice()
        val listOwnMeals = currentView?.findViewById<ListView>(R.id.listMyMeals)
        Thread.sleep(100)
        adapter = currentView?.context?.let {
            ListAdapter(
                it,
                list
            )
        }
        listOwnMeals?.adapter = adapter
    }

    // return the meals for the listview sorted by price
    private fun getFoodListItemsAndSortByPrice(): ArrayList<Meal> {
        var list = ArrayList<Meal>()
        GlobalScope.launch {
            val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(requireContext()))
            dbHelper.getMeals().forEach {
                list.add(it)
            }
            list = ArrayList<Meal>(list.sortedWith(compareBy { it.price }))
        }
        Thread.sleep(100)
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        var refreshFunction = {}
    }
}