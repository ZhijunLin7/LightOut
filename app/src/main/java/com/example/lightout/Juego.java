package com.example.lightout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Juego extends AppCompatActivity {

    private GridLayout gridJuego;
    private GestureDetectorCompat gestureDetector;
    private ImageButton[][] imageButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);


        gridJuego = (GridLayout) findViewById(R.id.Gridjuego);


        imageButtons = new ImageButton[4][4];
        this.configurarGrid(gridJuego, 4);


        int childCount = gridJuego.getChildCount();
        Toast.makeText(Juego.this, "" + childCount, Toast.LENGTH_SHORT).show();

        this.anadirOnclickListener(imageButtons);

    }

    //----------------------------------------------------------------------------------------------

    //Añadir textview al Gridlayout
    public void anadirTextview(GridLayout gridJuego, int numColumnaFila) {

        for (int i = 0; i < numColumnaFila; i++) {
            for (int j = 0; j < numColumnaFila; j++) {
                // Poner el row and colum weight
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(
                        GridLayout.spec(GridLayout.UNDEFINED, 1f),
                        GridLayout.spec(GridLayout.UNDEFINED, 1f)
                );
                layoutParams.width = 0;
                layoutParams.height = 0;

                ImageButton imageButton= new ImageButton(this);
                imageButton.setBackgroundResource(R.drawable.zombi);
                imageButton.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
                gridJuego.addView(imageButton, layoutParams);

                //Anadir los textview al array 2d
                imageButtons[i][j] = imageButton;
            }
        }
    }

    public void configurarGrid(GridLayout gridjuego, int numColumnaFila) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;

        //Configuracion del gridjuego
        gridjuego.getLayoutParams().height = width - 40;
        gridjuego.setColumnCount(numColumnaFila);
        gridjuego.setRowCount(numColumnaFila);


        this.anadirTextview(gridjuego, numColumnaFila);

    }

    public void anadirOnclickListener(ImageButton[][] imageButtons) {
        ImageButton imageButton;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                imageButton = imageButtons[i][j];
                int finalI = i;
                int finalJ = j;
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Juego.this, "i=" + finalI + "j=" + finalJ, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    //----------------------------------------------------------------------------------------------

    public void apagarEncender(TextView textView) {

    }


}