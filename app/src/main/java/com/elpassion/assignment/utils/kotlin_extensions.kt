package com.elpassion.assignment.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.annotation.LayoutRes
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast
import com.elpassion.assignment.R
import com.squareup.picasso.Picasso


/**
* Created by pavel on 5/2/18.
*/

// Extension for Activity Intents
inline  fun <reified  T : Activity> Activity.goToActivity(init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
}

// Extension for Fancy Default
fun Activity.toastDefault(message: String, duration: Int = Toast.LENGTH_SHORT) = FancyToast.makeText(this, message, duration, FancyToast.DEFAULT, false)!!.show()

// Extension for Fancy Default
fun Activity.toastSuccess(message: String, duration: Int = Toast.LENGTH_SHORT) = FancyToast.makeText(this, message, duration, FancyToast.SUCCESS, false)!!.show()

// Extension for Fancy Default
fun Activity.toastError(message: String, duration: Int = Toast.LENGTH_SHORT) = FancyToast.makeText(this, message, duration, FancyToast.ERROR, false)!!.show()



// Extension for Picasso Charge
fun ImageView.loadImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.with(context).load(imageUrl).into(this)
    }
}

// Extension for Picasso Fetch
fun ImageView.fetchImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).fetch()
    } else {
        Picasso.with(context).load(imageUrl).fetch()
    }
}

fun Activity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

