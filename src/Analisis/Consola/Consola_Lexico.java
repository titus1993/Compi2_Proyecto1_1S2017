/* The following code was generated by JFlex 1.6.0 */


package Analisis.Consola;

import Constante.Constante;
import java_cup.runtime.Symbol;
import Interface.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.0
 * from the specification file <tt>Consola_Lexico.jflex</tt>
 */
public class Consola_Lexico implements java_cup.runtime.Scanner {

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
     0,  0,  0,  0,  0,  0,  0,  0,  0, 48, 45, 49, 48, 48,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
    48, 20, 46,  0, 14, 47, 21,  5, 15, 16,  3,  1, 38,  2, 44,  4, 
    43, 43, 43, 43, 43, 43, 43, 43, 43, 43,  0,  0, 18, 19, 17,  0, 
     0, 28, 39, 27,  8, 26, 39, 34, 35, 41, 39, 39, 25,  6, 31,  7, 
     9, 12, 13, 40, 10, 29, 33, 39, 32, 39, 39, 23,  0, 24,  0, 42, 
     0, 28, 39, 27,  8, 26, 39, 34, 35, 41, 39, 39, 25,  6, 31,  7, 
     9, 12, 13, 40, 10, 29, 33, 39, 32, 39, 39, 36, 22, 37,  0,  0, 
     0,  0,  0,  0,  0, 49,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
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
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\1\4\6"+
    "\1\1\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
    "\3\1\1\15\1\16\3\6\1\1\1\17\1\20\1\21"+
    "\2\6\1\22\1\23\1\1\1\24\1\25\4\0\1\6"+
    "\1\0\4\6\1\0\1\6\1\26\1\27\1\30\1\31"+
    "\1\32\1\33\1\34\2\6\1\0\1\6\1\0\2\6"+
    "\2\0\1\35\1\36\3\0\1\37\2\40\1\0\3\6"+
    "\1\41\1\42\1\0\1\6\1\43\2\6\2\44\1\0"+
    "\1\42\2\6\1\22\3\0\1\45\1\46\1\45\1\6"+
    "\1\47\3\6\1\50\1\47\1\50\1\51\1\52\1\0"+
    "\4\6\1\53\1\6\2\54\1\55\1\6\1\56\1\6"+
    "\1\57";

