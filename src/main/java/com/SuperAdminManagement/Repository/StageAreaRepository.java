package com.SuperAdminManagement.Repository;

import com.SuperAdminManagement.Model.StagingArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface StageAreaRepository extends JpaRepository<StagingArea,Integer> {
    StagingArea findByIsDeletedAndSubOrganizationIdAndStagingAreaId(boolean b, Integer subOrgId, String stagingAreaId);

    List<StagingArea> findByIsDeletedAndSubOrganizationIdAndDockId(boolean b, Integer subOrgId, Integer dockId);
}
