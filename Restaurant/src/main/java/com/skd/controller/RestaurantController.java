package com.skd.controller;

import com.skd.dto.FilterSearchAddressDTO;
import com.skd.dto.FilterSearchMenuItemDTO;
import com.skd.dto.FilterSearchRestaurantDTO;
import com.skd.dto.RestaurantDTO;
import com.skd.entity.Address;
import com.skd.entity.MenuItem;
import com.skd.entity.Restaurant;
import com.skd.mapper.RestaurantMapper;
import com.skd.repository.RestaurantRepository;
import com.skd.service.AddressService;
import com.skd.service.MenuItemService;
import com.skd.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;
    private final RestaurantMapper restaurantMapper;
    private final AddressService addressService;

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

    @PostMapping("/register")
    public ResponseEntity<?> registerRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        RestaurantDTO registeredRestaurant = restaurantService.register(restaurantDTO);
        return new ResponseEntity<>(registeredRestaurant, HttpStatus.CREATED);
    }

    @GetMapping("/admin/fetchAll")
    public ResponseEntity<?> fetchAllRestaurants(){
        List<RestaurantDTO> restaurantDTOS = restaurantService.fetchAll();
        return new ResponseEntity<>(restaurantDTOS, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> fetchById(@PathVariable Long restaurantId){
        return new ResponseEntity<>(restaurantService.fetchById(restaurantId), HttpStatus.OK);
    }

    @GetMapping("/admin/approve/{restaurantId}")
    public ResponseEntity<?> approveRestaurant(@PathVariable Long restaurantId){
        return new ResponseEntity<>(restaurantService.approveRestaurant(restaurantId), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchAndFilterRestaurant(@RequestBody FilterSearchRestaurantDTO filterSearchRestaurantDTO, @RequestParam("page") int page,
                                                       @RequestParam("pageLength") int pageLength, @RequestParam("sort") String sort){
        Pageable pageable = PageRequest.of(page, pageLength, Sort.by(sort).ascending());
        Page<Restaurant> restaurant= restaurantService.searchAndFilterRestaurant(filterSearchRestaurantDTO, pageable);
        List<Restaurant> restaurants = restaurant.getContent();
        List<RestaurantDTO> restaurantDTOS = restaurants.stream().map(r -> restaurantMapper.toRestaurantDTO(r)).collect(Collectors.toList());
        return new ResponseEntity<>(restaurantDTOS, HttpStatus.OK);
    }
    @PostMapping("/menuitems/search/restaurant")
    public ResponseEntity<?> searchAndFilterRestaurantByMenuItems(@RequestBody FilterSearchMenuItemDTO filterSearchMenuItemDTO, @RequestParam("page") int page,
                                                       @RequestParam("pageLength") int pageLength, @RequestParam("sort") String sort){
        Pageable pageable = PageRequest.of(page, pageLength, Sort.by(sort).ascending());
        Page<MenuItem> menuItem= menuItemService.searchAndFilterMenuItems(filterSearchMenuItemDTO, pageable);
        List<MenuItem> menuItems = menuItem.getContent();

        List<Restaurant> restaurants = menuItems.stream().map(mi -> mi.getRestaurant()).collect(Collectors.toList());
        List<RestaurantDTO> restaurantDTOS = restaurants.stream().map(restaurant -> restaurantMapper.toRestaurantDTO(restaurant)).collect(Collectors.toList());

        return new ResponseEntity<>(restaurantDTOS, HttpStatus.OK);
    }

    @PostMapping("/menuitems/search/menuitem")
    public ResponseEntity<?> searchAndFilterMenuItems(@RequestBody FilterSearchMenuItemDTO filterSearchMenuItemDTO, @RequestParam("page") int page,
                                                                 @RequestParam("pageLength") int pageLength, @RequestParam("sort") String sort){
        Pageable pageable = PageRequest.of(page, pageLength, Sort.by(sort).ascending());
        Page<MenuItem> menuItem= menuItemService.searchAndFilterMenuItems(filterSearchMenuItemDTO, pageable);
        List<MenuItem> menuItems = menuItem.getContent();

        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @PostMapping("/address/search/restaurant")
    public ResponseEntity<?> searchAndFilterRestaurantByAddress(@RequestBody FilterSearchAddressDTO filterSearchAddressDTO, @RequestParam("page") int page,
                                                                @RequestParam("pageLength") int pageLength, @RequestParam("sort") String sort){
        Pageable pageable = PageRequest.of(page, pageLength, Sort.by(sort).ascending());
        Page<Address> address= addressService.searchAndFilterAddress(filterSearchAddressDTO, pageable);
        List<Address> addresses = address.getContent();

        List<Restaurant> restaurants = addresses.stream().map(a -> a.getRestaurant()).collect(Collectors.toList());
        List<RestaurantDTO> restaurantDTOS = restaurants.stream().map(restaurant -> restaurantMapper.toRestaurantDTO(restaurant)).collect(Collectors.toList());

        return new ResponseEntity<>(restaurantDTOS, HttpStatus.OK);
    }

    @PostMapping("/address/search/address")
    public ResponseEntity<?> searchAndFilterAddress(@RequestBody FilterSearchAddressDTO filterSearchAddressDTO, @RequestParam("page") int page,
                                                                @RequestParam("pageLength") int pageLength, @RequestParam("sort") String sort){
        Pageable pageable = PageRequest.of(page, pageLength, Sort.by(sort).ascending());
        Page<Address> address= addressService.searchAndFilterAddress(filterSearchAddressDTO, pageable);
        List<Address> addresses = address.getContent();

        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

}
