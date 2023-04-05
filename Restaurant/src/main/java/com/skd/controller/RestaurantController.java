package com.skd.controller;

import com.skd.entity.Address;
import com.skd.entity.MenuItem;
import com.skd.entity.Restaurant;
import com.skd.enums.ItemType;
import com.skd.enums.ItemUnit;
import com.skd.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    @PostConstruct
    public void initialize(){
//        MenuItem menuItem1 = MenuItem.builder()
//                .name("Veg-Kofta")
//                .description("It's a veg item usually served with chapati")
//                .price(BigDecimal.valueOf(140.0))
//                .itemType(ItemType.SOLID_VEG)
//                .itemUnit(ItemUnit.FULL_PLATE)
//                .isAvailable(true)
//                .build();
//
//        MenuItem menuItem2 = MenuItem.builder()
//                .name("Veg Biryani")
//                .description("It's a veg item usually served during lunch or breakfast")
//                .price(BigDecimal.valueOf(200.0))
//                .itemType(ItemType.SOLID_VEG)
//                .itemUnit(ItemUnit.FULL_PLATE)
//                .isAvailable(true)
//                .build();
//
//        MenuItem menuItem3 = MenuItem.builder()
//                .name("Veg-Chowmin")
//                .description("It's a veg item usually served with manchurian")
//                .price(BigDecimal.valueOf(160.0))
//                .itemType(ItemType.SOLID_NON_VEG)
//                .itemUnit(ItemUnit.FULL_PLATE)
//                .isAvailable(true)
//                .build();
//
//        List<MenuItem> menuItems = Arrays.asList(menuItem1,menuItem2,menuItem3);
//
//        Address address1 = Address.builder()
//                .street("Ratu Road Piska more")
//                .landmark("opposite Dwivedi's home")
//                .pincode("834005")
//                .city("Ranchi")
//                .state("Jharkhand")
//                .build();
//
//        Address address2 = Address.builder()
//                .street("Rospa Tower MG Road")
//                .landmark("near Tanishq Jwellers")
//                .pincode("834004")
//                .city("Ranchi")
//                .state("Jharkhand")
//                .build();
//
//        List<Address> addresses = Arrays.asList(address1,address2);
//
//        Restaurant restaurant = Restaurant.builder()
//                .name("Kaveri")
//                .description("Serves Pure Veg food only. Known for delicious taste across Ranchi." +
//                        " Min. order should be worth 300 Rs. for free delivery")
//                .addresses(addresses)
//                .phoneNumber("7042388107")
//                .fssaiNumber(99839944992274L)
//                .isOpen(true)
//                .totalOrdersReceived(0)
//                .menuItems(menuItems)
//                .build();
//
//        menuItems.stream().forEach(menuItem -> menuItem.setRestaurant(restaurant));
//        addresses.stream().forEach(address -> address.setRestaurant(restaurant));
//
//        restaurantRepository.save(restaurant);
    }
}
