import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

public class Statistics {

    private int totalTraffic;
    private LocalDateTime minTime, maxTime;
    private HashSet<String> existingReferences, nonExistingReferences;
    private HashMap<String, Integer> osRate, browserRate;

    public Statistics() {
        totalTraffic = 1;
        minTime = LocalDateTime.MAX;
        maxTime = LocalDateTime.MIN;
        existingReferences = new HashSet<>();
        nonExistingReferences = new HashSet<>();
        osRate = new HashMap<>();
        browserRate = new HashMap<>();
    }

    public void addEntry(LogEntry logEntry) {

        addTrafficAndTime(logEntry);
        addReferences(logEntry);
        addOSCount(logEntry);
        addBrowserCount(logEntry);

    }

    private void addReferences(LogEntry logEntry){
        if(logEntry.getResponseCode() == 200) this.existingReferences.add(logEntry.getPath());
        if(logEntry.getResponseCode() == 404) this.nonExistingReferences.add(logEntry.getPath());
    }

    private void addOSCount(LogEntry logEntry){

        String os = logEntry.getUserAgent().getOs();
        osRate.put(os, (osRate.containsKey(os) ? osRate.get(os) + 1 : 1));

    }

    private void addBrowserCount(LogEntry logEntry){

        String browser = logEntry.getUserAgent().getBrowser();
        browserRate.put(browser, (browserRate.containsKey(browser) ? browserRate.get(browser) + 1 : 1));

    }

    private void addTrafficAndTime(LogEntry logEntry){

        this.totalTraffic += (logEntry.getResponseSize() / 1000);

        if (minTime.isAfter(logEntry.getDateTime())) minTime = logEntry.getDateTime();

        if (maxTime.isBefore(logEntry.getDateTime())) maxTime = logEntry.getDateTime();

    }

    public double getTrafficRate(){
        double res = 0;

        Duration duration = Duration.between(minTime, maxTime);
        long hours = duration.toHours();

        res = this.totalTraffic / hours;

        return res;
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

}
