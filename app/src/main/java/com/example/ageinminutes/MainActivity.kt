package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            ClickDatePicker(view)
        }
    }

    fun ClickDatePicker(view: View){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view,
                selyear,
                selmonth,
                seldayOfMonth ->
            Toast.makeText(this,"Those choosen year $selyear , the chosen month is ${selmonth+1}," +
                    "the day is $seldayOfMonth",Toast.LENGTH_LONG).show()
            val selectedDate = "$seldayOfMonth/${selmonth+1}/${selyear}"
            tvSelectedDate.setText(selectedDate)

            val simpledateformat = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

            val theDate = simpledateformat.parse(selectedDate)  //.time gives in milliseconds
            val selectedDateInMinutes = theDate!!.time/60000  //dividing by 1000 converts it to seconds another 60 converts to minutes

            val currentDate =  simpledateformat.parse(simpledateformat.format(System.currentTimeMillis()))
            val currentDateInMin = currentDate!!.time/60000

            val diffInMinutes = currentDateInMin - selectedDateInMinutes

            tvAgeInMin.setText(diffInMinutes.toString())
        },
            year,
            month,
            day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }
}