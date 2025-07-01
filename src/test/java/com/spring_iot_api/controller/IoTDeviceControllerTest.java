package com.spring_iot_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spring_iot_api.domain.IoTDevice;
import com.spring_iot_api.service.IoTDeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class IoTDeviceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IoTDeviceService service;

    @InjectMocks
    private IoTDeviceController controller;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    /**
     * Test GET /api/devices
     * Verifies that all IoT devices are returned with HTTP 200 OK.
     */
    @Test
    void testGetAllDevices() throws Exception {
        IoTDevice device = new IoTDevice(1, "Sensor", "Temperature", true, LocalDateTime.now());
        when(service.getAllDevices()).thenReturn(List.of(device));

        mockMvc.perform(get("/api/devices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Sensor"));
    }
    /**
     * Test GET /api/devices/{id} when device is found
     * Verifies the device is returned with HTTP 200 OK.
     */
    @Test
    void testGetDeviceByIdFound() throws Exception {
        IoTDevice device = new IoTDevice(1, "Sensor", "Temperature", true, LocalDateTime.now());
        when(service.getDevice(1)).thenReturn(Optional.of(device));

        mockMvc.perform(get("/api/devices/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Temperature"));
    }
    /**
     * Test GET /api/devices/{id} when device is not found
     * Verifies HTTP 404 Not Found is returned.
     */
    @Test
    void testGetDeviceByIdNotFound() throws Exception {
        when(service.getDevice(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/devices/1"))
                .andExpect(status().isNotFound());
    }
    /**
     * Test POST /api/devices
     * Verifies that a new device is created and returned with HTTP 200 OK.
     */
    @Test
    void testCreateDevice() throws Exception {
        IoTDevice device = new IoTDevice(1, "Sensor", "Temperature", true, LocalDateTime.now());
        when(service.createDevice(any())).thenReturn(device);

        mockMvc.perform(post("/api/devices")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(device)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sensor"));
    }
    /**
     * Test PUT /api/devices/{id} when device is found
     * Verifies the updated device is returned with HTTP 200 OK.
     */
    @Test
    void testUpdateDeviceFound() throws Exception {
        IoTDevice device = new IoTDevice(1, "UpdatedSensor", "Temperature", false, LocalDateTime.now());
        when(service.updateDevice(any(), any())).thenReturn(Optional.of(device));

        mockMvc.perform(put("/api/devices/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(device)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("UpdatedSensor"))
                .andExpect(jsonPath("$.active").value(false));
    }
    /**
     * Test PUT /api/devices/{id} when device is not found
     * Verifies HTTP 404 Not Found is returned.
     */
    @Test
    void testUpdateDeviceNotFound() throws Exception {
        IoTDevice device = new IoTDevice(1, "UpdatedSensor", "Temperature", false, LocalDateTime.now());
        when(service.updateDevice(any(), any())).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/devices/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(device)))
                .andExpect(status().isNotFound());
    }
    /**
     * Test DELETE /api/devices/{id} when device is found
     * Verifies HTTP 200 OK is returned on successful deletion.
     */
    @Test
    void testDeleteDeviceFound() throws Exception {
        when(service.deleteDevice(1)).thenReturn(true);

        mockMvc.perform(delete("/api/devices/1"))
                .andExpect(status().isOk());
    }
    /**
     * Test DELETE /api/devices/{id} when device is not found
     * Verifies HTTP 404 Not Found is returned.
     */
    @Test
    void testDeleteDeviceNotFound() throws Exception {
        when(service.deleteDevice(1)).thenReturn(false);

        mockMvc.perform(delete("/api/devices/1"))
                .andExpect(status().isNotFound());
    }
}
