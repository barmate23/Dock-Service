package com.SuperAdminManagement.Repository;

import com.SuperAdminManagement.Model.CommonMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonMasterRepository extends JpaRepository<CommonMaster,Integer> {
    CommonMaster findByIsDeletedAndTypeAndMasterValue(boolean b, String qcs, String unassigned);
}
