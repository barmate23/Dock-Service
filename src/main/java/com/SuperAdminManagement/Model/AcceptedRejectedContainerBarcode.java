package com.SuperAdminManagement.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "tbl_AcceptedRejectedContainerBarcode")
public class AcceptedRejectedContainerBarcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "OrganizationId")
    private Integer organizationId;

    @Column(name = "SubOrganizationId")
    private Integer subOrganizationId;

    @ManyToOne
    @JoinColumn(name = "acceptedRejectedContainerId", referencedColumnName = "id")
    private AcceptedRejectedContainer acceptedRejectedContainer;


    @ManyToOne
    @JoinColumn(name = "accepted_rejected_staging_area_id")
    private AcceptedRejectedStagingArea acceptedRejectedStagingArea;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private CommonMaster status;

    @Column(name = "crrContainerCode")
    private String crrContainerCode;

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
