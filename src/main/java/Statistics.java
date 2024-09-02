import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Statistics {

    private int totalTraffic, notBotCount, errorRespCount;
    private LocalDateTime minTime, maxTime;
    private HashSet<String> existingReferences, nonExistingReferences, domains;
    private HashMap<String, Integer> osRate, browserRate, ipAddresses;
    private HashMap<LocalDateTime, Integer> visitRate;

    public Statistics() {
        totalTraffic = 1;
        notBotCount = 0;
        errorRespCount = 0;
        minTime = LocalDateTime.MAX;
        maxTime = LocalDateTime.MIN;
        existingReferences = new HashSet<>();
        nonExistingReferences = new HashSet<>();
        ipAddresses = new HashMap<>();
        osRate = new HashMap<>();
        browserRate = new HashMap<>();
        visitRate = new HashMap<>();
        domains = new HashSet<>();
    }

    public void addEntry(LogEntry logEntry) {

        addTrafficAndTime(logEntry);
        addReferences(logEntry);
        addOSCount(logEntry);
        addBrowserCount(logEntry);
        addBotCount(logEntry);
        addErrorCount(logEntry);
        addVisitRate(logEntry);
        addDomain(logEntry);

    }

    private void addReferences(LogEntry logEntry){
        if(logEntry.getResponseCode() == 200) this.existingReferences.add(logEntry.getPath());
        if(logEntry.getResponseCode() == 404) this.nonExistingReferences.add(logEntry.getPath());
    }

    private void addErrorCount(LogEntry logEntry){
        if(logEntry.getResponseCode() > 400) this.errorRespCount++;
    }

    private void addOSCount(LogEntry logEntry){

        String os = logEntry.getUserAgent().getOs();
        osRate.put(os, (osRate.containsKey(os) ? osRate.get(os) + 1 : 1));

    }

    private void addBrowserCount(LogEntry logEntry){

        String browser = logEntry.getUserAgent().getBrowser();
        browserRate.put(browser, (browserRate.containsKey(browser) ? browserRate.get(browser) + 1 : 1));

    }

    private void addBotCount(LogEntry logEntry) {

        if (!logEntry.getUserAgent().isBot()) {
            notBotCount++;

            ipAddresses.put(logEntry.getIpAddr(),
                    (ipAddresses.containsKey(logEntry.getIpAddr()) ?
                            ipAddresses.get(logEntry.getIpAddr()) + 1 : 1));
        }

    }

    private void addTrafficAndTime(LogEntry logEntry){

        this.totalTraffic += (logEntry.getResponseSize() / 1000);

        if (minTime.isAfter(logEntry.getDateTime())) minTime = logEntry.getDateTime();

        if (maxTime.isBefore(logEntry.getDateTime())) maxTime = logEntry.getDateTime();

    }

    public double getTrafficRate(){
        return this.totalTraffic / getLogDurationHours();
    }

    public double getVisitsRate(){
        return this.notBotCount / getLogDurationHours();
    }

    public double getErrorRate(){
        return this.errorRespCount / getLogDurationHours();
    }

    public double getUniqUserRate(){
        return notBotCount / this.ipAddresses.size();
    }
    public long getLogDurationHours(){

        Duration duration = Duration.between(minTime, maxTime);
        return duration.toHours();
    }

    public HashMap<String, Double> getOSRate(){

        HashMap<String, Double> calculatedRate = new HashMap<>();

        if (osRate.isEmpty()) return calculatedRate;

        Double sum = 0.0;
        for (Integer i : osRate.values()) {
            sum += i;
        }

        for (String key : osRate.keySet()) {
            calculatedRate.put(key, osRate.get(key) / sum);
        }

        //проверка
        Double sum2 = 0.0;
        for (Double i : calculatedRate.values()) {
            sum2 += i;
        }
        System.out.println(sum2);

        return calculatedRate;

    }

    public HashMap<String, Double> getBrowserRate(){

        HashMap<String, Double> calculatedRate = new HashMap<>();

        if (browserRate.isEmpty()) return calculatedRate;

        Double sum = 0.0;
        for (Integer i : browserRate.values()) {
            sum += i;
        }

        for (String key : browserRate.keySet()) {
            calculatedRate.put(key, browserRate.get(key) / sum);
        }

        //проверка
        Double sum2 = 0.0;
        for (Double i : calculatedRate.values()) {
            sum2 += i;
        }
        System.out.println(sum2);

        return calculatedRate;

    }

    public HashSet<String> getExistingReferences() {
        return existingReferences;
    }

    public HashSet<String> getNonExistingReferences() {
        return nonExistingReferences;
    }

    private void addVisitRate(LogEntry logEntry) {

        if (!logEntry.getUserAgent().isBot()) {

            visitRate.put(logEntry.getDateTime(),
                    (visitRate.containsKey(logEntry.getDateTime()) ?
                            visitRate.get(logEntry.getDateTime()) + 1 : 1));
        }
    }

    public int getMaxVisitRate() {

        int maxVisits = visitRate.values().stream()
                .max(Integer::compareTo)
                .orElse(0);

        return maxVisits;
    }

    private void addDomain(LogEntry logEntry) {

        //нагуглил +-

        String referer = logEntry.getReferer();

        URI uri = null;
        try {
            uri = new URI(referer);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String domain = uri.getHost();
        if (domain != null) {
            domains.add(domain);
        }

    }

    public HashSet<String> getDomains() {
        return domains;
    }

    public String getMaxUserVisitRate() {

        String maxKey = "";
        int maxValue = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry: ipAddresses.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxKey = entry.getKey();
            }
        }

        return "IP-Адрес: " + maxKey
                + " посетил " + maxValue + " раз.";
    }

}
