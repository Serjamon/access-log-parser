import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

public class Statistics {

    private int totalTraffic;
    private LocalDateTime minTime, maxTime;
    private HashSet<String> references;
    private HashMap<String, Integer> osRate;

    public Statistics() {
        totalTraffic = 1;
        minTime = LocalDateTime.MAX;
        maxTime = LocalDateTime.MIN;
        references = new HashSet<>();
        osRate = new HashMap<>();
    }

    public void addEntry(LogEntry logEntry) {

        addTrafficAndTime(logEntry);
        addReference(logEntry);
        addOSCount(logEntry);

    }

    private void addReference(LogEntry logEntry){
        if(logEntry.getResponseCode() == 200) this.references.add(logEntry.getPath());
    }

    private void addOSCount(LogEntry logEntry){

        String os = logEntry.getUserAgent().getOs();
        osRate.put(os, (osRate.containsKey(os) ? osRate.get(os) + 1 : 1));

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

    public HashSet<String> getReferences() {
        return references;
    }
}
