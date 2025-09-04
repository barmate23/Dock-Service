package com.SuperAdminManagement.Response;

import com.SuperAdminManagement.Model.LicenseLine;
import com.SuperAdminManagement.Model.ModuleUserLicenceKey;
import lombok.Data;

import java.util.List;
@Data
public class UserLicenceKeysRes {
    private LicenseLine licenseLine;
    private List<ModuleUserLicenceKey> userLicenceKeys;
}
