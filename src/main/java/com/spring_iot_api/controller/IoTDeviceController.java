package com.spring_iot_api.controller;

import com.spring_iot_api.domain.IoTDevice;
import com.spring_iot_api.service.IoTDeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class IoTDeviceController {

    private final IoTDeviceService service;

    public IoTDeviceController(IoTDeviceService service) {
        this.service = service;
    }

    @GetMapping
    public List<IoTDevice> getAllDevices() {
        return service.getAllDevices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IoTDevice> getDevice(@PathVariable Integer id) {
        return service.getDevice(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IoTDevice> createDevice(@RequestBody IoTDevice iotDevice) {
        return ResponseEntity.ok(service.createDevice(iotDevice));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IoTDevice> updateDevice(@PathVariable Integer id,
                                                  @RequestBody IoTDevice iotDevice) {
        return service.updateDevice(id, iotDevice)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Integer id) {
        return service.deleteDevice(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
