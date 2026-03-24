import java.util.*;

class Transaction {
    int id;
    int amount;
    String merchant;
    String time;

    public Transaction(int id, int amount, String merchant, String time) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
        this.time = time;
    }
}

public class TwoSumTransactions {

    // Classic Two Sum
    public List<int[]> findTwoSum(List<Transaction> transactions, int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        for (Transaction t : transactions) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                result.add(new int[]{map.get(complement).id, t.id});
            }

            map.put(t.amount, t);
        }

        return result;
    }


    // Detect duplicates (same amount + merchant)
    public void detectDuplicates(List<Transaction> transactions) {

        HashMap<String, List<Integer>> map = new HashMap<>();

        for (Transaction t : transactions) {

            String key = t.amount + "_" + t.merchant;

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(t.id);
        }

        for (String key : map.keySet()) {

            if (map.get(key).size() > 1) {
                System.out.println("Duplicate transactions: " + map.get(key));
            }
        }
    }


    // Demo
    public static void main(String[] args) {

        TwoSumTransactions obj = new TwoSumTransactions();

        List<Transaction> transactions = Arrays.asList(
                new Transaction(1, 500, "StoreA", "10:00"),
                new Transaction(2, 300, "StoreB", "10:15"),
                new Transaction(3, 200, "StoreC", "10:30"),
                new Transaction(4, 500, "StoreA", "10:45")
        );

        // Two Sum
        List<int[]> pairs = obj.findTwoSum(transactions, 500);

        for (int[] pair : pairs) {
            System.out.println("Pair: " + pair[0] + ", " + pair[1]);
        }

        // Duplicates
        obj.detectDuplicates(transactions);
    }
}