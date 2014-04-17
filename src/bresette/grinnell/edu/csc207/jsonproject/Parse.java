package bresette.grinnell.edu.csc207.jsonproject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

//With help from Alex Greenberg
// Used http://stackoverflow.com/questions/2915453/how-to-get-hashtable-values-as-arraylist for unparsing hashtables


public class Parse
{
  public static Object parse(String str)
  {
    JSONInput input = new JSONInput(str);
    return parse(input);
  } // Parse(String)

  public static Object parse(JSONInput json)
  {
    switch (json.value.charAt(json.index))
      {
        case '[':
          {
            json.index++;
            ArrayList<Object> list = new ArrayList<Object>();
            while (json.value.charAt(json.index) != ']'
                   && json.index < (json.value.length() - 1))
              {
                list.add(parse(json));
                if (json.value.charAt(json.index) == ',')
                  json.index++;
              } // while
            json.index++;
            return list;
          } //case [

        case '{':
          {
            json.index++;
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
              }
            return hash;
          }//case {

        case '"':
          {
            String myString;
            json.index++;
            int start = json.index;
            while (json.index < (json.value.length() - 1)
                   && (json.value.charAt(json.index) != '"' || json.value.charAt(json.index) == '\\'))
              {
                json.index++;
              }
            myString = json.value.substring(start, json.index);
            json.index++;
            return myString;
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
          {
            int start = json.index;
            while (json.index < json.value.length()
                   && (Character.isDigit(json.value.charAt(json.index))
                       || json.value.charAt(json.index) == '.'
                       || json.value.charAt(json.index) == '-'
                       || json.value.charAt(json.index) == 'e' || json.value.charAt(json.index) == 'E'))
              {
                json.index++;
              }
            String valStr = json.value.substring(start, json.index);
            BigDecimal digit = BigDecimal.valueOf(Double.valueOf(valStr));
            return digit;
          } // all numeric cases

        case 'n':
          {
            if (json.value.substring(json.index, json.index + 4)
                          .equalsIgnoreCase("null"))
              {
                json.index = json.index + 4;
                return null;
              }
            else
              return "Incorrect JSON";
          } // case n

        case 't':
          {
            if (json.value.substring(json.index, json.index + 4)
                          .equalsIgnoreCase("true"))
              {
                json.index = json.index + 4;
                return true;
              }
            else
              return "Incorrect JSON";
          } // case t

        case 'f':
          {
            if (json.value.substring(json.index, json.index + 5)
                          .equalsIgnoreCase("false"))
              {
                json.index = json.index + 5;
                return false;
              }
            else
              return "Incorrect JSON";
          } // case f
        default:
          return "Incorrect JSON";
      }

  } // Parse(Object)
  
  public static String unparse(Object ob) throws ClassNotFoundException
  {
    if(ob.getClass() == Class.forName("java.lang.String"))
      {
        return "\"" + ob + "\"";
      }
    else if(ob.getClass() == Class.forName("java.math.BigDecimal"))
      {
        return ob.toString();
      }
    else if(ob.getClass() == Class.forName("java.util.ArrayList"))
      {
        StringBuilder myString = new StringBuilder();
        myString.append('[');
        ArrayList<Object> a =  (ArrayList<Object>) ob;
        int i = 0;
        while(i < a.size()-1)
          {
            myString.append(unparse(a.get(i)));
            myString.append(',');
            i++;
          }
        myString.append(unparse(a.get(a.size()-1)));
        myString.append(']');
        return myString.toString(); 
      }
    else if(ob.getClass() == Class.forName("java.util.Hashtable"))
      {
        StringBuilder myString = new StringBuilder();
        myString.append('{');
        Hashtable hash = (Hashtable) ob;
        ArrayList<Object> keys = Collections.list(hash.keys());
        ArrayList<Object> vals = Collections.list(hash.elements());
        int i = 0;
        while(i < vals.size()-1)
          {
            myString.append(unparse(keys.get(i)));
            myString.append(':');
            myString.append(unparse(vals.get(i)));
            myString.append(',');
            i++;
          }
        myString.append(unparse(keys.get(keys.size() - 1)));
        myString.append(':');
        myString.append(unparse(vals.get(vals.size() - 1)));
        myString.append('}');
        return myString.toString(); 
      }
    else if(ob.getClass() == Class.forName("java.lang.Boolean"))
      {
        return ob.toString();
      }
    else if(ob.toString() == null)
      {
        return "null";
      }
    else
      return "Not a properly formatted Object";
  }
} // class ParseObject

