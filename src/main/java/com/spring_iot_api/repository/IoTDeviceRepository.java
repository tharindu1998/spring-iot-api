package com.spring_iot_api.repository;

import com.spring_iot_api.domain.IoTDevice;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Repository class for managing IoTDevice entities in-memory.
 * Uses ConcurrentHashMap for thread-safe storage and operations.
 */
@Repository
public class IoTDeviceRepository {

    private final ConcurrentHashMap<Integer, IoTDevice> deviceStore = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(0);

    public List<IoTDevice> findAll() {
        return new ArrayList<>(deviceStore.values());
    }

    public Optional<IoTDevice> findById(Integer id) {
        return Optional.ofNullable(deviceStore.get(id));
    }

    public IoTDevice save(IoTDevice iotDevice) {
        int id = idGenerator.incrementAndGet();
        iotDevice.setId(id);
        iotDevice.setLastCommunicationTimestamp(LocalDateTime.now());
        deviceStore.put(id, iotDevice);
        return iotDevice;
    }

    public void delete(Integer id) {
        deviceStore.remove(id);
    }

    public boolean existsById(Integer id) {
        return deviceStore.containsKey(id);
    }
}
