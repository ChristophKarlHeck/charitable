package de.hka.charitable.ui.meine_angebote

import android.content.Intent
import android.os.Bundle
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

class MeineAngeboteFragment : Fragment() {

    private lateinit var meineAngeboteViewModel: MeineAngeboteViewModel
    private var _binding: FragmentMeineAngeboteBinding? = null


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
        val list = this.getFoodListItems();
        val listOwnMeals = view.findViewById<ListView>(R.id.listMyMeals)
        val openCreateMealActivityButton =
            view.findViewById<FloatingActionButton>(R.id.addNewMealButton)
        // wait for the Database Request
        Thread.sleep(100);
        val adapter = ListAdapter(
            view.context,
            list
        )
        listOwnMeals.adapter = adapter
        listOwnMeals.isClickable = true;
        listOwnMeals.setOnItemClickListener { parent, view, position, id ->
         //TODO Load View of Item on click
        }
        openCreateMealActivityButton.setOnClickListener {
            val intent = Intent(view.context, CreateMealActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getFoodListItems(): ArrayList<Meal> {
        var list = ArrayList<Meal>();
        GlobalScope.launch {
            val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(requireContext()))
            dbHelper.getMeals().forEach {
                list.add(it);
            };
        }
        return list;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}