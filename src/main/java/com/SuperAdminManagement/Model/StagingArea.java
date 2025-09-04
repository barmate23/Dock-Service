package com.SuperAdminManagement.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_StagingArea")
@Data
public class StagingArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "OrganizationId")
    private Integer organizationId;

    @Column(name = "SubOrganizationId")
    private Integer subOrganizationId;

    @Column(name = "StagingArea")
    private String stagingArea;

    @Column(name = "stagingAreaId")
    private String stagingAreaId;

    @ManyToOne
    @JoinColumn(name = "DockId")
    private Dock dock;

    @Column(name = "IsDeleted")
    private Boolean isDeleted;


    @Column(name = "CreatedOn")
    private Date createdOn;

    @Column(name = "CreatedBy")
    private Integer createdBy;


    @Column(name = "ModifiedOn")
    private Date modifiedOn;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

}

