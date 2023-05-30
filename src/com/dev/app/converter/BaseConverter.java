package com.dev.app.converter;

import static java.lang.System.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.dev.app.converter.view.Brush;

public final class BaseConverter extends NumericBasis {
  NumericBasis input;
  boolean status;
  int valueLen;
  String  decimal;

  Basis[] basis = Basis.values();

  HashMap<Character, Integer> letters = new HashMap<>(
    Map.of(
        'A', 10, 'B', 11, 'C', 12,
        'D', 13, 'E', 14, 'F', 15));

  public BaseConverter(NumericBasis inp) {
    this.input = inp;
    this.valueLen = inp.getValue().length();
    this.decimal = toDecimal();
  }

  private static <T, E> T getKey(Map<T, E> map, E value) {

    for (Entry<T, E> entry : map.entrySet()) {
      if (value.equals(entry.getValue())) {
        return entry.getKey();
      }
    }

    return null;
  }

  public String toDecimal() {

    int accumulator = 0;
    int base = 0;

    switch (input.getIndexBase()) { 

      // decimal
      case 2:
        break;

      // binário
      case 1:
        base = 2;

        for (int i = 0, j = valueLen - 1; i < valueLen; i++, j--) {
          if (input.getValue().charAt(i) == '1')
            accumulator += Math.pow(base, j);
        }

        break;

      // octal
      case 3:
        base = 8;
        for (int i = 0, j = valueLen - 1; i < valueLen; i++, j--) {
          accumulator += Integer.parseInt(
              String.valueOf(input.getValue().charAt(i))) * Math.pow(base, j);
        }

        break;

      // hexadecimal
      case 4:
        base = 16;

        char[] chars = input.getValue().toUpperCase().toCharArray();

        for (int i = 0, j = valueLen - 1; i < valueLen; i++, j--) {
          if (letters.containsKey(chars[i])) {
            accumulator += letters.get(chars[i]) * Math.pow(base, j);
            continue;
          }
          accumulator += Integer.parseInt(
              String.valueOf(chars[i])) * Math.pow(base, j);
        }

        break;

      default:
        break;
    }

    return (accumulator != 0) ? String.valueOf(accumulator) : input.getValue();
  }

  public String toHexadecimal() {

    int intDecimal = Integer.parseInt(this.decimal);
    LinkedList<String> restValues = new LinkedList<>();
    int quocient = 0;
    int tmpRest = 0;
    StringBuilder output = new StringBuilder();

    do {

      tmpRest = intDecimal % Basis.HEXADECIMAL.getBasisValue();
      quocient = intDecimal / Basis.HEXADECIMAL.getBasisValue();

      if (letters.containsValue(tmpRest)) {
        restValues.add(String.valueOf(getKey(letters, tmpRest)));
      } else {
        restValues.add(String.valueOf(tmpRest));
      }

      intDecimal /= Basis.HEXADECIMAL.getBasisValue();
    } while (quocient != 0);

    Iterator<String> it = restValues.descendingIterator();

    ArrayList<String> reverseList = new ArrayList<>();

    while (it.hasNext()) {
      reverseList.add(it.next());
    }

    for (String str : reverseList) {
      output.append(str);
    }

    return (input.getIndexBase() != 4) ? output.toString() : input.getValue();
  }

  public String toBinary() {

    int intDecimal = Integer.parseInt(this.decimal);
    LinkedList<String> restValues = new LinkedList<>();
    int quocient = 0;
    int tmpRest = 0;
    StringBuilder output = new StringBuilder();

    do {

      tmpRest = intDecimal % Basis.BINARY.getBasisValue();
      quocient = intDecimal / Basis.BINARY.getBasisValue();

      restValues.add(String.valueOf(tmpRest));

      intDecimal /= Basis.BINARY.getBasisValue();
    } while (quocient != 0);

    Iterator<String> it = restValues.descendingIterator();

    ArrayList<String> reverseList = new ArrayList<>();

    while (it.hasNext()) {
      reverseList.add(it.next());
    }

    for (String str : reverseList) {
      output.append(str);
    }

    return (input.getIndexBase() != 1) ? output.toString() : input.getValue();
  }

  public String toOctal() {

    int intDecimal = Integer.parseInt(this.decimal);
    LinkedList<String> restValues = new LinkedList<>();
    int quocient = 0;
    int tmpRest = 0;
    StringBuilder output = new StringBuilder();


    do {

      tmpRest = intDecimal % Basis.OCTAL.getBasisValue();
      quocient = intDecimal / Basis.OCTAL.getBasisValue();

      restValues.add(String.valueOf(tmpRest));

      intDecimal /= Basis.OCTAL.getBasisValue();
    } while (quocient != 0);

    Iterator<String> it = restValues.descendingIterator();

    ArrayList<String> reverseList = new ArrayList<>();

    while (it.hasNext()) {
      reverseList.add(it.next());
    }

    for (String str : reverseList) {
      output.append(str);
    }

    return (input.getIndexBase() != 1) ? output.toString() : input.getValue();
  }

  public void show() {
    out.println("Valor inserido na base " +
        basis[input.getIndexBase() - 1].getDesc() + ": " + input.getValue());
    Brush.divisor();
    out.println("Valor na base Decimal: " + this.decimal);
    out.println("Valor na base Hexadecimal: " + toHexadecimal());
    out.println("Valor na base Binária: " + toBinary());
    out.println("Valor na base Octal: " + toOctal());
  }
}
