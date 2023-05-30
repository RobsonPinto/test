package com.dev.app.converter;

public enum Basis {

  BINARY(1, "Binário", 2),
  DECIMAL(2, "Decimal", 10),
  OCTAL(3, "Octal", 8),
  HEXADECIMAL(4, "Hexadecimal", 16);

  private final int index;
  private final String description;
  private final int basisValue;

  private Basis(int index, String desc, int basis) {
    this.index = index;
    this.description = desc;
    this.basisValue = basis;
  }

  public int getIndex() {
    return this.index;
  }

  public String getDesc() {
    return this.description;
  }

  public int getBasisValue() {
    return basisValue;
  }
}