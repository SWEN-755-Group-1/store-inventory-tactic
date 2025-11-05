# Supermarket Inventory Tracking System

## Availability Tactic

This project implements an availability tactic using an Event-Driven pattern to ensure the local store operation remains functional even if the Central Inventory Service is temporarily unavailable (due to network latency, scheduled maintenance, or failure).

The system prioritizes local availability through the following mechanism:
1. **Fault Tolerance via Queue**: The change is persisted as an InventoryEvent and written to the Event Queue. This queue acts as a durable buffer, ensuring the change is not lost if the central system is down or slow.
2. **Asynchronous Synchronization**: The Sync Agent constantly and asynchronously processes events from the queue, applying them to the Central Inventory Service.
3. **Consistency & Conflict Resolution**: This pattern results in consistency between the local and central inventories. The Sync Agent also includes logic to detect conflicting events (like unusually large quantity changes) and triggers a conflict resolution mechanism to prevent data corruption.

## Running the Application

To run the application, open a terminal in the project directory and run the following commands:

**Step 1:** Compile the files
```
javac *.java
```

**Step 2:** Run the Main file view a simulated demo to exhibit the availability tactic
```
java Main
```
