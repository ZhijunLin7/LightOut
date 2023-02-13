package com.example.lightout;

import android.content.Context;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

public class Cell extends androidx.appcompat.widget.AppCompatImageButton {

    private int contador;
    private boolean encendido;

    public Cell(@NonNull Context context, int contador, boolean encendido) {
        super(context);
        this.contador = contador;
        this.encendido = encendido;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public boolean isEncendido() {
        return encendido;
    }

    public void setEncendido(boolean encendido) {
        this.encendido = encendido;
    }
}
