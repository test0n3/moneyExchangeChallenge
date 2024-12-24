package com.alura.moneyExchangeChallenge.main;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.alura.moneyExchangeChallenge.helpers.RequestGenerator;
import com.alura.moneyExchangeChallenge.models.Money;
import com.alura.moneyExchangeChallenge.models.Rate;

public class App {
    public static void main(String[] args) {
        String menuOption = "1";
        Scanner userInput = new Scanner(System.in);
        do {
            // First menu
            do {
                openingMenu();
                menuOption = userInput.nextLine();
                if (Pattern.matches("^[1-2]$", menuOption)) {
                    break;
                }
            } while (true);

            if (menuOption.equals("2")) {
                break;
            }
            // Second menu: base code
            String baseCoin = "";
            do {
                baseCodeMenu();
                baseCoin = userInput.nextLine();
                if (Pattern.matches("^[1-6]$", baseCoin)) {
                    break;
                }
            } while (true);
            // Third menu: amount to exchange
            double amount = 0.0;
            do {
                amountMenu();
                amount = userInput.nextDouble();
                if (Pattern.matches("^[0-9]+(.[0-9]+)?$", Double.toString(amount))) {
                    break;
                } else {
                    System.out.println("Invalid amount");
                }
            } while (true);
            // Fourth menu: target code
            String targetCoin = "";
            do {
                targetCodeMenu();
                targetCoin = userInput.nextLine();
                if (Pattern.matches("^[1-6]$", targetCoin)) {
                    break;
                }
            } while (true);

            Rate rate = new RequestGenerator()
                    .rateRequest(questionToCurrency(baseCoin),
                            questionToCurrency(targetCoin));
            printResult(questionToCurrency(baseCoin), questionToCurrency(targetCoin), amount, rate);

        } while (true);
        userInput.close();
    }

    public static void openingMenu() {
        System.out.println("Sea Bienvenido/a al Conversor de Monedas\n" +
                "========================================");
        System.out.println("1. Ingrese el monto y las monedas a calcular.");
        System.out.println("2. Salir.");
        System.out.print("Ingrese una opción[1-2]: ");
    }

    public static void currencyMenu() {
        System.out.println("Monedas disponibles");
        for (int i = 0; i < Money.values().length; i++) {
            Money money = Money.fromIndex(i);
            System.out.println(i + 1 + ". " + money.getName() + " (" + money.getSymbol() + ")");
        }
    }

    public static void baseCodeMenu() {
        System.out.println("Moneda que dispone");
        currencyMenu();
        System.out.print("Elija su moneda: ");
    }

    public static void targetCodeMenu() {
        System.out.println("Moneda que desea tener");
        currencyMenu();
        System.out.print("Elija la moneda que le interesa: ");
    }

    public static void amountMenu() {
        System.out.print("Ingrese el monto a calcular: ");
    }

    public static void printResult(String baseCoin, String targetCoin, double amount, Rate rate) {
        Money baseCurrency = Money.valueOf(baseCoin);
        Money targetCurrency = Money.valueOf(targetCoin);
        System.out.println(
                "El cambio de " + amount + " " + baseCurrency.getName() + " es "
                        + amount * rate.conversion_rate()
                        + " " + targetCurrency.getName());
    }

    public static String questionToCurrency(String option) {
        switch (option) {
            case "1":
                return "ARS";
            case "2":
                return "BOB";
            case "3":
                return "BRL";
            case "4":
                return "CLP";
            case "5":
                return "COP";
            case "6":
                return "USD";
            default:
                return "Invalid option";
        }
    }
}
