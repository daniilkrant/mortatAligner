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
import com.krant.daniil.coptercalculator.databinding.ThirdFragmentTabbedActvitiyBinding
import java.text.DecimalFormat

/**
 * A placeholder fragment containing a simple view.
 */
class ThirdFragment : Fragment(), TextWatcher {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: ThirdFragmentTabbedActvitiyBinding? = null
    lateinit var targetDistanceEd : EditText
    lateinit var landDistanceEd : EditText
    lateinit var epsilonEd : EditText
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

        _binding = ThirdFragmentTabbedActvitiyBinding.inflate(inflater, container, false)
        val root = binding.root
        targetDistanceEd = binding.targetDistance
        targetDistanceEd.addTextChangedListener(this)
        epsilonEd = binding.epsilon
        epsilonEd.addTextChangedListener(this)
        return root
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun afterTextChanged(p0: Editable?) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        if ((targetDistanceEd.text.isNotEmpty()) &&
            (epsilonEd.text.isNotEmpty())) {

            val targetDistance = targetDistanceEd.text.toString().toDouble()
            val epsilon = epsilonEd.text.toString().toDouble()

            var resultTan = targetDistance * (epsilon / 1000)
            binding.resDistance.text = df.format(resultTan)
        }
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): ThirdFragment {
            return ThirdFragment().apply {
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