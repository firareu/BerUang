package com.capstone.beruang.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.capstone.beruang.R
import com.capstone.beruang.data.repository.PreferenceManager
import com.capstone.beruang.data.repository.UserRepository
import com.capstone.beruang.data.retrofit.ApiConfig
import com.capstone.beruang.data.retrofit.ApiService
import com.capstone.beruang.databinding.FragmentProfileBinding
import com.capstone.beruang.ui.login.LoginActivity
import com.capstone.beruang.ui.management.allocation.edit.EditActivity
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val userRepository: UserRepository by lazy {
        UserRepository(PreferenceManager.getInstance(requireContext()))
    }
    private lateinit var userId: String
    private lateinit var apiService: ApiService

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        userId = userRepository.getUserId().toString()
        Log.d("HomeFragment", userId)

        apiService = ApiConfig.getApiService()
        lifecycleScope.launch {
            try {
                val response = apiService.getUserData(userId)
                val user = response.user
                val tvUsername = view?.findViewById<TextView>(R.id.name)
                val tvEmail = view?.findViewById<TextView>(R.id.email)
                tvUsername!!.text = user.name
                tvEmail!!.text = user.email
                context?.let {
                    Glide.with(it)
                        .load(user.profilePicture)
                        .into(binding.imgItemPhoto)
                }
            } catch (e: Exception) {
                Log.e("UserData", "Failed to fetch user data: ${e.message}")
            }
        }

        /*val textView: TextView = binding.textHome
        profileViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        binding.logOutButton.setOnClickListener {
            showAlertDialog()
        }
        setupAction()
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
                userRepository.logoutUser()
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
            }
            .show()
    }

    private fun setupAction() {
        binding.btnEdit.setOnClickListener {
            val intentEdit = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intentEdit)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}