import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {

    private int totalTraffic;
    private LocalDateTime minTime, maxTime;

    public Statistics() {
        totalTraffic = 0;
        minTime = LocalDateTime.MAX;
        maxTime = LocalDateTime.MIN;
    }

    public void addEntry(LogEntry logEntry) {

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

}
