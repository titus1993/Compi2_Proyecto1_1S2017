
package Analisis.Graphik;

import Constante.Constante;
import java_cup.runtime.Symbol;
import Interface.*;

%%
%{
    //Código de usuario
%}

%cup
%class Graphik_Lexico
%public
%line
%char
%column
%full
%ignorecase
%unicode



TVar = "var"
TEntero = "entero"
TDecimal = "decimal"
TCadena = "cadena"
TCaracter = "caracter"
TBool = "bool"
TProtegido = "protegido"
TPublico = "publico"
TPrivado = "privado"
TAls = "als"
TNuevo = "nuevo"
TSi = "si"
TSino = "sino"
TSeleccion = "seleccion"
TCaso = "caso"
TDefecto = "defecto"
TPara = "para"
TMientras = "mientras"
THacer = "hacer"
TContinuar = "continuar"
TTerminar = "terminar"
TInterrogacion = [?]
TRetornar = "retornar"
TVacio = "vacio"
THereda = "hereda"
TImportar = "importar"
TGk = "gk"
TIncluye = "incluir_hk"
TLlamar = "llamar"
TLlamarHk = "llamarhk"
TGraphikar_Funcion = "graphikar_funcion"
TDatos = "datos"
TColumna = "columna"
TProcesar = "procesar"
TDonde = "donde"
TDondeCada = "dondecada"
TDondeTodo = "dondetodo"
TImprimir = "imprimir"
TInicio = "inicio"

//Operaciones aritmeticas
TMas = "+"
TMenos = "-"
TPor = "*"
TDivision = "/"
TPotencia = "^"
TAumento = "++"
TDisminucion = "--"
TParentesis_Abre = "("
TParentesis_Cierra = ")"   

   
//Operaciones realcionales
TMayor = ">"
TMenor = "<"
TMayorIgual = ">="
TMenorIgual = "<="
TIgualacion = "=="
TDiferenciacion = "!="

//operaciones condicionales
TAnd = "&&"
TOr = "||"
TXor = "&|"
TNot = "!"


TIgual = "="
TCorchete_Abre = "["
TCorchete_Cierra = "]"


//archivos Haskell
TLlave_Abre = "{"
TLlave_Cierra = "}"
TComa = ","
TDosPuntos = ":"
TPunto = "."
    
