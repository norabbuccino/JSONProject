package edu.grinnell.bresette.doughert1.JSON;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Some simple experiments with JSON.
 */
public class JSONExpt
{
  /**
   * One experiment.  Print a JSON string.  Parse the string.  Print the
   * parsed version.
   */
  public static void expt(PrintWriter pen, String str)
    throws Exception
  {
    pen.println("Input:    " + str);
    Object obj = Parse.parse(str);
    pen.println("Parsed:   " + obj);
    String json = Parse.unparse(obj);
    pen.println("Unparsed: " + json);
    obj = Parse.parse(json);
    pen.println("Reparsed: " + obj);
    json = Parse.unparse(obj);
    pen.println("Unparsed: " + json);
    pen.println();
  } // expt(String)

  /**
   * A host of experiments.
   */
  public static void main(String[] args)
    throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    expt(pen, "11");
    expt(pen, "0.2");
    expt(pen, "\"Hello\"");
    expt(pen, "[null,true,false]");
    expt(pen, "[null,[true,false],[],[\"a\",\"b\"]]");
    expt(pen, "\"\\\"\"");
    expt(pen, "{\"a\":\"alpha\",\"b\":\"beta\"}");
    expt(pen, "{\"a\":\"alpha\",\"b\":{\"a\":\"alpha\",\"b\":\"beta\"}}");
    expt(pen,
         "{\"a\":\"alpha\",\"b\":{\"a\":\"alpha\",\"b\":\"beta\"},\"c\":{},\"d\":\"d\"}");
  } // main(String[])
} // JSONExpt