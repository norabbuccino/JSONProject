                          JSON Parsing Library

 What it does
 ------------
 This is a simple parsing library which can parse JSON strings to Java
 objects and can also turn Java objects back into JSON strings. A graphic
 user interface is included which can be used for parsing JSON strings to
 Java. At this time, the GUI does not include unparsing.

 This parser can be used with JSON strings, arrays, objects, numbers, and
 literals in valid combinations, including embedded arrays and objects. The
 included GUI parses valid JSON into Java; in the case of invalid JSON, the
 GUI displays the applicable error message.

 This parser can be used without the GUI using the Java console, though
 that is not nearly as fun (or visually appealing). The unparser can only
 be used through the Java console at this time.

 Parser
 ------
 This parser takes a string of JSON as input. A moderate level of error
 catching is included, but the user should take care in their input; the
 error catching is not all-encompassing. A certain level of validity is
 assumed in the input. Depending on the JSON provided, the parser returns
 the applicable Java object or objects.

 Unparser
 --------
 This unparser takes a Java object as input and returns the applicable
 string of JSON. Valid Java objects are: strings, numbers, literals (true,
 false, or null), arrays, and hash tables. The output provided is the
 corresponding JSON: strings, numbers, literals (true, false, or null),
 arrays, and objects. A certain level of validity is assumed in the input;
 error catching is minimal.

 GUI
 ---
 Included with this parser is a simple GUI which may be used to parse a
 string of JSON to the corresponding Java object or objects. At this time,
 the GUI does not support unparsing from Java to JSON. As with the parser,
 a certain level of vailidty is assumed with the input, though a moderate
 level of error catching is included.

 Licensing
 ---------
 We use a GPL v2 License. Please see the file called LICENSE for details.

 Authors
 -------
 Written by Helen Dougherty and Leonora Bresette Buccino. 
 With help from Sam Rebelsky, Alex Greenberg, and other internet
 sources. For a list of full citations see the code. 

 Documentation
 -------------
 Javadoc included in code

 

 
