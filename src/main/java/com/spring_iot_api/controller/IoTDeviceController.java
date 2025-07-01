package com.spring_iot_api.controller;

import com.spring_iot_api.domain.IoTDevice;
import com.spring_iot_api.service.IoTDeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Retrieve all IoT devices")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public List<IoTDevice> getAllDevices() {
        return service.getAllDevices();
    }

    @Operation(summary = "Get a specific IoT device by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device found"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<IoTDevice> getDevice(@PathVariable Integer id) {
        return service.getDevice(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new IoT device")
    @ApiResponse(responseCode = "200", description = "Device created successfully")
    @PostMapping
    public ResponseEntity<IoTDevice> createDevice(@RequestBody IoTDevice iotDevice) {
        return ResponseEntity.ok(service.createDevice(iotDevice));
    }

    @Operation(summary = "Update an existing IoT device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device updated"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<IoTDevice> updateDevice(@PathVariable Integer id,
                                                  @RequestBody IoTDevice iotDevice) {
        return service.updateDevice(id, iotDevice)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an IoT device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device deleted"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Integer id) {
        return service.deleteDevice(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
