package com.example.movementmeasure

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private lateinit var sensitivitySeekBar: SeekBar
    private lateinit var sensitivityTextView: TextView
    private lateinit var linearAccelerationSensor: Sensor
    // Define a sensitivity threshold
    var sensitivityThreshold = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)

        sensitivitySeekBar = findViewById(R.id.sensitivitySeekBar)
        sensitivityTextView = findViewById(R.id.sensitivityTextView)
        sensitivitySeekBar.max = 15




        sensitivitySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sensitivityThreshold = progress
                sensitivityTextView.text = "Sensitivity: $sensitivityThreshold"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(accelerometerListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(accelerometerListener)
    }

    private val accelerometerListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

        override fun onSensorChanged(event: SensorEvent?) {
            if (event != null && event.sensor.type == Sensor.TYPE_LINEAR_ACCELERATION) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                // Calculate the magnitude of the acceleration
                val magnitude = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
                Log.d(magnitude.toString(), "magnitude")
                if (magnitude > sensitivityThreshold) {
                    // Significant movement detected
                    val message = "Significant movement detected: Magnitude = $magnitude"
                    showToast(message)
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun calculateMagnitude(x: Float, y: Float, z: Float): Float {
        return Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
    }

    fun isMagnitudeAboveThreshold(magnitude: Float): Boolean {
        return magnitude > sensitivityThreshold
    }
}
