package edu.grinnell.bresette.doughert1.JSON;

public class JSONInput
{
  /**
   * a string to store the value
   */
  String value;

  /**
   * stores the current index you are looking at
   */
  int index;

  /**
   * constructs a new JSONInput object
   * @param str
   *    a string
   */
  public JSONInput(String str)
  {
    this.value = str;
    this.index = 0;
  }//JSONInput(String)
}//class JSONInput
