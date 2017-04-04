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
import Ejecucion_GraphiK.ControlArchivo;

/**
 *
 * @author Titus
 */
public class FMetodo {

    public Ambito Ambito;
    public ArrayList<Simbolo> Parametros;
    public int Fila, Columna;
    public String Tipo, Nombre;
    public String Visibilidad;

    public FMetodo(String visibilidad, ArrayList<Simbolo> parametro, Ambito ambito, int fila, int columna, String tipo, String nombre) {
        this.Visibilidad = visibilidad;
        this.Ambito = ambito;
        this.Parametros = parametro;
        this.Fila = fila;
        this.Columna = columna;
        this.Tipo = tipo;
        this.Nombre = nombre;
    }

    public void EjecutarInstrucciones(ArrayList<Simbolo> instrucciones, Objeto Tabla, Objeto Padre, int pos) {
        if (TitusNotificaciones.ContarErrores()) {
            for (Simbolo instruccion : instrucciones) {
                if (TitusNotificaciones.ContarErrores() && !Tabla.TablaVariables.IsContinuar() && !Tabla.TablaVariables.IsTerminar() && !Tabla.TablaVariables.IsRertorno()) {
                    switch (instruccion.Rol) {
                        case Constante.TImprimir:
                            EjecutarMetodoImprimir(instruccion, Tabla, Padre);
                            break;
                        case Constante.TMetodo:
                            EjecutarMetodo(instruccion, Tabla, Padre, pos);
                            break;
                        case Constante.TDeclaracion:
                            EjecutarDeclaracion(instruccion, Tabla, Padre);
                            break;
                        case Constante.TAsignacion:
                            EjecutarAsignacion(instruccion, Tabla, Padre);
                            break;
                        case Constante.TRetornar:
                            EjecutarRetorno(Tabla, instruccion);
                            break;
                        case Constante.TContinuar:
                            EjecutarContinuar(Tabla, instruccion);
                            break;
                        case Constante.TTerminar:
                            EjecutarTerminar(Tabla, instruccion);
                            break;
                        case Constante.TSi:
                            EjecutarSi(instruccion, Tabla, Padre);
                            break;
                        case Constante.TMientras:
                            EjecutarMientras(instruccion, Tabla, Padre);
                            break;
                        case Constante.TSeleccion:
                            EjecutarSelecciona(instruccion, Tabla, Padre);
                            break;
                        case Constante.TPara:
                            EjecutarPara(instruccion, Tabla, Padre);
                            break;

                        case Constante.THacer:
                            EjecutarHacerMientras(instruccion, Tabla, Padre);
                            break;

                        case Constante.TGraficarFuncion:
                            EjecutarGraficarFuncion(instruccion, Tabla, Padre);
                            break;
                            
                        case Constante.TDatos:
                            EjecutarDatos(instruccion, Tabla, Padre);
                            break;
                            
                        default:
                            break;
                    }
                }
            }
        }
    }

    public void EjecutarDatos(Simbolo sim, Objeto Tabla, Objeto Padre){
        FDatos datos = new FDatos();
        datos.EjecutarDatos(sim, Tabla, Padre);
    }
    
     public void EjecutarGraficarFuncion(Simbolo sim, Objeto Tabla, Objeto Padre) {
        FGraphikar graficar = (FGraphikar) sim.Valor;
        graficar.Ejecutar(Tabla, sim, Padre);
    }

    public void EjecutarHacerMientras(Simbolo sim, Objeto Tabla, Objeto Padre) {
        FHacerMientras mientras = (FHacerMientras) sim.Valor;
        mientras.EjecutarHacerMientras(Tabla, sim, Padre);
    }

    public void EjecutarPara(Simbolo sim, Objeto Tabla, Objeto Padre) {
        FPara para = (FPara) sim.Valor;
        para.EjecutarPara(Tabla, sim, Padre);
    }

    public void EjecutarSi(Simbolo sim, Objeto Tabla, Objeto Padre) {
        FSi si = (FSi) sim.Valor;
        si.EjecutarSi(Tabla, sim, Padre);
    }

    public void EjecutarSelecciona(Simbolo sim, Objeto Tabla, Objeto Padre) {
        FSelecciona selecciona = (FSelecciona) sim.Valor;
        selecciona.EjecutarSelecciona(Tabla, sim, Padre);
    }

    public void EjecutarMientras(Simbolo sim, Objeto Tabla, Objeto Padre) {
        FMientras mientras = (FMientras) sim.Valor;
        mientras.EjecutarMientras(Tabla, sim, Padre);
    }

    public void EjecutarRetorno(Objeto Tabla, Simbolo instruccion) {
        FNodoExpresion exp = (FNodoExpresion) instruccion.Valor;        
        exp = exp.ResolverExpresion(Tabla, 1);
        if (exp.Tipo.equals(Constante.TVariableArreglo)) {
            exp = exp.PosArreglo;
        }
        Tabla.TablaVariables.InsertarVariable(new Variable(Constante.Graphik, instruccion.Visibilidad, exp.Nombre, instruccion.Nombre, instruccion.Nombre, instruccion.Fila, instruccion.Columna, instruccion.Ambito, exp, Tabla));
    }

