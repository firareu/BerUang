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
import com.capstone.beruang.data.ArticleData.articleList
import com.capstone.beruang.data.ArticleRepository
import com.capstone.beruang.databinding.FragmentArticleBinding
import com.google.android.material.chip.Chip

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupArticleAdapter()
        setupChipGroup()
    }

    private fun setupViewModel() {
        val repository = ArticleRepository()
        val viewModelFactory = ArticleViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ArticleViewModel::class.java]
    }

//    private fun setupAdapters() {
////        setupRowAdapter()
//        setupArticleAdapter()
//    }

//    private fun setupRowAdapter() {
//        viewModel.articleList.observe(viewLifecycleOwner) { articles ->
//            binding.rvRow.adapter = RowAdapter(articles)
//            binding.rvArticle.adapter = ArticleListAdapter(articles)
//            binding.rvRow.layoutManager =
//                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        }
//    }

    private fun setupArticleAdapter() {
        binding.rvArticle.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRow.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvArticle.setHasFixedSize(true)
        viewModel.articleList.observe(viewLifecycleOwner) { articles ->
            val rowAdapter = RowAdapter(articles)
            val articleListAdapter = ArticleListAdapter(articles)
            binding.rvRow.adapter = rowAdapter
            binding.rvArticle.adapter = articleListAdapter
//            articleListAdapter.addArticle(articles)
            Log.d("Testt", articleList.size.toString())
            Log.d("tess", rowAdapter.itemCount.toString())
            Log.d("tess", articleListAdapter.itemCount.toString())
        }
    }

    private fun setupChipGroup() {
        val chipGroup = binding.chipGroup
        val chipTexts = listOf(
            "Asuransi", "Perbankan", "Korporasi", "Bursa dan Saham", "Bisnis",
            "Berita", "dasda"
        )

        for (chipText in chipTexts) {
            val chip = createChip(chipText)
            chipGroup.addView(chip)
            setChipCheckedChangeListener(chip)
        }
    }

    private fun createChip(chipText: String): Chip {
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

        return chip
    }

    private fun setChipCheckedChangeListener(chip: Chip) {
        chip.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Filter articles based on the selected category
                val filteredArticles = viewModel.articleList.value?.filter { it.category == chip.text.toString() }
                binding.rvArticle.adapter = ArticleListAdapter(filteredArticles.orEmpty())
            } else {
                // Reset the adapter to the original list when the chip is unchecked
                binding.rvArticle.adapter = ArticleListAdapter(viewModel.articleList.value.orEmpty())
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

