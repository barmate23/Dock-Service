package com.SuperAdminManagement.Repository;

import com.SuperAdminManagement.Model.Dock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DockRepository extends JpaRepository<Dock,Integer> {
    List<Dock> findByIsDeletedAndSubOrganizationId(boolean b, Integer subOrgId);

    List<Dock> findByIsDeletedAndSubOrganizationIdAndDockSupervisorId(boolean b, Integer subOrgId, Integer userId);

    Dock findByIsDeletedAndSubOrganizationIdAndId(boolean b, Integer subOrgId, Integer dockId);
}
