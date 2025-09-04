package com.SuperAdminManagement.Request;

import lombok.Data;

import javax.persistence.Column;
import java.sql.Time;

@Data
public class DockTimeActivityRequest {
    private Integer dockId;
    private String status;
    private Time startTime;
    private Time endTime;
}
