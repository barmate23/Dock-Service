package com.SuperAdminManagement.Response;

import com.SuperAdminManagement.Model.Dock;
import com.SuperAdminManagement.Model.Item;
import lombok.Data;

import java.util.List;

@Data
public class DockByCRResponse {
    private Dock dock;
    private List<ItemDto>items;
}
