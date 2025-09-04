package com.SuperAdminManagement.Repository;

import com.SuperAdminManagement.Model.ASNHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ASNHeadRepository extends JpaRepository<ASNHead,Integer> {

    ASNHead findByIsDeletedAndSubOrganizationIdAndCinBarcodeNumber(boolean b, Integer subOrgId, Integer cinId);

    ASNHead findByIsDeletedAndSubOrganizationIdAndAsnNumber(boolean b, Integer subOrgId, String asnNumber);
}
