package bresette.grinnell.edu.csc207.jsonproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

public class ParseEXPT
{
  public static void main(String[] args)
    throws ClassNotFoundException, IOException
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    Object ob = Parse.parse("[\"n\",2]");
    pen.println(ob);
    String result = Parse.unparse(ob);
    pen.println(result);
    pen.println(Parse.parse(result));

    Object two = Parse.parse("{\"nora\":20}");
    pen.println(two);
    String res = Parse.unparse(two);
    pen.println(res);
    pen.println(Parse.parse(res));

    
    Object nullity = Parse.parse("null");
    pen.println(nullity);
    String nullresult = Parse.unparse(nullity);
    pen.println(nullresult);
    pen.println(Parse.parse(nullresult));
    
    
    BufferedReader eyes;
    java.io.File infile;
    java.io.FileReader istream;
    infile = new java.io.File ("/Users/norabuccino/Desktop/jsonExamples.txt");
    istream = new java.io.FileReader (infile);
    eyes = new BufferedReader (istream);

    String line;
    while((line = eyes.readLine()) != null)
      {
        pen.println(Parse.parse(line));
      }
  }

}
