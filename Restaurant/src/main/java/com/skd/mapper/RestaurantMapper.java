package com.skd.mapper;

import com.skd.dto.AddressDTO;
import com.skd.dto.MenuItemDTO;
import com.skd.dto.RestaurantDTO;
import com.skd.entity.Address;
import com.skd.entity.MenuItem;
import com.skd.entity.Restaurant;
import com.skd.enums.ItemType;
import com.skd.enums.ItemUnit;
import com.skd.enums.State;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {
    public Restaurant toRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDTO.getName())
                .description(restaurantDTO.getDescription())

                .phoneNumber(restaurantDTO.getPhoneNumber())
                .isOpen(restaurantDTO.isOpen())
                .fssaiNumber(restaurantDTO.getFssaiNumber())
                .state(State.IN_QUEUE)
                .addresses(toAddress(restaurantDTO.getAddresses()))
                .menuItems(toMenuItem(restaurantDTO.getMenuItems()))
                .servingPincodes(restaurantDTO.getServingPincodes())
                .build();
        return restaurant;
    }

    public List<MenuItem> toMenuItem(List<MenuItemDTO> menuItems) {
        return menuItems.stream().map(menuItemDTO -> MenuItem.builder()
                        .name(menuItemDTO.getName())
                        .description(menuItemDTO.getDescription())
                        .price(menuItemDTO.getPrice())
                        .itemType(ItemType.valueOf(menuItemDTO.getItemType()))
                        .itemUnit(ItemUnit.valueOf(menuItemDTO.getItemUnit()))
                        .isAvailable(menuItemDTO.isAvailable())
                        .build())
                .collect(Collectors.toList());

    }

    public List<Address> toAddress(List<AddressDTO> addresses) {
        return addresses.stream().map(addressDTO -> Address.builder()
                        .street(addressDTO.getStreet())
                        .pincode(addressDTO.getPincode())
                        .city(addressDTO.getCity())
                        .state(addressDTO.getState())
                        .landmark(addressDTO.getLandmark())
                        .build())
                .collect(Collectors.toList());
    }

    public RestaurantDTO toRestaurantDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = RestaurantDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .phoneNumber(restaurant.getPhoneNumber())
                .isOpen(restaurant.getIsOpen())
                .fssaiNumber(restaurant.getFssaiNumber())
                .state(restaurant.getState().name())
                .addresses(toAddressDTO(restaurant.getAddresses()))
                .menuItems(toMenuItemDTO(restaurant.getMenuItems()))
                .servingPincodes(restaurant.getServingPincodes())
                .build();
        return restaurantDTO;
    }

    public List<MenuItemDTO> toMenuItemDTO(List<MenuItem> menuItems) {
        return menuItems.stream().map(menuItem -> MenuItemDTO.builder()
                        .id(menuItem.getId())
                        .name(menuItem.getName())
                        .description(menuItem.getDescription())
                        .price(menuItem.getPrice())
                        .itemType(menuItem.getItemType().name())
                        .itemUnit(menuItem.getItemUnit().name())
                        .isAvailable(menuItem.isAvailable())
                        .build())
                .collect(Collectors.toList());
    }

    public List<AddressDTO> toAddressDTO(List<Address> addresses) {
        return addresses.stream().map(address -> AddressDTO.builder()
                        .id(address.getId())
                        .street(address.getStreet())
                        .pincode(address.getPincode())
                        .city(address.getCity())
                        .state(address.getState())
                        .landmark(address.getLandmark())
                        .build())
                .collect(Collectors.toList());
    }
}
