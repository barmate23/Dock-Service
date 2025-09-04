package com.SuperAdminManagement.Repository;

import com.SuperAdminManagement.Model.DockTimeActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DockTimeActivityRepository extends JpaRepository<DockTimeActivity,Integer> {
}
