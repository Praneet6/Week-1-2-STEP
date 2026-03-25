import java.util.*;

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // access-order
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

public class MultiLevelCache {

    // L1 and L2 caches
    private LRUCache<String, String> L1;
    private LRUCache<String, String> L2;

    // Simulated DB
    private HashMap<String, String> database;

    public MultiLevelCache() {
        L1 = new LRUCache<>(3);   // small fast cache
        L2 = new LRUCache<>(5);   // larger cache
        database = new HashMap<>();

        // preload DB
        database.put("video1", "Video Data 1");
        database.put("video2", "Video Data 2");
        database.put("video3", "Video Data 3");
    }


    public String getVideo(String videoId) {

        // L1 check
        if (L1.containsKey(videoId)) {
            System.out.println("L1 Cache HIT");
            return L1.get(videoId);
        }

        // L2 check
        if (L2.containsKey(videoId)) {
            System.out.println("L2 Cache HIT");

            String data = L2.get(videoId);

            // promote to L1
            L1.put(videoId, data);

            return data;
        }

        // L3 (DB)
        System.out.println("L3 Database HIT");

        String data = database.get(videoId);

        if (data != null) {
            L2.put(videoId, data);
        }

        return data;
    }


    // Demo
    public static void main(String[] args) {

        MultiLevelCache cache = new MultiLevelCache();

        System.out.println(cache.getVideo("video1")); // L3
        System.out.println(cache.getVideo("video1")); // L2 → L1
        System.out.println(cache.getVideo("video1")); // L1

        System.out.println(cache.getVideo("video2")); // L3
        System.out.println(cache.getVideo("video3")); // L3
    }
}