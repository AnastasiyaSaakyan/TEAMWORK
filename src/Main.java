package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int sumProducts = 0;
        Scanner scanner = new Scanner(System.in);

        String products[] = {"Тунец", "Лосось", "Горбуша", "Кета", "Палтус", "Камбала"};
        boolean isSale = true;
        int saleCount = 3;
        int prices[] = {75, 50, 100, 70, 20, 40};
        int count[] = new int[products.length];

        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + "руб/шт");
        }

        while (true) {
            int productNumber = 0;
            int productCount = 0;

            System.out.println("Выберите товар и количество или введите 'end'");

            String input = scanner.nextLine();

            if (input.equals("end")) {
                break;
            }

// Проверяем введённые данные
            try {
                String splitedInput[] = input.split(" ");
                if (splitedInput.length != 2) {
                    System.out.println("Вы должны ввести ровно два числа");
                    continue;
                }

                productNumber = Integer.parseInt(splitedInput[0]) - 1;
                productCount = Integer.parseInt(splitedInput[1]);
                if (productNumber < 0 || productNumber >= products.length) {
                    System.out.println("Неправильно введён номер товара");
                    System.out.println("Номер продукта должен быть больше 0 и меньше " + products.length);
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Не соблюдён формат ввода данных");
                continue;
            }


// Высчитываем новое количество товара
            if (productCount == 0) {
                count[productNumber] = 0;
            } else {
                if (productCount * (-1) > count[productNumber]) {
                    System.out.println("Вы пытаетесь вычесть товара больше, чем есть в корзине");
                    continue;
                }
                count[productNumber] += productCount;
            }
        }// Считаем сумму по каждому товару с учётом скидки (если есть распродажа)
        int tempResult = 0;
        System.out.println("Ваша корзина:");
        for (int i = 0; i < products.length; i++) {
            if (count[i] <= 0) {
                continue;
            }

// Подитог за товарную позицию
            if (isSale) {
                int productCountSale = count[i] - (int) count[i] / saleCount;
                tempResult = prices[i] * productCountSale;
            } else {
                tempResult = prices[i] * count[i];
            }

            System.out.print(products[i] + " " + count[i] + " шт " +
                    prices[i] + "руб./шт " + tempResult + " руб. в сумме");
            int bonus = prices[i] * count[i] - tempResult;
            if (bonus > 0) {
                System.out.println(" (Скидка: " + bonus + " руб.");
            } else {
                System.out.println();
            }
            sumProducts += tempResult;
        }
        System.out.println("Итого: " + sumProducts + " руб.");
    }

}