package edu.grinnell.bresette.doughert1.JSON;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

// With help from Alex Greenberg
// Used http://stackoverflow.com/questions/2915453/how-to-get-hashtable-values-as-arraylist for unparsing hashtables
// Used Sam Rebelsky's Sample JSON Parser code for unparsing arraylists

/*
 * Ideas for improvements
 * -Adding support for whitespace characters
 * -Turn spreadsheets into Arrays and back
 * -Manipulate the parsed JSON
 * -Support additional input or output formats
 * -Good error reporting for incorrect input
 * -Return all strings, numbers, literals or arrays that occur in the JSON input
 */

/**
 * 
 * @author Nora Bresette Buccino and Helen Dougherty
 * 
 * Citations
 * Suggestion about creating a separate JSONInput object came from Alex Greenberg
 * Used http://stackoverflow.com/questions/2915453/how-to-get-hashtable-values-as-arraylist for unparsing hashtables
 * Used Sam Rebelsky's sample JSON parser code to help fix our code for unparsing arraylists
 *
 */
public class Parse
{
  /**
   * A method that parses a string of JSON input and returns an object representing that JSON string
   * @param str
   *    a String of JSON input
   * @return
   *    an Object representing the JSON input
   * @throws Exception 
   * @pre
   *    str must be valid JSON
   * @post
   *    returns a object representing JSON input from str
   */
  public static Object parse(String str)
    throws Exception
  {
    //Makes the string into a JSONInput object so we can keep track of the index
    JSONInput input = new JSONInput(str);
    return parse(input);
  } // Parse(String)

  /**
   * parsing the array
   * @param json
   * @return
   * @throws Exception
   */
  public static Object parseArray(JSONInput json)
    throws Exception
  {
    // create a place to store the elements of the array
    ArrayList<Object> list = new ArrayList<Object>();
    // keep going until we hit the end brace or run out of characters
    while (json.value.charAt(json.index) != ']'
           && json.index < (json.value.length() - 1))
      {
        //parse the next element of the array
        list.add(parse(json));
        // we should see either a comma or an end brace
        // if we see a comma, skip over it
        if (json.value.charAt(json.index) == ',')
          json.index++;
      } // while
    // skip over the end brace
    json.index++;
    return list;
  }  //parseArray(JSONInput)

  /**
   * parsing the string
   * @param json
   * @return
   */
  public static Object parseString(JSONInput json)
  {
    //create a new string to store the input
    String myString;
    //create a variable to store where the beginning of the substring will be
    int start = json.index;
    //keep going until we hit the end of the string
    while (json.index < (json.value.length() - 1)
           && (json.value.charAt(json.index) != '"' || json.value.charAt(json.index) == '\\'))
      {
        //skip over the character
        json.index++;
      } // while
    //extract the string
    myString = json.value.substring(start, json.index);
    //skip over the end quote
    json.index++;
    return myString;
  } // parseString(JSONInput)

  /**
   * parsing the object
   * @param json
   * @return
   * @throws Exception
   */
  public static Object parseObject(JSONInput json)
    throws Exception
  {
    Hashtable<String, Object> hash = new Hashtable<String, Object>();
    while (json.value.charAt(json.index) != '}')
      {
        String key;
        Object val;

        key = (String) parse(json);
        json.index++;
        val = parse(json);
        hash.put(key, val);
        if (json.value.charAt(json.index) == ',')
          json.index++;
      } // while
    return hash;
  }

  /**
   * parsing a number
   * @param json
   * @return
   */
  public static Object parseNumber(JSONInput json)
  {
    int start = json.index;
    while (json.index < json.value.length()
           && (Character.isDigit(json.value.charAt(json.index))
               || json.value.charAt(json.index) == '.'
               || json.value.charAt(json.index) == '-'
               || json.value.charAt(json.index) == 'e' || json.value.charAt(json.index) == 'E'))
      {
        json.index++;
      } // while
    String valStr = json.value.substring(start, json.index);
    BigDecimal digit = BigDecimal.valueOf(Double.valueOf(valStr));
    return digit;
  }

  /**
   * parsing a literal
   * @param json
   * @return
   * @throws Exception
   */
  public static Object parseLiteral(JSONInput json)
    throws Exception
  {
    if (json.value.substring(json.index, json.index + 4).equals("null")
        || json.value.substring(json.index, json.index + 4).equals("true")
        || json.value.substring(json.index, json.index + 5).equals("false"))
      {
        if (json.value.charAt(json.index) == 'n')
          {
            json.index = json.index + 4;
            return null;
          }
        else if (json.value.charAt(json.index) == 'f')
          {
            json.index = json.index + 5;
            return false;
          }
        else
          {
            json.index = json.index + 4;
            return true;
          }
      }
    else
      throw new Exception("Not null, true or false");
  }

  /**
   * A method that parses a JSONInput object and returns an object representing that JSONInput
   * @param json
   *    a JSONInput object with a string value and int index
   * @return
   *    an Object representing the JSON input
   * @throws Exception 
   * @pre
   *    JSONInput.value must be valid JSON
   * @post
   *    returns java objects representing the JSON input
   */
  public static Object parse(JSONInput json)
    throws Exception
  {
    switch (json.value.charAt(json.index))
      {
        case '[':
          {
            json.index++;
            return parseArray(json);
          } //case [

        case '{':
          {
            json.index++;
            return parseObject(json);
          }//case {

        case '"':
          {
            json.index++;
            return parseString(json);
          }//case "

        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
        case '-':
        case '.':
          {
            return parseNumber(json);
          } // all numeric cases
        default:
          return parseLiteral(json);
      }//switch
  } // Parse(Object)

  /**
   * A method to parse a Java object to a string of JSON
   * The Java object may be a String, a Hashtable, 
   * an ArrayList, a BigDecimal number, the booleans true 
   * or false, or null.
   * @param ob
   * @return
   * @throws ClassNotFoundException
   */
  public static String unparse(Object ob)
    throws ClassNotFoundException
  {
    if (ob == null)
      {
        return "null";
      } // if null
    else if (ob instanceof String)
      {
        return "\"" + ob + "\"";
      } // if string
    else if (ob instanceof BigDecimal)
      {
        return ob.toString();
      } // if number
    else if (ob instanceof ArrayList)
      {
        //Took code from Sam's sample JSON parser
        StringBuilder result = new StringBuilder();
        boolean first = true; // Hack!
        ArrayList<Object> a = (ArrayList<Object>) ob;
        result.append("[");
        for (Object obj : a)
          {
            if (!first)
              result.append(",");
            else
              first = false;
            result.append(unparse(obj));
          } //  for
        result.append("]");
        return result.toString();

      }//else if ArrayList
    else if (ob instanceof Hashtable)
      {
        StringBuilder myString = new StringBuilder();
        myString.append('{');
        Hashtable hash = (Hashtable) ob;
        ArrayList<Object> keys = Collections.list(hash.keys());
        ArrayList<Object> vals = Collections.list(hash.elements());
        int i = 0;
        while (i < vals.size() - 1)
          {
            myString.append(unparse(keys.get(i)));
            myString.append(':');
            myString.append(unparse(vals.get(i)));
            myString.append(',');
            i++;
          }//while
        myString.append(unparse(keys.get(keys.size() - 1)));
        myString.append(':');
        myString.append(unparse(vals.get(vals.size() - 1)));
        myString.append('}');
        return myString.toString();
      }//else if Hashtable
    else if (ob instanceof Boolean)
      {
        return ob.toString();
      }//else if Boolean
    else
      return "Not a properly formatted Object";
  } //unparse(Object)
} // class ParseObject

