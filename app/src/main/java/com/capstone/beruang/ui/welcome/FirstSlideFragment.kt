package com.capstone.beruang.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.capstone.beruang.R

private const val ARG_PARAM1 = "param1"

class FirstSlideFragment : Fragment() {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_first_slide, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textView = view.findViewById<TextView>(R.id.textFragment)
        val imageFragment = view.findViewById<ImageView>(R.id.imageFragment)

        when (param1) {
            "2" -> {
                setTextAndImage(R.string.text_third, R.string.text_third_detail, R.drawable.img, textView, imageFragment)
            }
            "1" -> {
                setTextAndImage(R.string.text_second, R.string.text_second_detail, R.drawable.img, textView, imageFragment)
            }
            else -> {
                setTextAndImage(R.string.text_first, R.string.text_first_detail, R.drawable.img, textView, imageFragment)
            }
        }
    }

    private fun setTextAndImage(titleResId: Int, detailResId: Int, imageResId: Int, textView: TextView, imageView: ImageView) {
        textView.text = getString(titleResId) + "\n" + getString(detailResId)
        imageView.setBackgroundResource(imageResId)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            FirstSlideFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}