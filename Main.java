public class Main {
    public static void main(String[] args) {
        // Setup the components
        InventoryQueue queue = new InventoryQueue();
        CentralInventoryService centralService = new CentralInventoryService();
        SyncAgent syncAgent = new SyncAgent(queue, centralService);
        LocalInventoryService localService = new LocalInventoryService(queue, syncAgent);

        System.out.println("--- Inventory Synchronization Tactic ---");

        // Scenario 1: Simple update
        localService.inventoryChange("SKU-1001", -5);

        // Scenario 2: Another update
        localService.inventoryChange("SKU-1002", 2);

        // Scenario 3: Update that triggers a simulated conflict
        localService.inventoryChange("SKU-1003", 50);
    }
}