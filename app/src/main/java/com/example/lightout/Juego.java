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
    private TextView [][] textViews;

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


        textViews = new TextView[4][4];
        this.añadirTextview(gridJuego);


        int childCount =gridJuego.getChildCount();
        Toast.makeText(Juego.this, ""+childCount, Toast.LENGTH_SHORT).show();

        this.anadirOnclickListener(textViews);

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

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                // Poner el row and colum weight
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(
                        GridLayout.spec(GridLayout.UNDEFINED, 1f),
                        GridLayout.spec(GridLayout.UNDEFINED, 1f)
                );

                // Poner estilo al textview
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(30);
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                textView.setBackgroundResource(R.drawable.circulo_lightout);
                textView.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
                textView.setTextColor(Color.WHITE);

                gridJuego.addView(textView, layoutParams);
                textViews[i][j]=textView;

            }
        }
    }

    public void anadirOnclickListener(TextView [][] textViews){
        TextView textView;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                textView=textViews[i][j];
                int finalI = i;
                int finalJ = j;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pintar(textViews,finalI,finalJ);
                        Toast.makeText(Juego.this, "i="+ finalI+"j="+finalJ, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    }

    public void pintar(TextView [][] textViews,int x , int y){

        apagarEncender(textViews[x][y]);


        try {
            if (textViews[x+1][y] != null) {
                apagarEncender(textViews[x+1][y]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (textViews[x-1][y] != null) {
                apagarEncender(textViews[x-1][y]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (textViews[x][y+1] != null) {
                apagarEncender(textViews[x][y+1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (textViews[x][y-1] != null) {
                apagarEncender(textViews[x][y-1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
    public void apagarEncender(TextView textView){
        if (textView.getBackground().getConstantState() == getResources().getDrawable(R.drawable.circulo_lightout).getConstantState()) {
            textView.setBackgroundResource(R.drawable.circulo_lighton);
        }
        else {
            textView.setBackgroundResource(R.drawable.circulo_lightout);
        }



    }


}