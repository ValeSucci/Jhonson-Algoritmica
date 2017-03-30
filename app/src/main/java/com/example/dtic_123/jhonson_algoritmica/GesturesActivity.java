package com.example.dtic_123.jhonson_algoritmica;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class GesturesActivity extends Activity {

    public static final String DEBUG_TAG = "GesturesActivity";
    private RelativeLayout layout2;
    private int posX;
    private int posY;
    private Lienzo fondoCreo;
    private Context context;
    private int numeroDeNodo = 0;

    private ArrayList<Nodo> listNodos = new ArrayList<>();
    private ArrayList<Nodo> nodosDeAct = new ArrayList<>();

    private Algoritmo alg = new Algoritmo();
    private Nodo nodo;
    private boolean mismoNodo = false;
    private boolean actividadCompleta = false;
    private Arista arista;
    private ArrayList<Arista> listAristas = new ArrayList<>();

    private Button btnAniadirAristas;

    private boolean colocarNodos = true;
    private boolean colocarAristas = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestures);

        layout2 = (RelativeLayout) findViewById(R.id.layout2);
        btnAniadirAristas = (Button) findViewById(R.id.btnAniadirAristas);
        context = this;

        alg.llenarListaDeAct();
        alg.llenarListaDeActInv();


        btnAniadirAristas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colocarNodos = false;
                colocarAristas = true;
                // TODO borrar este boton y crear otro
                Button btnEjecutarAlgoritmo = new Button(context);
                btnEjecutarAlgoritmo.setText("Ejecutar Algoritmo");
                btnEjecutarAlgoritmo.setLayoutParams(btnAniadirAristas.getLayoutParams());
                //setContentView(btnEjecutarAlgoritmo);
            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        posX = (int) event.getX();
        posY = (int) event.getY();

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                Log.d(DEBUG_TAG, "La accion ha sido ABAJO en ("+posX+", "+posY);
                return true;
            case (MotionEvent.ACTION_MOVE):
                Log.d(DEBUG_TAG, "La accion ha sido MOVER en ("+posX+", "+posY);
                return true;
            case (MotionEvent.ACTION_UP):
                Log.d(DEBUG_TAG, "La accion ha sido ARRIBA en ("+posX+", "+posY);
                alg.ejecutarAlgoritmo();
                Log.i("lienzo", "añade el View de lienzo");
                int posAristaInX = 0;
                int posAristaInY = 0;
                int posAristaFinX = 0;
                int posAristaFinY = 0;
                //presiona dentro del nodo
                if(!listNodos.isEmpty()) {
                    for (Nodo n : listNodos) {
                        if ((posX <= n.getPosX() + 40 && posX >= n.getPosX() - 40) && (posY <= n.getPosY() + 40 && posY >= n.getPosY() - 40)) {
                            mismoNodo = true;
                            //if presiono el boton para añadir actividades
                            if(colocarAristas) {
                                n.setColor(Color.BLUE);
                                if (nodosDeAct.size() < 2) {
                                    nodosDeAct.add(n);
                                    if (nodosDeAct.size() == 2) {
                                        actividadCompleta = true;
                                        posAristaInX = nodosDeAct.get(0).getPosX();
                                        posAristaInY = nodosDeAct.get(0).getPosY();
                                        posAristaFinX = nodosDeAct.get(1).getPosX();
                                        posAristaFinY = nodosDeAct.get(1).getPosY();
                                        Log.i("arista", "inicia en " + nodosDeAct.get(0).getNombre() + " y acaba en " + nodosDeAct.get(1).getNombre());
                                        nodosDeAct.clear();
                                    } else {
                                        actividadCompleta = false;
                                    }
                                } else {
                                    actividadCompleta = false;
                                }
                            }
                            break;
                        } else {
                            mismoNodo = false;
                        }
                    }
                    if (!mismoNodo && colocarNodos) {
                        nodo = new Nodo("N" + numeroDeNodo, posX, posY);
                        listNodos.add(nodo);
                        numeroDeNodo++;
                    }
                    if(actividadCompleta && colocarAristas) {
                        arista = new Arista(0,posAristaInX, posAristaInY, posAristaFinX, posAristaFinY);
                        listAristas.add(arista);
                        Log.i("arista", "se ha aniadido la arista en posiciones ("+posAristaInX+", "+posAristaInY+") a ("+posAristaFinX+", "+posAristaFinY+")");
                    }
                } else {
                    if(colocarNodos) {
                        nodo = new Nodo("N" + numeroDeNodo, posX, posY);
                        listNodos.add(nodo);
                        numeroDeNodo++;
                    }
                }
                fondoCreo = new Lienzo(context);
                layout2.addView(fondoCreo);

                return true;
            case (MotionEvent.ACTION_CANCEL):
                Log.d(DEBUG_TAG, "La accion ha sido CANCEL en ("+posX+", "+posY);
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                Log.d(DEBUG_TAG, "La accion ha sido fuera del elemento de la pantallaen ("+posX+", "+posY);
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    class Lienzo extends View {

        public Lienzo(Context context) {
            super(context);
            Log.i("lienzo","crear un lienzo");
        }

        protected void onDraw(Canvas canvas) {
            Log.i("lienzo", "onDraw del lienzo");
            canvas.drawRGB(255, 255, 0);
            Paint pincel1 = new Paint();
            pincel1.setARGB(255, 255, 0, 0);
            pincel1.setStrokeWidth(10);
            pincel1.setStyle(Paint.Style.STROKE);
            Paint pincel2 = new Paint();
            pincel2.setARGB(255, 255, 0, 0);
            pincel2.setStrokeWidth(2);
            pincel2.setStyle(Paint.Style.STROKE);
            Paint pincel3 = new Paint();
            pincel3.setARGB(255, 255, 0, 0);
            pincel3.setStrokeWidth(5);
            pincel3.setStyle(Paint.Style.FILL);
            Paint pincel4 = new Paint();
            pincel4.setARGB(255, 255, 0, 0);
            pincel4.setStrokeWidth(3);
            pincel4.setStyle(Paint.Style.STROKE);
            pincel1.setColor(Color.BLACK);
            pincel2.setColor(Color.BLACK);
            pincel3.setColor(Color.GREEN);
            pincel4.setColor(Color.BLACK);
            canvas.drawColor(Color.CYAN);
            for(Nodo n : listNodos) {
                pincel1.setColor(n.getColor());
                pincel2.setColor(n.getColor());
                canvas.drawCircle(n.getPosX(), n.getPosY(), 40, pincel1);
                canvas.drawText(n.getNombre(), n.getPosX(), n.getPosY(), pincel2);
                if(!listAristas.isEmpty()) {
                    Log.i("arista", "la lista de aristas no esta vacia");
                    for(Arista a : listAristas) {
                        canvas.drawLine(a.getPosInicialX(), a.getPosInicialY(), a.getPosFinalX(), a.getPosFinalY(), pincel3);
                        //dibujar tringulito
                        Path pathTri = new Path();
                        pathTri.moveTo(75,50);
                        pathTri.lineTo(100, 75);
                        pathTri.lineTo(100,25);
//                        pathTri.setFillType(Path.FillType.EVEN_ODD);
  /*                      canvas.path
                        ctx.beginPath();
                        ctx.moveTo(75,50);
                        ctx.lineTo(100,75);
                        ctx.lineTo(100,25);
                        ctx.fill();*/
                        canvas.drawPath(pathTri, pincel3);
                        //TODO hacer que aparezcan los attr
                        // canvas.drawText(""+a.getAtributo(),abs(a.getPosFinalX()-a.getPosInicialX())/2, abs(a.getPosFinalY()-a.getPosFinalY())/2,pincel4);
                        Log.i("arista", "se deberia estar dibujando una arista .-. en pos "+a.getPosInicialX()+a.getPosFinalX());
                        Log.i("arista", "el attr de la arista es "+a.getAtributo()+"en pos ("+abs(a.getPosFinalX()-a.getPosInicialX())/2+", " +abs(a.getPosFinalY()-a.getPosFinalY())/2+")");
                    }
                }
            }
        }
    }
}

