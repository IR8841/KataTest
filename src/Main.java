import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static int a;
    private static int b;
    private static int c;
    private static String firstValue = "";
    private static String secondValue = "";
    private static String mathOper;


    public static void main(String[] args) throws IOException {
        System.out.println("Input: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();


        System.out.print("Output: \n" + calc(input));
    }


    public static String calc(String input) throws IOException {
        checkStr(input);
        if ((a < 1 || a > 10) || (b < 1 || b > 10)) throw new IOException();
        switch (mathOper) {
            case "+" -> c = a + b;
            case "-" -> c = a - b;
            case "*" -> c = a * b;
            case "/" -> c = a / b;
        }
        if (!tryParseInt(firstValue) && !tryParseInt(secondValue)) {
            if (c < 1) throw new IOException();
            return getRome(c);
        }
        return Integer.toString(c);
    }

    static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getRome (int arab) {
        String rome = "";
        if(arab > 0 && arab <= 10) {
            rome = Rome.values()[arab-1].toString();
        } else if (arab > 10 && arab < 100) {
            int leftDigit = (arab/10) + 8;
            int rightDigit = arab%10;
            if (rightDigit != 0) {
            rome = Rome.values()[leftDigit].toString() + Rome.values()[rightDigit-1].toString();
            }
            else {
                rome = Rome.values()[leftDigit].toString();
            }
        } else if (arab == 100) {
            rome = Rome.C.toString();
        }

        return rome;

    }

    public static void checkStr(String input){
        String str = input.replaceAll(" ", "");
        String[] arr = str.split("");
        int q = 0;

        for (int i = 0; i < arr.length; i++) {
        if (arr[i].equals("*")  || arr[i].equals("+")  || arr[i].equals("-")  || arr[i].equals("/"))
            {
               mathOper = arr[i];
               break;
            }
            q++;
            firstValue += String.valueOf(str.charAt(i));
        }
        for (int i = q + 1; i < arr.length; i++ ) {

            secondValue += String.valueOf(str.charAt(i));
        }


        if (tryParseInt(firstValue) && tryParseInt(secondValue) ) {
             a = Integer.parseInt(firstValue);
             b = Integer.parseInt(secondValue);
        }
        else {
             if (!tryParseInt(firstValue) && !tryParseInt(secondValue)){
                Rome romeFirst = Rome.valueOf(firstValue);
                a = romeFirst.getArab();
                Rome romeSecond = Rome.valueOf(secondValue);
                b = romeSecond.getArab();

            }
            }
        }


}




