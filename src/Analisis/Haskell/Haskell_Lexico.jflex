
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
TVerdadero = "true"
TFalso = "false"

TVerdadero = "true"
TFalso = "false"
    
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
TEnd = "end"
TIf = "if"
TThen = "then"
TElse = "else"
TCase = "case"
TLlave_Abre = "{"
TLlave_Cierra = "}"
TComa = ","
TPuntoyComa = ";"
TDosPuntos = ":";
    
Id      = [A-Za-z][_0-9A-Za-z]*
Numero = [0-9]+("."[0-9]+)?
Caracter  = [\'] [^\'\n] [\']
Cadena = [\"] [^\"\n]* [\"]

SPACE   = [\ \r\t\f\t]
ENTER   = [\ \n]
%%


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
{TFalso}              { return new Symbol(sym.TFalso, yyline, yycolumn, yytext()); }
{TVerdadero}          { return new Symbol(sym.TVerdadero, yyline, yycolumn, yytext()); }    
//Operaciones realcionales
{TMayor}              { return new Symbol(sym.TMayor, yyline, yycolumn, yytext()); }
{TMenor}              { return new Symbol(sym.TMenor, yyline, yycolumn, yytext()); }
{TMayorIgual}         { return new Symbol(sym.TMayorIgual, yyline, yycolumn, yytext()); }
{TMenorIgual}         { return new Symbol(sym.TMenorIgual, yyline, yycolumn, yytext()); }
{TIgualacion}         { return new Symbol(sym.TIgualacion, yyline, yycolumn, yytext()); }
{TDiferenciacion}     { return new Symbol(sym.TDiferenciacion, yyline, yycolumn, yytext()); }

//operaciones condicionales
{TAnd}                { return new Symbol(sym.TAnd, yyline, yycolumn, yytext()); }
{TOr}                 { return new Symbol(sym.TOr, yyline, yycolumn, yytext()); }

{TIgual}              { return new Symbol(sym.TIgual, yyline, yycolumn, yytext()); }
{TCorchete_Abre}      { return new Symbol(sym.TCorchete_Abre, yyline, yycolumn, yytext()); }
{TCorchete_Cierra}    { return new Symbol(sym.TCorchete_Cierra, yyline, yycolumn, yytext()); }

//palabras reservadas
{TLet}                { return new Symbol(sym.TLet, yyline, yycolumn, yytext()); }
{TCalcular}           { return new Symbol(sym.TCalcular, yyline, yycolumn, yytext()); }
{TSucc}               { return new Symbol(sym.TSucc, yyline, yycolumn, yytext()); }
{TDecc}               { return new Symbol(sym.TDecc, yyline, yycolumn, yytext()); }
{TMin}                { return new Symbol(sym.TMin, yyline, yycolumn, yytext()); }
{TMax}                { return new Symbol(sym.TMax, yyline, yycolumn, yytext()); }

//operaciones en lista
{TSum}                { return new Symbol(sym.TSum, yyline, yycolumn, yytext()); }
{TProduct}            { return new Symbol(sym.TProduct, yyline, yycolumn, yytext()); }
{TRevers}             { return new Symbol(sym.TRevers, yyline, yycolumn, yytext()); }
{TImpr}               { return new Symbol(sym.TImpr, yyline, yycolumn, yytext()); }
{TPar}                { return new Symbol(sym.TPar, yyline, yycolumn, yytext()); }
{TAsc}                { return new Symbol(sym.TAsc, yyline, yycolumn, yytext()); }
{TDesc}               { return new Symbol(sym.TDesc, yyline, yycolumn, yytext()); }
{TLength}             { return new Symbol(sym.TLength, yyline, yycolumn, yytext()); }
{TAumento}            { return new Symbol(sym.TAumento, yyline, yycolumn, yytext()); }
{TIndiceLista}        { return new Symbol(sym.TIndiceLista, yyline, yycolumn, yytext()); }

//archivos Haskell
{TEnd}                { return new Symbol(sym.TEnd, yyline, yycolumn, yytext()); }
{TIf}                 { return new Symbol(sym.TIf, yyline, yycolumn, yytext()); }
{TThen}               { return new Symbol(sym.TThen, yyline, yycolumn, yytext()); }
{TElse}               { return new Symbol(sym.TElse, yyline, yycolumn, yytext()); }
{TCase}               { return new Symbol(sym.TCase, yyline, yycolumn, yytext()); }
{TLlave_Abre}         { return new Symbol(sym.TLlave_Abre, yyline, yycolumn, yytext()); }
{TLlave_Cierra}       { return new Symbol(sym.TLlave_Cierra, yyline, yycolumn, yytext()); }
{TComa}               { return new Symbol(sym.TComa, yyline, yycolumn, yytext()); }
{TPuntoyComa}         { return new Symbol(sym.TPuntoyComa, yyline, yycolumn, yytext()); }
{TDosPuntos}          { System.out.println(yytext());return new Symbol(sym.TDosPuntos, yyline, yycolumn, yytext()); }
{Id}                  { return new Symbol(sym.Id, yyline, yycolumn, yytext()); }
{Numero}              { return new Symbol(sym.Numero, yyline, yycolumn, yytext()); }
{Caracter}            { return new Symbol(sym.Caracter, yyline, yycolumn, yytext()); }
{Cadena}              { return new Symbol(sym.Cadena, yyline, yycolumn, yytext()); }

{SPACE}               {}
{ENTER}               {}
. {
        String errLex = "Error léxico, caracter irreconocible: '"+yytext()+"' en la línea: "+(yyline+1)+" y columna: "+yycolumn;
        System.out.println(errLex);
}