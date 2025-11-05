/**
 * Represents the immediate, in-store inventory system used for daily operations
 * This service prioritizes speed and availability, using the Event Queue to defer non-critical central communication.
 */
public class LocalInventoryService {
    private final InventoryQueue eventQueue;
    private final SyncAgent syncAgent;

    public LocalInventoryService(InventoryQueue eventQueue, SyncAgent syncAgent) {
        this.eventQueue = eventQueue;
        this.syncAgent = syncAgent;
    }

    /**
     * The primary entry point for any local inventory manipulation.
     */
    public void inventoryChange(String inventoryId, int change) {
        System.out.println("\nLOCAL: Item " + inventoryId + " changed by " + change);

        // 1. Writes events to Event Queue
        InventoryEvent event = new InventoryEvent(inventoryId, change);
        eventQueue.enqueue(event);

        // 2. Triggers sync
        syncAgent.triggerSync();
    }
}