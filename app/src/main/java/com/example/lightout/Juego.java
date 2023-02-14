package com.example.lightout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lightout.databinding.ActivityJuegoBinding;

public class Juego extends AppCompatActivity {

    private ActivityJuegoBinding binding;
    private GridLayout gridJuego;
    private GestureDetectorCompat gestureDetector;
    private Cell[][] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityJuegoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        gridJuego = binding.Gridjuego;

        cells= new Cell[4][4];
        this.configurarGrid(gridJuego, 4);
        this.anadirOnclickListener(cells,4);

        this.hacerJuego(cells);


        binding.newgame.setOnClickListener(view -> hacerJuego(cells));
    }

    //----------------------------------------------------------------------------------------------

    //Añadir textview al Gridlayout
    public void anadirCell(GridLayout gridJuego, int numColumnaFila) {

        for (int i = 0; i < numColumnaFila; i++) {
            for (int j = 0; j < numColumnaFila; j++) {
                // Poner el row and colum weight
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(
                        GridLayout.spec(GridLayout.UNDEFINED, 1f),
                        GridLayout.spec(GridLayout.UNDEFINED, 1f)
                );
                layoutParams.width = 0;
                layoutParams.height = 0;


                Cell cell= new Cell(this,false,false);
                cell.setBackgroundResource(R.drawable.zombi_sin_color);
                cell.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
                gridJuego.addView(cell, layoutParams);

                //Anadir los textview al array 2d
                cells[i][j] = cell;
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


        this.anadirCell(gridjuego, numColumnaFila);

    }

    public void anadirOnclickListener(ImageButton[][] imageButtons,int numColumnaFila) {
        ImageButton imageButton;
        for (int i = 0; i < numColumnaFila; i++) {
            for (int j = 0; j < numColumnaFila; j++) {
                imageButton = imageButtons[i][j];
                int finalI = i;
                int finalJ = j;
                imageButton.setOnClickListener(view -> {
                    apagarEncender(finalI,finalJ);
                    Toast.makeText(Juego.this, "i=" + finalI + "j=" + finalJ, Toast.LENGTH_SHORT).show();
                });
            }
        }
    }

    //----------------------------------------------------------------------------------------------

    public void  hacerJuego(Cell [][] cells){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                this.reniciarCell(cells[i][j]);
                int num = (int) (Math.random()*2);
                if (num == 0) {
                    this.apagarEncender(i,j);
                }
            }
        }
    }

    public void apagarEncender(int i,int j){

        this.cambiarEstadoSolucion(cells[i][j]);

        this.cambiarEstado(cells[i][j]);
        
        if (i > 0) {
            this.cambiarEstado(cells[i-1][j]);
        }
        if (i < cells.length-1) {
            this.cambiarEstado(cells[i+1][j]);
        }
        if (j > 0) {
            this.cambiarEstado(cells[i][j-1]);
        }
        if (j < cells.length-1) {
            this.cambiarEstado(cells[i][j+1]);
        }
    }

    public void cambiarEstado(Cell cell) {
        if (cell.isEncendido()) {
            cell.setEncendido(false);
            cell.setBackgroundResource(R.drawable.zombi_sin_color);
        }else {
            cell.setEncendido(true);
            cell.setBackgroundResource(R.drawable.zombi);
        }
    }

    public void cambiarEstadoSolucion(Cell cell) {
        cell.setSolucion(!cell.isSolucion());
    }

    public void reniciarCell(Cell cell){
        cell.setSolucion(false);
        cell.setEncendido(false);
    }


}