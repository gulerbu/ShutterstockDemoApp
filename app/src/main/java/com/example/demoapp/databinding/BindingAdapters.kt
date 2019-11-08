package com.example.demoapp.databinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

private const val IMAGE_HEIGHT = 1000
private const val IMAGE_WIDTH = 1500

@BindingAdapter("viewVisibility")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String) {
    Picasso.get()
        .load(url)
        .resize(IMAGE_WIDTH, IMAGE_HEIGHT)
        .centerInside()
        .into(imageView)

}