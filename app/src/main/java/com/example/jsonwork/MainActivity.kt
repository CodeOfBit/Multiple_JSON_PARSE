package com.example.jsonwork

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {
    private val TAG: String = "Read File Data"
    private lateinit var dataView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataView = findViewById(R.id.dataView)
        loadJSONFromAsset()
    }

    private fun loadJSONFromAsset() {
        //function to load the JSON from the Asset and return the object
        var data: String? = null
        try {
            val jsonFileLoad = assets.open("json.txt")
            val inputStreamReader = InputStreamReader(jsonFileLoad)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var str: String?
            while (bufferedReader.readLine().also { str = it } != null) {
                if (str != "|") {
                    stringBuilder.append(str)
                    data = stringBuilder.toString()
                    eventrap(data)
                    //str = bufferedReader.readLine()
                    // Log.e(TAG, "" + data)
                } else {
                    stringBuilder.delete(0, stringBuilder.length)
                }

            }
            jsonFileLoad.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    fun eventrap(data: String) {
        try {
            val obj = JSONObject(data)
            val subassemblies: JSONObject = obj.getJSONObject("subassemblies")
            val componentAnalyzer1: JSONObject = subassemblies.getJSONObject("componentAnalyzer1")
            val collectors: JSONObject = componentAnalyzer1.getJSONObject("collectors")
            //bp1
            val bp1: JSONObject = collectors.getJSONObject("bp1")
            var csvArray = bp1.getJSONArray("CSV")
            /*for (i in 0 until csvArray.length()) {
                val bpArray = csvArray.getJSONArray(i)
                Log.e("timestamp-bp1", bpArray.getString(0))
                Log.e("value-bp1", bpArray.getString(1))
            }*/
            //gy1
            val gy1 = collectors.getJSONObject("gy1")
            csvArray = gy1.getJSONArray("CSV")
            for (i in 0 until csvArray.length()) {
                val gyArray = csvArray.getJSONArray(i)
                Log.e("timestamp-gy1", gyArray.getString(0))
                for (j in 0 until gyArray.length()) {
                    //val exampleArray = gyArray[j]
                    // Log.e("value", gyArray.getDouble(1).toString())
                    Log.e("value-gy1", gyArray.getString(1))
                    Log.e("value-gy1", gyArray.getString(2))
                    Log.e("value-gy1", gyArray.getString(3))
                }
            }

            //ac1
           /* val ac1 = collectors.getJSONObject("ac1")
            csvArray = ac1.getJSONArray("CSV")
            for (i in 0 until csvArray.length()) {
                val acArray = csvArray.getJSONArray(i)
                for (j in 0 until acArray.length()) {
                    val exampleArray = acArray[j]
                    Log.e("timestamp-ac1", acArray.getString(0))
                    Log.e("value-ac1", acArray.getString(1))
                    Log.e("value-ac1", acArray.getString(2))
                    Log.e("value-ac1", acArray.getString(3))
                }
            }*/
        } catch (e: JSONException) {
            Log.d(TAG, "Error : ${e.message}")
            e.printStackTrace()
        }
    }
}























