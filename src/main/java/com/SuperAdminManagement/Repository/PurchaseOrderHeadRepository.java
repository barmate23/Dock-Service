package com.SuperAdminManagement.Repository;

import com.SuperAdminManagement.Model.PurchaseOrderHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderHeadRepository extends JpaRepository<PurchaseOrderHead,Integer> {

    PurchaseOrderHead findByIsDeletedAndSubOrganizationIdAndPurchaseOrderNumber(boolean b, Integer subOrgId, String poNumber);
}
