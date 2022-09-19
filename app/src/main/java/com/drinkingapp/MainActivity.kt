package com.drinkingapp

import android.content.Intent
import android.content.IntentSender
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.io.NotActiveException

class MainActivity : AppCompatActivity() {
    private val orangeJuice = "orange Juice"
    private val appelJuice = "appel Juice"
    private val mangoJuice = "mango Juice"
    private val kewiJuice = "kewi Juice"
    private val mapOfDrinks = mapOf(
        orangeJuice to 10.0,
        appelJuice to 15.0,
        mangoJuice to 20.0,
        kewiJuice to 25.0,
    )

    private lateinit var priceValue:TextView
    private lateinit var dropDownMenue:AutoCompleteTextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
        populateDropDownMenu()
        dropDownMenue.setOnItemClickListener { adapter, view, position, id ->
            setPrice()
        }
        button.setOnClickListener(){
            //Toast.makeText(this,"Email app not found",Toast.LENGTH_SHORT).show()
            buttonSubmit()
        }

    }
    private fun buttonSubmit(){

            val drink = dropDownMenue.text.toString()
            val sendMailDrink = Intent(Intent.ACTION_SENDTO)
            sendMailDrink.data = Uri.parse("mailto:")
            sendMailDrink.putExtra(Intent.EXTRA_EMAIL, arrayOf("mohamedrohiem597@gmail.com"))
            sendMailDrink.putExtra(Intent.EXTRA_SUBJECT, "Drink Order")
            sendMailDrink.putExtra(Intent.EXTRA_TEXT, "order : $drink")
            startActivity(sendMailDrink)




    }
    private  fun setPrice(){
        var price=mapOfDrinks[dropDownMenue.text.toString()]
        priceValue.setText(price.toString())
    }
    private fun initializeView(){
        dropDownMenue=findViewById(R.id.dropdown_menu)
        priceValue=findViewById(R.id.total_price_value)
        button=findViewById(R.id.submit_button)

    }
    fun populateDropDownMenu(){
        val listOfDrinks = listOf(orangeJuice, appelJuice, mangoJuice, kewiJuice)
        val adapter=ArrayAdapter(this,R.layout.drop_down_list,listOfDrinks)
        dropDownMenue.setAdapter(adapter)

        val mapOfDrinks = mapOf(
            orangeJuice to 10,
            appelJuice to 15,
            mangoJuice to 20,
            kewiJuice to 25,
        )
    }


}