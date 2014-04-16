package bresette.grinnell.edu.csc207.jsonproject;

import java.io.PrintWriter;

public class ParseEXPT
{
  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.println(Parse.parse("[1,2]"));
  }

}
