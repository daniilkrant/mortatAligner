package com.krant.daniil.coptercalculator.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.krant.daniil.coptercalculator.databinding.SecondFragmentTabbedActvitiyBinding
import java.text.DecimalFormat

/**
 * A placeholder fragment containing a simple view.
 */
class SecondFragment : Fragment(), TextWatcher {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: SecondFragmentTabbedActvitiyBinding? = null
    lateinit var linSizeEd : EditText
    lateinit var angleSizeEd : EditText
    private val df = DecimalFormat("0.0000")

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SecondFragmentTabbedActvitiyBinding.inflate(inflater, container, false)
        val root = binding.root
        linSizeEd = binding.linSize
        linSizeEd.addTextChangedListener(this)
        angleSizeEd = binding.angleSize
        angleSizeEd.addTextChangedListener(this)
        return root
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun afterTextChanged(p0: Editable?) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        if ((linSizeEd.text.isNotEmpty()) &&
            (angleSizeEd.text.isNotEmpty())) {

            val linSize = linSizeEd.text.toString().toDouble()
            val angleSize = angleSizeEd.text.toString().toDouble()

            val resDistance = (linSize * 1000) / angleSize
            binding.resDistance.text = df.format(resDistance)
        }
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): SecondFragment {
            return SecondFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}