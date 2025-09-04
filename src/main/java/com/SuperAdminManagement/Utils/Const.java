package com.SuperAdminManagement.Utils;

public class Const {
    public static final int OTP_LENGTH = 6;
    public static final long OTP_EXPIRY_DURATION = 5 * 60 * 1000; // 5 minutes in milliseconds

    public static final long PWD_EXPIRY_DURATION = 90*24*60 * 60 * 1000;

    public static final String SUPER_ADMIN="Super Admin";
    public static final String EMAIL_REGEX = "^[a-z0-9+_.-]+@(.+)$";

    public static final String GMAIL_REGEX = "^[a-z0-9+_.-]+@gmail.com$";
    public static final String YAHOO_REGEX = "^[a-z0-9+_.-]+@yahoo.com$";
    public static final String OUTLOOK_REGEX ="^[a-z0-9+_.-]+@outlook.com$";
    public static final String PAN_REGEX = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
    public static final String CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

    public static final Integer STATUS_200=200;
    public static final Integer STATUS_500=500;
    public static final Integer CODE_1=1;
    public static final Integer CODE_0=0;
    public static final String ADMIN="ADMN";
    public static final String GREEN="GREEN";
    public static final String RED="RED";
    public static final String YELLOW="YELLOW";

    public static final String CRITICAL="CRITICAL";

    public static final String NONCRITICAL="NON-CRITICAL";

    public static final String CREATED = "CREATED";
    public static final String EXECUTE= "EXECUTE";
    public static final String CONFIRM= "CONFIRM";
    public static final String POST= "POST";
    public static final String HOLD= "HOLD";
    public static final String CANCEL= "CANCEL";

    public static final String PRODUCTIONPALNORORDER="Production Plan or Orders";
    public static final String NOSHORTAGEPLANORORDER="No Shortage Plan or Orders";
    public static final String SHORTAGEALLPLANSORORDER="Shortage All Plans or Orders";
    public static final String PLANLISTFETCHSUCCESSFULLY="PPE Plan List Fetch Successfully";
    public static final String ASSIGNPLANSUCCESSFULLY="Assign Plan Successfully";
    public static final String PLANSTATUSUPDATEDSUCCESSFULLY="Plan Status Updated Successfully";
    public static final String GETALLFILTERSUCCESSFULLY="GET ALL FILTERS SUCCESSFULLY";
    public static final String PPEDATAFETCHSUCCESSFULLY="PPE DATA FETCH SUCCESSFULLY!!!";
    public static final String PAGENOTFOUND=" PPE NOT FOUND!!!";
    public static final String CANNOTPOSTPLANMESSAGE="You cannot post the plan which is having Critical Shortage Item";

    public static final String DOCKSFETCHSUCCESSFULLY="All Docks fetch successfully..!";
    public static final String DOCKSFETCHFAILED ="Docks data is empty..!";
    public static final String ASNDATAFETCHSUCCESSFULLY ="All ASN Data Fetch Successfully..!";
    public static final String ASNDATAEMPTY ="ASN data is empty..!";
    public static final String ASNLINEFETCHSUCCESSFULLY ="All Asn Line Fetch Successfully..!";
    public static final String ASNLINEDATAEMPTY ="ASN Line data is empty..!";
    public static final String DQCS="DQCS";
    public static final String UNASSIGNED="Unassigned";
    public static final String CRRGENERATED="CRRGENERATED";


    public static final String ARCB = "ARCB";
    public static final String CREATE_CRR="CRR";
    public static final String CREATE = "CREATE";
    public static final String PICK = "PICK";
    public static final String PUT = "PUT";
    public static final String STAGINGPUT = "STAGINGPUT";
    public static final String DOCKPICKED = "DOCKPICKED";
    public static final String ASN = "ASN";
}
