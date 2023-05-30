package com.dev.app;

import java.util.Scanner;

import com.dev.app.converter.BaseConverter;
import com.dev.app.converter.NumericBasis;
import com.dev.app.converter.view.Brush;

public class App {
  public static void main(String[] args) throws java.lang.Exception {

    NumericBasis input =  new NumericBasis();

    Scanner getter = new Scanner(System.in);

    Brush.header();
    Brush.getIndexBaseMsg();
    Brush.userInput();
    String value = getter.nextLine();
    input.setIndexBase(Integer.parseInt(value));

    Brush.divisor();

    Brush.getValueMsg();
    Brush.userInput();
    String valueString = getter.nextLine();
    input.setValue(valueString);

    Brush.divisor();

    BaseConverter converter = new BaseConverter(input);

    converter.show();

    getter.close();

  }
}