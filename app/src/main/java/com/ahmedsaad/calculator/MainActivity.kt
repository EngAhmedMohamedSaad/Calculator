package com.ahmedsaad.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import permission.manager.iTools
import java.lang.Exception
import javax.xml.xpath.XPathExpression
import kotlin.math.E

class MainActivity : AppCompatActivity() {
    var t = iTools(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        btn0.setOnClickListener { appendOnExpresstion("0" , true) }
        btn1.setOnClickListener { appendOnExpresstion("1" , true) }
        btn2.setOnClickListener { appendOnExpresstion("2" , true) }
        btn3.setOnClickListener { appendOnExpresstion("3" , true) }
        btn4.setOnClickListener { appendOnExpresstion("4" , true) }
        btn5.setOnClickListener { appendOnExpresstion("5" , true) }
        btn6.setOnClickListener { appendOnExpresstion("6" , true) }
        btn7.setOnClickListener { appendOnExpresstion("7" , true) }
        btn8.setOnClickListener { appendOnExpresstion("8" , true) }
        btn9.setOnClickListener { appendOnExpresstion("9" , true) }
        btnDot.setOnClickListener { appendOnExpresstion("." , true) }

        //Operations
        btnAdd.setOnClickListener { appendOnExpresstion("+" , false) }
        btnSup.setOnClickListener { appendOnExpresstion("-" , false) }
        btnMulti.setOnClickListener { appendOnExpresstion("*" , false) }
        btnDiv.setOnClickListener { appendOnExpresstion("/" , false) }
        btnOpen.setOnClickListener { appendOnExpresstion("(" , false) }
        btnClose.setOnClickListener { appendOnExpresstion(")" , false) }

        btnAc.setOnClickListener {
            txtShowNumber.text = ""
            txtResult.text = ""
        }
        btnDeleteOneNumber.setOnClickListener{
            val string = txtShowNumber.text.toString()
            if (string.isNotEmpty()){
                txtShowNumber.text = string.substring(0, string.length-1)
            }
            txtResult.text = ""
        }

        btnEqual.setOnClickListener {
            try {

                val expression = ExpressionBuilder(txtShowNumber.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    txtResult.text = longResult.toString()
                else
                    txtResult.text = result.toString()

            }catch (e:Exception){
                t.toastLong(e.message.toString())
                Log.d("Exception" , "message  : " + e.message)
            }
        }

    }


    fun appendOnExpresstion (string: String, canClear: Boolean){
        if(txtResult.text.isNotEmpty()){
            txtShowNumber.text = ""
        }

        if (canClear){
            txtResult.text = ""
            txtShowNumber.append(string)

        }else{
            txtShowNumber.append(txtResult.text)
            txtShowNumber.append(string)
            txtResult.text = ""

        }
    }


    var isNewOp = true
    fun btnNumberEvent(view:View){
        try {

        if (isNewOp){
            txtShowNumber.text = ""
        }
        isNewOp=false
        var txtNumber:String =txtShowNumber.text.toString()
        var btnSelect = view as Button

        when(btnSelect.id){
            btn0.id->{
                txtNumber += "0"
            }
            btn1.id->{
                txtNumber += "1"
            }
            btn2.id->{
                txtNumber += "2"
            }
            btn3.id->{
                txtNumber += "3"
            }
            btn4.id->{
                txtNumber += "4"
            }
            btn5.id->{
                txtNumber += "5"
            }
            btn6.id->{
                txtNumber +="6"
            }
            btn7.id->{
                txtNumber += "7"
            }
            btn8.id->{
                txtNumber += "8"
            }
            btn9.id->{
                txtNumber += "9"
            }
            btnDot.id->{
                txtNumber += "."
            }
            btnOpen.id->{
                txtNumber += "("
            }
            btnClose.id->{
                txtNumber += ")"
            }
            btnPluse.id->{
                txtNumber = "-$txtNumber"
            }
        }
        txtShowNumber.text = txtNumber
        }
        catch(ex:Exception){t.toastShort(ex.message.toString())}
    }

    var oldNumber = ""
    var op ="+"
    fun btnOpEvent(view:View){
        try {
            oldNumber = txtShowNumber.text.toString()
            isNewOp = true
            val btnSelect = view as Button
            when (btnSelect.id) {
                btnAdd.id -> {
                    op = "+"
                }
                btnSup.id -> {
                    op = "-"
                }
                btnMulti.id -> {
                    op = "*"
                }
                btnDiv.id -> {
                    op = "/"
                }

            }
        }
            catch(ex:Exception){t.toastShort(ex.message.toString())}
        }

    fun btnEqualEvent(view:View) {
        try {
            var newNumber = txtShowNumber.text.toString()
            var finaNumber: Any? = null
            when (op) {
                "+" -> {
                    finaNumber = oldNumber.toDouble() + newNumber.toDouble()
                }
                "-" -> {
                    finaNumber = oldNumber.toDouble() - newNumber.toDouble()
                }
                "*" -> {
                    finaNumber = oldNumber.toDouble() * newNumber.toDouble()
                }
                "/" -> {
                    finaNumber = oldNumber.toDouble() / newNumber.toDouble()
                }

            }
            txtShowNumber.text = finaNumber.toString()
            isNewOp = true
        }
        catch(ex:Exception){t.toastShort(ex.message.toString())}

    }

    fun btnClear(view:View){
        try {
            isNewOp = true
            txtShowNumber.text = "0"
        }
        catch(ex:Exception){t.toastShort(ex.message.toString())}


    }

    fun btnDelete(view:View) {
        try {
            var strDel = btnDeleteOneNumber.toString()
            if (strDel.isNotEmpty()) {
                txtShowNumber.text = strDel.substring(0, strDel.length-1)

            }
            txtShowNumber.text = ""
        }
        catch(ex:Exception){t.toastShort(ex.message.toString())}

    }
    }






