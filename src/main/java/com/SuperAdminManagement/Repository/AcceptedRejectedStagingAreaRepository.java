package com.SuperAdminManagement.Repository;

import com.SuperAdminManagement.Model.AcceptedRejectedStagingArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcceptedRejectedStagingAreaRepository extends JpaRepository<AcceptedRejectedStagingArea,Integer> {
    List<AcceptedRejectedStagingArea> findByIsDeletedAndSubOrganizationIdAndStagingAreaStagingAreaId(boolean b, Integer subOrgId, String stagingAreaId);

    List<AcceptedRejectedStagingArea> findByIsDeletedAndSubOrganizationIdAndStagingAreaDockId(boolean b, Integer subOrgId, Integer dockId);

    AcceptedRejectedStagingArea findByIsDeletedAndSubOrganizationIdAndId(boolean b, Integer subOrgId, Integer stagingAreaId);
}
