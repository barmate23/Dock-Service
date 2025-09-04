package com.SuperAdminManagement.Response;

import com.SuperAdminManagement.Model.Item;
import lombok.Data;

import java.util.List;

@Data
public class CRRDockResponse {
    private Integer id;
    private String dockId;
    private String dockName;
    private List<Item> items;
}
