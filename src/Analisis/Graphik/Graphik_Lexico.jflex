
package Analisis.Graphik;

import Constante.Constante;
import java_cup.runtime.Symbol;
import Interface.*;

%%
%{
    //Código de usuario
%}

%cup
%class Consola_Lexico
%public
%line
%char
%column
%full
%ignorecase
%unicode




//Operaciones aritmeticas
TMas = "+"
TMenos = "-"
TPor = "*"
TDivision = "/"
TModulo = "'mod'"
TPotencia = "'pot'"
TRaiz = "'sqrt'"
TDolar = "$"
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

TIgual = "="
TCorchete_Abre = "["
TCorchete_Cierra = "]"

//palabras reservadas
TLet = "let"
TCalcular = "calcular"
TSucc = "succ"
TDecc = "decc"
TMin = "min"
TMax = "max"

//operaciones en lista
TSum = "sum"
TProduct = "product"
TRevers = "revers"
TImpr = "impr"
TPar = "par"
TAsc = "asc"
TDesc = "desc"
TLength = "length"
TAumento = "++"
TIndiceLista = "!!"

//archivos Haskell
TLlave_Abre = "{"
TLlave_Cierra = "}"
TComa = ","
    
Id      = [A-Za-z][_0-9A-Za-z]*
Numero = [0-9]+("."[0-9]+)?
Caracter  = [\'] [^\'\n] [\']
Cadena = [\"] [^\"\n]* [\"]
TPorcentaje = "%"

SPACE   = [\ \r\t\f\t]
ENTER   = [\ \n]
%%


//Operaciones aritmeticas
{TMas}                { System.out.println(yytext()); return  new Symbol(sym.TMas, yyline, yycolumn, yytext()); }
{TMenos}              { System.out.println(yytext()); return  new Symbol(sym.TMenos, yyline, yycolumn, yytext()); }
{TPor}                { System.out.println(yytext()); return  new Symbol(sym.TPor, yyline, yycolumn, yytext()); }
{TDivision}           { System.out.println(yytext()); return  new Symbol(sym.TDivision, yyline, yycolumn, yytext()); }
{TModulo}             { System.out.println(yytext()); return  new Symbol(sym.TModulo, yyline, yycolumn, yytext()); }
{TPotencia}           { System.out.println(yytext()); return  new Symbol(sym.TPotencia, yyline, yycolumn, yytext()); }
{TRaiz}               { System.out.println(yytext()); return  new Symbol(sym.TRaiz, yyline, yycolumn, yytext()); }
{TDolar}              { System.out.println(yytext()); return  new Symbol(sym.TDolar, yyline, yycolumn, yytext()); }
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

{TIgual}              { System.out.println(yytext()); return  new Symbol(sym.TIgual, yyline, yycolumn, yytext()); }
{TCorchete_Abre}      { System.out.println(yytext()); return  new Symbol(sym.TCorchete_Abre, yyline, yycolumn, yytext()); }
{TCorchete_Cierra}    { System.out.println(yytext()); return  new Symbol(sym.TCorchete_Cierra, yyline, yycolumn, yytext()); }

//palabras reservadas
{TLet}                { System.out.println(yytext()); return  new Symbol(sym.TLet, yyline, yycolumn, yytext()); }
{TCalcular}           { System.out.println(yytext()); return  new Symbol(sym.TCalcular, yyline, yycolumn, yytext()); }
{TSucc}               { System.out.println(yytext()); return  new Symbol(sym.TSucc, yyline, yycolumn, yytext()); }
{TDecc}               { System.out.println(yytext()); return  new Symbol(sym.TDecc, yyline, yycolumn, yytext()); }
{TMin}                { System.out.println(yytext()); return  new Symbol(sym.TMin, yyline, yycolumn, yytext()); }
{TMax}                { System.out.println(yytext()); return  new Symbol(sym.TMax, yyline, yycolumn, yytext()); }

//operaciones en lista
{TSum}                { System.out.println(yytext()); return  new Symbol(sym.TSum, yyline, yycolumn, yytext()); }
{TProduct}            { System.out.println(yytext()); return  new Symbol(sym.TProduct, yyline, yycolumn, yytext()); }
{TRevers}             { System.out.println(yytext()); return  new Symbol(sym.TRevers, yyline, yycolumn, yytext()); }
{TImpr}               { System.out.println(yytext()); return  new Symbol(sym.TImpr, yyline, yycolumn, yytext()); }
{TPar}                { System.out.println(yytext()); return  new Symbol(sym.TPar, yyline, yycolumn, yytext()); }
{TAsc}                { System.out.println(yytext()); return  new Symbol(sym.TAsc, yyline, yycolumn, yytext()); }
{TDesc}               { System.out.println(yytext()); return  new Symbol(sym.TDesc, yyline, yycolumn, yytext()); }
{TLength}             { System.out.println(yytext()); return  new Symbol(sym.TLength, yyline, yycolumn, yytext()); }
{TAumento}            { System.out.println(yytext()); return  new Symbol(sym.TAumento, yyline, yycolumn, yytext()); }
{TIndiceLista}        { System.out.println(yytext()); return  new Symbol(sym.TIndiceLista, yyline, yycolumn, yytext()); }

//archivos Haskell
{TLlave_Abre}         { System.out.println(yytext()); return  new Symbol(sym.TLlave_Abre, yyline, yycolumn, yytext()); }
{TLlave_Cierra}       { System.out.println(yytext()); return  new Symbol(sym.TLlave_Cierra, yyline, yycolumn, yytext()); }
{TComa}               { System.out.println(yytext()); return  new Symbol(sym.TComa, yyline, yycolumn, yytext()); }
{Id}                  { System.out.println(yytext()); return  new Symbol(sym.Id, yyline, yycolumn, yytext()); }
{Numero}              { System.out.println(yytext()); return  new Symbol(sym.Numero, yyline, yycolumn, yytext()); }
{Caracter}            { System.out.println(yytext()); return  new Symbol(sym.Caracter, yyline, yycolumn, yytext()); }
{Cadena}              { System.out.println(yytext()); return  new Symbol(sym.Cadena, yyline, yycolumn, yytext()); }
{TPorcentaje}         { System.out.println(yytext()); return  new Symbol(sym.TPorcentaje, yyline, yycolumn, yytext()); }

{SPACE}               {}
{ENTER}               {}
. {
        //String errLex = "Error léxico, caracter irreconocible: '"+yytext()+"' en la línea: "+(yyline)+" y columna: "+yycolumn;
        TitusNotificaciones.InsertarError(Constante.TErrorLexico,"Simbolo " + yytext() + " no reconocido", yyline, yycolumn);
}