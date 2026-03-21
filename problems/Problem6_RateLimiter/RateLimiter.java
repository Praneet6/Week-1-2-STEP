import java.util.*;

class TokenBucket {
    int tokens;
    int maxTokens;
    long lastRefillTime;
    int refillRate; // tokens per second

    public TokenBucket(int maxTokens, int refillRate) {
        this.tokens = maxTokens;
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.lastRefillTime = System.currentTimeMillis();
    }

    // refill tokens based on time passed
    public void refill() {
        long now = System.currentTimeMillis();
        long seconds = (now - lastRefillTime) / 1000;

        if (seconds > 0) {
            int newTokens = (int) seconds * refillRate;
            tokens = Math.min(maxTokens, tokens + newTokens);
            lastRefillTime = now;
        }
    }
}

public class RateLimiter {

    private HashMap<String, TokenBucket> clients = new HashMap<>();

    private int MAX_TOKENS = 5;     // limit
    private int REFILL_RATE = 1;    // 1 token per second


    public boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId,
                new TokenBucket(MAX_TOKENS, REFILL_RATE));

        TokenBucket bucket = clients.get(clientId);

        bucket.refill();

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return true;
        }

        return false;
    }


    public void getStatus(String clientId) {

        TokenBucket bucket = clients.get(clientId);

        System.out.println("Client: " + clientId +
                " | Remaining tokens: " + bucket.tokens);
    }


    // Demo
    public static void main(String[] args) throws InterruptedException {

        RateLimiter rl = new RateLimiter();

        String client = "user123";

        for (int i = 0; i < 7; i++) {
            System.out.println("Request " + (i+1) + ": "
                    + rl.checkRateLimit(client));
        }

        rl.getStatus(client);

        System.out.println("Waiting for refill...");
        Thread.sleep(3000);

        System.out.println("After refill:");
        System.out.println(rl.checkRateLimit(client));
    }
}