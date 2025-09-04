package com.SuperAdminManagement.Request;

import com.SuperAdminManagement.Model.ASNOrderLine;
import com.SuperAdminManagement.Model.PurchaseOrderLine;
import com.SuperAdminManagement.Model.Reason;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class AcceptedRejectedContainerRequest {
    private Integer itemId;
    private Integer asnLineId;
    private Integer poLineId;
    private Integer reasonId;
    private Boolean isAccepted;
    private Integer acceptedRejectedContainerQuantity;
}
