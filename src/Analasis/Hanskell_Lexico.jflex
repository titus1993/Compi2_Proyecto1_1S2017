
package Analisis

import Constante.Constante;
import java_cup.runtime.Symbol;

//Scanner para generar C3D
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
%cupsym symHaskell




Id      = [A-Za-z][_0-9A-Za-z]*
Numero = [0-9]+"."[0-9]*
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

Id                  { return new Symbol(symHaskell.Id, yyline, yycolumn, yytext()); }
Numero              { return new Symbol(symHaskell.Numero, yyline, yycolumn, yytext()); }
Caracter            { return new Symbol(symHaskell.Caracter, yyline, yycolumn, yytext()); }
Cadena              { return new Symbol(symHaskell.Cadena, yyline, yycolumn, yytext()); }

SPACE               {}
ENTER               {}

//Operaciones aritmeticas
TMas                { return new Symbol(symHaskell.TMas, yyline, yycolumn, yytext()); }
TMenos              { return new Symbol(symHaskell.TMenos, yyline, yycolumn, yytext()); }
TPor                { return new Symbol(symHaskell.TPor, yyline, yycolumn, yytext()); }
TDivision           { return new Symbol(symHaskell.TDivision, yyline, yycolumn, yytext()); }
TModulo             { return new Symbol(symHaskell.TModulo, yyline, yycolumn, yytext()); }
TPotencia           { return new Symbol(symHaskell.TPotencia, yyline, yycolumn, yytext()); }
TRaiz               { return new Symbol(symHaskell.TRaiz, yyline, yycolumn, yytext()); }
TDolar              { return new Symbol(symHaskell.TDolar, yyline, yycolumn, yytext()); }
TParentesis_Abre    { return new Symbol(symHaskell.TParentesis_Abre, yyline, yycolumn, yytext()); }
TParentesis_Cierra  { return new Symbol(symHaskell.TParentesis_Cierra, yyline, yycolumn, yytext()); }
Booleano            { return new Symbol(symHaskell.Booleano, yyline, yycolumn, yytext()); }
    
//Operaciones realcionales
TMayor              { return new Symbol(symHaskell.TMayor, yyline, yycolumn, yytext()); }
TMenor              { return new Symbol(symHaskell.TMenor, yyline, yycolumn, yytext()); }
TMayorIgual         { return new Symbol(symHaskell.TMayorIgual, yyline, yycolumn, yytext()); }
TMenorIgual         { return new Symbol(symHaskell.TMenorIgual, yyline, yycolumn, yytext()); }
TIgualacion         { return new Symbol(symHaskell.TIgualacion, yyline, yycolumn, yytext()); }
TDiferenciacion     { return new Symbol(symHaskell.TDiferenciacion, yyline, yycolumn, yytext()); }

//operaciones condicionales
TAnd                { return new Symbol(symHaskell.TAnd, yyline, yycolumn, yytext()); }
TOr                 { return new Symbol(symHaskell.TOr, yyline, yycolumn, yytext()); }

TIgual              { return new Symbol(symHaskell.TIgual, yyline, yycolumn, yytext()); }
TCorchete_Abre      { return new Symbol(symHaskell.TCorchete_Abre, yyline, yycolumn, yytext()); }
TCorchete_Cierra    { return new Symbol(symHaskell.TCorchete_Cierra, yyline, yycolumn, yytext()); }

//palabras reservadas
TLet                { return new Symbol(symHaskell.TLet, yyline, yycolumn, yytext()); }
TCalcular           { return new Symbol(symHaskell.TCalcular, yyline, yycolumn, yytext()); }
TSucc               { return new Symbol(symHaskell.TSucc, yyline, yycolumn, yytext()); }
TDecc               { return new Symbol(symHaskell.TDecc, yyline, yycolumn, yytext()); }
TMin                { return new Symbol(symHaskell.TMin, yyline, yycolumn, yytext()); }
TMax                { return new Symbol(symHaskell.TMax, yyline, yycolumn, yytext()); }

//operaciones en lista
TSum                { return new Symbol(symHaskell.TSum, yyline, yycolumn, yytext()); }
TProduct            { return new Symbol(symHaskell.TProduct, yyline, yycolumn, yytext()); }
TRevers             { return new Symbol(symHaskell.TRevers, yyline, yycolumn, yytext()); }
TImpr               { return new Symbol(symHaskell.TImpr, yyline, yycolumn, yytext()); }
TPar                { return new Symbol(symHaskell.TPar, yyline, yycolumn, yytext()); }
TAsc                { return new Symbol(symHaskell.TAsc, yyline, yycolumn, yytext()); }
TDesc               { return new Symbol(symHaskell.TDesc, yyline, yycolumn, yytext()); }
TLength             { return new Symbol(symHaskell.TLength, yyline, yycolumn, yytext()); }
TAumento            { return new Symbol(symHaskell.TAumento, yyline, yycolumn, yytext()); }
TIndiceLista        { return new Symbol(symHaskell.TIndiceLista, yyline, yycolumn, yytext()); }

//archivos Haskell
TEnd                { return new Symbol(symHaskell.TEnd, yyline, yycolumn, yytext()); }
TIf                 { return new Symbol(symHaskell.TIf, yyline, yycolumn, yytext()); }
TThen               { return new Symbol(symHaskell.TThen, yyline, yycolumn, yytext()); }
TElse               { return new Symbol(symHaskell.TElse, yyline, yycolumn, yytext()); }
TCase               { return new Symbol(symHaskell.TCase, yyline, yycolumn, yytext()); }
TLlave_Abre         { return new Symbol(symHaskell.TLlave_Abre, yyline, yycolumn, yytext()); }
TLlave_Cierra       { return new Symbol(symHaskell.TLlave_Cierra, yyline, yycolumn, yytext()); }
TComa               { return new Symbol(symHaskell.TComa, yyline, yycolumn, yytext()); }

. {
        String errLex = "Error léxico, caracter irreconocible: '"+yytext()+"' en la línea: "+(yyline+1)+" y columna: "+yycolumn;
        System.out.println(errLex);
}