package com.SuperAdminManagement.Response;

import com.SuperAdminManagement.Model.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
public class DockResponse {
    private String dockId;
    private String dockName;
    private String instrument;
    private String AsnNumber;
    private String PoNumber;
    private Date   dueDate;
    private LocalTime time;
    private String buyer;
    private String buyerMob;
    private String landline;
    private String alternativeMobile;
    private String supplier;
    private String supplierId;
    private String supplierName;
    private String location;
   private List<ItemDto>items;
}
