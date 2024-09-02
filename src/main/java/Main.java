import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws LineTooLongException {

        String path;
        int linesTotal = 0, yaBots = 0, googleBots = 0;
        int maxLineLength = 1024;
        Statistics stat = new Statistics();

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

                LogEntry logLine = new LogEntry(line);
                stat.addEntry(logLine);


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //значения делил на 1000, тк тип Инт - "пропадают" копейки в виде сообщений <1КБ или результатов округления
        //думаю, это не критично.
        System.out.println("Средний траффик в час (КБ): " + stat.getTrafficRate());
        System.out.println("Доли ОС:");
        System.out.println(stat.getOSRate());
        System.out.println("Доли Браузеров:");
        System.out.println(stat.getBrowserRate());
        System.out.println("Несуществующие адреса в запросах:");
        System.out.println(stat.getNonExistingReferences());
        System.out.println("Посещений/час за период(не боты):");
        System.out.println(stat.getVisitsRate());
        System.out.println("Ошибок/час за период(коды 4хх, 5хх):");
        System.out.println(stat.getErrorRate());
        System.out.println("Уникальных пользователей/час за период(среднее):");
        System.out.println(stat.getUniqUserRate());
        System.out.println("Максимум уникальных посещений в секунду:");
        System.out.println(stat.getMaxVisitRate());
        System.out.println("Список сайтов, со страниц которых есть ссылки на текущий сайт:");
        System.out.println(stat.getDomains());
        System.out.println("Максимальная посещаемость одним пользователем:");
        System.out.println(stat.getMaxUserVisitRate());


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
