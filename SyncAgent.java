public class SyncAgent {
    private final InventoryQueue eventQueue;
    private final CentralInventoryService centralService;

    public SyncAgent(InventoryQueue eventQueue, CentralInventoryService centralService) {
        this.eventQueue = eventQueue;
        this.centralService = centralService;
    }

    public void triggerSync() {
        System.out.println("SYNC AGENT: Sync triggered.");
        processEvents();
    }

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
                    centralService.updateInventory(event.getInventoryId(), event.getQuantityChange());
                }
            }
        }
    }
}