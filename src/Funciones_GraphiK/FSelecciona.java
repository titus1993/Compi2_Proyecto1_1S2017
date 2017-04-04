/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_GraphiK;

import Constante.Constante;
import java.util.ArrayList;
import Ejecucion_GraphiK.*;
import Interface.TitusNotificaciones;

/**
 *
 * @author Titus
 */
public class FSelecciona {

    public ArrayList<FCaso> Casos;
    public Ambito Ambito;
    public FCaso Defecto;
    public FNodoExpresion Condicion;

    public FSelecciona(FNodoExpresion condicion, ArrayList<FCaso> casos, FCaso defecto, Ambito ambito) {
        this.Casos = casos;
        this.Defecto = defecto;
        this.Ambito = ambito;
        this.Condicion = condicion;
    }

    public void EjecutarSelecciona(Objeto Tabla, Simbolo instruccion, Objeto padre) {
        FNodoExpresion condicion = this.Condicion.ResolverExpresion(padre);
        if (condicion.Tipo.equals(Constante.TVariableArreglo)) {
            condicion = condicion.PosArreglo;
        }
        if (TitusNotificaciones.ContarErrores()) {
            int cont = 0;
            int posencontrado = -1;
            Boolean encontrado = false;
            while (cont < Casos.size() && !Tabla.TablaVariables.IsTerminar() && !Tabla.TablaVariables.IsRertorno() && TitusNotificaciones.ContarErrores()) {
                if (Casos.get(cont).Valor.Tipo.equals(condicion.Tipo)) {
                    if (!encontrado) {
                        if (Casos.get(cont).Valor.Tipo.equals(Constante.TEntero)) {
                            if (Casos.get(cont).Valor.Entero == condicion.Entero) {
                                FMetodo metodo = new FMetodo(Constante.TPublico, new ArrayList<Simbolo>(), this.Casos.get(cont).Ambito, 0, 0, Constante.TVacio, Constante.TCase);
                                metodo.EjecutarInstrucciones(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla, padre);
                                encontrado = true;
                                posencontrado = cont;
                                if (Tabla.TablaVariables.IsTerminar()) {
                                    Variable tope = Tabla.TablaVariables.ObtenerTope();
                                    Tabla.TablaVariables.SacarVariable();
                                    metodo.SacarAmbito(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla);
                                    Tabla.TablaVariables.InsertarVariable(tope);
                                }
                                //metodo.SacarAmbito(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla);
                            }
                        } else if (Casos.get(cont).Valor.Tipo.equals(Constante.TDecimal)) {
                            if (Casos.get(cont).Valor.Decimal == condicion.Decimal) {
                                FMetodo metodo = new FMetodo(Constante.TPublico, new ArrayList<Simbolo>(), this.Casos.get(cont).Ambito, 0, 0, Constante.TVacio, Constante.TCase);
                                metodo.EjecutarInstrucciones(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla, padre);
                                encontrado = true;
                                posencontrado = cont;
                                if (Tabla.TablaVariables.IsTerminar()) {
                                    Variable tope = Tabla.TablaVariables.ObtenerTope();
                                    Tabla.TablaVariables.SacarVariable();
                                    metodo.SacarAmbito(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla);
                                    Tabla.TablaVariables.InsertarVariable(tope);
                                }
                                //metodo.SacarAmbito(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla);

                            }
                        } else if (Casos.get(cont).Valor.Tipo.equals(Constante.TCadena)) {
                            if (Casos.get(cont).Valor.Cadena.equals(condicion.Cadena)) {
                                FMetodo metodo = new FMetodo(Constante.TPublico, new ArrayList<Simbolo>(), this.Casos.get(cont).Ambito, 0, 0, Constante.TVacio, Constante.TCase);
                                metodo.EjecutarInstrucciones(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla, padre);
                                encontrado = true;
                                posencontrado = cont;
                                if (Tabla.TablaVariables.IsTerminar()) {
                                    Variable tope = Tabla.TablaVariables.ObtenerTope();
                                    Tabla.TablaVariables.SacarVariable();
                                    metodo.SacarAmbito(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla);
                                    Tabla.TablaVariables.InsertarVariable(tope);
                                }
                                //metodo.SacarAmbito(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla);

                            }
                        } else if (Casos.get(cont).Valor.Tipo.equals(Constante.TCaracter)) {
                            if (Casos.get(cont).Valor.Caracter == condicion.Caracter) {
                                FMetodo metodo = new FMetodo(Constante.TPublico, new ArrayList<Simbolo>(), this.Casos.get(cont).Ambito, 0, 0, Constante.TVacio, Constante.TCase);
                                metodo.EjecutarInstrucciones(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla, padre);
                                encontrado = true;
                                posencontrado = cont;
                                if (Tabla.TablaVariables.IsTerminar()) {
                                    Variable tope = Tabla.TablaVariables.ObtenerTope();
                                    Tabla.TablaVariables.SacarVariable();
                                    metodo.SacarAmbito(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla);
                                    Tabla.TablaVariables.InsertarVariable(tope);
                                }
                                //metodo.SacarAmbito(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla);

                            }
                        } else if (Casos.get(cont).Valor.Tipo == Constante.TBool) {
                            if (Casos.get(cont).Valor.Bool == condicion.Bool) {
                                FMetodo metodo = new FMetodo(Constante.TPublico, new ArrayList<Simbolo>(), this.Casos.get(cont).Ambito, 0, 0, Constante.TVacio, Constante.TCase);
                                metodo.EjecutarInstrucciones(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla, padre);
                                encontrado = true;
                                posencontrado = cont;
                                if (Tabla.TablaVariables.IsTerminar()) {
                                    Variable tope = Tabla.TablaVariables.ObtenerTope();
                                    Tabla.TablaVariables.SacarVariable();
                                    metodo.SacarAmbito(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla);
                                    Tabla.TablaVariables.InsertarVariable(tope);
                                }
                                //metodo.SacarAmbito(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla);

                            }
                        } else {
                            if (condicion.Tipo.equals(Constante.TObjeto)) {
                                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Se esperaba un tipo " + Casos.get(cont).Valor.Tipo + " no un tipo " + Casos.get(cont).Valor.Nombre, Casos.get(cont).Valor.Fila, Casos.get(cont).Valor.Columna);
                            } else {
                                TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Se esperaba un tipo " + Casos.get(cont).Valor.Tipo + " no un tipo " + Casos.get(cont).Valor.Tipo, Casos.get(cont).Valor.Fila, Casos.get(cont).Valor.Columna);
                            }
                        }
                    }

                    if (encontrado & cont > posencontrado) {
                        FMetodo metodo = new FMetodo(Constante.TPublico, new ArrayList<Simbolo>(), this.Casos.get(cont).Ambito, 0, 0, Constante.TVacio, Constante.TCase);
                        metodo.EjecutarInstrucciones(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla, padre);
                        metodo.SacarAmbito(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla);

                    }
                } else {
                    if (condicion.Tipo.equals(Constante.TObjeto)) {
                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Se esperaba un tipo " + Casos.get(cont).Valor.Tipo + " no un tipo " + Casos.get(cont).Valor.Nombre, Casos.get(cont).Valor.Fila, Casos.get(cont).Valor.Columna);
                    } else {
                        TitusNotificaciones.InsertarError(Constante.TErrorSemantico, "Se esperaba un tipo " + Casos.get(cont).Valor.Tipo + " no un tipo " + Casos.get(cont).Valor.Tipo, Casos.get(cont).Valor.Fila, Casos.get(cont).Valor.Columna);
                    }
                }
                cont++;
            }
            if (!encontrado && !Tabla.TablaVariables.IsTerminar() && !Tabla.TablaVariables.IsRertorno() && TitusNotificaciones.ContarErrores()) {
                if (Defecto != null) {
                    FMetodo metodo = new FMetodo(Constante.TPublico, new ArrayList<Simbolo>(), this.Defecto.Ambito, 0, 0, Constante.TVacio, Constante.TCase);
                    metodo.EjecutarInstrucciones(this.Defecto.Ambito.TablaSimbolo, Tabla, padre);
                    if (Tabla.TablaVariables.IsTerminar()) {
                        Variable tope = Tabla.TablaVariables.ObtenerTope();
                        Tabla.TablaVariables.SacarVariable();
                        metodo.SacarAmbito(this.Defecto.Ambito.TablaSimbolo, Tabla);
                        Tabla.TablaVariables.InsertarVariable(tope);
                    }
                    //metodo.SacarAmbito(this.Casos.get(cont).Ambito.TablaSimbolo, Tabla);

                }
            }

            if (Tabla.TablaVariables.IsTerminar()) {
                Tabla.TablaVariables.SacarVariable();
            }
        }
    }
}
