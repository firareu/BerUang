package com.capstone.beruang.ui.article

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.beruang.R
import com.capstone.beruang.adapter.ArticleListAdapter
import com.capstone.beruang.adapter.RowAdapter
import com.capstone.beruang.data.dataclass.ArticleRepository
import com.capstone.beruang.databinding.FragmentArticleBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = binding.root

        // Inside onCreateView or onCreate method in your fragment or activity
        val repository = ArticleRepository()
        val viewModelFactory = ArticleViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ArticleViewModel::class.java]
        setupAdapters()
        val chipGroup = view.findViewById<ChipGroup>(R.id.chipGroup)

        // Example: Create and add 5 Chip buttons
        val chipTexts = listOf("Chip 1", "Chip 2", "Chip 3", "Chip 4", "Chip 5", "asssssssassssssssaaaaaaaa", "dasda")

        for (chipText in chipTexts) {
            val chip = Chip(requireContext())
            chip.text = chipText
            chip.isClickable = true
            chip.isCheckable = true

            val paddingStartEnd = resources.getDimensionPixelSize(R.dimen.chipStartEnd)
            val paddingTopBottom = resources.getDimensionPixelSize(R.dimen.chipTopBottom)
            chip.setPadding(paddingStartEnd, paddingTopBottom, paddingStartEnd, paddingTopBottom)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            val marginStart = resources.getDimensionPixelSize(R.dimen.zoro)
            val marginStartEnd = resources.getDimensionPixelSize(R.dimen.chipStartEnd)
            val marginTopBottom = resources.getDimensionPixelSize(R.dimen.chipTopBottom)


            layoutParams.setMargins(marginStart, marginTopBottom, marginStartEnd, marginTopBottom)
            chip.layoutParams = layoutParams

            chipGroup.addView(chip)

            chip.setOnCheckedChangeListener { buttonView, isChecked ->
                // Handle chip selection/deselection here
                Log.d("ChipSelection", "Selected chip text: ${chip.text}, isChecked: $isChecked")
            }
        }

        return view
    }

    private fun setupAdapters() {
        // Setup RowAdapter
        viewModel.articleList.observe(viewLifecycleOwner) { articles ->
            // Update the RecyclerView adapter with the new list of articles
            val adapter = RowAdapter(articles)
            binding.rvRow.adapter = adapter
            binding.rvRow.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
//        val dataArticle = ArticleData.articleList
//        val adapter = RowAdapter(dataArticle)
//        binding.rvRow.adapter = adapter
//        binding.rvRow.layoutManager =
//            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRow.setHasFixedSize(true)

        // Setup TagAdapter
//        val dataTag = TagData.tagList
//        val tagAdapter = TagAdapter(requireContext(), dataTag)
//        binding.rvTag.adapter = tagAdapter
//        binding.rvTag.layoutManager = GridLayoutManager(requireContext(), 2)
//
//        tagAdapter.setOnItemClickListener {
//            val intent = Intent(requireContext(), ListArticleActivity::class.java)
//            startActivity(intent)
//        }
        viewModel.articleList.observe(viewLifecycleOwner) { articles ->
            // Update the RecyclerView adapter with the new list of articles
            val adapter = ArticleListAdapter(articles)
            binding.rvArticle.adapter = adapter
            binding.rvArticle.layoutManager =
                LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

