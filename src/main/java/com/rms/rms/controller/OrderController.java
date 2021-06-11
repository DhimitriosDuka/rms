package com.rms.rms.controller;

import com.rms.rms.dto.order.OrderCreateDto;
import com.rms.rms.dto.order.OrderResponseDto;
import com.rms.rms.dto.order.UpdateStatusDto;
import com.rms.rms.entity.OrderMenuItem;
import com.rms.rms.filters.OrderFilter;
import com.rms.rms.service.OrderService;
import com.rms.rms.utils.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Path.ORDER_PATH)
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @PostMapping
    public ResponseEntity<OrderResponseDto> save(@RequestBody OrderCreateDto order) {
        return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<OrderResponseDto>> findAll(@RequestBody(required = false) OrderFilter orderFilter) {
        return new ResponseEntity<>(orderService.findAllByFilter(orderFilter), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @PutMapping(Path.CANCEL_ORDER_PATH)
    public ResponseEntity<OrderResponseDto> cancelOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.cancel(id), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DELIVERY')")
    @PutMapping(Path.UPDATE_STATUS_ORDER_PATH)
    public ResponseEntity<OrderResponseDto> updateStatus(@PathVariable Long id, @RequestBody UpdateStatusDto status) {
        return new ResponseEntity<>(orderService.updateStatus(id, status), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @PostMapping(Path.ADD_MENU_ITEM_TO_ORDER)
    public ResponseEntity<OrderResponseDto> addMenuItemToOrder(@PathVariable Long id, @RequestBody OrderMenuItem order) {
        return new ResponseEntity<>(orderService.addMenuItemToOrder(id, order), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @DeleteMapping(Path.DELETE_MENU_ITEM_FROM_ORDER)
    public void deleteMenuItemFromOrder(@PathVariable Long menuItemId, @PathVariable Long orderId) {
        orderService.deleteMenuItemFromOrder(orderId, menuItemId);
    }

}
