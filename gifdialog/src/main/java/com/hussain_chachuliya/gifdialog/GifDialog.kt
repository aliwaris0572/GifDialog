package com.hussain_chachuliya.gifdialog

import android.app.Dialog
import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.widget.TextView
import com.facebook.common.util.UriUtil
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView


class GifDialog private constructor(private var ctx: Context) {

    private var mDialog: Dialog? = null
    private var map: HashMap<String, Dialog>? = null

    private var resourceId: Int? = null
    private var text: String
    private var textColor: Int
    private var textSize: Int
    private var textBackgroundColor: Int
    private var dialogBackgroundResource: Int
    private var isCancelable: Boolean
    private var dimAmount: Float

    init {
        this.text = ""
        this.isCancelable = true
        this.textColor = ContextCompat.getColor(ctx, android.R.color.white)
        this.textBackgroundColor = ContextCompat.getColor(ctx, android.R.color.black)
        this.dialogBackgroundResource = android.R.color.transparent
        this.textSize = 12
        dimAmount = 0.5f
        map = HashMap()
    }

    companion object {
        fun with(context: Context): GifDialog {
            if (!Fresco.hasBeenInitialized())
                Fresco.initialize(context)
            return GifDialog(context)
        }
    }

    fun setResourceId(id: Int): GifDialog {
        resourceId = id
        return this
    }

    fun setText(t: String): GifDialog {
        text = t
        return this
    }

    fun setTextColor(id: Int): GifDialog {
        textColor = id
        return this
    }

    fun setTextSize(value: Int): GifDialog {
        textSize = value
        return this
    }

    fun setTextBackgroundColor(id: Int): GifDialog {
        textBackgroundColor = id
        return this
    }

    fun isCancelable(value: Boolean): GifDialog {
        isCancelable = value
        return this
    }

    fun setDimAmount(value: Float): GifDialog {
        dimAmount = value
        return this
    }

    fun setDialogBackgroundResource(id: Int): GifDialog {
        dialogBackgroundResource = id
        return this
    }

    fun showDialog(tag: String) {
        // Remove redundant dialogs
        if (map?.containsKey(tag) != null){
            map?.get(tag)?.dismiss()
            map?.remove(tag)
        }

        // Create a new dialog
        mDialog = Dialog(ctx)
        mDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog?.setContentView(R.layout.custom)
        mDialog?.window?.setBackgroundDrawableResource(dialogBackgroundResource)
        mDialog?.window?.setDimAmount(dimAmount)
        mDialog?.setCancelable(isCancelable)

        // set the custom dialog components - text, image and button
        val textView = mDialog?.findViewById<TextView>(R.id.textView)
        if (text.isEmpty())
            textView?.visibility = View.GONE
        else {
            textView?.visibility = View.VISIBLE
            textView?.setTextColor(textColor)
            textView?.setBackgroundColor(textBackgroundColor)
            textView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())
            textView?.text = text
        }


        val draweeView = mDialog?.findViewById<SimpleDraweeView>(R.id.draweeView)
        val controller: DraweeController = Fresco.newDraweeControllerBuilder()
                .setUri(UriUtil.getUriForResourceId(resourceId!!))
                .setAutoPlayAnimations(true)
                .build()
        draweeView?.controller = controller

        map?.put(tag, mDialog!!)
        mDialog?.show()
    }

    fun dismissDialog(tag: String) {
        val d = map?.get(tag)

        if(d != null){
            d.dismiss()
            map?.remove(tag)
        }
    }
}
