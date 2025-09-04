package com.SuperAdminManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Data
@Table(name = "tbl_AcceptedRejectedContainer")
public class AcceptedRejectedContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "OrganizationId")
    private Integer organizationId;

    @Column(name = "SubOrganizationId")
    private Integer subOrganizationId;

    @ManyToOne
    @JoinColumn(name = "reason_id")
    private Reason reason;

    @ManyToOne
    @JoinColumn(name = "purchaseOrderLineId")
    private PurchaseOrderLine purchaseOrderLine;

    @ManyToOne
    @JoinColumn(name = "AsnOrderLineId")
    private ASNOrderLine asnOrderLine;

    @Column(name = "acceptedRejectedContainerQuantity")
    private Integer acceptedRejectedContainerQuantity;

    @Column(name = "qnNumber")
    private Integer qnNumber;

    @Column(name = "qcDate")
    private Date qcDate;

    @ManyToOne
    @JoinColumn(name = "qcEngineer")
    private Users qcEngineer;

    @Column(name = "startTime")
    private Time startTime;

    @Column(name = "endTime")
    private Time endTime;

    @ManyToOne
    @JoinColumn(name = "dock_id")
    private Dock dock;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dock_operator_id")
    private Users dockOperator;
    
    @ManyToOne
    @JoinColumn(name = "status_id")
    private CommonMaster status;

    @Column(name = "isAccepted")
    private Boolean isAccepted;

    @Column(name = "IsDeleted")
    private Boolean isDeleted;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "CreatedOn")
    private Date createdOn;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @Column(name = "ModifiedOn")
    private Date modifiedOn;
}