Id      = [A-Za-z][_0-9A-Za-z]*
Entero  = [0-9]+
Decimal = [0-9]+("."[0-9]+)?
Caracter  = [\'] [^\'\n] [\']
Cadena = [\"] [^\"\n]* [\"]
Falso = "falso"
Verdadero = "verdadero"
ComentarioMultiLinea = "#/" [^/]* "/#"
ComentarioLinea = "#" ([^\n]*)


SPACE   = [\ \r\t\f\t]
ENTER   = [\ \n]
%%


//Graphik
{TVar}                  { System.out.println(yytext()); return  new Symbol(sym.TVar, yyline, yycolumn, yytext()); }
{TEntero}                  { System.out.println(yytext()); return  new Symbol(sym.TEntero, yyline, yycolumn, yytext()); }
{TDecimal}                  { System.out.println(yytext()); return  new Symbol(sym.TDecimal, yyline, yycolumn, yytext()); }
{TCadena}                  { System.out.println(yytext()); return  new Symbol(sym.TCadena, yyline, yycolumn, yytext()); }
{TCaracter}                  { System.out.println(yytext()); return  new Symbol(sym.TCaracter, yyline, yycolumn, yytext()); }
{TBool}                  { System.out.println(yytext()); return  new Symbol(sym.TBool, yyline, yycolumn, yytext()); }
{TPublico}              { System.out.println(yytext()); return  new Symbol(sym.TPublico, yyline, yycolumn, yytext()); }
{TPrivado}              { System.out.println(yytext()); return  new Symbol(sym.TPrivado, yyline, yycolumn, yytext()); }
{TProtegido}            { System.out.println(yytext()); return  new Symbol(sym.TProtegido, yyline, yycolumn, yytext()); }
{Verdadero}            { System.out.println(yytext()); return  new Symbol(sym.Verdadero, yyline, yycolumn, yytext()); }
{Falso}            { System.out.println(yytext()); return  new Symbol(sym.Falso, yyline, yycolumn, yytext()); }
{TAls}            { System.out.println(yytext()); return  new Symbol(sym.TAls, yyline, yycolumn, yytext()); }
{TPunto}            { System.out.println(yytext()); return  new Symbol(sym.TPunto, yyline, yycolumn, yytext()); }
{TNuevo}            { System.out.println(yytext()); return  new Symbol(sym.TNuevo, yyline, yycolumn, yytext()); }
{TSi}            { System.out.println(yytext()); return  new Symbol(sym.TSi, yyline, yycolumn, yytext()); }
{TSino}            { System.out.println(yytext()); return  new Symbol(sym.TSino, yyline, yycolumn, yytext()); }
{TSeleccion}            { System.out.println(yytext()); return  new Symbol(sym.TSeleccion, yyline, yycolumn, yytext()); }
{TCaso}            { System.out.println(yytext()); return  new Symbol(sym.TCaso, yyline, yycolumn, yytext()); }
{TDefecto}            { System.out.println(yytext()); return  new Symbol(sym.TDefecto, yyline, yycolumn, yytext()); }
{TPara}            { System.out.println(yytext()); return  new Symbol(sym.TPara, yyline, yycolumn, yytext()); }
{TMientras}            { System.out.println(yytext()); return  new Symbol(sym.TMientras, yyline, yycolumn, yytext()); }
{THacer}            { System.out.println(yytext()); return  new Symbol(sym.THacer, yyline, yycolumn, yytext()); }
{TTerminar}            { System.out.println(yytext()); return  new Symbol(sym.TTerminar, yyline, yycolumn, yytext()); }
{TContinuar}            { System.out.println(yytext()); return  new Symbol(sym.TContinuar, yyline, yycolumn, yytext()); }
{TInterrogacion}            { System.out.println(yytext()); return  new Symbol(sym.TInterrogacion, yyline, yycolumn, yytext());}
{TRetornar}            { System.out.println(yytext()); return  new Symbol(sym.TRetornar, yyline, yycolumn, yytext()); }
{TVacio}            { System.out.println(yytext()); return  new Symbol(sym.TVacio, yyline, yycolumn, yytext()); }
{THereda}            { System.out.println(yytext()); return  new Symbol(sym.THereda, yyline, yycolumn, yytext()); }
{TImportar}            { System.out.println(yytext()); return  new Symbol(sym.TImportar, yyline, yycolumn, yytext()); }
{TGk}            { System.out.println(yytext()); return  new Symbol(sym.TGk, yyline, yycolumn, yytext()); }
{TIncluye}            { System.out.println(yytext()); return  new Symbol(sym.TIncluye, yyline, yycolumn, yytext()); }
{TLlamar}            { System.out.println(yytext()); return  new Symbol(sym.TLlamar, yyline, yycolumn, yytext()); }
{TLlamarHk}            { System.out.println(yytext()); return  new Symbol(sym.TLlamarHk, yyline, yycolumn, yytext()); }
{TGraphikar_Funcion}            { System.out.println(yytext()); return  new Symbol(sym.TGraphikar_Funcion, yyline, yycolumn, yytext()); }
{TDatos}            { System.out.println(yytext()); return  new Symbol(sym.TDatos, yyline, yycolumn, yytext()); }
{TColumna}            { System.out.println(yytext()); return  new Symbol(sym.TColumna, yyline, yycolumn, yytext()); }
{TProcesar}            { System.out.println(yytext()); return  new Symbol(sym.TProcesar, yyline, yycolumn, yytext()); }
{TDonde}            { System.out.println(yytext()); return  new Symbol(sym.TDonde, yyline, yycolumn, yytext()); }
{TDondeCada}            { System.out.println(yytext()); return  new Symbol(sym.TDondeCada, yyline, yycolumn, yytext()); }
{TDondeTodo}            { System.out.println(yytext()); return  new Symbol(sym.TDondeTodo, yyline, yycolumn, yytext()); }
{TImprimir}            { System.out.println(yytext()); return  new Symbol(sym.TImprimir, yyline, yycolumn, yytext()); }
{TInicio}            { System.out.println(yytext()); return  new Symbol(sym.TInicio, yyline, yycolumn, yytext()); }

//Operaciones aritmeticas
{TMas}                { System.out.println(yytext()); return  new Symbol(sym.TMas, yyline, yycolumn, yytext()); }
{TMenos}              { System.out.println(yytext()); return  new Symbol(sym.TMenos, yyline, yycolumn, yytext()); }
{TPor}                { System.out.println(yytext()); return  new Symbol(sym.TPor, yyline, yycolumn, yytext()); }
{TDivision}           { System.out.println(yytext()); return  new Symbol(sym.TDivision, yyline, yycolumn, yytext()); }
{TPotencia}           { System.out.println(yytext()); return  new Symbol(sym.TPotencia, yyline, yycolumn, yytext()); }
{TAumento}            { System.out.println(yytext()); return  new Symbol(sym.TAumento, yyline, yycolumn, yytext()); }
{TDisminucion}        { System.out.println(yytext()); return  new Symbol(sym.TDisminucion, yyline, yycolumn, yytext()); }
{TParentesis_Abre}    { System.out.println(yytext()); return  new Symbol(sym.TParentesis_Abre, yyline, yycolumn, yytext()); }
{TParentesis_Cierra}  { System.out.println(yytext()); return  new Symbol(sym.TParentesis_Cierra, yyline, yycolumn, yytext()); } 
//Operaciones realcionales
{TMayor}              { System.out.println(yytext()); return  new Symbol(sym.TMayor, yyline, yycolumn, yytext()); }
{TMenor}              { System.out.println(yytext()); return  new Symbol(sym.TMenor, yyline, yycolumn, yytext()); }
{TMayorIgual}         { System.out.println(yytext()); return  new Symbol(sym.TMayorIgual, yyline, yycolumn, yytext()); }
{TMenorIgual}         { System.out.println(yytext()); return  new Symbol(sym.TMenorIgual, yyline, yycolumn, yytext()); }
{TIgualacion}         { System.out.println(yytext()); return  new Symbol(sym.TIgualacion, yyline, yycolumn, yytext()); }
{TDiferenciacion}     { System.out.println(yytext()); return  new Symbol(sym.TDiferenciacion, yyline, yycolumn, yytext()); }

//operaciones condicionales

{TAnd}                { System.out.println(yytext()); return  new Symbol(sym.TAnd, yyline, yycolumn, yytext()); }
{TOr}                 { System.out.println(yytext()); return  new Symbol(sym.TOr, yyline, yycolumn, yytext()); }
{TXor}                { System.out.println(yytext()); return  new Symbol(sym.TXor, yyline, yycolumn, yytext()); }
{TNot}                { System.out.println(yytext()); return  new Symbol(sym.TNot, yyline, yycolumn, yytext()); }

{TIgual}              { System.out.println(yytext()); return  new Symbol(sym.TIgual, yyline, yycolumn, yytext()); }
{TCorchete_Abre}      { System.out.println(yytext()); return  new Symbol(sym.TCorchete_Abre, yyline, yycolumn, yytext()); }
{TCorchete_Cierra}    { System.out.println(yytext()); return  new Symbol(sym.TCorchete_Cierra, yyline, yycolumn, yytext()); }

//archivos Haskell
{TLlave_Abre}         { System.out.println(yytext()); return  new Symbol(sym.TLlave_Abre, yyline, yycolumn, yytext()); }
{TLlave_Cierra}       { System.out.println(yytext()); return  new Symbol(sym.TLlave_Cierra, yyline, yycolumn, yytext()); }
{TComa}               { System.out.println(yytext()); return  new Symbol(sym.TComa, yyline, yycolumn, yytext()); }
{TDosPuntos}          { System.out.println(yytext()); return  new Symbol(sym.TDosPuntos, yyline, yycolumn, yytext()); }
{Id}                  { System.out.println(yytext()); return  new Symbol(sym.Id, yyline, yycolumn, yytext()); }
{Entero}              { System.out.println(yytext()); return  new Symbol(sym.Entero, yyline, yycolumn, yytext()); }
{Decimal}              { System.out.println(yytext()); return  new Symbol(sym.Decimal, yyline, yycolumn, yytext()); }
{Caracter}            { System.out.println(yytext()); return  new Symbol(sym.Caracter, yyline, yycolumn, yytext()); }
{Cadena}              { System.out.println(yytext()); return  new Symbol(sym.Cadena, yyline, yycolumn, yytext()); }
{ComentarioMultiLinea}  {System.out.println(yytext());}
{ComentarioLinea}       {System.out.println(yytext());}

{SPACE}               {}
{ENTER}               {}
. {
        //String errLex = "Error léxico, caracter irreconocible: '"+yytext()+"' en la línea: "+(yyline)+" y columna: "+yycolumn;
        TitusNotificaciones.InsertarError(Constante.TErrorLexico,"Simbolo " + yytext() + " no reconocido", yyline, yycolumn);
}