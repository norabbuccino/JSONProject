package edu.grinnell.bresette.doughert1.JSON;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

//http://www.udemy.com/blog/java-gui-tutorial/ adapted my code from their sample code!

public class ParseGUI
    extends JFrame
{
  JLabel JSONInputLabel, JSONOutputLabel;
  JTextField inputText, outputText;
  JButton parseButton, exitButton;
  static final int HEIGHT = 400;
  static final int WIDTH = 400;
  ParseButtonHandler parseButtonHandler;
  ExitButtonHandler exitButtonHandler;

  public ParseGUI()
  {
    JSONInputLabel = new JLabel("FEED ME! ");
    JSONOutputLabel = new JLabel("Your Object is ", SwingConstants.RIGHT);

    inputText = new JTextField(12);
    outputText = new JTextField(12);

    //This section specifies the handlers for the buttons and adds an ActionListener.

    parseButton = new JButton("NOM NOM NOM");
    parseButtonHandler = new ParseButtonHandler();
    parseButton.addActionListener(parseButtonHandler);

    exitButton = new JButton("Close");
    exitButtonHandler = new ExitButtonHandler();
    exitButton.addActionListener(exitButtonHandler);

    setTitle("Pacman JSON");
    Container pane = getContentPane();
    pane.setLayout(new FlowLayout());

    //Grid layout requires that you add components to the content pane in the order they should appear

    pane.add(JSONInputLabel);
    pane.add(inputText);
    pane.add(JSONOutputLabel);
    pane.add(outputText);
    pane.add(parseButton);
    pane.add(exitButton);

    setSize(HEIGHT, WIDTH);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private class ParseButtonHandler
      implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      Object output;
      try
        {
          output = Parse.parse((String)inputText.getText());
          outputText.setText("" + output);
        }
      catch (Exception e1)
        {
          e1.printStackTrace();
        }
      
    }
  }

  public class ExitButtonHandler
      implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
     System.exit(0);
    }
  }

  public static void main(String[] args)
  {
    ParseGUI rectObj = new ParseGUI();
  }
}