package com.skd.criteriabuilder;

import com.skd.dto.FilterSearchAddressDTO;
import com.skd.dto.FilterSearchMenuItemDTO;
import com.skd.entity.Address;
import com.skd.entity.MenuItem;
import com.skd.entity.Restaurant;
import com.skd.enums.ItemType;
import com.skd.enums.ItemUnit;
import com.skd.enums.State;
import com.skd.util.CriteriaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
@Slf4j
@RequiredArgsConstructor
public class AddressSearchCriteriaBuilder {

    public Specification<Address> buildAddressSpec(FilterSearchAddressDTO filterSearchAddressDTO){
        log.info("Building Restaurant Specification");
        Specification<Address> addressSpecification = where(buildAddressSpecifications(filterSearchAddressDTO));
        return addressSpecification;
    }

    private Specification<Address> buildAddressSpecifications(FilterSearchAddressDTO filterSearchAddressDTO) {
        return new Specification<Address>() {
            @Override
            public Predicate toPredicate(Root<Address> addressRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.isDistinct();
                List<Predicate> filterPredicates = handleFilterPredicates(filterSearchAddressDTO, criteriaBuilder, addressRoot);
                Predicate[] filterPrdicatesArray = filterPredicates.toArray(new Predicate[filterPredicates.size()]);
                Predicate combinedFilterPredicates = criteriaBuilder.and(filterPrdicatesArray);
                return combinedFilterPredicates;
            }
        };
    }

    private List<Predicate> handleFilterPredicates(FilterSearchAddressDTO filterSearchAddressDTO, CriteriaBuilder criteriaBuilder, Root<Address> addressRoot) {
        List<Predicate> predicatesForFilter = new ArrayList<>();
            if (filterSearchAddressDTO != null) {
            if (filterSearchAddressDTO.getId() != null)
                CriteriaUtils.addEqualFilterPredicateEqual(predicatesForFilter, criteriaBuilder, addressRoot, "id", filterSearchAddressDTO.getId());
            if (filterSearchAddressDTO.getStreet() != null)
                CriteriaUtils.addLikeFilterPredicateEqual(predicatesForFilter, criteriaBuilder, addressRoot, "street", filterSearchAddressDTO.getStreet());
            if (filterSearchAddressDTO.getPincode() != null)
                CriteriaUtils.addLikeFilterPredicateEqual(predicatesForFilter, criteriaBuilder, addressRoot, "pincode", filterSearchAddressDTO.getPincode());
            if (filterSearchAddressDTO.getCity() != null)
                CriteriaUtils.addLikeFilterPredicateEqual(predicatesForFilter, criteriaBuilder, addressRoot, "city", filterSearchAddressDTO.getCity());
            if (filterSearchAddressDTO.getState() != null)
                CriteriaUtils.addLikeFilterPredicateEqual(predicatesForFilter, criteriaBuilder, addressRoot, "state", filterSearchAddressDTO.getState());
            if (filterSearchAddressDTO.getLandmark() != null)
                CriteriaUtils.addLikeFilterPredicateEqual(predicatesForFilter, criteriaBuilder, addressRoot, "landmark", filterSearchAddressDTO.getLandmark());

        }
        return predicatesForFilter;
    }

    }