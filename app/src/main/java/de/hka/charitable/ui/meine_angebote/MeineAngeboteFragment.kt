package de.hka.charitable.ui.meine_angebote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import de.hka.charitable.R
import de.hka.charitable.databinding.FragmentMeineAngeboteBinding

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
                val listOwnMeals = view.findViewById<ListView>(R.id.listMyMeals)
                val adapter = ArrayAdapter<String>(view.context, android.R.layout.simple_list_item_1, this.getFoodListItems())
                listOwnMeals.adapter = adapter

        }

        private fun getFoodListItems(): Array<String> {
                val list = arrayOf(
                        "Cristiano Ronaldo",
                        "Messi",
                        "Neymar",
                        "Isco",
                        "Hazard",
                        "Mbappe",
                        "Hazard",
                        "Ziyech",
                        "Suarez"
                )
                return list;
        }


        override fun onDestroyView() {
                super.onDestroyView()
                _binding = null
                }
        }