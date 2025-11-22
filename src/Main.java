import model.NegativeValueOfPPriceException;
import model.Price;
import model.ShopWasNotFound;

import java.util.*;

public class Main {

    private static Price createPrice(Scanner sc) {
        Price price = new Price();
        boolean validInput = false;

        System.out.println();

        validInput = false;
        while (!validInput) {
            try {
                System.out.println("Вкажіть назву товару: ");
                String productName = sc.nextLine();
                price.setProductName(productName);
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Спробуйте ще раз.");
            }
        }

        validInput = false;
        while (!validInput) {
            try {
                System.out.println("Вкажіть назву магазину, в якому продається товар: ");
                String shopName = sc.nextLine();
                price.setShopName(shopName);
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Спробуйте ще раз.");
            }
        }

        validInput = false;
        while (!validInput) {
            try {
                System.out.println("Вкажіть вартість товару в гривнях: ");
                String inputValue = sc.nextLine();
                price.setPriceInHryvnias(Double.parseDouble(inputValue));
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Невірний формат вартості! Коректний формат \"999999.99\". Спробуйте ще раз.");
            } catch (NegativeValueOfPPriceException e) {
                System.out.println(e.getMessage());
            }
        }
        return price;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Price[] priceArray = {createPrice(scanner), createPrice(scanner)};

        Arrays.sort(priceArray, Comparator.comparing(Price::getShopName));
        System.out.println();
        System.out.println("Прайс після сортування за назвами магазинів:");
        for (Price price : priceArray) {
            System.out.println(price.toString());
        }

        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println();
                System.out.println("Вкажіть назву магазину для виведення на екран інформації про товари, що продаються в цьому магазині: ");
                String shopName = scanner.nextLine();
                List<Price> list = Arrays.asList(priceArray);
                boolean result = list.stream().anyMatch(price -> price.getShopName().equalsIgnoreCase(shopName));
                if (!result) {
                    throw new ShopWasNotFound("Магазину з ім'ям '" + shopName + "' немає в прайс-листі! Спробуйте ще раз.");
                }
                validInput = true;
                System.out.println();
                System.out.println("Список товарів, що продаються в магазині '" + shopName + "'");
                for (Price price : list) {
                    if  (price.getShopName().equalsIgnoreCase(shopName)) {
                        System.out.println(price.toString());
                    }
                }
            } catch(ShopWasNotFound e) {
                System.out.println(e.getMessage());
            }
        }
    }
}