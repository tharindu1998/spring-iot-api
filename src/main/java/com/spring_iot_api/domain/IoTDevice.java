package com.spring_iot_api.domain;

import lombok.*;

import java.time.LocalDateTime;

/**
 * IoTDevice model class.
 * Represents an IoT device with attributes for name, type, status, and last communication timestamp.
 */
@AllArgsConstructor
@NoArgsConstructor
public class IoTDevice {
    private Integer id;
    private String name;
    private String type;
    private boolean active;

    private LocalDateTime lastCommunicationTimestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getLastCommunicationTimestamp() {
        return lastCommunicationTimestamp;
    }

    public void setLastCommunicationTimestamp(LocalDateTime lastCommunicationTimestamp) {
        this.lastCommunicationTimestamp = lastCommunicationTimestamp;
    }

    @Override
    public String toString() {
        return "IoTDevice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", active=" + active +
                ", lastCommunicationTimestamp=" + lastCommunicationTimestamp +
                '}';
    }
}