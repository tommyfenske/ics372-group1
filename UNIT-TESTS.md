# Unit Tests

## What Was Created

4 test classes with 17 tests:
- `OrderTest.java` - 5 tests
- `ItemTest.java` - 3 tests
- `XmlParserTest.java` - 4 tests
- `OrderManagerTest.java` - 5 tests

## How to Run

### IntelliJ IDEA
1. Right-click `src/test/java/ordersystem`
2. Select "Run 'Tests in ordersystem'"
3. View results in Run panel

### Terminal
```bash
./gradlew test
```

Note: Console method may fail due to Java 25 compatibility. Use IntelliJ instead.

## What Gets Tested

### Feature 1: Cancel Orders
- Cancel from INCOMING status
- Cancel from STARTED status
- Cannot cancel COMPLETED orders

### Feature 3: XML Import with Bug Handling
- Parse valid XML files
- Handle malformed XML gracefully
- Handle missing/invalid data

### Feature 4: Order Types
- Delivery orders
- Pickup orders
- To Go orders

### Core Functionality
- Order creation and state transitions
- Item data management
- Order lifecycle (INCOMING -> STARTED -> COMPLETE)

## Expected Output

All tests pass with green checkmarks.

You will see error messages like:
```
Cannot import order due to an error in the data/format of the XML file: malformed_invalid_price.xml
```

These are expected they prove our error handling works correctly.

## Test Results

When successful:
- 17 tests passed
- 0 tests failed
- All green checkmarks in test tree
  ![Test Results Screenshot](Unit_Test_screenshot)


## Files

Tests: `src/test/java/ordersystem/`
Test data: `src/test/resources/test_orders/`
