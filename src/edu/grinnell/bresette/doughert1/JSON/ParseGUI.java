package edu.grinnell.bresette.doughert1.JSON;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.awt.*;

/*
* Citations
* http://www.udemy.com/blog/java-gui-tutorial/ adapted our code from their sample code!
* http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
* http://stackoverflow.com/questions/789517/java-how-to-create-a-custom-dialog-box
* used fonts "Love Ya Like a Sister" by Kimberly Geswein and "Journal" by Fontourist both from Dafont.com
*/

/**
 * Create a GUI for parsing JSON strings
 * @author Leonora Bresette Buccino and Helen Dougherty
 */

public class ParseGUI
    extends JFrame
{
  /**
   * Fields for all of the images that will be used in the GUI
   */
  ImageIcon titleIcon;
  ImageIcon buttonIcon;
  ImageIcon errorIcon;
  ImageIcon inputIcon;
  ImageIcon objectIcon;

  /**
   * the background color that will be used throughout the GUI
   */
  Color bgColor = new Color(140, 211, 217);

  /**
   * the title for the overall parser
   */
  JLabel title;

  /**
   * the label for the parse text box
   */
  JLabel parseLabel;

  /**
   * the label for the output text box
   */
  JLabel outputLabel;

  /**
   * the text box for parsing
   */
  JTextArea parseText;

  /**
   * the text box for output
   */
  JTextArea outputText;

  /**
   * the button to parse
   */
  JButton parseButton;

  /**
   * the actionListener for the parse button
   */
  ParseButtonHandler parseButtonHandler;

  /**
   * the width of the window
   */
  int WIDTH = 770;

  /**
   * the height of the window
   */
  int HEIGHT = 350;

  /**
   * Create a new ParseGUI
   */
  public ParseGUI()
  {
    // importing all of the icons to be used
    titleIcon = new ImageIcon("just-a-very-simple-json-parser-long.png");
    buttonIcon = new ImageIcon("parse-button.png");
    errorIcon = new ImageIcon("error.png");
    inputIcon = new ImageIcon("input.png");
    objectIcon = new ImageIcon("your-object-is.png");

    // initializing the labels
    title = new JLabel(titleIcon);
    parseLabel = new JLabel(inputIcon);
    outputLabel = new JLabel(objectIcon);

    // creating the text areas
    parseText = new JTextArea(3, 30);
    outputText = new JTextArea(5, 30);

    // This section specifies the handlers for the buttons and adds an ActionListener.
    parseButton = new JButton(buttonIcon);
    parseButton.setBackground(bgColor);
    Border emptyBorder = BorderFactory.createEmptyBorder();
    parseButton.setBorder(emptyBorder);
    parseButtonHandler = new ParseButtonHandler();
    parseButton.addActionListener(parseButtonHandler);

    // set the title and specify the layout
    setTitle("a simple JSON parser");
    Container pane = getContentPane();
    SpringLayout layout = new SpringLayout();
    pane.setLayout(layout);
    pane.setBackground(bgColor);

    // add the elements to the pane
    pane.add(title);
    pane.add(parseLabel);
    pane.add(parseText);
    pane.add(parseButton);
    pane.add(outputLabel);
    pane.add(outputText);

    //place the title image
    layout.putConstraint(SpringLayout.WEST, title, 5, SpringLayout.WEST, pane);

    //place the parse label
    layout.putConstraint(SpringLayout.WEST, parseLabel, 20, SpringLayout.WEST,
                         pane);
    layout.putConstraint(SpringLayout.NORTH, parseLabel, 130,
                         SpringLayout.NORTH, pane);
    //place the parse text box
    layout.putConstraint(SpringLayout.WEST, parseText, 230, SpringLayout.WEST,
                         parseLabel);
    layout.putConstraint(SpringLayout.NORTH, parseText, 130,
                         SpringLayout.NORTH, pane);
    //place the parse button
    layout.putConstraint(SpringLayout.WEST, parseButton, 340,
                         SpringLayout.WEST, parseText);
    layout.putConstraint(SpringLayout.NORTH, parseButton, 137,
                         SpringLayout.NORTH, pane);

    //place the output label
    layout.putConstraint(SpringLayout.WEST, outputLabel, 20, SpringLayout.WEST,
                         pane);
    layout.putConstraint(SpringLayout.NORTH, outputLabel, 200,
                         SpringLayout.NORTH, pane);

    //place the output text box
    layout.putConstraint(SpringLayout.WEST, outputText, 230, SpringLayout.WEST,
                         outputLabel);
    layout.putConstraint(SpringLayout.NORTH, outputText, 200,
                         SpringLayout.NORTH, pane);

    setSize(WIDTH, HEIGHT);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  } // ParseGUI()

  /**
   * a class that implements ActionListener and controls the result of clicking the parse button
   * @author Leonora Bresette Buccino and Helen Dougherty
   *
   */
  public class ParseButtonHandler
      implements ActionListener
  {
    /**
     * controls the action performed when the parse button is clicked
     */
    public void actionPerformed(ActionEvent e)
    {
      Object output;
      try
        {
          // print the result of parsing the input to the screen in the output box
          output = Parse.parse((String) parseText.getText());
          outputText.setText("" + output);
        } // try
      catch (Exception e1)
        {
          // create a dialog box that pops up and displays the error message
          UIManager.put("OptionPane.background", bgColor);
          UIManager.put("Panel.background", bgColor);
          UIManager.put("OptionPane.messageForeground", Color.black);
          JOptionPane.showMessageDialog(null, e1.getMessage(), "Error Message",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        errorIcon);
        } // catch

    } // actionPerformed(ActionEvent)
  } // class ParseButtonHandler

  public static void main(String[] args)
  {
    ParseGUI rectObj = new ParseGUI();
  } // main(String[])

} // class ParseGUI