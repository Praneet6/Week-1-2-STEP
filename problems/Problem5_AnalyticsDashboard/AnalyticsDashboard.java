import java.util.*;

class Event {
    String url;
    String userId;
    String source;

    public Event(String url, String userId, String source) {
        this.url = url;
        this.userId = userId;
        this.source = source;
    }
}

public class AnalyticsDashboard {

    // url -> total views
    private HashMap<String, Integer> pageViews = new HashMap<>();

    // url -> unique users
    private HashMap<String, Set<String>> uniqueUsers = new HashMap<>();

    // source -> count
    private HashMap<String, Integer> trafficSource = new HashMap<>();


    // Process event
    public void processEvent(Event event) {

        // Page views
        pageViews.put(event.url,
                pageViews.getOrDefault(event.url, 0) + 1);

        // Unique users
        uniqueUsers.putIfAbsent(event.url, new HashSet<>());
        uniqueUsers.get(event.url).add(event.userId);

        // Traffic source
        trafficSource.put(event.source,
                trafficSource.getOrDefault(event.source, 0) + 1);
    }


    // Get top 10 pages
    public List<Map.Entry<String, Integer>> getTopPages() {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        pq.addAll(pageViews.entrySet());

        List<Map.Entry<String, Integer>> result = new ArrayList<>();

        int k = 10;

        while (!pq.isEmpty() && k-- > 0) {
            result.add(pq.poll());
        }

        return result;
    }


    // Display dashboard
    public void getDashboard() {

        System.out.println("Top Pages:");

        List<Map.Entry<String, Integer>> topPages = getTopPages();

        for (Map.Entry<String, Integer> entry : topPages) {

            String url = entry.getKey();
            int views = entry.getValue();
            int unique = uniqueUsers.get(url).size();

            System.out.println(url + " → " + views +
                    " views (" + unique + " unique)");
        }

        System.out.println("\nTraffic Sources:");

        int total = 0;
        for (int count : trafficSource.values()) {
            total += count;
        }

        for (String source : trafficSource.keySet()) {

            int count = trafficSource.get(source);
            double percent = (count * 100.0) / total;

            System.out.println(source + ": " +
                    String.format("%.2f", percent) + "%");
        }
    }


    public static void main(String[] args) {

        AnalyticsDashboard dashboard = new AnalyticsDashboard();

        dashboard.processEvent(new Event("/article/news", "user1", "google"));
        dashboard.processEvent(new Event("/article/news", "user2", "facebook"));
        dashboard.processEvent(new Event("/sports/match", "user3", "direct"));
        dashboard.processEvent(new Event("/article/news", "user1", "google"));

        dashboard.getDashboard();
    }
}