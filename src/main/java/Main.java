import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws LineTooLongException {

        String path;
        int linesTotal = 0, yaBots = 0, googleBots = 0;
        int maxLineLength = 1024;

        path = readFileName();

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader =
                    new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                int length = line.length();

                linesTotal++;
                if (length > maxLineLength) {
                    throw new LineTooLongException("Длина строки №" + linesTotal + " превышает максимально допустимую:" + maxLineLength);
                }

                LogLineParser logLine = new LogLineParser(line);
                logLine.parse();
                if(logLine.isYandexBot()) yaBots++;
                if(logLine.isGoogleBot()) googleBots++;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Double yaPerc = (double)yaBots/linesTotal*100;
        Double googlePerc = (double)googleBots/linesTotal*100;
        System.out.println("Доля запросов YandexBot: " + String.format("%.2f", yaPerc) + "%");
        System.out.println("Доля запросов Googlebot: " + String.format("%.2f", googlePerc) + "%");

    }

    public static String readFileName(){

        String path;
        int tryCount = 1;

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

        return path;

    }


}
