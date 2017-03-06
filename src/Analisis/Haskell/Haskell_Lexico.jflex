
package Analisis.Haskell;

import Constante.Constante;
import java_cup.runtime.Symbol;

%%
%{
    //Código de usuario
%}

%cup
%class Haskell_Lexico
%public
%line
%char
%column
%full
%ignorecase




Id      = [A-Za-z][_0-9A-Za-z]*
Numero = [0-9]+("."[0-9]+)?
Caracter  = [\'] [^\'\n] [\']
Cadena = [\"] [^\"\n]* [\"]

SPACE   = [\ \r\t\f\t]
ENTER   = [\ \n]

//Operaciones aritmeticas
TMas = Constante.TMas
TMenos = Constante.TMenos
TPor = Constante.TPor
TDivision = Constante.TDivision
TModulo = Constante.TModulo
TPotencia = Constante.TPotenciaH
TRaiz = Constante.TRaiz
TDolar = Constante.TDolar
TParentesis_Abre = Constante.TParentesis_Abre
TParentesis_Cierra = Constante.TParentesis_Cierra    
TVerdadero = Constante.TVerdadero
TFalso = Constante.TFalso

Booleano = TVerdadero | TFalso
    
//Operaciones realcionales
TMayor = Constante.TMayor
TMenor = Constante.TMenor
TMayorIgual = Constante.TMayorIgual
TMenorIgual = Constante.TMenorIgual
TIgualacion = Constante.TIgualacion
TDiferenciacion = Constante.TDiferenciacion

//operaciones condicionales
TAnd = Constante.TAnd
TOr = Constante.TOr

TIgual = Constante.TIgual
TCorchete_Abre = Constante.TCorchete_Abre
TCorchete_Cierra = Constante.TCorchete_Cierra

//palabras reservadas
TLet = Constante.TLet
TCalcular = Constante.TCalcular
TSucc = Constante.TSucc
TDecc = Constante.TDecc
TMin = Constante.TMin
TMax = Constante.TMax

//operaciones en lista
TSum = Constante.TSum
TProduct = Constante.TProduct
TRevers = Constante.TRevers
TImpr = Constante.TImpr
TPar = Constante.TPar
TAsc = Constante.TAsc
TDesc = Constante.TDesc
TLength = Constante.TLength
TAumento = Constante.TAumento
TIndiceLista = Constante.TIndiceLista

//archivos Haskell
TEnd = Constante.TEnd
TIf = Constante.TIf
TThen = Constante.TThen
TElse = Constante.TElse
TCase = Constante.TCase
TLlave_Abre = Constante.TLlave_Abre
TLlave_Cierra = Constante.TLlave_Cierra
TComa = Constante.TComa
    
%%

{Id}                  { return new Symbol(sym.Id, yyline, yycolumn, yytext()); }
{Numero}              { System.out.println(yytext()); return new Symbol(sym.Numero, yyline, yycolumn, yytext()); }
{Caracter}            { return new Symbol(sym.Caracter, yyline, yycolumn, yytext()); }
{Cadena}              { return new Symbol(sym.Cadena, yyline, yycolumn, yytext()); }

{SPACE}               {}
{ENTER}               {}

//Operaciones aritmeticas
{TMas}                { return new Symbol(sym.TMas, yyline, yycolumn, yytext()); }
{TMenos}              { return new Symbol(sym.TMenos, yyline, yycolumn, yytext()); }
{TPor}                { return new Symbol(sym.TPor, yyline, yycolumn, yytext()); }
{TDivision}           { return new Symbol(sym.TDivision, yyline, yycolumn, yytext()); }
{TModulo}             { return new Symbol(sym.TModulo, yyline, yycolumn, yytext()); }
{TPotencia}           { return new Symbol(sym.TPotencia, yyline, yycolumn, yytext()); }
{TRaiz}               { return new Symbol(sym.TRaiz, yyline, yycolumn, yytext()); }
{TDolar}              { return new Symbol(sym.TDolar, yyline, yycolumn, yytext()); }
{TParentesis_Abre}    { return new Symbol(sym.TParentesis_Abre, yyline, yycolumn, yytext()); }
{TParentesis_Cierra}  { return new Symbol(sym.TParentesis_Cierra, yyline, yycolumn, yytext()); }
{Booleano}            { return new Symbol(sym.Booleano, yyline, yycolumn, yytext()); }
    
//Operaciones realcionales
TMayor              { return new Symbol(sym.TMayor, yyline, yycolumn, yytext()); }
TMenor              { return new Symbol(sym.TMenor, yyline, yycolumn, yytext()); }
TMayorIgual         { return new Symbol(sym.TMayorIgual, yyline, yycolumn, yytext()); }
TMenorIgual         { return new Symbol(sym.TMenorIgual, yyline, yycolumn, yytext()); }
TIgualacion         { return new Symbol(sym.TIgualacion, yyline, yycolumn, yytext()); }
TDiferenciacion     { return new Symbol(sym.TDiferenciacion, yyline, yycolumn, yytext()); }

//operaciones condicionales
TAnd                { return new Symbol(sym.TAnd, yyline, yycolumn, yytext()); }
TOr                 { return new Symbol(sym.TOr, yyline, yycolumn, yytext()); }

TIgual              { return new Symbol(sym.TIgual, yyline, yycolumn, yytext()); }
TCorchete_Abre      { return new Symbol(sym.TCorchete_Abre, yyline, yycolumn, yytext()); }
TCorchete_Cierra    { return new Symbol(sym.TCorchete_Cierra, yyline, yycolumn, yytext()); }

//palabras reservadas
TLet                { return new Symbol(sym.TLet, yyline, yycolumn, yytext()); }
TCalcular           { return new Symbol(sym.TCalcular, yyline, yycolumn, yytext()); }
TSucc               { return new Symbol(sym.TSucc, yyline, yycolumn, yytext()); }
TDecc               { return new Symbol(sym.TDecc, yyline, yycolumn, yytext()); }
TMin                { return new Symbol(sym.TMin, yyline, yycolumn, yytext()); }
TMax                { return new Symbol(sym.TMax, yyline, yycolumn, yytext()); }

//operaciones en lista
TSum                { return new Symbol(sym.TSum, yyline, yycolumn, yytext()); }
TProduct            { return new Symbol(sym.TProduct, yyline, yycolumn, yytext()); }
TRevers             { return new Symbol(sym.TRevers, yyline, yycolumn, yytext()); }
TImpr               { return new Symbol(sym.TImpr, yyline, yycolumn, yytext()); }
TPar                { return new Symbol(sym.TPar, yyline, yycolumn, yytext()); }
TAsc                { return new Symbol(sym.TAsc, yyline, yycolumn, yytext()); }
TDesc               { return new Symbol(sym.TDesc, yyline, yycolumn, yytext()); }
TLength             { return new Symbol(sym.TLength, yyline, yycolumn, yytext()); }
TAumento            { return new Symbol(sym.TAumento, yyline, yycolumn, yytext()); }
TIndiceLista        { return new Symbol(sym.TIndiceLista, yyline, yycolumn, yytext()); }

//archivos Haskell
TEnd                { return new Symbol(sym.TEnd, yyline, yycolumn, yytext()); }
TIf                 { return new Symbol(sym.TIf, yyline, yycolumn, yytext()); }
TThen               { return new Symbol(sym.TThen, yyline, yycolumn, yytext()); }
TElse               { return new Symbol(sym.TElse, yyline, yycolumn, yytext()); }
TCase               { return new Symbol(sym.TCase, yyline, yycolumn, yytext()); }
TLlave_Abre         { return new Symbol(sym.TLlave_Abre, yyline, yycolumn, yytext()); }
TLlave_Cierra       { return new Symbol(sym.TLlave_Cierra, yyline, yycolumn, yytext()); }
TComa               { return new Symbol(sym.TComa, yyline, yycolumn, yytext()); }

. {
        String errLex = "Error léxico, caracter irreconocible: '"+yytext()+"' en la línea: "+(yyline+1)+" y columna: "+yycolumn;
        System.out.println(errLex);
}