  private static int [] zzUnpackAction() {
    int [] result = new int[122];
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
    "\0\0\0\62\0\144\0\62\0\62\0\62\0\226\0\310"+
    "\0\372\0\u012c\0\u015e\0\u0190\0\u01c2\0\62\0\62\0\62"+
    "\0\u01f4\0\u0226\0\u0258\0\u028a\0\u02bc\0\u02ee\0\62\0\62"+
    "\0\u0320\0\u0352\0\u0384\0\u03b6\0\62\0\62\0\62\0\u03e8"+
    "\0\u041a\0\u044c\0\62\0\u047e\0\62\0\62\0\u04b0\0\u04e2"+
    "\0\u0514\0\u0546\0\u0578\0\u05aa\0\u05dc\0\u060e\0\u0640\0\u0672"+
    "\0\u06a4\0\u06d6\0\62\0\62\0\62\0\62\0\62\0\62"+
    "\0\62\0\u0708\0\u073a\0\u076c\0\u079e\0\u07d0\0\u0802\0\u0834"+
    "\0\u0866\0\u047e\0\62\0\62\0\u0898\0\u08ca\0\u08fc\0\372"+
    "\0\62\0\372\0\u092e\0\u0960\0\u0992\0\u09c4\0\372\0\62"+
    "\0\u09f6\0\u0a28\0\372\0\u0a5a\0\u0a8c\0\62\0\372\0\u0abe"+
    "\0\372\0\u0af0\0\u0b22\0\u0866\0\u0b54\0\u0b86\0\u0bb8\0\62"+
    "\0\372\0\372\0\u0bea\0\62\0\u0c1c\0\u0c4e\0\u0c80\0\62"+
    "\0\372\0\372\0\62\0\62\0\u0cb2\0\u0ce4\0\u0d16\0\u0d48"+
    "\0\u0d7a\0\62\0\u0dac\0\62\0\372\0\372\0\u0dde\0\372"+
    "\0\u0e10\0\372";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[122];
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
    "\1\12\1\13\1\11\1\14\1\11\1\15\1\16\1\17"+
    "\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27"+
    "\1\30\1\31\1\11\1\32\1\33\1\11\1\34\5\11"+
    "\1\35\1\36\1\37\1\11\1\40\1\41\1\2\1\42"+
    "\1\2\1\43\1\44\1\45\1\43\64\0\1\46\60\0"+
    "\5\47\1\0\1\50\2\47\1\51\1\47\1\52\34\47"+
    "\1\52\4\47\1\0\4\47\6\0\5\11\1\0\2\11"+
    "\13\0\3\11\1\53\1\11\1\54\5\11\3\0\2\11"+
    "\1\55\2\11\14\0\5\11\1\0\2\11\13\0\5\11"+
    "\1\0\5\11\3\0\5\11\14\0\5\11\1\0\2\11"+
    "\13\0\1\11\1\56\3\11\1\0\5\11\3\0\5\11"+
    "\14\0\5\11\1\0\1\11\1\57\13\0\3\11\1\60"+
    "\1\11\1\0\5\11\3\0\5\11\43\0\1\61\32\0"+
    "\5\11\1\0\2\11\13\0\1\11\1\62\3\11\1\0"+
    "\5\11\3\0\5\11\31\0\1\63\61\0\1\64\61\0"+
    "\1\65\61\0\1\66\1\67\62\0\1\70\62\0\1\71"+
    "\41\0\5\11\1\0\2\11\13\0\1\11\1\72\3\11"+
    "\1\0\5\11\3\0\5\11\14\0\5\11\1\0\2\11"+
    "\13\0\3\11\1\73\1\11\1\0\5\11\3\0\5\11"+
    "\14\0\5\11\1\74\2\11\13\0\5\11\1\0\5\11"+
    "\3\0\1\11\1\75\3\11\14\0\1\76\61\0\5\11"+
    "\1\0\2\11\13\0\4\11\1\77\1\0\5\11\3\0"+
    "\5\11\14\0\1\100\4\11\1\0\2\11\13\0\5\11"+
    "\1\0\5\11\3\0\5\11\61\0\1\42\1\101\5\0"+
    "\55\102\1\0\1\103\3\102\5\0\1\104\61\0\1\104"+
    "\1\0\1\105\57\0\1\104\1\0\1\106\57\0\1\104"+
    "\6\0\1\107\53\0\5\11\1\0\2\11\13\0\5\11"+
    "\1\0\1\11\1\110\3\11\3\0\5\11\45\0\1\111"+
    "\30\0\5\11\1\0\2\11\13\0\5\11\1\0\1\112"+
    "\4\11\3\0\5\11\14\0\5\11\1\113\2\11\13\0"+
    "\2\11\1\114\2\11\1\0\5\11\3\0\1\11\1\115"+
    "\3\11\14\0\1\11\1\116\3\11\1\0\2\11\13\0"+
    "\5\11\1\0\5\11\3\0\5\11\14\0\5\11\1\0"+
    "\1\11\1\117\13\0\5\11\1\0\5\11\3\0\5\11"+
    "\14\0\1\120\24\0\1\121\34\0\5\11\1\0\2\11"+
    "\13\0\5\11\1\0\2\11\1\122\2\11\3\0\5\11"+
    "\14\0\4\11\1\123\1\0\2\11\13\0\5\11\1\0"+
    "\1\124\4\11\3\0\5\11\14\0\5\11\1\0\2\11"+
    "\13\0\1\125\4\11\1\0\5\11\3\0\5\11\41\0"+
    "\1\126\34\0\5\11\1\0\2\11\13\0\2\11\1\127"+
    "\2\11\1\0\5\11\3\0\5\11\17\0\1\130\56\0"+
    "\1\131\4\11\1\0\2\11\13\0\2\11\1\132\2\11"+
    "\1\0\5\11\3\0\5\11\14\0\3\11\1\133\1\11"+
    "\1\0\2\11\13\0\5\11\1\0\5\11\3\0\5\11"+
    "\61\0\1\134\16\0\1\135\63\0\1\136\64\0\1\137"+
    "\77\0\1\140\34\0\5\11\1\0\2\11\13\0\2\11"+
    "\1\141\2\11\1\0\5\11\3\0\5\11\14\0\5\11"+
    "\1\0\2\11\13\0\2\11\1\142\2\11\1\0\5\11"+
    "\3\0\5\11\14\0\2\11\1\143\2\11\1\0\2\11"+
    "\13\0\5\11\1\0\5\11\3\0\5\11\41\0\1\144"+
    "\34\0\5\11\1\0\2\11\13\0\1\11\1\145\3\11"+
    "\1\0\5\11\3\0\5\11\14\0\5\11\1\0\2\11"+
    "\13\0\5\11\1\0\3\11\1\146\1\11\3\0\5\11"+
    "\14\0\5\11\1\0\2\11\13\0\2\11\1\147\2\11"+
    "\1\0\5\11\3\0\5\11\23\0\1\150\52\0\5\11"+
    "\1\0\2\11\13\0\2\11\1\151\2\11\1\0\5\11"+
    "\3\0\5\11\14\0\5\11\1\0\1\11\1\152\13\0"+
    "\5\11\1\0\5\11\3\0\5\11\13\0\1\153\61\0"+
    "\1\154\66\0\1\155\55\0\5\11\1\0\2\11\13\0"+
    "\4\11\1\156\1\0\5\11\3\0\5\11\14\0\5\11"+
    "\1\0\1\11\1\157\13\0\5\11\1\0\5\11\3\0"+
    "\5\11\14\0\4\11\1\160\1\0\2\11\13\0\5\11"+
    "\1\0\5\11\3\0\5\11\14\0\5\11\1\0\2\11"+
    "\13\0\4\11\1\161\1\0\5\11\3\0\5\11\13\0"+
    "\1\162\62\0\5\11\1\0\2\11\13\0\2\11\1\163"+
    "\2\11\1\0\5\11\3\0\5\11\14\0\5\11\1\164"+
    "\2\11\13\0\5\11\1\0\5\11\3\0\1\11\1\165"+
    "\3\11\14\0\5\11\1\0\2\11\13\0\5\11\1\0"+
    "\4\11\1\166\3\0\5\11\14\0\5\11\1\0\2\11"+
    "\13\0\1\167\4\11\1\0\5\11\3\0\5\11\14\0"+
    "\4\11\1\170\1\0\2\11\13\0\5\11\1\0\5\11"+
    "\3\0\5\11\14\0\5\11\1\0\2\11\13\0\3\11"+
    "\1\171\1\11\1\0\5\11\3\0\5\11\14\0\5\11"+
    "\1\0\1\11\1\172\13\0\5\11\1\0\5\11\3\0"+
    "\5\11\6\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3650];
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
    "\1\0\1\11\1\1\3\11\7\1\3\11\6\1\2\11"+
    "\4\1\3\11\3\1\1\11\1\1\2\11\4\0\1\1"+
    "\1\0\4\1\1\0\1\1\7\11\2\1\1\0\1\1"+
    "\1\0\2\1\2\0\2\11\3\0\1\1\1\11\1\1"+
    "\1\0\4\1\1\11\1\0\4\1\1\11\1\1\1\0"+
    "\4\1\3\0\1\11\3\1\1\11\3\1\1\11\2\1"+
    "\2\11\1\0\4\1\1\11\1\1\1\11\6\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[122];
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
  public Consola_Lexico(java.io.Reader in) {
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
          { //String errLex = "Error léxico, caracter irreconocible: '"+yytext()+"' en la línea: "+(yyline)+" y columna: "+yycolumn;
        TitusNotificaciones.InsertarError(Constante.TErrorLexico,"Simbolo " + yytext() + " no reconocido", yyline, yycolumn);
          }
        case 48: break;
        case 2: 
          { System.out.println(yytext()); return  new Symbol(sym.TMas, yyline, yycolumn, yytext());
          }
        case 49: break;
        case 3: 
          { System.out.println(yytext()); return  new Symbol(sym.TMenos, yyline, yycolumn, yytext());
          }
        case 50: break;
        case 4: 
          { System.out.println(yytext()); return  new Symbol(sym.TPor, yyline, yycolumn, yytext());
          }
        case 51: break;
        case 5: 
          { System.out.println(yytext()); return  new Symbol(sym.TDivision, yyline, yycolumn, yytext());
          }
        case 52: break;
        case 6: 
          { System.out.println(yytext()); return  new Symbol(sym.Id, yyline, yycolumn, yytext());
          }
        case 53: break;
        case 7: 
          { System.out.println(yytext()); return  new Symbol(sym.TDolar, yyline, yycolumn, yytext());
          }
        case 54: break;
        case 8: 
          { System.out.println(yytext()); return  new Symbol(sym.TParentesis_Abre, yyline, yycolumn, yytext());
          }
        case 55: break;
        case 9: 
          { System.out.println(yytext()); return  new Symbol(sym.TParentesis_Cierra, yyline, yycolumn, yytext());
          }
        case 56: break;
        case 10: 
          { System.out.println(yytext()); return  new Symbol(sym.TMayor, yyline, yycolumn, yytext());
          }
        case 57: break;
        case 11: 
          { System.out.println(yytext()); return  new Symbol(sym.TMenor, yyline, yycolumn, yytext());
          }
        case 58: break;
        case 12: 
          { System.out.println(yytext()); return  new Symbol(sym.TIgual, yyline, yycolumn, yytext());
          }
        case 59: break;
        case 13: 
          { System.out.println(yytext()); return  new Symbol(sym.TCorchete_Abre, yyline, yycolumn, yytext());
          }
        case 60: break;
        case 14: 
          { System.out.println(yytext()); return  new Symbol(sym.TCorchete_Cierra, yyline, yycolumn, yytext());
          }
        case 61: break;
        case 15: 
          { System.out.println(yytext()); return  new Symbol(sym.TLlave_Abre, yyline, yycolumn, yytext());
          }
        case 62: break;
        case 16: 
          { System.out.println(yytext()); return  new Symbol(sym.TLlave_Cierra, yyline, yycolumn, yytext());
          }
        case 63: break;
        case 17: 
          { System.out.println(yytext()); return  new Symbol(sym.TComa, yyline, yycolumn, yytext());
          }
        case 64: break;
        case 18: 
          { System.out.println(yytext()); return  new Symbol(sym.Numero, yyline, yycolumn, yytext());
          }
        case 65: break;
        case 19: 
          { 
          }
        case 66: break;
        case 20: 
          { System.out.println(yytext()); return  new Symbol(sym.TPorcentaje, yyline, yycolumn, yytext());
          }
        case 67: break;
        case 21: 
          { System.out.println(yytext()); return  new Symbol(sym.TAumento, yyline, yycolumn, yytext());
          }
        case 68: break;
        case 22: 
          { System.out.println(yytext()); return  new Symbol(sym.TMayorIgual, yyline, yycolumn, yytext());
          }
        case 69: break;
        case 23: 
          { System.out.println(yytext()); return  new Symbol(sym.TMenorIgual, yyline, yycolumn, yytext());
          }
        case 70: break;
        case 24: 
          { System.out.println(yytext()); return  new Symbol(sym.TIgualacion, yyline, yycolumn, yytext());
          }
        case 71: break;
        case 25: 
          { System.out.println(yytext()); return  new Symbol(sym.TDiferenciacion, yyline, yycolumn, yytext());
          }
        case 72: break;
        case 26: 
          { System.out.println(yytext()); return  new Symbol(sym.TIndiceLista, yyline, yycolumn, yytext());
          }
        case 73: break;
        case 27: 
          { System.out.println(yytext()); return  new Symbol(sym.TAnd, yyline, yycolumn, yytext());
          }
        case 74: break;
        case 28: 
          { System.out.println(yytext()); return  new Symbol(sym.TOr, yyline, yycolumn, yytext());
          }
        case 75: break;
        case 29: 
          { System.out.println(yytext()); return  new Symbol(sym.Cadena, yyline, yycolumn, yytext());
          }
        case 76: break;
        case 30: 
          { System.out.println(yytext()); return  new Symbol(sym.Caracter, yyline, yycolumn, yytext());
          }
        case 77: break;
        case 31: 
          { System.out.println(yytext()); return  new Symbol(sym.TMax, yyline, yycolumn, yytext());
          }
        case 78: break;
        case 32: 
          { System.out.println(yytext()); return  new Symbol(sym.TMin, yyline, yycolumn, yytext());
          }
        case 79: break;
        case 33: 
          { System.out.println(yytext()); return  new Symbol(sym.TPar, yyline, yycolumn, yytext());
          }
        case 80: break;
        case 34: 
          { System.out.println(yytext()); return  new Symbol(sym.TSum, yyline, yycolumn, yytext());
          }
        case 81: break;
        case 35: 
          { System.out.println(yytext()); return  new Symbol(sym.TLet, yyline, yycolumn, yytext());
          }
        case 82: break;
        case 36: 
          { System.out.println(yytext()); return  new Symbol(sym.TAsc, yyline, yycolumn, yytext());
          }
        case 83: break;
        case 37: 
          { System.out.println(yytext()); return  new Symbol(sym.TDesc, yyline, yycolumn, yytext());
          }
        case 84: break;
        case 38: 
          { System.out.println(yytext()); return  new Symbol(sym.TDecc, yyline, yycolumn, yytext());
          }
        case 85: break;
        case 39: 
          { System.out.println(yytext()); return  new Symbol(sym.TSucc, yyline, yycolumn, yytext());
          }
        case 86: break;
        case 40: 
          { System.out.println(yytext()); return  new Symbol(sym.TImpr, yyline, yycolumn, yytext());
          }
        case 87: break;
        case 41: 
          { System.out.println(yytext()); return  new Symbol(sym.TModulo, yyline, yycolumn, yytext());
          }
        case 88: break;
        case 42: 
          { System.out.println(yytext()); return  new Symbol(sym.TPotencia, yyline, yycolumn, yytext());
          }
        case 89: break;
        case 43: 
          { System.out.println(yytext()); return  new Symbol(sym.TRaiz, yyline, yycolumn, yytext());
          }
        case 90: break;
        case 44: 
          { System.out.println(yytext()); return  new Symbol(sym.TRevers, yyline, yycolumn, yytext());
          }
        case 91: break;
        case 45: 
          { System.out.println(yytext()); return  new Symbol(sym.TLength, yyline, yycolumn, yytext());
          }
        case 92: break;
        case 46: 
          { System.out.println(yytext()); return  new Symbol(sym.TProduct, yyline, yycolumn, yytext());
          }
        case 93: break;
        case 47: 
          { System.out.println(yytext()); return  new Symbol(sym.TCalcular, yyline, yycolumn, yytext());
          }
        case 94: break;
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
