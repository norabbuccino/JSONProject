package edu.grinnell.bresette.doughert1.JSON;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.awt.*;

//http://www.udemy.com/blog/java-gui-tutorial/ adapted my code from their sample code!
//http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
//http://stackoverflow.com/questions/789517/java-how-to-create-a-custom-dialog-box
//used fonts "Love Ya Like a Sister" by Kimberly Geswein and "Journal" by Fontourist both from Dafont.com

public class ParseGUI
    extends JFrame
{
  ImageIcon titleIcon =
      new ImageIcon("just-a-very-simple-json-parser-long.png");
  ImageIcon buttonIcon = new ImageIcon("parse-button.png");
  ImageIcon errorIcon = new ImageIcon("error.png");
  ImageIcon inputIcon = new ImageIcon("input.png");
  ImageIcon objectIcon = new ImageIcon("your-object-is.png");

  Color bgColor = new Color(140, 211, 217);

  JLabel parserTitle;
  JLabel pacmanPicture;
  JLabel parseLabel;
  JLabel unparseLabel;
  JTextArea parseText;
  JTextArea unparseText;
  JButton parseButton;
  JButton unparseButton;
  static final int HEIGHT = 770;
  static final int WIDTH = 350;
  ParseButtonHandler parseButtonHandler;
  UnparseButtonHandler unparseButtonHandler;

  public ParseGUI()
  {
    parserTitle = new JLabel(titleIcon);
    parseLabel = new JLabel(inputIcon);
    unparseLabel = new JLabel(objectIcon);

    parseText = new JTextArea(3, 30);
    unparseText = new JTextArea(5, 30);

    //This section specifies the handlers for the buttons and adds an ActionListener.

    parseButton = new JButton(buttonIcon);
    parseButton.setBackground(bgColor);
    Border emptyBorder = BorderFactory.createEmptyBorder();
    parseButton.setBorder(emptyBorder);
    parseButtonHandler = new ParseButtonHandler();
    parseButton.addActionListener(parseButtonHandler);

    setTitle("a simple JSON parser");
    Container pane = getContentPane();
    SpringLayout layout = new SpringLayout();
    pane.setLayout(layout);
    pane.setBackground(bgColor);

    pane.add(parserTitle);
    pane.add(parseLabel);
    pane.add(parseText);
    pane.add(parseButton);
    pane.add(unparseLabel);
    pane.add(unparseText);

    //place the title image
    layout.putConstraint(SpringLayout.WEST, parserTitle, 5, SpringLayout.WEST,
                         pane);

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

    //place the unparse label
    layout.putConstraint(SpringLayout.WEST, unparseLabel, 20,
                         SpringLayout.WEST, pane);
    layout.putConstraint(SpringLayout.NORTH, unparseLabel, 200,
                         SpringLayout.NORTH, pane);

    //place the unparse text box
    layout.putConstraint(SpringLayout.WEST, unparseText, 230,
                         SpringLayout.WEST, unparseLabel);
    layout.putConstraint(SpringLayout.NORTH, unparseText, 200,
                         SpringLayout.NORTH, pane);

    setSize(HEIGHT, WIDTH);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    parseLabel.setForeground(Color.white);
    unparseLabel.setForeground(Color.white);

  }

  private class ParseButtonHandler
      implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      Object output;
      try
        {
          output = Parse.parse((String) parseText.getText());
          parseText.setText("");
          unparseText.setText("" + output);
        }
      catch (Exception e1)
        {
          UIManager UI = new UIManager();
          UI.put("OptionPane.background", bgColor);
          UI.put("Panel.background", bgColor);
          UI.put("OptionPane.messageForeground", Color.black);
          JOptionPane errorMessage = new JOptionPane();
          errorMessage.showMessageDialog(null, e1.getMessage(),
                                         "Error Message",
                                         JOptionPane.INFORMATION_MESSAGE, errorIcon);
        }

    }
  }

  public class UnparseButtonHandler
      implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      Object output;
      try
        {
          //This does not work properly as it only works for strings                   
          output = Parse.unparse(unparseText.getText());
          unparseText.setText("");
          parseText.setText("" + output);
        }
      catch (Exception e1)
        {
          e1.printStackTrace();
        }
    }
  }

  public static void main(String[] args)
  {
    ParseGUI rectObj = new ParseGUI();
  }
}