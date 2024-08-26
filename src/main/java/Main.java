import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws LineTooLongException {

        int tryCount = 1;//"начиная с единицы" - увеличение произойдет после вывода сообщения
        String path;
        int linesTotal = 0, longestLine = 0, shortestLine = 1025;
        int maxLineLength = 1024;


        do {
            System.out.println("Введите путь к log файлу: ");
            path = new Scanner(System.in).nextLine();
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
                break;
            } else {
                System.out.println("Указанный файл не существует!");
            }

        } while (true);

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader =
                    new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                int length = line.length();

                if(length > maxLineLength) {
                    throw new LineTooLongException("Длина строки №" + linesTotal + " превышает максимально допустимую:" + maxLineLength);
                }

                linesTotal++;
                if(length > longestLine) longestLine = length;
                if(shortestLine > length) shortestLine = length;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Всего строк в файле: " + linesTotal);
        System.out.println("Самая длинная строка: " + longestLine);
        System.out.println("Самая короткая строка: " + shortestLine);

    }

}
