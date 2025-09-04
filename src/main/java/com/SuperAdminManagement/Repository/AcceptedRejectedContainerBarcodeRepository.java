package com.SuperAdminManagement.Repository;

import com.SuperAdminManagement.Model.AcceptedRejectedContainerBarcode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcceptedRejectedContainerBarcodeRepository extends JpaRepository<AcceptedRejectedContainerBarcode,Integer> {

    List<AcceptedRejectedContainerBarcode> findByIsDeletedAndStatusMasterValueAndSubOrganizationIdAndAcceptedRejectedContainerAsnOrderLineIdAndAcceptedRejectedContainerAsnOrderLineItemId(boolean b, String pick, Integer subOrgId, Integer asnLineId, Integer itemId);

    List<AcceptedRejectedContainerBarcode> findByIsDeletedAndStatusMasterValueAndSubOrganizationIdAndAcceptedRejectedContainerPurchaseOrderLineIdAndAcceptedRejectedContainerPurchaseOrderLineItemId(boolean b, String pick, Integer subOrgId, Integer poLineId, Integer itemId);

    List<AcceptedRejectedContainerBarcode> findByIsDeletedAndSubOrganizationIdAndAcceptedRejectedContainerAsnOrderLineIdAndAcceptedRejectedContainerAsnOrderLineItemId(boolean b, Integer subOrgId, Integer asnLineId, Integer itemId);

    List<AcceptedRejectedContainerBarcode> findByIsDeletedAndSubOrganizationIdAndAcceptedRejectedContainerPurchaseOrderLineIdAndAcceptedRejectedContainerPurchaseOrderLineItemId(boolean b, Integer subOrgId, Integer poLine, Integer itemId);

    List<AcceptedRejectedContainerBarcode> findByIsDeletedAndSubOrganizationIdAndAcceptedRejectedContainerAsnOrderLineItemId(boolean b, Integer subOrgId, Integer itemId);

    List<AcceptedRejectedContainerBarcode> findByIsDeletedAndIsAcceptedAndStatusMasterValueAndSubOrganizationIdAndAcceptedRejectedContainerAsnOrderLineIdAndAcceptedRejectedContainerAsnOrderLineItemId(boolean b, Boolean status, String pick, Integer subOrgId, Integer asnLineId, Integer itemId);

    List<AcceptedRejectedContainerBarcode> findByIsDeletedAndIsAcceptedAndStatusMasterValueAndSubOrganizationIdAndAcceptedRejectedContainerPurchaseOrderLineIdAndAcceptedRejectedContainerPurchaseOrderLineItemId(boolean b, Boolean status, String pick, Integer subOrgId, Integer poLineId, Integer itemId);

    List<AcceptedRejectedContainerBarcode> findByIsDeletedAndSubOrganizationIdAndAcceptedRejectedContainerDockIdAndAcceptedRejectedContainerAsnOrderLineItemId(boolean b, Integer subOrgId, Integer id, Integer id1);

    

    List<AcceptedRejectedContainerBarcode> findByIsDeletedAndSubOrganizationIdAndAcceptedRejectedContainerAsnOrderLineIdAndIsAccepted(boolean b, Integer subOrgId, Integer asnLineId, Boolean isAccepted);


    List<AcceptedRejectedContainerBarcode> findByIsDeletedAndSubOrganizationIdAndAcceptedRejectedContainerPurchaseOrderLineIdAndIsAccepted(boolean b, Integer subOrgId, Integer poLineId, Boolean isAccepted);
}
