
import java.util.Scanner;

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] coffeeMachine = {400, 540, 120, 9, 550};
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = scanner.next();
            switch (action) {
                case "buy": {
                    coffeeMachine = buy(coffeeMachine);
                    break;
                }
                case "fill": {
                    coffeeMachine = fill(coffeeMachine);
                    break;
                }
                case "take": {
                    coffeeMachine = take(coffeeMachine);
                    break;
                }
                case "remaining": {
                    printCaffeMachineSupply(coffeeMachine);
                    break;
                }
                case "exit": {

                    return;
                }
                default:
                    break;
            }
        }
    }

    private static int[] buy(int[] arr) {
        int[] espresso = {250, 0, 16, 1, 4};
        int[] latte = {350, 75, 20, 1, 7};
        int[] cappuccino = {200, 100, 12, 1, 6};
        int[][] listOfCoffee = {espresso, latte, cappuccino};

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu : ");
        String coffeePeak = scanner.next();
        if (coffeePeak.equals("back")) {
            return arr;
        }
        if (checkSupply(arr, listOfCoffee, Integer.parseInt(coffeePeak))) {
            System.out.println("I have enough resources, making you a coffee!");
            for (int i = 0; i < arr.length; i++) {
                if (i == 4) {
                    arr[i] += listOfCoffee[Integer.parseInt(coffeePeak) - 1][i];
                    break;
                }
                arr[i] -= listOfCoffee[Integer.parseInt(coffeePeak) - 1][i];
            }
        }
        return arr;
    }

    private static boolean checkSupply(int[] coffeMachine, int[][] listOfCoffee, int peak) {
        for (int i = 0; i < coffeMachine.length; i++) {
            if (coffeMachine[i] < listOfCoffee[peak - 1][i]) {
                switch (i) {
                    case 0: {
                        System.out.println("Sorry, not enough water!");
                        break;
                    }
                    case 1: {
                        System.out.println("Sorry, not enough milk!");
                    }
                    case 2: {
                        System.out.println("Sorry, not enough coffee beans!");
                    }
                    case 3: {
                        System.out.println("Sorry, not enough disposable cups!");
                    }
                }
                return false;
            }
        }
        return true;
    }

    private static int[] fill(int[] arr) {
        System.out.println("Write how many ml of water you want to add: ");
        arr[0] += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        arr[1] += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        arr[2] += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        arr[3] += scanner.nextInt();
        return arr;
    }

    private static int[] take(int[] arr) {
        System.out.printf("I gave you $%d\n", arr[4]);
        arr[4] = 0;
        return arr;
    }

    private static void printCaffeMachineSupply(int[] arr) {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", arr[0]);
        System.out.printf("%d ml of milk\n", arr[1]);
        System.out.printf("%d g of coffee beans\n", arr[2]);
        System.out.printf("%d disposable cups\n", arr[3]);
        System.out.printf("$%d of money\n", arr[4]);
    }

    private static int[] inputAmountOfIngredients() {
        System.out.println("Write how many ml of water the coffee machine has:");
        int water = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has: ");
        int milk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has: ");
        int coffeeBeans = scanner.nextInt();
        int disposableCups = scanner.nextInt();
        int money = scanner.nextInt();
        return new int[]{water, milk, coffeeBeans};
    }

    private static void estimateNumberOfServings() {
        int[] ingredients = inputAmountOfIngredients();
        System.out.println("Write how many cups of coffee you will need:");
        int cupsOfCoffee = scanner.nextInt();
        int min = getMin(ingredients);
        if (cupsOfCoffee == min) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (cupsOfCoffee < min) {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)", min - cupsOfCoffee);
        } else {
            System.out.printf("No, I can make only %d cup(s) of coffee", min);
        }
    }

    private static int getMin(int[] arr) {
        int[] oneServeIngredients = {200, 50, 15};
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            int amountOfServe = arr[i] / oneServeIngredients[i];
            if (amountOfServe < min) {
                min = amountOfServe;
            }
        }
        return min;
    }
}