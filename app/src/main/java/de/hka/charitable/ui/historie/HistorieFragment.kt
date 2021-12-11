package de.hka.charitable.ui.historie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import de.hka.charitable.databinding.FragmentHistorieBinding

class HistorieFragment : Fragment() {

    private lateinit var historieViewModel: HistorieViewModel
    private var _binding: FragmentHistorieBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historieViewModel =
            ViewModelProvider(this).get(HistorieViewModel::class.java)

        _binding = FragmentHistorieBinding.inflate(inflater, container, false)
        val root: View = binding.root

 /*       val textView: TextView = binding.textHistorie
        historieViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}