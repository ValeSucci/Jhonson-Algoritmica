package com.example.dtic_123.jhonson_algoritmica;

/**
 * Created by DTIC-123 on 28/03/2017.
 */

public class Arista {

    private int posFinalY = 0;
    private int posFinalX = 0;
    private int posInicialY = 0;

    public int getPosFinalY() {
        return posFinalY;
    }

    public void setPosFinalY(int posFinalY) {
        this.posFinalY = posFinalY;
    }

    public int getPosFinalX() {
        return posFinalX;
    }

    public void setPosFinalX(int posFinalX) {
        this.posFinalX = posFinalX;
    }

    public int getPosInicialY() {
        return posInicialY;
    }

    public void setPosInicialY(int posInicialY) {
        this.posInicialY = posInicialY;
    }

    public int getPosInicialX() {
        return posInicialX;
    }

    public void setPosInicialX(int posInicialX) {
        this.posInicialX = posInicialX;
    }

    private int posInicialX = 0;
    private int atributo;


    public Arista(int atributo) {
        this.atributo = atributo;
    }

    public Arista(int atributo, int posInicialX, int posInicialY, int posFinalX, int posFinalY) {
        this.atributo = atributo;
        this.posInicialX = posInicialX;
        this.posInicialY = posInicialY;
        this.posFinalX = posFinalX;
        this.posFinalY = posFinalY;
    }

    public int getAtributo() {
        return atributo;
    }


    public void setAtributo(int atributo) {
        this.atributo = atributo;
    }
}
