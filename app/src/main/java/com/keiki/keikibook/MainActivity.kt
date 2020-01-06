package com.keiki.keikibook

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.content_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import java.security.cert.Extension


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        tvZero.setOnClickListener{appendOnExpression("0",true)}
        tvOne.setOnClickListener{appendOnExpression("1",true)}
        tvTwo.setOnClickListener{appendOnExpression("2",true)}
        tvThree.setOnClickListener{appendOnExpression("3",true)}
        tvFour.setOnClickListener{appendOnExpression("4",true)}
        tvFive.setOnClickListener{appendOnExpression("5",true)}
        tvSix.setOnClickListener{appendOnExpression("6",true)}
        tvSeven.setOnClickListener{appendOnExpression("7",true)}
        tvEight.setOnClickListener{appendOnExpression("8",true)}
        tvNine.setOnClickListener{appendOnExpression("9",true)}
        tvDot.setOnClickListener{appendOnExpression(".",true)}

        tvPlus. setOnClickListener{appendOnExpression("+",false)}
        tvMinus.setOnClickListener{appendOnExpression("-",false)}
        tvMul.setOnClickListener{appendOnExpression("*",false)}
        tvDivide.setOnClickListener{appendOnExpression("/",false)}
        tvOpen.setOnClickListener{appendOnExpression("(",false)}
        tvClose.setOnClickListener{appendOnExpression(")",false)}

        tvClear.setOnClickListener{
            tvExpression.text=""
            tvResult.text=""
        }
        tvBack.setOnClickListener{
            val string =tvExpression.text.toString()
            if(string.isNotEmpty()){
                tvExpression.text=string.substring(0,string.length-1)
            }
            tvResult.text=""
        }

        tvEquals.setOnClickListener{
            try {
                val expression=ExpressionBuilder(tvExpression.text.toString()).build()
                val result=expression.evaluate()
                val longResult=result.toLong()
                if(result==longResult.toDouble()){
                    tvResult.text=longResult.toString()
                }else{
                    tvResult.text=result.toString()
                }
            }catch(e:Exception) {
                Log.d("exception","message"+e.message)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun appendOnExpression(string:String,canClear:Boolean){
        if(tvResult.text.isNotEmpty()){
            tvExpression.text=""
        }
        if(canClear){
            tvResult.text=""
            tvExpression.append(string)
        }else{
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text=""
        }
    }
}
