package com.spring_iot_api.domain;

import lombok.*;

import java.time.LocalDateTime;

/**
 * IoTDevice model class.
 * Represents an IoT device with attributes for name, type, status, and last communication timestamp.
 */

public class IoTDevice {
    private Integer id;
    private String name;
    private String type;
    private boolean active;

    private LocalDateTime lastCommunicationTimestamp;

    public IoTDevice() {
    }

    public IoTDevice(Integer id, String name, String type, boolean active, LocalDateTime lastCommunicationTimestamp) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.active = active;
        this.lastCommunicationTimestamp = lastCommunicationTimestamp;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLastCommunicationTimestamp(LocalDateTime lastCommunicationTimestamp) {
        this.lastCommunicationTimestamp = lastCommunicationTimestamp;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getLastCommunicationTimestamp() {
        return lastCommunicationTimestamp;
    }

    @Override
    public String toString() {
        return "IoTDevice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", lastCommunicationTimestamp=" + lastCommunicationTimestamp +
                '}';
    }
}