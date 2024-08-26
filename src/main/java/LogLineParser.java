public class LogLineParser {

    String inputLine;
    String ipAdress;
    String valFirst;
    String valSec;
    String dateTime;
    String reqMethod;
    String httpResp;
    String msgSize;
    String referer;
    String userAgent;
    final String yaBot = "YandexBot";
    final String googleBot = "Googlebot";

    public LogLineParser(String inputLine) {
        this.inputLine = inputLine;
    }

    public void parse() throws CannotParseLogException {

        String regex = "(\\S+) (\\S+) (\\S+) \\[([^\\]]+)\\] \"([^\"]+)\" (\\d{3}) (\\S+) \"([^\"]*)\" \"([^\"]*)\"";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(this.inputLine);

        if (matcher.find()) {

            ipAdress = matcher.group(1);
            valFirst = matcher.group(2);
            valSec = matcher.group(3);
            dateTime = matcher.group(4);
            reqMethod = matcher.group(5);
            httpResp = matcher.group(6);
            msgSize = matcher.group(7);
            referer = matcher.group(8);
            userAgent = matcher.group(9);

        } else {
            throw new CannotParseLogException("Не могу прочитать запись!");
        }

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

        //            очистьте от пробелов каждый получившийся фрагмент;
//            возьмите второй фрагмент;
//            отделите в этом фрагменте часть до слэша.
//            Получившийся фрагмент будет соответствовать используемой программе, которая производит запросы.

        String[] parts = userAgent.split(";");
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
