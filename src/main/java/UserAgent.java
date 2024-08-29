public class UserAgent {

    private String inputString, os, browser;
    private final String yaBot = "YandexBot";
    private final String googleBot = "Googlebot";

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

    private String getBotFragment() {

        String fragment;
        int charIndex;

        String[] parts = inputString.split(";");
        if (parts.length >= 2) {
            fragment = parts[1];
            fragment = fragment.replace(" ", "");
            charIndex = fragment.indexOf("/");
            if (charIndex <= 0) return "";
            fragment = fragment.substring(0, charIndex);
        } else return "";

        return fragment;
    }

}
