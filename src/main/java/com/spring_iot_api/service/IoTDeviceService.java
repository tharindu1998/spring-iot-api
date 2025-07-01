package com.spring_iot_api.service;

import com.spring_iot_api.domain.IoTDevice;
import com.spring_iot_api.repository.IoTDeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IoTDeviceService {

    private final IoTDeviceRepository repository;

    public IoTDeviceService(IoTDeviceRepository repository) {
        this.repository = repository;
    }

    public List<IoTDevice> getAllDevices() {
        return repository.findAll();
    }

    public Optional<IoTDevice> getDevice(Integer id) {
        return repository.findById(id);
    }

    public IoTDevice createDevice(IoTDevice iotDevice) {
        return repository.save(iotDevice);
    }

    public Optional<IoTDevice> updateDevice(Integer id, IoTDevice updated) {
        if (repository.existsById(id)) return Optional.empty();
        updated.setId(id);
        return Optional.of(repository.save(updated));
    }

    public boolean deleteDevice(Integer id) {
        if (repository.existsById(id)) return false;
        repository.delete(id);
        return true;
    }

}
