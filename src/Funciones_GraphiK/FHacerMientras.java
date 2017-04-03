/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Constante.Constante;
import Ejecucion_GraphiK.Ambito;
import Ejecucion_GraphiK.Objeto;
import Ejecucion_GraphiK.Simbolo;
import Interface.TitusNotificaciones;
import java.util.ArrayList;

/**
 *
 * @author Titus
 */
public class FHacerMientras {
    public Ambito Ambito;
    public FNodoExpresion Condicion;
   
    public FHacerMientras(FNodoExpresion condicion, Ambito ambito){
        this.Ambito = ambito;
        this.Condicion = condicion;
    }
    
    public void EjecutarHacerMientras(Objeto Tabla, Simbolo instruccion, Objeto padre) {
        FNodoExpresion condicion = this.Condicion.ResolverExpresion(padre);
        if (TitusNotificaciones.ContarErrores()) {
            if (condicion.Tipo.equals(Constante.TBool)) {
                do{
                    FMetodo metodo = new FMetodo(Constante.TPublico, new ArrayList<Simbolo>(), this.Ambito, 0, 0, Constante.TVacio, Constante.TSi);
                    metodo.EjecutarInstrucciones(Ambito.TablaSimbolo, Tabla, padre);
                    
                    if(Tabla.TablaVariables.IsContinuar()){
                        Tabla.TablaVariables.SacarVariable();
                    }
                    
                    condicion = this.Condicion.ResolverExpresion(padre);
                    
                    metodo.SacarAmbito(Ambito.TablaSimbolo, Tabla);
                }while (TitusNotificaciones.ContarErrores() && condicion.Bool && !Tabla.TablaVariables.IsRertorno() && !Tabla.TablaVariables.IsTerminar());
                 
                if(Tabla.TablaVariables.IsTerminar()){
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
