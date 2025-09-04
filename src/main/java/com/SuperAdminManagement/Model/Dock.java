package com.SuperAdminManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_docks")
@Data
public class Dock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "OrganizationId")
    private Integer organizationId;

    @Column(name = "SubOrganizationId")
    private Integer subOrganizationId;

    @Column(name = "DockId")
    private String dockId;

    @Column(name = "DockName")
    private String dockName;

    @Column(name = "Attribute")
    private String attribute;

    @Transient
    private List<Store> store;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "DockSupervisorId")
    private Users dockSupervisor;
    @JsonIgnore
    @Column(name = "IsOccupied")
    private Boolean isOccupied;
    @JsonIgnore
    @Column(name = "IsDeleted")
    private Boolean isDeleted;

    @JsonIgnore
    @Column(name = "CreatedOn")
    private Date createdOn;
    @JsonIgnore
    @Column(name = "CreatedBy")
    private Integer createdBy;

    @JsonIgnore
    @Column(name = "ModifiedOn")
    private Date modifiedOn;
    @JsonIgnore
    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

}

