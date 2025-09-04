package com.SuperAdminManagement.Repository;

import com.SuperAdminManagement.Model.AcceptedRejectedContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcceptedRejectedContainerRepository extends JpaRepository<AcceptedRejectedContainer,Integer> {
    List<AcceptedRejectedContainer> findByIsDeletedAndSubOrganizationIdAndAsnOrderLineId(boolean b, Integer subOrgId, Integer id);

    List<AcceptedRejectedContainer> findByIsDeletedAndSubOrganizationIdAndPurchaseOrderLineId(boolean b, Integer subOrgId, Integer id);

    List<AcceptedRejectedContainer> findByIsDeletedAndSubOrganizationIdAndDockDockId(boolean b, Integer subOrgId, String dockId);

    List<AcceptedRejectedContainer> findByIsDeletedAndSubOrganizationIdAndStatusMasterValue(boolean b, Integer subOrgId, String crrgenerated);

    List<AcceptedRejectedContainer> findByIsDeletedAndSubOrganizationIdAndDockDockIdAndStatusMasterValue(boolean b, Integer subOrgId, String dockId, String crrgenerated);

    Optional<AcceptedRejectedContainer> findByIsDeletedAndIsAcceptedAndSubOrganizationIdAndAsnOrderLineIdAndAsnOrderLineItemId(boolean b, Boolean isAccepted, Integer subOrgId, Integer asnLineId, Integer itemId);

    List<AcceptedRejectedContainer> findByIsDeletedAndStatusMasterValueAndSubOrganizationIdAndAsnOrderLineId(boolean b, String crrgenerated, Integer subOrgId, Integer id);

    List<AcceptedRejectedContainer> findByIsDeletedAndStatusMasterValueAndSubOrganizationIdAndPurchaseOrderLineId(boolean b, String crrgenerated, Integer subOrgId, Integer id);

    List<AcceptedRejectedContainer> findByIsDeletedAndSubOrganizationIdAndDockIdIsNotNull(boolean b, Integer subOrgId);

    List<AcceptedRejectedContainer> findByIsDeletedAndSubOrganizationIdAndDockId(boolean b, Integer subOrgId, Integer dockId);

    List<AcceptedRejectedContainer> findByIsDeletedAndSubOrganizationIdAndAsnOrderLineAsnHeadIdAsnNumberAndStatusIdIsNull(boolean b, Integer subOrgId, String asnNumber);

    List<AcceptedRejectedContainer> findByIsDeletedAndSubOrganizationIdAndStatusMasterValueAndDockOperatorId(boolean b, Integer subOrgId, String crrgenerated,Integer id);

    List<AcceptedRejectedContainer> findByIsDeletedAndSubOrganizationIdAndStatusMasterValueInAndDockOperatorId(boolean b, Integer subOrgId, List<String> constant, Integer userId);

    List<AcceptedRejectedContainer> findByIsDeletedAndSubOrganizationIdAndStatusMasterValueIn(boolean b, Integer subOrgId, List<String> status);
}
