import java.util.*;

public class UsernameChecker {

    private HashMap<String, Integer> usernameMap = new HashMap<>();
    private HashMap<String, Integer> attemptFrequency = new HashMap<>();

    public boolean checkAvailability(String username) {

        attemptFrequency.put(username,
                attemptFrequency.getOrDefault(username, 0) + 1);

        return !usernameMap.containsKey(username);
    }

    public void registerUser(String username, int userId) {
        usernameMap.put(username, userId);
    }

    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {

            String suggestion = username + i;

            if (!usernameMap.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        String alt = username.replace("_", ".");
        if (!usernameMap.containsKey(alt))
            suggestions.add(alt);

        return suggestions;
    }

    public String getMostAttempted() {

        String result = "";
        int max = 0;

        for (String user : attemptFrequency.keySet()) {

            int count = attemptFrequency.get(user);

            if (count > max) {
                max = count;
                result = user;
            }
        }

        return result;
    }
}