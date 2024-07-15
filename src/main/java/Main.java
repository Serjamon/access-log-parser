import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int tryCount = 1;//"начиная с единицы" - увеличение произойдет после вывода сообщения

        do {
            System.out.println("Введите путь к log файлу: ");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExist = file.exists();
            boolean isDirectory = file.isDirectory();

            if (isDirectory) {
                System.out.println("Указан путь к директории, а не к файлу!");
                continue;
            }

            if (fileExist) {
                System.out.println("Путь указан верно.");
                System.out.println("Это файл номер " + tryCount++);
                //По условию останавливать ввод не требуется.
                //break;
            } else {
                System.out.println("Указанный файл не существует!");
            }

        } while (true);

    }

    //Переделал на переменные в самом методе main, как в условии задачи.

//    //Проверим, существует ли файл с логами
//    public static boolean fileExist(String path) {
//        File file = new File(path);
//        return file.exists();
//    }
//
//    //Проверим, является ли введенный путь путем к директории, а не к файлу
//    public static boolean isDirectory(String path) {
//        File file = new File(path);
//        return file.isDirectory();
//    }
}
