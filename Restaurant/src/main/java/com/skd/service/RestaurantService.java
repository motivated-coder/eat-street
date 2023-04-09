package com.skd.service;

import com.skd.criteriabuilder.RestaurantSearchCriteriaBuilder;
import com.skd.dto.FilterSearchRestaurantDTO;
import com.skd.dto.RestaurantDTO;
import com.skd.entity.Restaurant;
import com.skd.enums.State;
import com.skd.mapper.RestaurantMapper;
import com.skd.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final RestaurantSearchCriteriaBuilder restaurantSearchCriteriaBuilder;

    public RestaurantDTO register(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantMapper.toRestaurant(restaurantDTO);
        restaurant.getAddresses().stream().forEach(address -> address.setRestaurant(restaurant));
        restaurant.getMenuItems().stream().forEach(menuItem -> menuItem.setRestaurant(restaurant));
        return restaurantMapper.toRestaurantDTO(restaurantRepository.save(restaurant));
    }

    public List<RestaurantDTO> fetchAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(restaurant -> restaurantMapper.toRestaurantDTO(restaurant)).collect(Collectors.toList());
    }

    public RestaurantDTO fetchById(Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        return restaurantMapper.toRestaurantDTO(restaurant.get());
    }

    @Transactional
    public RestaurantDTO approveRestaurant(Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (Objects.nonNull(restaurant)) {
            restaurant.get().setState(State.APPROVED);
        }
        return restaurantMapper.toRestaurantDTO(restaurant.get());
    }

    public Page<Restaurant> searchAndFilterRestaurant(FilterSearchRestaurantDTO filterSearchRestaurantDTO, Pageable pageable) {
        return restaurantRepository.findAll(restaurantSearchCriteriaBuilder.buildRestaurantSpec(filterSearchRestaurantDTO), pageable);
    }
}
