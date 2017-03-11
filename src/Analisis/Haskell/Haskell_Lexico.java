/* The following code was generated by JFlex 1.6.0 */


package Analisis.Haskell;

import Constante.Constante;
import java_cup.runtime.Symbol;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.0
 * from the specification file <tt>Haskell_Lexico.jflex</tt>
 */
public class Haskell_Lexico implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0, 50, 48, 51, 50, 50,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
    50, 25, 49,  0, 14,  0, 26,  5, 15, 16,  3,  1, 39,  2, 47,  4, 
    46, 46, 46, 46, 46, 46, 46, 46, 46, 46, 41, 40, 23, 24, 22,  0, 
     0, 20, 42, 30,  8, 18, 19, 35, 36, 44, 42, 42, 21,  6, 32,  7, 
     9, 12, 13, 43, 10, 17, 34, 42, 33, 42, 42, 28,  0, 29,  0, 45, 
     0, 20, 42, 30,  8, 18, 19, 35, 36, 44, 42, 42, 21,  6, 32,  7, 
     9, 12, 13, 43, 10, 17, 34, 42, 33, 42, 42, 37, 27, 38,  0,  0, 
     0,  0,  0,  0,  0, 51,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\1\5\6"+
    "\1\1\1\6\1\7\1\10\1\11\4\6\1\12\1\13"+
    "\1\14\3\1\1\15\1\16\1\6\1\1\1\17\1\20"+
    "\1\21\1\22\1\1\2\6\1\23\1\24\1\1\1\25"+
    "\4\0\1\6\1\0\6\6\1\0\4\6\1\0\2\6"+
    "\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\6"+
    "\1\0\1\35\1\36\2\6\1\35\2\0\1\37\1\40"+
    "\3\0\1\41\2\42\1\0\3\6\1\43\2\6\1\44"+
    "\1\0\1\6\1\0\1\6\1\45\1\6\2\46\1\47"+
    "\1\6\1\0\2\6\1\0\1\44\2\6\1\23\3\0"+
    "\1\50\1\51\1\50\1\6\1\52\1\53\1\54\1\6"+
    "\2\55\1\0\2\6\1\56\1\6\1\56\1\57\1\54"+
    "\1\57\1\60\1\61\1\0\2\6\2\62\2\6\1\63"+
    "\1\6\2\64\1\65\1\6\1\66\1\6\1\67";

  private static int [] zzUnpackAction() {
    int [] result = new int[152];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\64\0\150\0\64\0\64\0\64\0\234\0\320"+
    "\0\u0104\0\u0138\0\u016c\0\u01a0\0\u01d4\0\u0208\0\64\0\64"+
    "\0\64\0\u023c\0\u0270\0\u02a4\0\u02d8\0\u030c\0\u0340\0\u0374"+
    "\0\u03a8\0\u03dc\0\u0410\0\64\0\64\0\u0444\0\u0478\0\64"+
    "\0\64\0\64\0\64\0\u04ac\0\u04e0\0\u0514\0\u0548\0\64"+
    "\0\u057c\0\64\0\u05b0\0\u05e4\0\u0618\0\u064c\0\u0680\0\u06b4"+
    "\0\u06e8\0\u071c\0\u0750\0\u0784\0\u07b8\0\u07ec\0\u0820\0\u0854"+
    "\0\u0888\0\u08bc\0\u08f0\0\u0924\0\u0958\0\u098c\0\64\0\64"+
    "\0\64\0\64\0\64\0\64\0\64\0\u09c0\0\u09f4\0\64"+
    "\0\64\0\u0a28\0\u0a5c\0\u0104\0\u0a90\0\u057c\0\64\0\64"+
    "\0\u0ac4\0\u0af8\0\u0b2c\0\u0104\0\64\0\u0104\0\u0b60\0\u0b94"+
    "\0\u0bc8\0\u0bfc\0\u0104\0\u0c30\0\u0c64\0\64\0\u0c98\0\u0ccc"+
    "\0\u0d00\0\u0d34\0\u0104\0\u0d68\0\64\0\u0104\0\u0104\0\u0d9c"+
    "\0\u0dd0\0\u0e04\0\u0e38\0\u0e6c\0\u0104\0\u0ea0\0\u0ed4\0\u0a90"+
    "\0\u0f08\0\u0f3c\0\u0f70\0\64\0\u0104\0\u0104\0\u0fa4\0\u0104"+
    "\0\u0104\0\64\0\u0fd8\0\64\0\u0104\0\u100c\0\u1040\0\u1074"+
    "\0\64\0\u10a8\0\u0104\0\64\0\u0104\0\u0104\0\64\0\64"+
    "\0\u10dc\0\u1110\0\u1144\0\64\0\u0104\0\u1178\0\u11ac\0\64"+
    "\0\u11e0\0\64\0\u0104\0\u0104\0\u1214\0\u0104\0\u1248\0\u0104";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[152];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\11\1\16\1\17\1\20"+
    "\1\21\1\11\1\22\1\23\1\24\1\25\1\26\1\27"+
    "\1\30\1\31\1\32\1\33\1\34\1\35\1\36\1\37"+
    "\5\11\1\40\1\41\1\42\1\43\1\44\1\11\1\45"+
    "\1\46\1\2\1\47\1\2\1\50\1\51\1\50\66\0"+
    "\1\52\62\0\5\53\1\0\1\54\2\53\1\55\1\53"+
    "\1\56\37\53\1\56\4\53\1\0\3\53\6\0\5\11"+
    "\1\0\2\11\3\0\3\11\1\57\1\11\10\0\1\11"+
    "\1\60\5\11\5\0\2\11\1\61\2\11\13\0\5\11"+
    "\1\0\2\11\3\0\5\11\10\0\1\11\1\0\5\11"+
    "\5\0\5\11\13\0\5\11\1\0\2\11\3\0\1\11"+
    "\1\62\3\11\10\0\1\11\1\0\5\11\5\0\5\11"+
    "\13\0\5\11\1\0\1\11\1\63\3\0\3\11\1\64"+
    "\1\11\10\0\1\11\1\0\5\11\5\0\5\11\13\0"+
    "\5\11\1\0\1\11\1\65\3\0\5\11\10\0\1\11"+
    "\1\0\4\11\1\66\5\0\5\11\26\0\1\67\50\0"+
    "\5\11\1\0\2\11\3\0\1\11\1\70\3\11\10\0"+
    "\1\11\1\0\5\11\5\0\5\11\13\0\5\11\1\0"+
    "\2\11\3\0\4\11\1\71\10\0\1\11\1\0\1\72"+
    "\4\11\5\0\5\11\13\0\5\11\1\0\2\11\3\0"+
    "\3\11\1\73\1\11\10\0\1\11\1\0\5\11\5\0"+
    "\5\11\13\0\5\11\1\74\2\11\3\0\5\11\10\0"+
    "\1\11\1\0\5\11\5\0\1\11\1\75\3\11\13\0"+
    "\5\11\1\0\2\11\3\0\1\11\1\76\3\11\10\0"+
    "\1\11\1\0\5\11\5\0\5\11\35\0\1\77\63\0"+
    "\1\100\63\0\1\101\63\0\1\102\1\103\64\0\1\104"+
    "\64\0\1\105\36\0\5\11\1\0\2\11\3\0\3\11"+
    "\1\106\1\11\10\0\1\11\1\0\5\11\5\0\5\11"+
    "\13\0\1\107\14\0\1\110\110\0\1\111\21\0\5\11"+
    "\1\0\2\11\3\0\1\112\4\11\10\0\1\11\1\0"+
    "\5\11\5\0\5\11\13\0\1\113\4\11\1\0\2\11"+
    "\3\0\2\11\1\114\2\11\10\0\1\11\1\0\5\11"+
    "\5\0\5\11\63\0\1\47\1\115\4\0\60\116\1\0"+
    "\1\117\2\116\5\0\1\120\63\0\1\120\1\0\1\121"+
    "\61\0\1\120\1\0\1\122\61\0\1\120\6\0\1\123"+
    "\55\0\5\11\1\0\2\11\3\0\5\11\10\0\1\11"+
    "\1\0\1\11\1\124\3\11\5\0\5\11\45\0\1\125"+
    "\31\0\5\11\1\0\2\11\3\0\5\11\10\0\1\11"+
    "\1\0\1\126\4\11\5\0\5\11\13\0\5\11\1\127"+
    "\2\11\3\0\5\11\10\0\1\130\1\0\5\11\5\0"+
    "\1\11\1\131\3\11\13\0\1\11\1\132\3\11\1\0"+
    "\2\11\3\0\5\11\10\0\1\11\1\0\5\11\5\0"+
    "\5\11\13\0\5\11\1\0\1\11\1\133\3\0\5\11"+
    "\10\0\1\11\1\0\5\11\5\0\5\11\13\0\5\11"+
    "\1\0\2\11\3\0\1\134\4\11\10\0\1\11\1\0"+
    "\5\11\5\0\5\11\13\0\5\11\1\0\2\11\3\0"+
    "\1\11\1\135\3\11\10\0\1\11\1\0\5\11\5\0"+
    "\5\11\13\0\1\136\27\0\1\137\33\0\5\11\1\0"+
    "\2\11\3\0\5\11\10\0\1\11\1\0\2\11\1\140"+
    "\2\11\5\0\5\11\13\0\5\11\1\141\2\11\3\0"+
    "\5\11\10\0\1\11\1\0\5\11\5\0\1\11\1\142"+
    "\3\11\13\0\2\11\1\143\2\11\1\0\2\11\3\0"+
    "\5\11\10\0\1\11\1\0\5\11\5\0\5\11\13\0"+
    "\5\11\1\0\2\11\3\0\4\11\1\144\10\0\1\11"+
    "\1\0\5\11\5\0\5\11\43\0\1\145\33\0\5\11"+
    "\1\0\2\11\3\0\5\11\10\0\1\146\1\0\5\11"+
    "\5\0\5\11\13\0\4\11\1\147\1\0\2\11\3\0"+
    "\5\11\10\0\1\11\1\0\1\150\4\11\5\0\5\11"+
    "\13\0\5\11\1\151\2\11\3\0\4\11\1\152\10\0"+
    "\1\11\1\0\5\11\5\0\1\11\1\153\3\11\16\0"+
    "\1\154\60\0\1\155\4\11\1\0\2\11\3\0\5\11"+
    "\10\0\1\156\1\0\5\11\5\0\5\11\13\0\3\11"+
    "\1\157\1\11\1\0\2\11\3\0\5\11\10\0\1\11"+
    "\1\0\5\11\5\0\5\11\63\0\1\160\15\0\1\161"+
    "\65\0\1\162\66\0\1\163\104\0\1\164\33\0\5\11"+
    "\1\0\2\11\3\0\5\11\10\0\1\165\1\0\5\11"+
    "\5\0\5\11\13\0\5\11\1\0\2\11\3\0\5\11"+
    "\10\0\1\166\1\0\5\11\5\0\5\11\13\0\2\11"+
    "\1\167\2\11\1\0\2\11\3\0\5\11\10\0\1\11"+
    "\1\0\5\11\5\0\5\11\13\0\5\11\1\0\2\11"+
    "\3\0\1\11\1\170\3\11\10\0\1\11\1\0\5\11"+
    "\5\0\5\11\13\0\5\11\1\0\2\11\3\0\5\11"+
    "\10\0\1\11\1\0\1\171\4\11\5\0\5\11\43\0"+
    "\1\172\33\0\5\11\1\0\2\11\3\0\1\11\1\173"+
    "\3\11\10\0\1\11\1\0\5\11\5\0\5\11\27\0"+
    "\1\174\47\0\5\11\1\0\2\11\3\0\1\11\1\175"+
    "\3\11\10\0\1\11\1\0\5\11\5\0\5\11\13\0"+
    "\5\11\1\176\2\11\3\0\5\11\10\0\1\11\1\0"+
    "\5\11\5\0\1\11\1\177\3\11\13\0\5\11\1\0"+
    "\2\11\3\0\5\11\10\0\1\11\1\0\3\11\1\200"+
    "\1\11\5\0\5\11\27\0\1\201\47\0\5\11\1\0"+
    "\2\11\3\0\5\11\10\0\1\202\1\0\5\11\5\0"+
    "\5\11\13\0\5\11\1\0\2\11\3\0\1\11\1\203"+
    "\3\11\10\0\1\11\1\0\5\11\5\0\5\11\22\0"+
    "\1\204\54\0\5\11\1\0\2\11\3\0\5\11\10\0"+
    "\1\205\1\0\5\11\5\0\5\11\13\0\5\11\1\0"+
    "\1\11\1\206\3\0\5\11\10\0\1\11\1\0\5\11"+
    "\5\0\5\11\12\0\1\207\63\0\1\210\70\0\1\211"+
    "\57\0\5\11\1\0\2\11\3\0\1\212\4\11\10\0"+
    "\1\11\1\0\5\11\5\0\5\11\13\0\5\11\1\0"+
    "\1\11\1\213\3\0\5\11\10\0\1\11\1\0\5\11"+
    "\5\0\5\11\27\0\1\214\47\0\5\11\1\0\2\11"+
    "\3\0\1\11\1\215\3\11\10\0\1\11\1\0\5\11"+
    "\5\0\5\11\13\0\4\11\1\216\1\0\2\11\3\0"+
    "\5\11\10\0\1\11\1\0\5\11\5\0\5\11\13\0"+
    "\5\11\1\0\2\11\3\0\1\217\4\11\10\0\1\11"+
    "\1\0\5\11\5\0\5\11\12\0\1\220\64\0\5\11"+
    "\1\0\2\11\3\0\5\11\10\0\1\221\1\0\5\11"+
    "\5\0\5\11\13\0\5\11\1\222\2\11\3\0\5\11"+
    "\10\0\1\11\1\0\5\11\5\0\1\11\1\223\3\11"+
    "\13\0\5\11\1\0\2\11\3\0\5\11\10\0\1\11"+
    "\1\0\4\11\1\224\5\0\5\11\13\0\5\11\1\0"+
    "\2\11\3\0\4\11\1\225\10\0\1\11\1\0\5\11"+
    "\5\0\5\11\13\0\4\11\1\226\1\0\2\11\3\0"+
    "\5\11\10\0\1\11\1\0\5\11\5\0\5\11\13\0"+
    "\5\11\1\0\2\11\3\0\3\11\1\227\1\11\10\0"+
    "\1\11\1\0\5\11\5\0\5\11\13\0\5\11\1\0"+
    "\1\11\1\230\3\0\5\11\10\0\1\11\1\0\5\11"+
    "\5\0\5\11\5\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4732];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\3\11\10\1\3\11\12\1\2\11"+
    "\2\1\4\11\4\1\1\11\1\1\1\11\4\0\1\1"+
    "\1\0\6\1\1\0\4\1\1\0\2\1\7\11\1\1"+
    "\1\0\2\11\3\1\2\0\2\11\3\0\1\1\1\11"+
    "\1\1\1\0\6\1\1\11\1\0\1\1\1\0\3\1"+
    "\1\11\3\1\1\0\2\1\1\0\4\1\3\0\1\11"+
    "\5\1\1\11\1\1\1\11\1\1\1\0\2\1\1\11"+
    "\2\1\1\11\2\1\2\11\1\0\2\1\1\11\3\1"+
    "\1\11\1\1\1\11\6\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[152];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    //Código de usuario


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Haskell_Lexico(java.io.Reader in) {
    this.zzReader = in;
  }



  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;           
    int totalRead = 0;
    while (totalRead < requested) {
      int numRead = zzReader.read(zzBuffer, zzEndRead + totalRead, requested - totalRead);
      if (numRead == -1) {
        break;
      }
      totalRead += numRead;
    }

    if (totalRead > 0) {
      zzEndRead += totalRead;
      if (totalRead == requested) { /* possibly more input available */
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      return false;
    }

    // totalRead = 0: End of stream
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 1: 
          { String errLex = "Error léxico, caracter irreconocible: '"+yytext()+"' en la línea: "+(yyline+1)+" y columna: "+yycolumn;
        System.out.println(errLex);
          }
        case 56: break;
        case 2: 
          { return new Symbol(sym.TMas, yyline, yycolumn, yytext());
          }
        case 57: break;
        case 3: 
          { return new Symbol(sym.TMenos, yyline, yycolumn, yytext());
          }
        case 58: break;
        case 4: 
          { return new Symbol(sym.TPor, yyline, yycolumn, yytext());
          }
        case 59: break;
        case 5: 
          { return new Symbol(sym.TDivision, yyline, yycolumn, yytext());
          }
        case 60: break;
        case 6: 
          { return new Symbol(sym.Id, yyline, yycolumn, yytext());
          }
        case 61: break;
        case 7: 
          { return new Symbol(sym.TDolar, yyline, yycolumn, yytext());
          }
        case 62: break;
        case 8: 
          { return new Symbol(sym.TParentesis_Abre, yyline, yycolumn, yytext());
          }
        case 63: break;
        case 9: 
          { return new Symbol(sym.TParentesis_Cierra, yyline, yycolumn, yytext());
          }
        case 64: break;
        case 10: 
          { return new Symbol(sym.TMayor, yyline, yycolumn, yytext());
          }
        case 65: break;
        case 11: 
          { return new Symbol(sym.TMenor, yyline, yycolumn, yytext());
          }
        case 66: break;
        case 12: 
          { return new Symbol(sym.TIgual, yyline, yycolumn, yytext());
          }
        case 67: break;
        case 13: 
          { return new Symbol(sym.TCorchete_Abre, yyline, yycolumn, yytext());
          }
        case 68: break;
        case 14: 
          { return new Symbol(sym.TCorchete_Cierra, yyline, yycolumn, yytext());
          }
        case 69: break;
        case 15: 
          { return new Symbol(sym.TLlave_Abre, yyline, yycolumn, yytext());
          }
        case 70: break;
        case 16: 
          { return new Symbol(sym.TLlave_Cierra, yyline, yycolumn, yytext());
          }
        case 71: break;
        case 17: 
          { return new Symbol(sym.TComa, yyline, yycolumn, yytext());
          }
        case 72: break;
        case 18: 
          { return new Symbol(sym.TPuntoyComa, yyline, yycolumn, yytext());
          }
        case 73: break;
        case 19: 
          { return new Symbol(sym.Numero, yyline, yycolumn, yytext());
          }
        case 74: break;
        case 20: 
          { 
          }
        case 75: break;
        case 21: 
          { return new Symbol(sym.TAumento, yyline, yycolumn, yytext());
          }
        case 76: break;
        case 22: 
          { return new Symbol(sym.TMayorIgual, yyline, yycolumn, yytext());
          }
        case 77: break;
        case 23: 
          { return new Symbol(sym.TMenorIgual, yyline, yycolumn, yytext());
          }
        case 78: break;
        case 24: 
          { return new Symbol(sym.TIgualacion, yyline, yycolumn, yytext());
          }
        case 79: break;
        case 25: 
          { return new Symbol(sym.TDiferenciacion, yyline, yycolumn, yytext());
          }
        case 80: break;
        case 26: 
          { return new Symbol(sym.TIndiceLista, yyline, yycolumn, yytext());
          }
        case 81: break;
        case 27: 
          { return new Symbol(sym.TAnd, yyline, yycolumn, yytext());
          }
        case 82: break;
        case 28: 
          { return new Symbol(sym.TOr, yyline, yycolumn, yytext());
          }
        case 83: break;
        case 29: 
          { return new Symbol(sym.TIf, yyline, yycolumn, yytext());
          }
        case 84: break;
        case 30: 
          { System.out.println(yytext());return new Symbol(sym.TDosPuntos, yyline, yycolumn, yytext());
          }
        case 85: break;
        case 31: 
          { return new Symbol(sym.Cadena, yyline, yycolumn, yytext());
          }
        case 86: break;
        case 32: 
          { return new Symbol(sym.Caracter, yyline, yycolumn, yytext());
          }
        case 87: break;
        case 33: 
          { return new Symbol(sym.TMax, yyline, yycolumn, yytext());
          }
        case 88: break;
        case 34: 
          { return new Symbol(sym.TMin, yyline, yycolumn, yytext());
          }
        case 89: break;
        case 35: 
          { return new Symbol(sym.TPar, yyline, yycolumn, yytext());
          }
        case 90: break;
        case 36: 
          { return new Symbol(sym.TSum, yyline, yycolumn, yytext());
          }
        case 91: break;
        case 37: 
          { return new Symbol(sym.TEnd, yyline, yycolumn, yytext());
          }
        case 92: break;
        case 38: 
          { return new Symbol(sym.TAsc, yyline, yycolumn, yytext());
          }
        case 93: break;
        case 39: 
          { return new Symbol(sym.TLet, yyline, yycolumn, yytext());
          }
        case 94: break;
        case 40: 
          { return new Symbol(sym.TDesc, yyline, yycolumn, yytext());
          }
        case 95: break;
        case 41: 
          { return new Symbol(sym.TDecc, yyline, yycolumn, yytext());
          }
        case 96: break;
        case 42: 
          { return new Symbol(sym.TVerdadero, yyline, yycolumn, yytext());
          }
        case 97: break;
        case 43: 
          { return new Symbol(sym.TThen, yyline, yycolumn, yytext());
          }
        case 98: break;
        case 44: 
          { return new Symbol(sym.TSucc, yyline, yycolumn, yytext());
          }
        case 99: break;
        case 45: 
          { return new Symbol(sym.TElse, yyline, yycolumn, yytext());
          }
        case 100: break;
        case 46: 
          { return new Symbol(sym.TCase, yyline, yycolumn, yytext());
          }
        case 101: break;
        case 47: 
          { return new Symbol(sym.TImpr, yyline, yycolumn, yytext());
          }
        case 102: break;
        case 48: 
          { return new Symbol(sym.TModulo, yyline, yycolumn, yytext());
          }
        case 103: break;
        case 49: 
          { return new Symbol(sym.TPotencia, yyline, yycolumn, yytext());
          }
        case 104: break;
        case 50: 
          { return new Symbol(sym.TFalso, yyline, yycolumn, yytext());
          }
        case 105: break;
        case 51: 
          { return new Symbol(sym.TRaiz, yyline, yycolumn, yytext());
          }
        case 106: break;
        case 52: 
          { return new Symbol(sym.TRevers, yyline, yycolumn, yytext());
          }
        case 107: break;
        case 53: 
          { return new Symbol(sym.TLength, yyline, yycolumn, yytext());
          }
        case 108: break;
        case 54: 
          { return new Symbol(sym.TProduct, yyline, yycolumn, yytext());
          }
        case 109: break;
        case 55: 
          { return new Symbol(sym.TCalcular, yyline, yycolumn, yytext());
          }
        case 110: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(sym.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
