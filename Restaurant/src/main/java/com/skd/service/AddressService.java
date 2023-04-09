package com.skd.service;

import com.skd.criteriabuilder.AddressSearchCriteriaBuilder;
import com.skd.dto.FilterSearchAddressDTO;
import com.skd.entity.Address;
import com.skd.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressSearchCriteriaBuilder addressSearchCriteriaBuilder;
    public Page<Address> searchAndFilterAddress(FilterSearchAddressDTO filterSearchAddressDTO, Pageable pageable) {
        return addressRepository.findAll(addressSearchCriteriaBuilder.buildAddressSpec(filterSearchAddressDTO), pageable);
    }
}
