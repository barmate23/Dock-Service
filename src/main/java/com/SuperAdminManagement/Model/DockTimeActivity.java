package com.SuperAdminManagement.Model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "tbl_DockTimeActivity")
@Data
public class DockTimeActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "OrganizationId")
    private Integer organizationId;

    @Column(name = "SubOrganizationId")
    private Integer subOrganizationId;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "dock_id")
    private Dock dockId;

    @ManyToOne
    @JoinColumn(name = "dockSupervisorId")
    private Users dockSupervisor;

    @Column(name = "startTime")
    private Time startTime;

    @Column(name = "endTime")
    private Time endTime;

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
