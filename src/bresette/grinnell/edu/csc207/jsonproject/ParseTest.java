package bresette.grinnell.edu.csc207.jsonproject;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.Test;

public class ParseTest
{

  @Test
  public void testParseString()
  {
    String hola = "\"Hello World\"";
    Object ob = "Hello World";
    assertEquals("Testing with one string", ob, Parse.parse(hola));
  } // test parse string

  @Test
  public void testUnparseString()
    throws ClassNotFoundException
  {
    String hola = "\"Hello World\"";
    assertEquals("Testing unparse string", hola,
                 Parse.unparse(Parse.parse(hola)));
  } // test unparse string

  @Test
  public void testParseNumber()
  {
    String num = "123.456";
    Object ob = BigDecimal.valueOf(123.456);
    assertEquals("Testing with one number", ob, Parse.parse(num));

    String num1 = "-11.23";
    assertEquals("Testing with one number", BigDecimal.valueOf(-11.23),
                 Parse.parse(num1));
    String num2 = ".1";
    assertEquals("Testing with one number", BigDecimal.valueOf(.1),
                 Parse.parse(num2));
    String num3 = "4e25";
    assertEquals("Testing with one number", BigDecimal.valueOf(4e25),
                 Parse.parse(num3));
    String num4 = "1.4e25";
    assertEquals("Testing with one number", BigDecimal.valueOf(1.4e25),
                 Parse.parse(num4));
    String num0 = "0";
    assertEquals("Testing with one number", BigDecimal.valueOf(0.0),
                 Parse.parse(num0));
  } // test parse number

  @Test
  public void testUnparseNumber()
    throws ClassNotFoundException
  {
    String num = "123.456";
    assertEquals("Testing unparse string", num, Parse.unparse(Parse.parse(num)));
  } // test unparse number

  @Test
  public void testParseLiterals()
  {
    String tru = "true";
    Object ob = true;
    assertEquals("Testing with true", ob, Parse.parse(tru));

    String fal = "false";
    Object obj = false;
    assertEquals("Testing with true", obj, Parse.parse(fal));

    String nul = "null";
    Object obje = null;
    assertEquals("Testing with true", obje, Parse.parse(nul));
  } // test parse literals

  @Test
  public void testUnparseLiterals()
    throws ClassNotFoundException
  {
    String tru = "true";
    assertEquals("Testing unparse true", tru, Parse.unparse(Parse.parse(tru)));

    String fal = "false";
    assertEquals("Testing unparse false", fal, Parse.unparse(Parse.parse(fal)));

    String nul = "null";
    assertEquals("Testing unparse null", nul, Parse.unparse(Parse.parse(nul)));
  } // test unparse literals

  @Test
  public void testParseObject()
  {
    String object = "{\"nora\":1,\"helen\":\"d\"}";
    assertEquals("Testing objects", BigDecimal.valueOf(1.0),
                 ((Hashtable) Parse.parse(object)).get("nora"));
    assertEquals("Testing objects", "d",
                 ((Hashtable) Parse.parse(object)).get("helen"));

  } // test parse objects

  @Test
  public void testParseArray()
  {
    String intArray = "[1,2,3,4,5,6,7,8,9]";
    String strArray = "[\"nora\",\"helen\",\"csc207\"]";
    String literalArray = "[true,false,null]";
    String mixedArray1 = "[1,2,\"hello\"]";
    String mixedArray2 = "[1,true,\"hello\"]";
    String nullArray = "[null,null,null]";
    String empty = "[]";

    ArrayList<Object> intArrayList = new ArrayList<Object>();
    for (int i = 1; i < 10; i++)
      {
        intArrayList.add(BigDecimal.valueOf((double) i));
      } // for
    assertEquals("Testing an array of numbers", intArrayList,
                 Parse.parse(intArray));

    ArrayList<Object> strArrayList = new ArrayList<Object>();
    strArrayList.add("nora");
    strArrayList.add("helen");
    strArrayList.add("csc207");
    assertEquals("Testing an array of strings", strArrayList,
                 Parse.parse(strArray));

    ArrayList<Object> literalArrayList = new ArrayList<Object>();
    literalArrayList.add(true);
    literalArrayList.add(false);
    literalArrayList.add(null);
    assertEquals("Testing an array of literals", literalArrayList,
                 Parse.parse(literalArray));

    ArrayList<Object> mixedArrayList1 = new ArrayList<Object>();
    mixedArrayList1.add(BigDecimal.valueOf((double) 1));
    mixedArrayList1.add(BigDecimal.valueOf((double) 2));
    mixedArrayList1.add("hello");
    assertEquals("Testing a mixed array", mixedArrayList1,
                 Parse.parse(mixedArray1));

    ArrayList<Object> mixedArrayList2 = new ArrayList<Object>();
    mixedArrayList2.add(BigDecimal.valueOf((double) 1));
    mixedArrayList2.add(true);
    mixedArrayList2.add("hello");
    assertEquals("Testing a mixed array", mixedArrayList2,
                 Parse.parse(mixedArray2));

    ArrayList<Object> nullArrayList = new ArrayList<Object>();
    nullArrayList.add(null);
    nullArrayList.add(null);
    nullArrayList.add(null);
    assertEquals("Testing a null array", nullArrayList, Parse.parse(nullArray));

    ArrayList<Object> emptyArrayList = new ArrayList<Object>();
    assertEquals("Testing empty array", emptyArrayList, Parse.parse(empty));
  } // test parse array

  @Test
  public void testParseNestedObject()
    throws ClassNotFoundException
  {
    String obj = "{\"name\":{\"first\":\"nora\",\"last\":\"buccino\"}}";
    String result = Parse.unparse(Parse.parse(obj));
    assertEquals("Testing with nested arrays", Parse.parse(result),
                 Parse.parse(obj));
  } // test parse nested objects

  @Test
  public void testParseNestedArray()
    throws ClassNotFoundException
  {
    String obj = "[1,2,3,[2,3,45]]";
    String result = Parse.unparse(Parse.parse(obj));
    assertEquals("Testing with nested arrays", Parse.parse(result),
                 Parse.parse(obj));
  } // test parse nested arrays

}
