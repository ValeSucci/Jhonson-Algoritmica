package com.example.dtic_123.jhonson_algoritmica;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by DTIC-123 on 28/03/2017.
 */

public class Algoritmo {

    private Nodo a = new Nodo("A");
    private Nodo b = new Nodo("B");
    private Nodo c = new Nodo("C");
    private Nodo d = new Nodo("D");

    private ArrayList<Actividad> listAct = new ArrayList<>();
    private ArrayList<Actividad> listActInv = new ArrayList<>();
    private ArrayList<Actividad> listActRedCritica = new ArrayList<>();

    private Actividad act1 = new Actividad(a, b, new Arista(3), "1");
    private Actividad act2 = new Actividad(a, c, new Arista(5), "2");
    private Actividad act3 = new Actividad(b, d, new Arista(4), "3");
    private Actividad act4 = new Actividad(c, d, new Arista(1), "4");


    //TODO ordenar la lista según la secuencia de cada actividad
    public void llenarListaDeAct () {
        listAct.add(act1);
        listAct.add(act2);
        listAct.add(act3);
        listAct.add(act4);
    }

    public void ejecutarAlgoritmo() {
        //llenar las casillas de valores de ida
        for(Actividad act : listAct) {
            act.llenarValoresDeIda();
 //           Log.i("algo", "se tiene la lista "+act.getNombre());
        }

        //llenar la casilla de valor de vuelta del ultimo nodo de la ultima actividad
        Actividad ultimaAct = listAct.get(listAct.size()-1);
        ultimaAct.getNodoLLegada().setValorVuelta(ultimaAct.getNodoLLegada().getValorIda());
        //Log.i("algo","el valor de vuelta del ultimo nodo de la ultima actividad es "+ultimaAct.getNodoLLegada().getValorVuelta());

        //llenar las casillas de valores de vuelta
        for(Actividad act : listActInv) {
            act.llenarValoresDeVuelta();
        }

        //hallar la holgura para cada actividad y guardar la actividad en una lista de red critica
        for (Actividad act : listAct) {
            if(act.hallarValorCritico() == 0) {
                listActRedCritica.add(act);
                Log.i("algo","La Red Critica esta compuesta por "+ act.getNombre());
            }
        }
    }

    public void llenarListaDeActInv() {
        for(int i = listAct.size()-1; i >= 0 ;i--) {
            listActInv.add(listAct.get(i));
//            Log.i("algo", "se invierte la lista de manera que "+listAct.get(i).getNombre());
        }
    }
}
