package com.example.dtic_123.jhonson_algoritmica;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by DTIC-123 on 28/03/2017.
 */

public class Nodo {

    private String nombre;
    private int valorIda;
    private int valorVuelta;
    private int posX = 0;
    private int posY = 0;
    private int color;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.valorIda = 0;
        this.valorVuelta = 0;
    }
    public Nodo(String nombre, int posX, int posY) {
        this.nombre = nombre;
        this.valorIda = 0;
        this.valorVuelta = 0;
        this.posX = posX;
        this.posY = posY;
        color = Color.BLACK;
    }

    public int getPosX() {
        return posX;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValorIda() {
        return valorIda;
    }

    public void setValorIda(int valorIda) {
        this.valorIda = valorIda;
    }

    public int getValorVuelta() {
        return valorVuelta;
    }

    public void setValorVuelta(int valorVuelta) {
        this.valorVuelta = valorVuelta;
    }

    public void crearDibujoNodo(Canvas canvas, int posX, int posY) {
        Log.i("lienzo", "onDraw del lienzo");
        canvas.drawRGB(255, 255, 0);
        Paint pincel1 = new Paint();
        pincel1.setARGB(255, 255, 0, 0);
        pincel1.setStrokeWidth(8);
        pincel1.setStyle(Paint.Style.STROKE);
        pincel1.setColor(Color.BLACK);
        canvas.drawColor(Color.CYAN);
        canvas.drawCircle(posX, posY, 30, pincel1);
        canvas.save();
    }
}
