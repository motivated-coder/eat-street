package com.skd.criteriabuilder;

import com.skd.dto.FilterSearchRestaurantDTO;
import com.skd.entity.Address;
import com.skd.entity.Restaurant;
import com.skd.enums.State;
import com.skd.util.CriteriaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
@Slf4j
@RequiredArgsConstructor
public class RestaurantSearchCriteriaBuilder {

    @PersistenceContext
    EntityManager entityManager;

    public List<Restaurant> findByCriteria(FilterSearchRestaurantDTO filterSearchRestaurantDTO) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurant> query = builder.createQuery(Restaurant.class);
        Root<Restaurant> restaurantRoot = query.from(Restaurant.class);
        Join<Restaurant, Address> restaurantAddressJoin = restaurantRoot.join("addresses", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        if (filterSearchRestaurantDTO.getRestaurantId() != null) {
            predicates.add(builder.equal(restaurantRoot.get("id"), filterSearchRestaurantDTO.getRestaurantId()));
        }

        query.where(predicates.toArray(new Predicate[0])).distinct(true);
        return entityManager.createQuery(query).getResultList();
    }

    public Specification<Restaurant> buildRestaurantSpec(FilterSearchRestaurantDTO filterSearchRestaurantDTO){
        log.info("Building Restaurant Specification");
        Specification<Restaurant> restaurantSpecification = where(buildRestaurantSpecifications(filterSearchRestaurantDTO));
        return restaurantSpecification;
    }

//    private Specification<MenuItem> buildAddressSpecification(FilterSearchDTO filterSearchDTO, Specification<Restaurant> restaurantSpecification) {
//        return null;
//    }

    private Specification<Restaurant> buildRestaurantSpecifications(FilterSearchRestaurantDTO filterSearchRestaurantDTO) {
        return new Specification<Restaurant>() {
            @Override
            public Predicate toPredicate(Root<Restaurant> restaurantRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> filterPredicates = handleFilterPredicates(filterSearchRestaurantDTO, criteriaBuilder, restaurantRoot);
                Predicate[] filterPrdicatesArray = filterPredicates.toArray(new Predicate[filterPredicates.size()]);
                Predicate combinedFilterPredicates = criteriaBuilder.and(filterPrdicatesArray);
                return combinedFilterPredicates;
            }
        };
    }

    private List<Predicate> handleFilterPredicates(FilterSearchRestaurantDTO filterSearchRestaurantDTO, CriteriaBuilder criteriaBuilder, Root<Restaurant> restaurantRoot) {
        List<Predicate> predicatesForFilter = new ArrayList<>();
            if (filterSearchRestaurantDTO != null) {
            if (filterSearchRestaurantDTO.getRestaurantId() != null)
                CriteriaUtils.addEqualFilterPredicateEqual(predicatesForFilter, criteriaBuilder, restaurantRoot, "id", filterSearchRestaurantDTO.getRestaurantId());
            if (filterSearchRestaurantDTO.getRestaurantName() != null)
                CriteriaUtils.addLikeFilterPredicateEqual(predicatesForFilter, criteriaBuilder, restaurantRoot, "name", filterSearchRestaurantDTO.getRestaurantName());
            if (filterSearchRestaurantDTO.getPhoneNumber() != null)
                CriteriaUtils.addLikeFilterPredicateEqual(predicatesForFilter, criteriaBuilder, restaurantRoot, "phoneNumber", filterSearchRestaurantDTO.getPhoneNumber());
            if (filterSearchRestaurantDTO.getFssaiNumber() != null)
                CriteriaUtils.addEqualFilterPredicateEqual(predicatesForFilter, criteriaBuilder, restaurantRoot, "fssaiNumber", filterSearchRestaurantDTO.getFssaiNumber());
            if (filterSearchRestaurantDTO.getTotalOrdersReceived() != null)
                CriteriaUtils.addTotalNoOfOrdersReceivedPredicate(predicatesForFilter, criteriaBuilder, restaurantRoot, "totalOrdersReceived", filterSearchRestaurantDTO.getTotalOrdersReceived());
            if (filterSearchRestaurantDTO.getRestaurantState() != null)
                addRestaurantStateFilterPredicate(predicatesForFilter, criteriaBuilder, restaurantRoot, "state", filterSearchRestaurantDTO.getRestaurantState());
            if (filterSearchRestaurantDTO.getIsOpen() != null)
                CriteriaUtils.addOpenFilterPredicate(predicatesForFilter, criteriaBuilder, restaurantRoot, "isOpen", filterSearchRestaurantDTO.getIsOpen());

        }
        return predicatesForFilter;
    }

    private void addRestaurantStateFilterPredicate(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                   Root<Restaurant> restaurantRoot, String state, State[] restaurantStates) {
        if(restaurantStates != null && restaurantStates.length> 0){
            predicatesForFilter.add(restaurantRoot.get("state").in((Object[]) restaurantStates));
        }

    }

    private void addPincodeFilterPredicate(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                           Root<Address> addressRoot, String pincode) {
        if (Objects.nonNull(pincode)) {
            predicatesForFilter.add(criteriaBuilder.equal(addressRoot.get("pincode"), pincode));

        }
    }
    }












