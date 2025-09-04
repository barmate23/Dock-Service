package com.SuperAdminManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_Users")
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "UserId")
    private String userId;
    @JsonIgnore
    @Column(name = "Users")
    private String users;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "SubOrganizationId")
    private Organization subOrganization;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ModuleUserLicenceKeyId", referencedColumnName = "Id")
    private ModuleUserLicenceKey moduleUserLicenceKey;

    @JsonIgnore
    @Column(name = "IsDefaultUser")
    private Boolean isDefaultUser;

    @JsonIgnore
    @Column(name = "IsLicenseAssign")
    private Boolean isLicenseAssign;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "reason_id")
    private Reason reason;

    @JsonIgnore
    @Column(name = "defaultUserCode")
    private String defaultUserCode;

    @Column(name = "Department")
    @JsonIgnore
    private String department;

    @JsonIgnore
    @Column(name = "Designation")
    private String designation;

    @Column(name = "UserType")
    @JsonIgnore
    private String userType;

    @JsonIgnore
    @Column(name = "StartDate")
    private Date startDate;

    @JsonIgnore
    @Column(name = "EndDate")
    private Date endDate;

    @JsonIgnore
    @Column(name = "DateOfBirth")
    private Date dateOfBirth;

    @Column(name = "Username")
    private String username;

    @JsonIgnore
    @Column(name = "EmployeeId")
    private String employeeId;


    @Column(name = "FirstName")
    private String firstName;


    @Column(name = "LastName")
    private String lastName;

    @JsonIgnore
    @Column(name = "Password")
    private String password;

    @JsonIgnore
    @Column(name = "FirstPassword")
    private String firstPassword;

    @JsonIgnore
    @Column(name = "SecondPassword")
    private String secondPassword;

    @JsonIgnore
    @Column(name = "ThirdPassword")
    private String thirdPassword;

    @JsonIgnore
    @Column(name = "PwdExpireDate")
    private Long pwdExpireDate;

    @JsonIgnore
    @Column(name = "EmailId")
    private String emailId;

    @JsonIgnore
    @Column(name = "CountryCode")
    private String countryCode;

    @JsonIgnore
    @Column(name = "MobileNo")
    private String mobileNo;

    @JsonIgnore
    @Column(name = "LandLineNumber")
    private String landLineNumber;

    @JsonIgnore
    @Column(name = "Image")
    private String image;

    @JsonIgnore
    @Column(name = "ImagePath")
    private String imagePath;

    @JsonIgnore
    @Column(name = "UserLanguage")
    private String userLanguage;

    @JsonIgnore
    @Column(name = "BiometricID")
    private String biometricId;

    @JsonIgnore
    @Column(name = "IsEmailVerified")
    private Boolean isEmailVerified;

    @JsonIgnore
    @Column(name = "Otp")
    private Integer otp;

    @JsonIgnore
    @Column(name = "OtpExpDate")
    private Long otpExpDate;

    @JsonIgnore
    @Column(name = "IsFirstLogin")
    private Boolean isFirstLogin;

    @JsonIgnore
    @Column(name = "FreeField1")
    private String freeField1;

    @JsonIgnore
    @Column(name = "FreeField2")
    private Integer freeField2;

    @JsonIgnore
    @Column(name = "FreeField3")
    private String freeField3;

    @JsonIgnore
    @Column(name = "FreeField4")
    private Integer freeField4;

    @JsonIgnore
    @Column(name = "IsActive")
    private Boolean isActive;

    @JsonIgnore
    @Column(name = "IsDeleted")
    private Boolean isDeleted;

    @JsonIgnore
    @Column(name = "CreatedBy")
    private Integer createdBy;

    @JsonIgnore
    @Column(name = "CreatedOn")
    private Date createdOn;

    @JsonIgnore
    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @JsonIgnore
    @Column(name = "ModifiedOn")
    private Date modifiedOn;

    @JsonIgnore
    @Transient
    private List<ModuleUserLicenceKey> additionalUserDeviceLicense;

}