    public void EjecutarTerminar(Objeto Tabla, Simbolo sim) {
        Tabla.TablaVariables.InsertarVariable(new Variable(Constante.Graphik, sim.Visibilidad, sim.Tipo, sim.Nombre, sim.Rol, sim.Fila, sim.Columna, sim.Ambito, Tabla, Tabla));
    }

    public void EjecutarContinuar(Objeto Tabla, Simbolo sim) {
        Tabla.TablaVariables.InsertarVariable(new Variable(Constante.Graphik, sim.Visibilidad, sim.Tipo, sim.Nombre, sim.Rol, sim.Fila, sim.Columna, sim.Ambito, Tabla, Tabla));
    }

    public Variable EjecutarMetodo(FLlamadaMetodo llamada, Objeto Tabla, Variable metodo, Objeto Padre, int pos) {
        Variable resultado = null;
        if (this.Parametros.size() == llamada.Parametros.size()) {
            int cont = 0;
            //metemos la variables de los parametros a la tabla
            while (cont < this.Parametros.size() && TitusNotificaciones.ContarErrores()) {
                //aqui le enviamos al padre
                FNodoExpresion resultadoparametro = llamada.Parametros.get(cont).ResolverExpresion(Padre, pos);
                if (resultadoparametro.Tipo.equals(Constante.TVariableArreglo)) {
                    resultadoparametro = resultadoparametro.PosArreglo;
                }
                if (TitusNotificaciones.ContarErrores()) {
                    if (this.Parametros.get(cont).Tipo.equals(resultadoparametro.Tipo) || (resultadoparametro.Tipo.equals(Constante.TObjeto) && this.Parametros.get(cont).Tipo.equals(resultadoparametro.Nombre))) {
                        ///////////////////////
                        FDeclaracion fd = (FDeclaracion) this.Parametros.get(cont).Valor;
                        //le asignamos el valor a la declaracion
                        fd.Valor = resultadoparametro;
                        //ejecutamos la declaracion---------------->
                        fd.EjecutarDeclaracion(Tabla, this.Parametros.get(cont), Padre);
                    } else {
                        if (resultadoparametro.Tipo.equals(Constante.TObjeto)) {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Se esperaba un tipo " + this.Parametros.get(cont).Tipo + ", no un tipo " + resultadoparametro.Nombre, llamada.Fila, llamada.Columna);

                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Se esperaba un tipo " + this.Parametros.get(cont).Tipo + ", no un tipo " + resultadoparametro.Tipo, llamada.Fila, llamada.Columna);
                        }
                    }
                }
                cont++;
            }

            //ejecutamos el cuerpo
            if (TitusNotificaciones.ContarErrores()) {
                EjecutarInstrucciones(this.Ambito.TablaSimbolo, Tabla, Tabla, pos);

                //obtenemos el valor del retun si hay
                if (Tabla.TablaVariables.IsRertorno()) {
                    Variable v = Tabla.TablaVariables.ObtenerTope();
                    FNodoExpresion exp = (FNodoExpresion) v.Valor;
                    if (exp.Tipo.equals(this.Tipo) || exp.Tipo.equals(Constante.TObjeto) && exp.Nombre.equals(this.Tipo)) {
                        resultado = v;
                    } else {
                        if (exp.Tipo.equals(Constante.TObjeto) && exp.Nombre.equals(this.Tipo)) {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El metodo " + this.Nombre + " es del tipo " + this.Tipo + " y se retorno un valor tipo " + exp.Nombre, this.Fila, this.Columna);
                        } else {
                            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El metodo " + this.Nombre + " es del tipo " + this.Tipo + " y se retorno un valor tipo " + exp.Tipo, this.Fila, this.Columna);
                        }
                    }
                    Tabla.TablaVariables.SacarVariable();
                }
                SacarAmbito(Ambito.TablaSimbolo, Tabla);
                SacarAmbito(this.Parametros, Tabla);
            }
        } else {
            TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "El metodo esperaba " + this.Parametros.size() + " parametros", llamada.Fila, llamada.Columna);
        }
        return resultado;
    }

    public void EjecutarDeclaracion(Simbolo instruccion, Objeto Tabla, Objeto Padre) {
        FDeclaracion decla = (FDeclaracion) instruccion.Valor;
        decla.EjecutarDeclaracion(Tabla, instruccion, Padre);
    }

    public void EjecutarAsignacion(Simbolo instruccion, Objeto Tabla, Objeto Padre) {
        FAsignacion asigna = (FAsignacion) instruccion.Valor;
        asigna.EjecutarAsignacion(Tabla, instruccion, Padre);
    }

    public void EjecutarMetodo(Simbolo instruccion, Objeto Tabla, Objeto Padre, int pos) {
        FLlamadaObjeto metodo = (FLlamadaObjeto) instruccion.Valor;
        metodo.Ejecutar(Tabla, Padre, pos);

    }

    public void EjecutarMetodoImprimir(Simbolo instruccion, Objeto Tabla, Objeto Padre) {
        FImprimir metodo = (FImprimir) instruccion.Valor;
        metodo.Imprimir(Tabla, Padre);
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
