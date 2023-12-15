package com.capstone.beruang.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.beruang.R
import com.capstone.beruang.databinding.FragmentHomeBinding
import com.capstone.beruang.databinding.FragmentProfileBinding
import com.capstone.beruang.ui.home.HomeViewModel
import com.capstone.beruang.ui.login.LoginActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root


        /*val textView: TextView = binding.textHome
        profileViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        binding.logOutButton.setOnClickListener {
            showAlertDialog()
        }
        return root
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val alert = builder.create()
        builder
            .setTitle(getString(R.string.logout))
            .setMessage(getString(R.string.logout_description))
            .setPositiveButton(getString(R.string.logout_cancel)) { _, _ ->
                alert.cancel()
            }
            .setNegativeButton(getString(R.string.yes)) { _, _ ->
                Firebase.auth.signOut()
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}