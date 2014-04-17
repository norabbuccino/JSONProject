package bresette.grinnell.edu.csc207.jsonproject;
//sources for JSON strings in file
//http://www.jsonexample.com
//http://json.org/example.html
//http://www.w3schools.com/json/
//http://adobe.github.io/Spry/samples/data_region/JSONDataSetSample.html

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

import org.junit.Test;

public class ParseTest
{

  @Test
  public void testParseFromFile() throws FileNotFoundException
  {
    BufferedReader eyes;
    java.io.File infile;
    java.io.FileReader istream;
    infile = new java.io.File ("/Users/norabuccino/Desktop/jsonExamples.txt");
    istream = new java.io.FileReader (infile);
    eyes = new BufferedReader (istream);
    
    
  }

}
