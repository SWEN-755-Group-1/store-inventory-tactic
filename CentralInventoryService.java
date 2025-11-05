/**
 * Represents the main, authoritative inventory database
 * Keeps track of company-wide stock level, replenishment required, planning, and availability.
 */
public class CentralInventoryService {
    /**
     * Executes the actual update on the central database based on a processed event.
     */
    public void updateInventory(String inventoryId, int change) {
        // In a real system, this would involve a complex API call or database transaction.
        System.out.println("CENTRAL: Inventory updated for " + inventoryId + ". Change: " + change);
    }

    /**
     * Logic executed when the Sync Agent determines a change is conflicting.
     */
    public void resolveConflict() {
        // In a real system, this might involve: flagging the item for manual audit or alerting a regional manager.
        System.out.println("CENTRAL: Conflict resolution logic executed.");
    }
}