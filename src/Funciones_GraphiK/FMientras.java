/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Constante.Constante;
import Ejecucion_GraphiK.*;
import Interface.TitusNotificaciones;
import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class FMientras {

    public Ambito Ambito;
    public FNodoExpresion Condicion;

    public FMientras(FNodoExpresion condicion, Ambito ambito) {
        this.Ambito = ambito;
        this.Condicion = condicion;
    }

    public void EjecutarMientras(Objeto Tabla, Simbolo instruccion, Objeto padre) {
        FNodoExpresion condicion = this.Condicion.ResolverExpresion(padre);
        if (condicion.Tipo.equals(Constante.TVariableArreglo)) {
            condicion = condicion.PosArreglo;
        }
        if (TitusNotificaciones.ContarErrores()) {
            if (condicion.Tipo.equals(Constante.TBool)) {
                while (TitusNotificaciones.ContarErrores() && condicion.Bool && !Tabla.TablaVariables.IsRertorno() && !Tabla.TablaVariables.IsTerminar()) {
                    FMetodo metodo = new FMetodo(Constante.TPublico, new ArrayList<Simbolo>(), this.Ambito, 0, 0, Constante.TVacio, Constante.TSi);
                    metodo.EjecutarInstrucciones(Ambito.TablaSimbolo, Tabla, padre);

                    if (Tabla.TablaVariables.IsContinuar()) {
                        Tabla.TablaVariables.SacarVariable();
                    }

                    condicion = this.Condicion.ResolverExpresion(padre);
                    if (condicion.Tipo.equals(Constante.TVariableArreglo)) {
                        condicion = condicion.PosArreglo;
                    }
                    metodo.SacarAmbito(Ambito.TablaSimbolo, Tabla);
                }
                if (Tabla.TablaVariables.IsTerminar()) {
                    Tabla.TablaVariables.SacarVariable();
                }

            } else {
                if (condicion.Tipo.equals(Constante.TObjeto)) {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Se esperaba un tipo bool no un tipo " + condicion.Nombre, Condicion.Fila, Condicion.Columna);
                } else {
                    TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Se esperaba un tipo bool no un tipo " + condicion.Tipo, Condicion.Fila, Condicion.Columna);
                }
            }
        }
    }
}
