package com.example.lightout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Juego extends AppCompatActivity {

    private GridLayout gridJuego;
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        gestureDetector = new GestureDetectorCompat(this, new GestureListener());
        gridJuego = (GridLayout) findViewById(R.id.Gridjuego);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        gridJuego.getLayoutParams().height=width-40;


        for (int i = 0; i < 16; i++) {
            this.añadirTextview(gridJuego);
        }

        int childCount =gridJuego.getChildCount();
        Toast.makeText(Juego.this, ""+childCount, Toast.LENGTH_SHORT).show();

    }
    //Gesture detector
    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            Toast.makeText(Juego.this, "onDown", Toast.LENGTH_SHORT).show();
            return super.onDown(e);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //Añadir textview al Gridlayout
    public void añadirTextview(GridLayout gridJuego) {

        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(
                GridLayout.spec(GridLayout.UNDEFINED, 1f),
                GridLayout.spec(GridLayout.UNDEFINED, 1f)
        );

        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setBackgroundResource(R.drawable.circulo_lightout);
        textView.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
        textView.setTextColor(Color.WHITE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setBackgroundColor(Color.WHITE);
                int num=gridJuego.indexOfChild(textView);
                Toast.makeText(Juego.this, "Soy :"+num, Toast.LENGTH_SHORT).show();
                if (gridJuego.getChildAt(num-4) != null) {
                    gridJuego.getChildAt(num-4).setBackgroundColor(Color.WHITE);
                }
                if (gridJuego.getChildAt(num+1) != null) {
                    gridJuego.getChildAt(num+1).setBackgroundColor(Color.WHITE);
                }
                if (gridJuego.getChildAt(num-1) != null) {
                    gridJuego.getChildAt(num-1).setBackgroundColor(Color.WHITE);
                }
                if (gridJuego.getChildAt(num+4) != null) {
                    gridJuego.getChildAt(num+4).setBackgroundColor(Color.WHITE);
                }
            }
        });

        gridJuego.addView(textView, layoutParams);
    }
}