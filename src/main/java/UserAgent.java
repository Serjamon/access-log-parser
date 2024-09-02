import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class UserAgent {

    private String inputString, os, browser;
    private final String yaBot = "YandexBot";
    private final String googleBot = "Googlebot";
    private boolean bot;

    public UserAgent(String inputString) {
        this.inputString = inputString;
        setValues();
    }

    private void setValues() {

        if (inputString.contains("Windows")) {
            os = "Windows";
        } else if (inputString.contains("Macintosh")) {
            os = "macOS";
        } else if (inputString.contains("Linux")) {
            os = "Linux";
        } else if (inputString.contains("iPhone")) {
            os = "iPhone";
        } else if (inputString.contains("Android")) {
            os = "Android";
        } else os = "other";

        if (inputString.contains("Chrome")) {
            browser = "Chrome";
        } else if (inputString.contains("Firefox")) {
            browser = "Firefox";
        } else if (inputString.contains("Safari")) {
            browser = "Safari";
        } else if (inputString.contains("MSIE")) {
            browser = "Internet Explorer";
        } else if (inputString.contains("Edge")) {
            browser = "Edge";
        } else browser = "other";

        bot = isBot();

    }

    public String getInputString() {
        return inputString;
    }

    public String getOs() {
        return os;
    }

    public String getBrowser() {
        return browser;
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "os='" + os + '\'' +
                ", browser='" + browser + '\'' +
                '}';
    }

    public boolean isYandexBot() {

        return getBotFragment().equals(yaBot);
    }

    public boolean isGoogleBot() {

        return getBotFragment().equals(googleBot);
    }

    public boolean isBot() {

        return getBotFragment().contains("bot");
    }

    private String getBotFragment() {

        String fragment;

        Optional<String> parts = Arrays.stream(inputString.split(";"))
                .filter(s -> s.contains("bot"))
                .findFirst();

        //TODO делал по описанию методов, м.б. можно сделать лучше
        if (parts.isPresent()) fragment = parts.get();
        else fragment = "";

        return fragment;
    }

}
