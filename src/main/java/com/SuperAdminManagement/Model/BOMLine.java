package com.SuperAdminManagement.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "tbl_BOMLine")
public class BOMLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "OrganizationId")
    private Integer organizationId;

    @Column(name = "SubOrganizationId")
    private Integer subOrganizationId;
    @ManyToOne
    @JoinColumn(name = "BomHeadId")
    private BoMHead bomHead;

    @Column(name = "Level")
    private Integer level;


    @Column(name = "stage")
    private Integer stage;

    @Column(name = "LineNumber")
    private Integer lineNumber;

    @ManyToOne
    @JoinColumn(name = "ItemId", referencedColumnName = "id")
    private Item item;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "UnitOfMeasure", length = 20)
    private String unitOfMeasure;

    @Column(name = "Class", length = 1)
    private String classType;

    @Column(name = "IssueType", length = 20)
    private String issueType;

    @Column(name = "Dependency", length = 50)
    private String dependency;

    @Column(name = "ReferenceDesignators", length = 255)
    private String referenceDesignators;

    @Column(name = "BomNotes", length = 255)
    private String bomNotes;

    @Column(name = "IsActive")
    private Boolean isActive;

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
