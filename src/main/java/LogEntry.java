import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {

    private final String inputLine;
    private String ipAddr;
    private String valFirst;
    private String valSec;
    private LocalDateTime time;
    private Method httpMethod;
    private String path;
    private int responseCode;
    private int responseSize;
    private String referer;
    private UserAgent userAgent;


    public LogEntry(String inputLine) throws CannotParseLogException {
        this.inputLine = inputLine;
        this.parse();
    }

    private void parse() throws CannotParseLogException {

        String regex = "(\\S+) (\\S+) (\\S+) \\[([^\\]]+)\\] \"([^\"]+)\" (\\d{3}) (\\S+) \"([^\"]*)\" \"([^\"]*)\"";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(this.inputLine);

        if (matcher.find()) {

            this.ipAddr = matcher.group(1);
            this.valFirst = matcher.group(2);
            this.valSec = matcher.group(3);
            this.time = dttmParse(matcher.group(4));
            String[] methodAndPath = matcher.group(5).split(" ");
            this.httpMethod = Method.valueOf(methodAndPath[0]);
            this.path = methodAndPath[1];
            this.responseCode = Integer.parseInt(matcher.group(6));
            this.responseSize = Integer.parseInt(matcher.group(7));
            this.referer = matcher.group(8);
            this.userAgent = new UserAgent(matcher.group(9));

        } else {
            throw new CannotParseLogException("Не могу прочитать запись!");
        }

    }

    private LocalDateTime dttmParse(String dttmString) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dttmString, formatter);
        return zonedDateTime.toLocalDateTime();

    }

    public String getInputLine() {
        return inputLine;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public String getValFirst() {
        return valFirst;
    }

    public String getValSec() {
        return valSec;
    }

    public LocalDateTime getDateTime() {
        return time;
    }

    public Method getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }
}
