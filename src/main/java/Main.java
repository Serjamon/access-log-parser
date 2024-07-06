import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Напишите в нём код, который будет получать последовательно два числа из консоли.

        System.out.println("Для выполнения программы потребуется ввести последовательно два целых числа.");
        System.out.println("Введите первое число:");
        int x = new Scanner(System.in).nextInt();

        System.out.println("Введите второе число:");
        int y = new Scanner(System.in).nextInt();

//        Затем напишите код, который будет вычислять и выводить в консоль
//        сумму,
//        разность,
//        произведение
//        и частное этих чисел.
//        Для расчёта суммы, разности и произведения используйте тип int, а для расчёта частного — тип double
        System.out.println("Сумма: " + x + "+" + y + "=" + (x+y));
        System.out.println("Разность: " + x + "-" + y + "=" +(x-y));
        System.out.println("Произведение: " + x + "*" + y + "=" + (x*y));
        System.out.println("Частное: " + x + "/" + y + "=" + ((double)x/y));

    }
}
