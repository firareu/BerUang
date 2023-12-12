package com.capstone.beruang.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.widget.Toolbar // Mengubah impor Toolbar
import com.capstone.beruang.R
import com.capstone.beruang.databinding.FragmentHomeBinding
import com.capstone.beruang.ui.login.LoginActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.visibility = View.GONE*/

        (activity as AppCompatActivity?)?.supportActionBar?.hide()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
