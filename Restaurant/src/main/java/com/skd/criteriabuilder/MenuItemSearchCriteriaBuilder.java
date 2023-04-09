package com.skd.criteriabuilder;

import com.skd.dto.FilterSearchMenuItemDTO;
import com.skd.dto.FilterSearchRestaurantDTO;
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
public class MenuItemSearchCriteriaBuilder {

    public Specification<MenuItem> buildMenuItemSpec(FilterSearchMenuItemDTO filterSearchMenuItemDTO){
        log.info("Building Restaurant Specification");
        Specification<MenuItem> menuItemSpecification = where(buildMenuItemSpecifications(filterSearchMenuItemDTO));
        return menuItemSpecification;
    }

    private Specification<MenuItem> buildMenuItemSpecifications(FilterSearchMenuItemDTO filterSearchMenuItemDTO) {
        return new Specification<MenuItem>() {
            @Override
            public Predicate toPredicate(Root<MenuItem> menuItemRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.isDistinct();
                List<Predicate> filterPredicates = handleFilterPredicates(filterSearchMenuItemDTO, criteriaBuilder, menuItemRoot);
                Predicate[] filterPrdicatesArray = filterPredicates.toArray(new Predicate[filterPredicates.size()]);
                Predicate combinedFilterPredicates = criteriaBuilder.and(filterPrdicatesArray);
                return combinedFilterPredicates;
            }
        };
    }

    private List<Predicate> handleFilterPredicates(FilterSearchMenuItemDTO filterSearchMenuItemDTO, CriteriaBuilder criteriaBuilder, Root<MenuItem> menuItemRoot) {
        List<Predicate> predicatesForFilter = new ArrayList<>();
            if (filterSearchMenuItemDTO != null) {
            if (filterSearchMenuItemDTO.getMenuItemId() != null)
                CriteriaUtils.addEqualFilterPredicateEqual(predicatesForFilter, criteriaBuilder, menuItemRoot, "id", filterSearchMenuItemDTO.getMenuItemId());
            if (filterSearchMenuItemDTO.getMenuName() != null)
                CriteriaUtils.addLikeFilterPredicateEqual(predicatesForFilter, criteriaBuilder, menuItemRoot, "name", filterSearchMenuItemDTO.getMenuName());
            if (filterSearchMenuItemDTO.getDescription() != null)
                CriteriaUtils.addLikeFilterPredicateEqual(predicatesForFilter, criteriaBuilder, menuItemRoot, "description", filterSearchMenuItemDTO.getDescription());
            if (filterSearchMenuItemDTO.getPrice() != null)
                CriteriaUtils.addMenuItemPricePredicate(predicatesForFilter, criteriaBuilder, menuItemRoot, "price", filterSearchMenuItemDTO.getPrice());
            if (filterSearchMenuItemDTO.getItemType() != null)
                addMenuItemTypeFilterPredicate(predicatesForFilter, criteriaBuilder, menuItemRoot, "itemType", filterSearchMenuItemDTO.getItemType());
            if (filterSearchMenuItemDTO.getItemUnit() != null)
                addMenuItemUnitFilterPredicate(predicatesForFilter, criteriaBuilder, menuItemRoot, "itemUnit", filterSearchMenuItemDTO.getItemUnit());
            if (filterSearchMenuItemDTO.getIsAvailable() != null)
                CriteriaUtils.addOpenFilterPredicate(predicatesForFilter, criteriaBuilder, menuItemRoot, "isAvailable", filterSearchMenuItemDTO.getIsAvailable());

        }
        return predicatesForFilter;
    }

    private void addRestaurantStateFilterPredicate(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                   Root<Restaurant> restaurantRoot, String state, State[] restaurantStates) {
        if(restaurantStates != null && restaurantStates.length> 0){
            predicatesForFilter.add(restaurantRoot.get("state").in((Object[]) restaurantStates));
        }

    }

    private void addMenuItemTypeFilterPredicate(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                   Root<?> root, String state, ItemType[] itemTypes) {
        if(itemTypes != null && itemTypes.length> 0){
            predicatesForFilter.add(root.get("itemType").in((Object[]) itemTypes));
        }

    }

    private void addMenuItemUnitFilterPredicate(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                Root<?> root, String state, ItemUnit[] itemUnits) {
        if(itemUnits != null && itemUnits.length> 0){
            predicatesForFilter.add(root.get("itemUnit").in((Object[]) itemUnits));
        }

    }


    }












