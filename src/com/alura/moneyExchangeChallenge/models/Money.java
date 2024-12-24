package com.alura.moneyExchangeChallenge.models;

public enum Money {
  ARS("Peso argentino", "$"),
  BOB("Boliviano boliviano", "Bs"),
  BRL("Real brasileño", "R$"),
  CLP("Peso chileno", "$"),
  COP("Peso colombiano", "COL$"),
  USD("Dólar estadounidense", "$");

  private final String name;
  private final String symbol;

  Money(String name, String symbol) {
    this.name = name;
    this.symbol = symbol;
  }

  public String getName() {
    return name;
  }

  public String getSymbol() {
    return symbol;
  }

  public static Money fromIndex(int index) {
    if (index < 0 || index >= values().length) {
      throw new IllegalArgumentException("Invalid index");
    }
    return values()[index];
  }
}
