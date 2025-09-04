package com.SuperAdminManagement.Response;

import lombok.Data;

@Data
public class ItemDto {
    private Integer id;
    private String itemName;
    private Integer itemQty;
    private String  itemCode;
    private Integer containerQty;
    private Integer asnLineId;
    private Integer poLineId;
    private Integer acceptedContainerQty;
    private Integer rejectedContainerQty;
}
