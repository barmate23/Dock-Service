package com.SuperAdminManagement.Repository;

import com.SuperAdminManagement.Model.PurchaseOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderLineRepository extends JpaRepository<PurchaseOrderLine,Integer> {

    List<PurchaseOrderLine> findByIsDeletedAndSubOrganizationIdAndPurchaseOrderHeadId(boolean b, Integer subOrgId, Integer poHeadId);

    PurchaseOrderLine findByIsDeletedAndSubOrganizationIdAndId(boolean b, Integer subOrgId, Integer poLineId);

    List<PurchaseOrderLine> findByIsDeletedAndSubOrganizationIdAndPurchaseOrderHeadPurchaseOrderNumber(boolean b, Integer subOrgId, String poNumber);
}
