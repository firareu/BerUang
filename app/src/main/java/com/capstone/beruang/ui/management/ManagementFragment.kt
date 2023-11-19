package com.capstone.beruang.ui.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.beruang.databinding.FragmentManagementBinding

class ManagementFragment : Fragment() {

    private var _binding: FragmentManagementBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val managementViewModel =
            ViewModelProvider(this).get(ManagementViewModel::class.java)

        _binding = FragmentManagementBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textManagement
        managementViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}