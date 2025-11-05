public class CentralInventoryService {
    public void updateInventory(String inventoryId, int change) {
        System.out.println("CENTRAL: Inventory updated for " + inventoryId + ". Change: " + change);
    }

    public void resolveConflict() {
        System.out.println("CENTRAL: Conflict resolution logic executed.");
    }
}