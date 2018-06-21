package com.hussain_chachuliya.sample

import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.hussain_chachuliya.gifdialog.GifDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var dialog: GifDialog? = null
    var dialog2: GifDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialog = GifDialog.with(this)
        dialog!!.isCancelable(false)
                .setText("Loading...")
                .setTextSize(18)
                .setTextBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
                .setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                .setResourceId(R.drawable.giphy5)
                .setWidth(100)
                .setHeight(100)
                .showDialog("1")

        dialog?.setResourceId(R.drawable.giphy6)
        dialog?.showDialog("2")
        dialog?.showDialog("3")
        dialog?.showDialog("4")

        val h = Handler()
        h.postDelayed({
           dialog?.dismissDialog("1")
            dialog?.showDialog("5")
        }, 5000)

        val h2 = Handler()
        h2.postDelayed({
            dialog?.dismissDialog("2")
            dialog?.dismissDialog("3")
            dialog?.dismissDialog("4")
            dialog?.dismissDialog("5")

            dialog2 = GifDialog.with(this)
            dialog2!!.isCancelable(true)
                    .setText("Loading...")
                    .setTextBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
                    .setTextColor(ContextCompat.getColor(this, android.R.color.black))
                    .setResourceId(R.drawable.giphy6)
                    .showDialog("11")
            val h3 = Handler()
            h3.postDelayed({
                dialog2?.dismissDialog("11")
            }, 10000)

        }, 8000)

        btnRecreate.setOnClickListener{
            this.recreate()
        }
    }
}
