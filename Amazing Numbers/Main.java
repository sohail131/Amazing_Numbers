// package numbers;

import java.util.*;

public class Main {
    public static List<String> properties = Arrays.asList("BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD", "EVEN", "ODD");
    public static List<String> mutual1 = Arrays.asList("EVEN", "ODD");
    public static List<String> mutual2 = Arrays.asList("DUCK", "SPY");
    public static List<String> mutual3 = Arrays.asList("SUNNY", "SQUARE");
    public static List<String> mutual4 = Arrays.asList("SAD", "HAPPY");

    public static void main(String[] args) {
//        write your code here
        Scanner scanner = new Scanner(System.in);

        displayStatements();

        while (true) {
            System.out.println("\nEnter a request:");
            String[] numbers = scanner.nextLine().split("\\s+");

            if (numbers.length == 1) {
                long number = Long.parseLong(numbers[0]);

                if (number == 0) {
                    System.out.println("\nGoodbye!\n");
                    break;
                } else if (number > 0) {
                    numberToKnowItsProperty(number);
                } else {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            } else if (numbers.length == 2) {
                if (Long.parseLong(numbers[0]) < 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else if (Integer.parseInt(numbers[1]) <= 0) {
                    System.out.println("The second parameter should be a natural number.");
                } else {
                    twoNaturalNumbers(numbers);
                }
            } else if (numbers.length == 3) {
                if (!properties.contains(numbers[2].toUpperCase()) && numbers[2].charAt(0) != '-') {
                    System.out.println("The property [" + numbers[2].toUpperCase() + "] is wrong.");
                    System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD, EVEN, ODD]");
                } else {
                    numbersAndProperties(numbers);
                }
            } else {
                numbersAndProperties(numbers);
            }
        }
    }

    private static boolean isBuzz(long number) {
        return number % 7 == 0 || String.valueOf(number).endsWith("7");
    }

    private static boolean isDuck(long number) {
        String num = String.valueOf(number);
        boolean flag = false;

        if (!num.startsWith("0")) {
            for (int i = 1; i < num.length(); i++) {
                if (num.charAt(i) == '0') {
                    flag = true;
                    break;
                }
            }

            return flag;
        }

        return false;
    }

    private static boolean isPalindrome(long number) {
        return String.valueOf(number).equals(new StringBuilder(String.valueOf(number)).reverse().toString());
    }

    private static boolean isGapful(long number) {
        String num = String.valueOf(number);

        return num.length() >= 3 && number % Long.parseLong(num.charAt(0) + num.substring(num.length() - 1)) == 0;
    }

    private static boolean isSpy(long number) {
        String num = String.valueOf(number);
        long sum = 0;
        long product = 1;

        for (int i = 0; i < num.length(); i++) {
            long value = Long.parseLong(String.valueOf(num.charAt(i)));
            sum += value;
            product *= value;
        }

        return sum == product;
    }

    public static boolean[] checkProperties(long number) {
        return new boolean[]{isBuzz(number), isDuck(number), isPalindrome(number), isGapful(number), isSpy(number), isSquare(number), isSunny(number), isJumping(number), isHappy(number), isSad(number), number % 2 == 0, number % 2 != 0};
    }

    private static boolean isSquare(long number) {
        return Math.sqrt(number) - Math.floor(Math.sqrt(number)) == 0;
    }

    private static boolean isSunny(long number) {
        return Math.sqrt(number + 1) - Math.floor(Math.sqrt(number + 1)) == 0;
    }

    private static boolean isJumping(long number) {
        String num = String.valueOf(number);
        boolean flag = true;

        for (int i = 0; i < num.length() - 1; i++) {
            int first = Integer.parseInt(String.valueOf(num.charAt(i)));
            int second = Integer.parseInt(String.valueOf(num.charAt(i + 1)));

            flag = flag && (((first - second) == 1) || ((first - second) == -1));
        }

        return flag;
    }

    private static boolean isHappy(long number) {
        String num = String.valueOf(number);
        boolean flag = true;

        while (true) {
            int sum = 0;

            for (int i = 0; i < num.length(); i++) {
                sum += Math.pow(Long.parseLong(String.valueOf(num.charAt(i))), 2);
            }

            if (sum < 10) {
                if (sum != 1) {
                    flag = false;
                }
                break;
            } else if (num.equals(String.valueOf(sum))) {
                flag = false;
                break;
            } else {
                num = String.valueOf(sum);
            }
        }

        return flag;
    }

    private static boolean isSad(long number) {
        String num = String.valueOf(number);
        boolean flag = true;

        while (true) {
            int sum = 0;

            for (int i = 0; i < num.length(); i++) {
                sum += Math.pow(Long.parseLong(String.valueOf(num.charAt(i))), 2);
            }

            if (sum < 10) {
                if (sum == 1) {
                    flag = false;
                }
                break;
            } else if (num.equals(String.valueOf(sum))) {
                break;
            } else {
                num = String.valueOf(sum);
            }
        }

        return flag;
    }

    private static void numberToKnowItsProperty(long number) {
        System.out.println("Properties of " + number);
        System.out.println("        even: " + (number % 2 == 0));
        System.out.println("         odd: " + (number % 2 != 0));
        System.out.println("        buzz: " + isBuzz(number));
        System.out.println("        duck: " + isDuck(number));
        System.out.println(" palindromic: " + isPalindrome(number));
        System.out.println("      gapful: " + isGapful(number));
        System.out.println("         spy: " + isSpy(number));
        System.out.println("      square: " + isSquare(number));
        System.out.println("       sunny: " + isSunny(number));
        System.out.println("     jumping: " + isJumping(number));
        System.out.println("       happy: " + isHappy(number));
        System.out.println("         sad: " + isSad(number));
    }

    private static void twoNaturalNumbers(String[] numbers) {
        for (long i = Long.parseLong(numbers[0]); i < Long.parseLong(numbers[0]) + Integer.parseInt(numbers[1]); i++) {
            boolean[] array = checkProperties(i);

            System.out.printf("\n%d is", i);

            for (int j = 0; j < array.length; j++) {
                if (array[j]) {
                    System.out.printf(" %s", properties.get(j));
                }
            }
        }
    }

    private static void displayStatements() {
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameters show how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }

    private static void numbersAndProperties(String[] numbers) {
        boolean flag = true;
        List<String> unknown = new ArrayList<>();
        List<String> include = new ArrayList<>();
        List<String> notInclude = new ArrayList<>();

        for (int i = 2; i < numbers.length; i++) {
            if (!properties.contains(numbers[i].toUpperCase()) && numbers[i].charAt(0) != '-') {
                unknown.add(numbers[i]);
                flag = false;
            } else if (numbers[i].charAt(0) == '-') {
                if (!properties.contains(numbers[i].toUpperCase().substring(1, numbers[i].length()))) {
                    unknown.add(numbers[i]);
                    flag = false;
                }
            }

            if (numbers[i].charAt(0) == '-') {
                notInclude.add(numbers[i].toUpperCase().substring(1, numbers[i].length()));
            } else {
                include.add(numbers[i]);
            }
        }

        if (flag) {
            if (checkMutualProperties(numbers)) {
                int counter = 0;

                for (long i = Long.parseLong(numbers[0]); ; i++) {
                    if (counter == Long.parseLong(numbers[1])) {
                        break;
                    } else {
                        boolean[] array = checkProperties(i);
                        boolean check = true;
                        List<Integer> indexes1 = new ArrayList<>();
                        List<Integer> indexes2 = new ArrayList<>();

                        for (int a = 0; a < array.length; a++) {
                            if ((!notInclude.contains(properties.get(a)) && array[a]) || (include.contains(properties.get(a)) && array[a]) || (!notInclude.contains(properties.get(a).substring(1, properties.get(a).length())) && array[a])) {
                                indexes1.add(properties.indexOf(properties.get(a)));
                            }
                        }

                        for (Integer integer : indexes1) {
                            check = check && array[integer];
                        }

                        for (String s : include) {
                            indexes2.add(properties.indexOf(s.toUpperCase()));
                        }

                        for (Integer integer : indexes2) {
                            check = check && array[integer];
                        }

                        for (String s : notInclude) {
                            check = check && !array[properties.indexOf(s)];
                        }

                        if (check) {
                            counter++;

                            System.out.printf("\n%d is", i);

                            for (Integer integer : indexes1) {
                                System.out.printf(" %s", properties.get(integer));
                            }

                            if (indexes1.size() != indexes2.size()) {
                                for (Integer integer : indexes2) {
                                    if (!indexes1.contains(integer)) {
                                        System.out.printf(" %s", properties.get(integer));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (unknown.size() == 1) {
                System.out.println("The property [" + unknown.get(0) + "] is wrong.");
            } else {
                System.out.print("The properties [");

                for (String s : unknown) {
                    System.out.print(s + ", ");
                }

                System.out.print("] are wrong.\n");
            }

            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD, EVEN, ODD]");
        }
    }

    private static boolean checkMutualProperties(String[] numbers) {
        boolean mutual = true;

        List<String> checked = new ArrayList<>();

        outer:
        for (int a = 2; a < numbers.length; a++) {
            for (int b = 2; b < numbers.length; b++) {
                if ((a != b && numbers[a].replaceAll("-", "").equals(numbers[b].replaceAll("-", "")) && !checked.contains(numbers[a])) || (a != b && ((mutual1.contains(numbers[a].toUpperCase()) && mutual1.contains(numbers[b].toUpperCase())) || (mutual2.contains(numbers[a].toUpperCase()) && mutual2.contains(numbers[b].toUpperCase())) || (mutual3.contains(numbers[a].toUpperCase()) && mutual3.contains(numbers[b].toUpperCase())) || (mutual4.contains(numbers[a].toUpperCase()) && mutual4.contains(numbers[b].toUpperCase()))) && !numbers[a].equals(numbers[b]))) {
                    mutual = false;
                } else if (numbers[b].charAt(0) == '-' && numbers[a].charAt(0) == '-') {
                    if (a != b && ((mutual1.contains(numbers[a].toUpperCase().substring(1, numbers[a].length())) && mutual1.contains(numbers[b].toUpperCase().substring(1, numbers[b].length()))) || (mutual2.contains(numbers[a].toUpperCase().substring(1, numbers[a].length())) && mutual2.contains(numbers[b].toUpperCase().substring(1, numbers[b].length()))) || (mutual3.contains(numbers[a].toUpperCase().substring(1, numbers[a].length())) && mutual3.contains(numbers[b].toUpperCase().substring(1, numbers[b].length()))) || (mutual4.contains(numbers[a].toUpperCase().substring(1, numbers[a].length())) && mutual4.contains(numbers[b].toUpperCase().substring(1, numbers[b].length())))) && !numbers[a].equals(numbers[b])) {
                        mutual = false;
                    }
                } else if (numbers[a].charAt(0) == '-' && numbers[a].toUpperCase().substring(1, numbers[a].length()).equals(numbers[b].toUpperCase())) {
                    if (a != b && ((mutual1.contains(numbers[a].toUpperCase().substring(1, numbers[a].length())) && mutual1.contains(numbers[b].toUpperCase())) || (mutual2.contains(numbers[a].toUpperCase().substring(1, numbers[a].length())) && mutual2.contains(numbers[b].toUpperCase())) || (mutual3.contains(numbers[a].toUpperCase().substring(1, numbers[a].length())) && mutual3.contains(numbers[b].toUpperCase())) || (mutual4.contains(numbers[a].toUpperCase().substring(1, numbers[a].length())) && mutual4.contains(numbers[b].toUpperCase()))) && !numbers[a].equals(numbers[b])) {
                        mutual = false;
                    }
                } else if (numbers[b].charAt(0) == '-' && numbers[a].toUpperCase().substring(1, numbers[a].length()).equals(numbers[b].toUpperCase())) {
                    if (a != b && ((mutual1.contains(numbers[a].toUpperCase()) && mutual1.contains(numbers[b].toUpperCase().substring(1, numbers[b].length()))) || (mutual2.contains(numbers[a].toUpperCase()) && mutual2.contains(numbers[b].toUpperCase().substring(1, numbers[b].length()))) || (mutual3.contains(numbers[a].toUpperCase()) && mutual3.contains(numbers[b].toUpperCase().substring(1, numbers[b].length()))) || (mutual4.contains(numbers[a].toUpperCase()) && mutual4.contains(numbers[b].toUpperCase().substring(1, numbers[b].length())))) && !numbers[a].equals(numbers[b])) {
                        mutual = false;
                    }
                }

                if (!mutual) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", numbers[a].toUpperCase(), numbers[b].toUpperCase());
                    System.out.println("There are no numbers with these properties.");
                    break outer;
                } else {
                    checked.add(numbers[a]);
                }
            }
        }

        return mutual;
    }
}
