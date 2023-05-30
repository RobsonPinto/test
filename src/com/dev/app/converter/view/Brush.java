package com.dev.app.converter.view;

import static java.lang.System.*;

import com.dev.app.converter.Basis;

public final class Brush {

  private Brush() {

  }

  public static void header() {
    out.println("\n\n");
    out.println("==============================");
    out.println(" CONVERSOR DE BASES NUMÉRICAS ");
    out.println("==============================");
    out.println("\n\n");
  }

  public static void divisor() {
    out.println("\n❦ ❦ ❦\n");
  }

  public static void getIndexBaseMsg() {
    out.println("Qual a base da entrada?: ");

    Basis[] bases = Basis.values();

    for (Basis base : bases) {
      String template = "%d) %s";
      String option = String.format(
          template,
          base.getIndex(),
          base.getDesc());

      out.println(option);
    }

  }

  public static void userInput() {
    out.print("\n\\User\\ ~> ");
  }

  public static void getValueMsg() {
    out.print("Qual o valor que deseja converter?: ");
  }

}
