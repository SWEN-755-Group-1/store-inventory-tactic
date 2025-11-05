/**
 * The dedicated process responsible for tracking the backlog of local changes and synchronizing them with the central system.
 * This ensures eventual consistency between the local and central inventory counts.
 */
public class SyncAgent {
    private final InventoryQueue eventQueue;
    private final CentralInventoryService centralService;

    public SyncAgent(InventoryQueue eventQueue, CentralInventoryService centralService) {
        this.eventQueue = eventQueue;
        this.centralService = centralService;
    }

    /**
     * Called by the Local Inventory Service after it successfully enqueues a new change.
     */
    public void triggerSync() {
        System.out.println("SYNC AGENT: Sync triggered.");
        processEvents();
    }

    /**
     * Core loop: retrieves events and attempts to apply them to central.
     */
    private void processEvents() {
        while (!eventQueue.isEmpty()) {
            InventoryEvent event = eventQueue.dequeue();
            if (event != null) {
                System.out.println("SYNC AGENT: Processing " + event);

                // Simple conflict check
                if (Math.abs(event.getQuantityChange()) > 10) {
                    System.out.println("SYNC AGENT: Detected a potential conflict for " + event.getInventoryId());
                    centralService.resolveConflict();
                } else {
                    // Standard update
                    centralService.updateInventory(event.getInventoryId(), event.getQuantityChange());
                }
            }
        }
    }
}