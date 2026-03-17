import java.util.*;

class DNSEntry {
    String ipAddress;
    long expiryTime;

    public DNSEntry(String ipAddress, long ttlSeconds) {
        this.ipAddress = ipAddress;
        this.expiryTime = System.currentTimeMillis() + ttlSeconds * 1000;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

public class DNSCache {

    private HashMap<String, DNSEntry> cache = new HashMap<>();

    private int hits = 0;
    private int misses = 0;


    // Resolve domain
    public String resolve(String domain) {

        if (cache.containsKey(domain)) {

            DNSEntry entry = cache.get(domain);

            if (!entry.isExpired()) {
                hits++;
                return "Cache HIT → " + entry.ipAddress;
            } else {
                cache.remove(domain); // remove expired
            }
        }

        // Cache miss → simulate DNS lookup
        misses++;
        String ip = queryUpstreamDNS(domain);

        // store with TTL (example 5 sec)
        cache.put(domain, new DNSEntry(ip, 5));

        return "Cache MISS → " + ip;
    }


    // Simulate external DNS
    private String queryUpstreamDNS(String domain) {
        return "192.168." + new Random().nextInt(255) + "." + new Random().nextInt(255);
    }


    // Cache stats
    public void getCacheStats() {

        int total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);

        System.out.println("Hits: " + hits);
        System.out.println("Misses: " + misses);
        System.out.println("Hit Rate: " + hitRate + "%");
    }


    // Cleanup expired entries
    public void cleanup() {

        Iterator<Map.Entry<String, DNSEntry>> it = cache.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, DNSEntry> entry = it.next();

            if (entry.getValue().isExpired()) {
                it.remove();
            }
        }
    }


    // Demo
    public static void main(String[] args) throws InterruptedException {

        DNSCache dns = new DNSCache();

        System.out.println(dns.resolve("google.com")); // MISS
        System.out.println(dns.resolve("google.com")); // HIT

        Thread.sleep(6000); // wait for TTL expiry

        System.out.println(dns.resolve("google.com")); // MISS again

        dns.getCacheStats();
    }
}