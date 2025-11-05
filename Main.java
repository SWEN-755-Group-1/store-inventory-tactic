public class Main {
    public static void main(String[] args) {
        // Initialize system the components
        InventoryQueue queue = new InventoryQueue();
        CentralInventoryService centralService = new CentralInventoryService();
        SyncAgent syncAgent = new SyncAgent(queue, centralService);
        LocalInventoryService localService = new LocalInventoryService(queue, syncAgent);

        System.out.println("--- Inventory Synchronization Tactic ---");

        // Scenario 1: A customer purchases items (a simple, clean update)
        // Local service handles the sale immediately, ensuring the POS doesn't wait for the corporate network.
        localService.inventoryChange("SKU-1001", -5);

        // Scenario 2: A small restock happens (another typical update)
        // A clerk receives a new pallet and adds the stock to the local count.
        localService.inventoryChange("SKU-1002", 2);

        // Scenario 3: An operation triggers a simulated conflict
        // A large unexpected shipment arrives or a bulk return happens, which might exceed a predefined safety threshold.
        localService.inventoryChange("SKU-1003", 50);
    }
}