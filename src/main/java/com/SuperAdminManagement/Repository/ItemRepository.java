package com.SuperAdminManagement.Repository;

import com.SuperAdminManagement.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    List<Item> findByIsDeletedAndSubOrganizationIdAndDockId(boolean b, Integer subOrgId, Integer dockId);
}
