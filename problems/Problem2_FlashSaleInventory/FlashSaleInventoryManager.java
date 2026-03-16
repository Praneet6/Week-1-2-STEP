import java.util.*;

public class FlashSaleInventoryManager {

    // productId -> stock
    private HashMap<String, Integer> stockMap = new HashMap<>();

    // productId -> waiting list
    private HashMap<String, Queue<Integer>> waitingList = new HashMap<>();


    // Add product with initial stock
    public void addProduct(String productId, int stock) {
        stockMap.put(productId, stock);
        waitingList.put(productId, new LinkedList<>());
    }


    // Check stock
    public int checkStock(String productId) {
        return stockMap.getOrDefault(productId, 0);
    }


    // Purchase item
    public synchronized String purchaseItem(String productId, int userId) {

        int stock = stockMap.getOrDefault(productId, 0);

        if (stock > 0) {

            stockMap.put(productId, stock - 1);

            return "Purchase successful. Remaining stock: " + (stock - 1);
        }

        // add to waiting list
        Queue<Integer> queue = waitingList.get(productId);
        queue.add(userId);

        return "Out of stock. Added to waiting list. Position: " + queue.size();
    }


    // Process next waiting customer if stock added
    public void restock(String productId, int quantity) {

        int currentStock = stockMap.getOrDefault(productId, 0);
        stockMap.put(productId, currentStock + quantity);

        Queue<Integer> queue = waitingList.get(productId);

        while (stockMap.get(productId) > 0 && !queue.isEmpty()) {

            int user = queue.poll();

            stockMap.put(productId, stockMap.get(productId) - 1);

            System.out.println("User " + user + " purchased from waiting list.");
        }
    }


    // Show waiting list
    public void showWaitingList(String productId) {
        System.out.println(waitingList.get(productId));
    }


    // Demo
    public static void main(String[] args) {

        FlashSaleInventoryManager manager = new FlashSaleInventoryManager();

        manager.addProduct("IPHONE15_256GB", 2);

        System.out.println(manager.purchaseItem("IPHONE15_256GB", 101));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 102));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 103));

        manager.showWaitingList("IPHONE15_256GB");

        manager.restock("IPHONE15_256GB", 1);
    }
}