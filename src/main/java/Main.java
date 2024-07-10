import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        //1
        {
            double x = 32.7377;
            var dec = new DecimalFormat("#0.000");

            double res = fraction(x);
            System.out.println("результат: " + dec.format(res));
        }

        //2
        {
            int x = 992468;
            System.out.println(sumLastNums(x));
        }

        //3
        {
            System.out.println(charToNum('0'));
            System.out.println(charToNum('4'));
            System.out.println(charToNum('9'));
        }

        //4
        {
            System.out.println(isPositive(-1));
            System.out.println(isPositive(0));
            System.out.println(isPositive(1));
        }

        //5
        {
            System.out.println("Задание №5");
            System.out.println(is2Digits(-1));
            System.out.println(is2Digits(7));
            System.out.println(is2Digits(13));
            System.out.println(is2Digits(235));
            System.out.println(is2Digits(4235));
        }

    }

    //    1) Необходимо реализовать метод таким образом, чтобы он возвращал только дробную часть числа х.
//    При выводе результата необходимо обеспечить точность вычислений — три знака после запятой
    public static double fraction(double x) {
        int num = (int) x;
        return x - num;
    }

    //    2) Необходимо реализовать метод таким образом, чтобы он возвращал результат сложения двух последних знаков числа х,
//    предполагая, что знаков в числе не менее двух.
    public static int sumLastNums(int x) {
        int lastTwoNums = x % 100;
        int num1 = lastTwoNums / 10;
        int num2 = lastTwoNums % 10;
        return num1 + num2;
    }

    //    3) Метод принимает символ х, который представляет собой один из “0 1 2 3 4 5 6 7 8 9”.
//    Необходимо реализовать метод таким образом, чтобы он преобразовывал символ в соответствующее число.
//    При реализации метода не использовать методы класса Character.
    public static int charToNum(char x) {
        return x - 48;
    }


    //4)  Необходимо реализовать метод таким образом, чтобы он принимал число x и возвращал true если оно положительное.
    public static boolean isPositive(int x) {
        return x > 0;
    }

    //    5) Необходимо реализовать метод таким образом, чтобы он принимал положительное число x и возвращал true если оно двузначное.
    public static boolean is2Digits(int x) {
        return ((x / 10) != 0 && (x / 100) == 0);
    }

}