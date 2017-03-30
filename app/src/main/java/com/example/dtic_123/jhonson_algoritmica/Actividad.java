package com.example.dtic_123.jhonson_algoritmica;

import android.util.Log;
import android.util.LogPrinter;

/**
 * Created by DTIC-123 on 28/03/2017.
 */

public class Actividad {

   // private Algoritmo alg;

    private Nodo nodoLLegada;
    private Nodo nodoSalida;
    private Arista arista;
    private int valorCasillaDeIda = 0;
    private Actividad[] secDeActividades; //no sé si es legal xD
    private int valorCasillaDeVuelta = 0;

    private int holgura;
    private String nombre;

    public Actividad(Nodo nodoSalida, Nodo nodoLlegada, Arista arista, String nombre) {
        this.nodoSalida = nodoSalida;
        this.arista = arista;
        this.nodoLLegada = nodoLlegada;
        this.nombre = nombre;
     //   this.alg = alg;

    }

    public void llenarValoresDeIda() {
        valorCasillaDeIda = nodoSalida.getValorIda()+arista.getAtributo();
        if(valorCasillaDeIda > nodoLLegada.getValorIda()) {
            nodoLLegada.setValorIda(valorCasillaDeIda);
            Log.i("algoritmo", "El valor en casilla de ida en el nodo "+nodoLLegada.getNombre() +" es "+nodoLLegada.getValorIda());
        }
    }

    public void llenarValoresDeVuelta() {
        valorCasillaDeVuelta = nodoLLegada.getValorVuelta()-arista.getAtributo();
        if(nodoSalida.getValorVuelta() == 0 || valorCasillaDeVuelta < nodoSalida.getValorVuelta()) {
            nodoSalida.setValorVuelta(valorCasillaDeVuelta);
            Log.i("algoritmo", "El valor en casilla de vuelta en el nodo "+nodoSalida.getNombre() +" es "+nodoSalida.getValorVuelta());
        }
    }

    public int hallarValorCritico() {
        holgura = nodoLLegada.getValorVuelta()-nodoSalida.getValorIda()-arista.getAtributo();
        Log.i("algoritmo", "La holgura de la actividad "+nombre+ " es "+holgura);
        return holgura;
       }

    public Nodo getNodoLLegada() {
        return nodoLLegada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNodoLLegada(Nodo nodoLLegada) {
        this.nodoLLegada = nodoLLegada;
    }

    public Nodo getNodoSalida() {
        return nodoSalida;
    }

    public void setNodoSalida(Nodo nodoSalida) {
        this.nodoSalida = nodoSalida;
    }

    public Arista getArista() {
        return arista;
    }

    public void setArista(Arista arista) {
        this.arista = arista;
    }

    public int getValorCasillaDeIda() {
        return valorCasillaDeIda;
    }

    public void setValorCasillaDeIda(int valorCasillaDeIda) {
        this.valorCasillaDeIda = valorCasillaDeIda;
    }

    public Actividad[] getSecDeActividades() {
        return secDeActividades;
    }

    public void setSecDeActividades(Actividad[] secDeActividades) {
        this.secDeActividades = secDeActividades;
    }

}
