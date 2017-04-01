/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Ejecucion_GraphiK.Ambito;
import Ejecucion_GraphiK.Objeto;
import Ejecucion_GraphiK.Simbolo;
import Ejecucion_GraphiK.Variable;
import Interface.TitusNotificaciones;
import java.util.ArrayList;
import Constante.Constante;

/**
 *
 * @author Titus
 */
public class FMetodo {

    public Ambito Ambito;
    public ArrayList<Simbolo> Parametros;

    public FMetodo(ArrayList<Simbolo> parametro, Ambito ambito) {
        this.Ambito = ambito;
        this.Parametros = parametro;
    }

        
    public void EjecutarMetodo(FLlamadaMetodo llamada, Objeto Tabla, Variable metodo) {
        if (this.Parametros.size() == llamada.Parametros.size()) {
            int cont = 0;
            //metemos la variables de los parametros a la tabla
            while (cont < this.Parametros.size() && TitusNotificaciones.ContarErrores()) {
                FNodoExpresion resultadoparametro = llamada.Parametros.get(cont).ResolverExpresion(Tabla);
                if (TitusNotificaciones.ContarErrores()) {
                    if (this.Parametros.get(cont).Tipo.equals(resultadoparametro.Tipo)) {
                        Variable parametro = new Variable(Constante.Graphik, Constante.TPublico, this.Parametros.get(0).Tipo, this.Parametros.get(0).Nombre, this.Parametros.get(0).Rol, this.Parametros.get(0).Fila, this.Parametros.get(0).Columna, null, resultadoparametro, Tabla);
                        Tabla.TablaVariables.InsertarVariable(parametro);
                    } else {
                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Se esperaba un tipo " + this.Parametros.get(cont).Tipo + ", no un tipo " + resultadoparametro.Tipo, llamada.Fila, llamada.Columna);
                    }
                }
                cont++;
            }

            //ejecutamos el cuerpo
            if (TitusNotificaciones.ContarErrores()) {
                EjecutarInstrucciones(this.Ambito.TablaSimbolo, Tabla);

                //obtenemos el valor del retun si hay
                if (Tabla.TablaVariables.IsRertorno()) {
                    //obtener retorno
                }
                SacarAmbito(Ambito.TablaSimbolo, Tabla);
                SacarAmbito(this.Parametros, Tabla);
                
            }
        }

    }

    public void EjecutarInstrucciones(ArrayList<Simbolo> instrucciones, Objeto Tabla) {
        for (Simbolo instruccion : instrucciones) {
            if (instruccion.Rol.equals(Constante.TImprimir)) {
                EjecutarMetodoImprimir(instruccion, Tabla);
            } else if (instruccion.Rol.equals(Constante.TMetodo)) {
                EjecutarMetodo(instruccion, Tabla);
            } else if(instruccion.Rol.equals(Constante.TDeclaracion)){
                
            }
        }
    }   
    
    
    public void EjecutarMetodo(Simbolo instruccion, Objeto Tabla) {
        FLlamadaObjeto metodo = (FLlamadaObjeto) instruccion.Valor;
        metodo.Ejecutar(Tabla);

    }


    public void EjecutarMetodoImprimir(Simbolo instruccion, Objeto Tabla) {
        FImprimir metodo = (FImprimir) instruccion.Valor;

        metodo.Imprimir(Tabla);
    }

    
    
    public void SacarAmbito(ArrayList<Simbolo> ambito, Objeto tabla) {
        int cont = ambito.size() - 1;

        while (cont >= 0) {
            if (ambito.get(cont).Rol.equals(Constante.TDeclaracion)) {
                if (tabla.TablaVariables.ExisteVariableTope(ambito.get(cont).Nombre)) {
                    tabla.TablaVariables.SacarVariable(ambito.get(cont).Nombre);
                }
            }
            cont--;
        }
    }
}
