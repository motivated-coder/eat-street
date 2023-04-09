package com.skd.service;

import com.skd.criteriabuilder.MenuItemSearchCriteriaBuilder;
import com.skd.dto.FilterSearchMenuItemDTO;
import com.skd.dto.FilterSearchRestaurantDTO;
import com.skd.entity.MenuItem;
import com.skd.entity.Restaurant;
import com.skd.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final MenuItemSearchCriteriaBuilder menuItemSearchCriteriaBuilder;

    public Page<MenuItem> searchAndFilterMenuItems(FilterSearchMenuItemDTO filterSearchMenuItemDTO, Pageable pageable) {
        return menuItemRepository.findAll(menuItemSearchCriteriaBuilder.buildMenuItemSpec(filterSearchMenuItemDTO), pageable);
    }
}
