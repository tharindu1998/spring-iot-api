# spring-iot-api
A Spring Boot RESTful API for managing IoT devices, supporting CRUD operations with in-memory storage and unit testing. Built with Java and follows clean code and REST principles.

## Build and Run
```bash
./mvnw clean install
./mvnw spring-boot:run
```

## Sample Requests
- **GET** `/api/devices` - Get all devices
- **POST** `/api/devices` - Create a new device

## Model

### `IoTDevice`
```json
{
  "id": 1,
  "name": "Temperature Sensor",
  "type": "Sensor",
  "active": true,
  "lastCommunication": "2025-07-01T10:00:00"
}
```
- **GET** `/api/devices/1` - Get a device by ID
- **PUT** `/api/devices/1` - Update an existing one
- **DELETE** `/api/devices/1` - Delete a device by ID

## Test
```bash
./mvnw test
```
