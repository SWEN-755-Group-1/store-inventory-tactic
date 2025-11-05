import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Represents a single change in local inventory that needs to be communicated to central.
 */
class InventoryEvent {
    private final String inventoryId;
    private final int quantityChange;

    public InventoryEvent(String inventoryId, int quantityChange) {
        this.inventoryId = inventoryId;
        this.quantityChange = quantityChange;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public int getQuantityChange() {
        return quantityChange;
    }

    @Override
    public String toString() {
        return "EVENT [Item: " + inventoryId + ", Change: " + quantityChange + "]";
    }
}

/**
 * The buffer component (Event Queue) that decouples the local store service from the central corporate service.
 * This is crucial for availability: if the central network is down, the local service can still enqueue events and continue operating.
 */
public class InventoryQueue {
    private final Queue<InventoryEvent> queue = new ConcurrentLinkedQueue<>();

    public void enqueue(InventoryEvent event) {
        System.out.println("EVENT QUEUE: WRITE " + event);
        queue.add(event);
    }

    public InventoryEvent dequeue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}