package com.krant.daniil.coptercalculator.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.krant.daniil.coptercalculator.R
import com.krant.daniil.coptercalculator.databinding.FirstFragmentTabbedActvitiyBinding
import java.text.DecimalFormat

/**
 * A placeholder fragment containing a simple view.
 */
class FirstFragment : Fragment(), TextWatcher {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FirstFragmentTabbedActvitiyBinding? = null
    lateinit var droneHeightEd : EditText
    lateinit var landDistanceEd : EditText
    lateinit var angleEd : EditText
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
    ): View {

        _binding = FirstFragmentTabbedActvitiyBinding.inflate(inflater, container, false)
        val root = binding.root
        droneHeightEd = binding.droneHeight
        droneHeightEd.addTextChangedListener(this)
        landDistanceEd = binding.landDistance
        landDistanceEd.addTextChangedListener(this)
        angleEd = binding.angle
        angleEd.addTextChangedListener(this)
        return root
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun afterTextChanged(p0: Editable?) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        if ((droneHeightEd.text.isNotEmpty()) &&
            (angleEd.text.isNotEmpty())) {

            val droneHeight = droneHeightEd.text.toString().toDouble()
            val angle = angleEd.text.toString().toDouble()

            var resultTan = Math.tan(Math.toRadians(angle))
            binding.resTan.text = df.format(resultTan)

            val resDistance = droneHeight / resultTan
            binding.resDistance.text = df.format(resDistance)
        }
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): FirstFragment {
            return FirstFragment().apply {
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