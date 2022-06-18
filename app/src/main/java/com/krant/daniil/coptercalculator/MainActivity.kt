package com.krant.daniil.coptercalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import kotlin.math.sin
import kotlin.math.tan

class MainActivity : AppCompatActivity(), TextWatcher {
    lateinit var droneHeightEd : EditText
    lateinit var landDistanceEd : EditText
    lateinit var angleEd : EditText
    private val df = DecimalFormat("0.0000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        droneHeightEd = findViewById<EditText>(R.id.drone_height)
        droneHeightEd.addTextChangedListener(this)
        landDistanceEd = findViewById<EditText>(R.id.land_distance)
        landDistanceEd.addTextChangedListener(this)
        angleEd = findViewById<EditText>(R.id.angle)
        angleEd.addTextChangedListener(this)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun afterTextChanged(p0: Editable?) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        if ((droneHeightEd.text.isNotEmpty()) &&
                (angleEd.text.isNotEmpty())) {

            val droneHeight = droneHeightEd.text.toString().toDouble()
            val angle = angleEd.text.toString().toDouble()

            var resultTan = Math.tan(Math.toRadians(angle))
            findViewById<TextView>(R.id.res_tan).text = df.format(resultTan)

            val resDistance = droneHeight / resultTan
            findViewById<TextView>(R.id.res_distance).text = df.format(resDistance)
        }
    }
}