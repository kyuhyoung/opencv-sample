package eu.sisik.opencvsample

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.ivPhoto

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doCanny()
    }

    fun doCanny() {

        // Load my JPEG from assets
        var bitmap: Bitmap? = null
        assets.open("pinezici_krk_island.JPG").use {
            bitmap = BitmapFactory.decodeStream(it)
        }

        // Store result inside of app's cache folder
        var dest = cacheDir.absolutePath + "/canny.JPG"

        // Pass the bitmap to native C++ code and perform canny edge detection
        kanny(bitmap, dest)


        // Show the processed image
        ivPhoto.setImageBitmap(BitmapFactory.decodeFile(dest))
    }

    external fun kanny(src: Bitmap?, destinationPath: String): Void

    companion object {

        init {
            //System.loadLibrary("opencv_java4")
            System.loadLibrary("native-lib")
        }
    }
}
