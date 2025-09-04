package com.SuperAdminManagement.Response;

import com.SuperAdminManagement.Model.Organization;
import com.SuperAdminManagement.Model.Users;
import lombok.Data;

@Data
public class OrgResponse {
    private Organization organization;
    private Users orgUser;
}
