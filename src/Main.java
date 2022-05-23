import java.util.*;

public class Main {
    public static Set<Character> xo = Set.of('X', 'O');


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String gamePole = "         ";
        printBattleField(gamePole);
        boolean bol = false;
        while (!bol) {
            gamePole = enterCoordinates(gamePole, 'X');
            printBattleField(gamePole);
            bol = checkGameStatus(gamePole);
            if (bol) break;
            gamePole = enterCoordinates(gamePole, 'O');
            printBattleField(gamePole);
            bol = checkGameStatus(gamePole);
        }

    }

    private static String enterCoordinates(String str, char c) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter the coordinates:");
                int row = scanner.nextInt();
                int column = scanner.nextInt();
                if (row < 1 || row > 3 || column < 1 || column > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (xo.contains(str.charAt(compareNumber(new int[]{row, column})))) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else if (str.charAt(compareNumber(new int[]{row, column})) == ' ') {
                    return str.substring(0, compareNumber(new int[]{row, column})) +
                            c +
                            str.substring(compareNumber(new int[]{row, column}) + 1);
                }

            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }

        }
    }

    private static int compareNumber(int[] a) {
        return switch (Arrays.toString(a)) {
            case "[1, 1]" -> 0;
            case "[1, 2]" -> 1;
            case "[1, 3]" -> 2;
            case "[2, 1]" -> 3;
            case "[2, 2]" -> 4;
            case "[2, 3]" -> 5;
            case "[3, 1]" -> 6;
            case "[3, 2]" -> 7;
            case "[3, 3]" -> 8;
            default -> 100;
        };
    }

    private static boolean checkGameStatus(String str) {
        String[][] pole = separateInput(str);

        int winCounter = 0;
        String c = "";
        int countX = 0;
        int countO = 0;

        for (String[] strings : pole) {
            for (String elem : strings) {
                if (Objects.equals(elem, "X")) {
                    countX++;
                } else if (Objects.equals(elem, "O")) {
                    countO++;
                }
            }
        }


        for (int i = 0; i < pole.length; i++) {
            if ((Objects.equals(pole[i][0], "X")
                    || Objects.equals(pole[i][0], "O"))
                    && Objects.equals(pole[i][0], pole[i][1])
                    && Objects.equals(pole[i][2], pole[i][0])) {
                c = pole[i][0];
                winCounter += 1;
            }
        }

        for (int i = 0; i < pole.length; i++) {
            if ((Objects.equals(pole[0][i], "X")
                    || Objects.equals(pole[0][i], "O"))
                    && Objects.equals(pole[0][i], pole[1][i])
                    && Objects.equals(pole[2][i], pole[1][i])) {
                c = pole[0][i];
                winCounter += 1;
            }
        }

        if ((Objects.equals(pole[0][0], "X")
                || Objects.equals(pole[0][0], "O"))
                && Objects.equals(pole[0][0], pole[1][1])
                && Objects.equals(pole[1][1], pole[2][2])) {
            c = pole[0][0];
            winCounter += 1;
        }

        if ((Objects.equals(pole[0][2], "X")
                || Objects.equals(pole[0][2], "O"))
                && Objects.equals(pole[0][2], pole[1][1])
                && Objects.equals(pole[1][1], pole[2][0])) {
            c = pole[0][2];
            winCounter += 1;
        }

        if (winCounter == 1) {
            System.out.printf("%s wins", c);
            return true;
        } else if (winCounter > 1 || countO > countX || countX >= countO + 2) {
            System.out.println("Impossible");
            return false;
        } else if (winCounter == 0) {
            if (countX < 5) {
                return false;
            } else {
                System.out.println("Draw");
                return true;
            }
        }

        return false;
    }

    private static void printBattleField(String str) {
        String[][] pole = separateInput(str);
        System.out.println("---------");
        System.out.printf("""
                        | %s |
                        | %s |
                        | %s |\n""",
                Arrays.toString(pole[0]).replaceAll(",", "").substring(1, 6).replaceAll("_", " "),
                Arrays.toString(pole[1]).replaceAll(",", "").substring(1, 6).replaceAll("_", " "),
                Arrays.toString(pole[2]).replaceAll(",", "").substring(1, 6).replaceAll("_", " "));
        System.out.println("---------");
    }

    private static String[][] separateInput(String str) {
        String row1 = str.substring(0, 3);
        String row2 = str.substring(3, 6);
        String row3 = str.substring(6, 9);
        return new String[][]{row1.split(""), row2.split(""), row3.split("")};
    }
}