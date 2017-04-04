/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Constante.Constante;
import Ejecucion_GraphiK.Ambito;
import Ejecucion_GraphiK.Arreglo;
import Ejecucion_GraphiK.Objeto;
import Ejecucion_GraphiK.Simbolo;
import Interface.TitusNotificaciones;
import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class FGraphikar {

    public FNodoExpresion Arreglo1, Arreglo2;
    public Ambito Ambito;

    public FGraphikar(FNodoExpresion arreglo1, FNodoExpresion arreglo2, Ambito ambito) {
        this.Arreglo1 = arreglo1;
        this.Arreglo2 = arreglo2;
        this.Ambito = ambito;
    }

    ArrayList<Double> PosX = new ArrayList<>();
    ArrayList<Double> PosY = new ArrayList<>();

    public void Ejecutar(Objeto Tabla, Simbolo sim, Objeto Padre) {
        FNodoExpresion x = this.Arreglo1.ResolverExpresion(Padre);
        FNodoExpresion y = this.Arreglo2.ResolverExpresion(Padre);

        if ((x.Tipo.equals(Constante.TVariableArreglo) || x.Tipo.equals(Constante.TArreglo)) && (y.Tipo.equals(Constante.TArreglo) || y.Tipo.equals(Constante.TVariableArreglo))) {
            if (x.Tipo.equals(Constante.TVariableArreglo)) {
                LlenarporVariableX(x.ArregloResuelto);
            }else{
                LlenarporArregloX(x.Arreglo);
            }

            if (y.Tipo.equals(Constante.TVariableArreglo)) {
                LlenarporVariableY(y.ArregloResuelto);
            }else{
                LlenarporArregloY(y.Arreglo);
            }

            if (PosX.size() == PosY.size()) {

                TitusNotificaciones.InsertarGrafico(PosX, PosY);

            } else {
                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede graficar, los arreglos no tienen la misma cantidad de elementos", sim.Fila, sim.Columna);
            }
        } else {
            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El metodo grphikar_funcion esperaba dos arreglos como parametros", sim.Fila, sim.Columna);
        }
    }

    public void LlenarporVariableX(Arreglo arreglo) {
        int i = 0;
        while (i < arreglo.Posiciones.size()) {
            FNodoExpresion pos = arreglo.Posiciones.get(i);
            if (pos == null) {
                PosX.add(0.0);
            } else {
                if (pos.Tipo.equals(Constante.TEntero) || pos.Tipo.equals(Constante.TDecimal)) {
                    if (pos.Tipo.equals(Constante.TEntero)) {
                        Double aux = Double.valueOf(String.valueOf(pos.Entero));
                        PosX.add(aux);
                    } else {
                        PosX.add(pos.Decimal);
                    }
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede graficar un tipo " + pos.Tipo, pos.Fila, pos.Columna);
                }
            }
            i++;
        }
    }

    public void LlenarporVariableY(Arreglo arreglo) {
        int i = 0;
        while (i < arreglo.Posiciones.size()) {
            FNodoExpresion pos = arreglo.Posiciones.get(i);
            if (pos == null) {
                PosY.add(0.0);
            } else {
                if (pos.Tipo.equals(Constante.TEntero) || pos.Tipo.equals(Constante.TDecimal)) {
                    if (pos.Tipo.equals(Constante.TEntero)) {
                        Double aux = Double.valueOf(String.valueOf(pos.Entero));
                        PosY.add(aux);
                    } else {
                        PosY.add(pos.Decimal);
                    }
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede graficar un tipo " + pos.Tipo, pos.Fila, pos.Columna);
                }
            }
            i++;
        }
    }

    public void LlenarporArregloX(FNodoArreglo arreglo) {
        if (arreglo.Arreglo.get(0).equals(Constante.TArreglo)) {
            int i = 0;
            while(i<arreglo.Arreglo.size()){
                LlenarporArregloX(arreglo.Arreglo.get(i).Arreglo);
                i++;
            }
        } else {
            int i = 0;
            while (i < arreglo.Arreglo.size()) {
                FNodoExpresion p = arreglo.Arreglo.get(i);
                if (p.Tipo.equals(Constante.TEntero) || p.Tipo.equals(Constante.TDecimal)) {
                    if (p.Tipo.equals(Constante.TEntero)) {
                        Double aux = Double.valueOf(String.valueOf(p.Entero));
                        PosX.add(aux);
                    } else {
                        PosX.add(p.Decimal);
                    }
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede graficar un tipo " + p.Tipo, p.Fila, p.Columna);
                }
                i++;
            }
        }
    }
    
    public void LlenarporArregloY(FNodoArreglo arreglo) {
        if (arreglo.Arreglo.get(0).equals(Constante.TArreglo)) {
            int i = 0;
            while(i<arreglo.Arreglo.size()){
                LlenarporArregloX(arreglo.Arreglo.get(i).Arreglo);
                i++;
            }
        } else {
            int i = 0;
            while (i < arreglo.Arreglo.size()) {
                FNodoExpresion p = arreglo.Arreglo.get(i);
                if (p.Tipo.equals(Constante.TEntero) || p.Tipo.equals(Constante.TDecimal)) {
                    if (p.Tipo.equals(Constante.TEntero)) {
                        Double aux = Double.valueOf(String.valueOf(p.Entero));
                        PosY.add(aux);
                    } else {
                        PosY.add(p.Decimal);
                    }
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "No se puede graficar un tipo " + p.Tipo, p.Fila, p.Columna);
                }
                i++;
            }
        }
    }
}
