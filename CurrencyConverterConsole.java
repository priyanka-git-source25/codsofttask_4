package concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.text.DecimalFormat;

public class CurrencyConverterConsole {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#,##0.00");

        // Fixed exchange rates (example values, you can update them if needed)
        Map<String, Double> rates = new HashMap<>();
        rates.put("USDINR", 83.25);
        rates.put("USDEUR", 0.91);
        rates.put("USDGBP", 0.78);
        rates.put("USDJPY", 148.50);
        rates.put("USDAUD", 1.52);
        rates.put("USDCAD", 1.36);
        rates.put("USDCNY", 7.18);
        rates.put("USDSGD", 1.35);

        System.out.println("===== Currency Converter =====");
        System.out.println("Supported currencies: USD, EUR, GBP, INR, JPY, AUD, CAD, CNY, SGD");
        System.out.println("Type 'q' to quit at any prompt.");

        while (true) {
            System.out.print("\nEnter base currency: ");
            String base = scanner.next().trim().toUpperCase();
            if (base.equalsIgnoreCase("Q")) break;

            System.out.print("Enter target currency: ");
            String target = scanner.next().trim().toUpperCase();
            if (target.equalsIgnoreCase("Q")) break;

            System.out.print("Enter amount: ");
            String amountStr = scanner.next().trim();
            if (amountStr.equalsIgnoreCase("Q")) break;

            double amount;
            try {
                amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    System.out.println("Please enter a positive amount.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Try again.");
                continue;
            }

            String key = base + target;
            if (!rates.containsKey(key)) {
                System.out.println("Conversion rate not available for " + base + " to " + target);
                continue;
            }

            double rate = rates.get(key);
            double result = amount * rate;

            System.out.println("\n" + amount + " " + base + " = " + df.format(result) + " " + target);
            System.out.println("Exchange rate: 1 " + base + " = " + rate + " " + target);
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}
