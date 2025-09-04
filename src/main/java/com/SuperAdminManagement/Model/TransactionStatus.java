package com.SuperAdminManagement.Model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "tbl_transactionStatus")
@EntityListeners(AuditingEntityListener.class)
public class TransactionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "id")
    private Location location;

    @Column(name = "status", length = 30)
    private String status;

    @Column(name = "IsDeleted")
    private Boolean isDeleted;

    @CreatedBy
    @Column(name = "CreatedBy")
    private Integer createdBy;

    @CreatedDate
    @Column(name = "CreatedOn")
    private Date createdOn;

    @LastModifiedBy
    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @LastModifiedDate
    @Column(name = "ModifiedOn")
    private Date modifiedOn;
}
