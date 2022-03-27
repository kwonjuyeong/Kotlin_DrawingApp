package com.example.kotlindrawingapp


import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    private var drawingView: DrawingView ?= null
    private var mImageButtonCurrentPaint: ImageButton ?= null

//    private var mPickColorButton:Button? = null
//    private var mDefaultColor = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById(R.id.drawing_view)
        drawingView ?.setSizeForBrush(20.toFloat())

//        mDefaultColor = 0


        val linearLayoutPaintColors = findViewById<LinearLayout>(R.id.paint_color)

        //Linear에 있는 색들을 인덱스 값으로 불러온다. 살색 : [0] 등
        mImageButtonCurrentPaint = linearLayoutPaintColors[4] as ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
        )


        val ibBrush : ImageButton = findViewById(R.id.ib_brush)
        ibBrush.setOnClickListener{
            showBrushSizeChooserDialog()
        }

//        val colorPicker : ImageButton = findViewById(R.id.btn_pick_color)
//        colorPicker.setOnClickListener {
//          showColorPickerDialog()
//
//        }


    }

    //브러쉬 크기 설정해주는 함수
    private fun showBrushSizeChooserDialog(){
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush size :")
        val smallBtn: ImageButton = brushDialog.findViewById(R.id.small_brush)
        smallBtn.setOnClickListener{
            drawingView?.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }
        val mediumBtn: ImageButton = brushDialog.findViewById(R.id.medium_brush)
        mediumBtn.setOnClickListener{
            drawingView?.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }
        val largeBtn: ImageButton = brushDialog.findViewById(R.id.large_brush)
        largeBtn.setOnClickListener{
            drawingView?.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        }
        brushDialog.show()
    }

//    private fun showColorPickerDialog(){
//        val pickerDialog = Dialog(this)
//        ColorPickerPopup.Builder(this)
//            .initialColor(Color.RED)
//            .enableBrightness(true)
//            .enableAlpha(true)
//            .okTitle("Choose")
//            .cancelTitle("Cancel")
//            .showIndicator(true)
//            .showValue(true)
//            .build()
//            .show()
//    }



    fun paintClicked(view: View){
        if(view !== mImageButtonCurrentPaint){
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()
            drawingView?.setColor(colorTag)

            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
            )
            mImageButtonCurrentPaint?.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_normal)
            )
            mImageButtonCurrentPaint = view
        }
    }


}