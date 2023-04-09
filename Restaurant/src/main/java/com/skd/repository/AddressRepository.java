package com.skd.repository;

import com.skd.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressRepository extends JpaRepository<Address, Long> , JpaSpecificationExecutor<Address> {
}
