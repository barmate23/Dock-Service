package com.SuperAdminManagement.Repository;

import com.SuperAdminManagement.Model.CodeMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeMapperRepository extends JpaRepository<CodeMapper,Integer> {

    CodeMapper findByIsDeletedAndSubOrganizationIdAndCinBarcodeNumber(boolean b, Integer subOrgId, String cinNumber);
}
