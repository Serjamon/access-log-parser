import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        //1
        {
            double x = 32.7377;
            var dec = new DecimalFormat("#0.000");

            double res = fraction(x);
            System.out.println("Задание №1");
            System.out.println("результат: " + dec.format(res));
        }

        //2
        {
            System.out.println("Задание №2");
            int x = 992468;
            System.out.println(sumLastNums(x));
        }

        //3
        {
            System.out.println("Задание №3");
            System.out.println(charToNum('0'));
            System.out.println(charToNum('4'));
            System.out.println(charToNum('9'));
        }

        //4
        {
            System.out.println("Задание №4");
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

        //6
        {
            System.out.println("Задание №6");
            System.out.println(isUpperCase('A'));
            System.out.println(isUpperCase('f'));
            System.out.println(isUpperCase('Z'));
            System.out.println(isUpperCase('1'));
        }

        //7
        {
            System.out.println("Задание №7");
            System.out.println(isInRange(10, 35, 22));
            System.out.println(isInRange(10, 35, 2));
            System.out.println(isInRange(35, 30, 35));
            System.out.println(isInRange(5, 1, 3));
            System.out.println(isInRange(2, 15, 33));
        }

        //8
        {
            System.out.println("Задание №8");
            System.out.println(isDivisor (3, 6));
            System.out.println(isDivisor (2, 15));
            System.out.println(isDivisor (14, 7));
            System.out.println(isDivisor (7, 21));
        }

        //9
        {
            System.out.println("Задание №9");
            System.out.println(isEqual  (6, 6, 9));
            System.out.println(isEqual  (3, 3, 3));
            System.out.println(isEqual  (3, 6, 6));
        }

        //10
        {
            int sum=lastNumSum(5, 11);
            System.out.println("Задание №10");
            System.out.println(sum);
            System.out.println(sum=lastNumSum(sum, 123));
            System.out.println(sum=lastNumSum(sum, 14));
            System.out.println(sum=lastNumSum(sum, 1));

            System.out.println(lastNumSum(5, 11));
            System.out.println(lastNumSum(lastNumSum(5, 11), 123));
            System.out.println(lastNumSum(lastNumSum(lastNumSum(5, 11), 123), 14));
            System.out.println(lastNumSum(lastNumSum(lastNumSum(lastNumSum(5, 11), 123), 14), 1));

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

    //6) Необходимо реализовать метод таким образом, чтобы он принимал символ x
    // и возвращал true если это большая буква в диапазоне от ‘A’ до ‘Z’.
    // При реализации метода не использовать методы класса Character.
    public static boolean isUpperCase(char x){
        int symbolNum = (int)x;
        return 64 < symbolNum && symbolNum < 91;
    }

    //7) Метод принимает левую и правую границу [a и b] некоторого числового диапазона.
    // Необходимо реализовать метод таким образом, чтобы он возвращал true,
    // если num входит в указанный диапазон (включая границы).
    // Обратите внимание, что отношение a и b заранее неизвестно (неясно кто из них больше, а кто меньше)
    public static boolean isInRange(int a, int b, int num){
        return ((a<=b)&&(a<=num&&num<=b)) || ((a>b)&&(b<=num&&num<=a));
    }

    //8) Необходимо реализовать метод таким образом, чтобы он возвращал true
    // если любое из принятых чисел делит другое нацело.
    public static boolean isDivisor (int a, int b){
        return a%b==0 || b%a==0;
    }

    //9) Необходимо реализовать метод таким образом, чтобы он возвращал true
    // если все три полученных методом числа равны
    public static boolean isEqual (int a, int b, int c){
        return a==b && b==c;
    }

    //10) Выполните с его помощью последовательное сложение пяти чисел: 5, 11, 123, 14, 1,
    // и результат выведите на экран. Постарайтесь выполнить задачу, используя минимально возможное
    // количество вспомогательных переменных.
    public static int lastNumSum(int a, int b){
        return (a%10)+(b%10);
    }

}