package com.rms.rms.controller;

import com.rms.rms.dto.order.*;
import com.rms.rms.entity.OrderMenuItem;
import com.rms.rms.filters.OrderFilter;
import com.rms.rms.service.OrderService;
import com.rms.rms.utils.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Path.ORDER_PATH)
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @PostMapping
    public ResponseEntity<OrderResponseDto> save(@Valid @RequestBody OrderCreateDto order) {
        return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    @PostMapping(Path.ALL)
    public ResponseEntity<List<OrderResponseDto>> findAll(@Valid @RequestBody(required = false) OrderFilter orderFilter) {
        return new ResponseEntity<>(orderService.findAllByFilter(orderFilter), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DELIVERY', 'ROLE_OPERATOR')")
    @GetMapping(Path.ID)
    public ResponseEntity<OrderResponseDto> findById(@PathVariable @Min(0) Long id) {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @PostMapping(Path.CANCEL_ORDER_PATH)
    public ResponseEntity<OrderResponseDto> cancelOrder(@PathVariable @Min(0) Long id) {
        return new ResponseEntity<>(orderService.cancel(id), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DELIVERY')")
    @PutMapping(Path.UPDATE_STATUS_ORDER_PATH)
    public ResponseEntity<OrderResponseDto> updateStatus(@PathVariable @Min(0) Long id, @Valid @RequestBody UpdateStatusDto status) {
        return new ResponseEntity<>(orderService.updateStatus(id, status), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @PostMapping(Path.ADD_MENU_ITEM_TO_ORDER)
    public ResponseEntity<OrderResponseDto> addMenuItemToOrder(@PathVariable @Min(0) Long id, @Valid @RequestBody OrderMenuItem order) {
        return new ResponseEntity<>(orderService.addMenuItemToOrder(id, order), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @DeleteMapping(Path.DELETE_MENU_ITEM_FROM_ORDER)
    public void deleteMenuItemFromOrder(@PathVariable @Min(0) Long menuItemId, @PathVariable @Min(0) Long orderId) {
        orderService.deleteMenuItemFromOrder(orderId, menuItemId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(Path.REPORT)
    public ResponseEntity<List<OrderReportDto>> getReportOfOrders() {
        return new ResponseEntity<>(orderService.getReportOfOrders(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(Path.FOOD_GROUP_REPORT)
    public ResponseEntity<List<FoodGroupReportDto>> getReportOfFoodCategory() {
        return new ResponseEntity<>(orderService.getReportOfFoodCategory(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @GetMapping(Path.ALL_ORDERS_REPORT)
    public ResponseEntity<List<OrderResponseDto>> findAllOrdersOfCostumer() {
        return new ResponseEntity<>(orderService.findAllOrdersOfUser(), HttpStatus.OK);
    }

}
