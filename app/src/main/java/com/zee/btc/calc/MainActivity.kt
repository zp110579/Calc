package com.zee.btc.calc

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.zee.utils.UIUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_input_sure.setOnClickListener {
            try {
                val value = tv_input_text.text.toString().toDouble();
                upValue(value)
                down(value)
            } catch (e: Exception) {
                UIUtils.showToastShort("请输入正确值")
            }
        }
    }

    //涨
    fun upValue(value: Double) {
        tv_btc_up.text = value(value, 1);
        tv_btc_up.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    //跌
    fun down(value: Double) {
        tv_btc_down.text = value(value, -1);
        tv_btc_down.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    fun value(value: Double, type: Int): java.lang.StringBuilder {
        val builder = StringBuilder();
        for (i in 1..100) {
            val tempValue = BigDecimal(i * 0.01 * type)

            builder.append(
                formatDouble(tempValue.multiply(BigDecimal(100)), 6) + "%====>" + upValue(
                    BigDecimal(value),
                    tempValue
                )
            ).append("\n")
        }
        return builder;
    }


    fun upValue(baseValue: BigDecimal, value: BigDecimal): String {
        return formatDouble(baseValue.add(baseValue.multiply(value)), 6)
    }

    private fun formatDouble(d: BigDecimal, number: Int): String {
        val nf = NumberFormat.getInstance()
        //设置保留多少位小数
        nf.setMaximumFractionDigits(number)
        // 取消科学计数法
        nf.setGroupingUsed(false)
        //返回结果
        return nf.format(d)
    }
}
