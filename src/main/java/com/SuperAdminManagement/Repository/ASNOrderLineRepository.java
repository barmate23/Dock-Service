package com.SuperAdminManagement.Repository;

import com.SuperAdminManagement.Model.ASNOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ASNOrderLineRepository extends JpaRepository<ASNOrderLine,Integer> {

    List<ASNOrderLine> findByIsDeletedAndSubOrganizationIdAndAsnHeadIdId(boolean b, Integer subOrgId, Integer asnHeadId);

    ASNOrderLine findByIsDeletedAndSubOrganizationIdAndItemId(boolean b, Integer subOrgId, Integer itemId);

    ASNOrderLine findByIsDeletedAndSubOrganizationIdAndId(boolean b, Integer subOrgId, Integer asnLineId);

    List<ASNOrderLine> findByIsDeletedAndSubOrganizationIdAndAsnHeadIdAsnNumber(boolean b, Integer subOrgId, String asnNumber);

    ASNOrderLine findByIsDeletedAndSubOrganizationIdAndIdAndItemId(boolean b, Integer subOrgId, Integer asnLineId, Integer itemId);
}
