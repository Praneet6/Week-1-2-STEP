import java.util.*;

public class AutocompleteSystem {

    // query -> frequency
    private HashMap<String, Integer> frequencyMap = new HashMap<>();


    // Add or update query frequency
    public void addQuery(String query) {
        frequencyMap.put(query,
                frequencyMap.getOrDefault(query, 0) + 1);
    }


    // Get top 10 suggestions for prefix
    public List<String> search(String prefix) {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {

            if (entry.getKey().startsWith(prefix)) {
                pq.add(entry);
            }
        }

        List<String> result = new ArrayList<>();
        int k = 10;

        while (!pq.isEmpty() && k-- > 0) {
            result.add(pq.poll().getKey());
        }

        return result;
    }


    // Demo
    public static void main(String[] args) {

        AutocompleteSystem system = new AutocompleteSystem();

        system.addQuery("java tutorial");
        system.addQuery("java tutorial");
        system.addQuery("javascript basics");
        system.addQuery("java download");
        system.addQuery("java interview questions");

        System.out.println(system.search("jav"));
    }
}