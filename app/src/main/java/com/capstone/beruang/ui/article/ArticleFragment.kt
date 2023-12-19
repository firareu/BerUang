package com.capstone.beruang.ui.article

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.beruang.adapter.RowAdapter
import com.capstone.beruang.adapter.TagAdapter
import com.capstone.beruang.data.dataclass.ArticleData
import com.capstone.beruang.data.dataclass.Tag
import com.capstone.beruang.data.response.TagResponse
import com.capstone.beruang.databinding.FragmentArticleBinding
import com.example.submission.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import kotlin.math.log

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapters()
    }
    private val TAG = "ArticleFragment"
    private fun setupAdapters() {
        // Setup RowAdapter
        val dataArticle = ArticleData.articleList
        val adapter = RowAdapter(dataArticle)
        binding.rvRow.adapter = adapter
        binding.rvRow.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRow.setHasFixedSize(true)


        val tagService = ApiConfig.getApiService()
        lifecycleScope.launch {
            Log.d(TAG, "Inside lifecycleScope.launch")
            try {
                val tagService = ApiConfig.getApiService()
                Log.d(TAG, "tagService initialized: $tagService")

                val dataTags: List<TagResponse> = tagService.getTags()
                Log.d(TAG, "Data received: ${dataTags.size} items")

                val tagAdapter = TagAdapter(requireContext(), dataTags)
                binding.rvTag.adapter = tagAdapter
                binding.rvTag.layoutManager = GridLayoutManager(requireContext(), 2)

                tagAdapter.setOnItemClickListener {
                    val intent = Intent(requireContext(), ListArticleActivity::class.java)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception occurred: ${e.message}", e)
            }
        }

        /*// Setup TagAdapter
//        val dataTag = TagData.tagList
        val dataTag = Tag.getTag()
        val tagAdapter = TagAdapter(requireContext(), dataTag)
        binding.rvTag.adapter = tagAdapter
        binding.rvTag.layoutManager = GridLayoutManager(requireContext(), 2)

        tagAdapter.setOnItemClickListener {
            val intent = Intent(requireContext(), ListArticleActivity::class.java)
            startActivity(intent)
        }*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
