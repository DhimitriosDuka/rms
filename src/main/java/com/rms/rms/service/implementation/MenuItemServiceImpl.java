package com.rms.rms.service.implementation;

import com.rms.rms.dto.menu.item.MenuItemCreateDto;
import com.rms.rms.dto.menu.item.MenuItemResponseDto;
import com.rms.rms.dto.menu.item.MenuItemUpdateAmountDto;
import com.rms.rms.dto.menu.item.MenuItemUpdateDto;
import com.rms.rms.entity.MenuItem;
import com.rms.rms.entity.MenuItemIngredient;
import com.rms.rms.entity.embedded.MenuItemIngredientId;
import com.rms.rms.exception.MenuItemException;
import com.rms.rms.filters.MenuItemFilter;
import com.rms.rms.repository.MenuItemIngredientRepository;
import com.rms.rms.repository.MenuItemRepository;
import com.rms.rms.repository.OrderMenuItemRepository;
import com.rms.rms.service.MenuItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Validated
public class MenuItemServiceImpl extends BaseServiceImpl<MenuItemCreateDto, MenuItemUpdateDto, MenuItemResponseDto, MenuItem> implements MenuItemService {

    private final MenuItemIngredientRepository menuItemIngredientRepository;
    private final OrderMenuItemRepository orderMenuItemRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public MenuItemResponseDto update(@NotNull Long id, @Valid MenuItemUpdateDto menuItem) {

        jpaRepository.findById(id)
                .orElseThrow(() -> new MenuItemException("Menu item with id: " + id + " does not exist!"));

        MenuItem entityMenuItem = baseMapper.updateDtoToEntity(menuItem);
        entityMenuItem.setId(id);
        return baseMapper.entityToResponseDto(jpaRepository.save(entityMenuItem));

    }

    @Override
    public MenuItemResponseDto addIngredientToMenuItem(@NotNull Long menuItemId, @Valid MenuItemIngredient menuItemIngredient) {

        MenuItem menuItem = getMenuItem(menuItemId);

        Long ingredientId = menuItemIngredient.getIngredient().getId();
        MenuItemIngredientId menuItemIngredientId = new MenuItemIngredientId(ingredientId, menuItemId);

        Double caloriesOfIngredientInMenuItem = menuItem.getCalories() + calculateCalories(menuItemIngredient);
        menuItem.setCalories(caloriesOfIngredientInMenuItem);
        menuItemIngredient.setMenuItemIngredientId(menuItemIngredientId);
        menuItemIngredient.setMenuItem(menuItem);
        menuItemIngredientRepository.save(menuItemIngredient);
        return baseMapper.entityToResponseDto(jpaRepository.save(menuItem));

    }

    @Override
    public void deleteIngredientFromMenuItem(@NotNull Long menuItemId, @NotNull Long ingredientId) {

        MenuItem menuItem = getMenuItem(menuItemId);
        MenuItemIngredientId menuItemIngredientId = new MenuItemIngredientId(ingredientId, menuItemId);
        MenuItemIngredient menuItemIngredient = getMenuItemIngredient(menuItemId, ingredientId, menuItemIngredientId);

        Double remainingCalories = menuItem.getCalories() - calculateCalories(menuItemIngredient);
        menuItem.setCalories(remainingCalories);
        menuItemIngredientRepository.deleteByMenuItemIngredientId(menuItemIngredientId);

    }

    @Override
    public MenuItemResponseDto updateIngredientAmountOfMenuItem(@NotNull Long menuItemId, @NotNull Long ingredientId, @NotNull MenuItemUpdateAmountDto amount) {

        MenuItemIngredientId menuItemIngredientId = new MenuItemIngredientId(ingredientId, menuItemId);
        MenuItemIngredient menuItemIngredient = getMenuItemIngredient(menuItemId, ingredientId, menuItemIngredientId);
        menuItemIngredient.setAmount(amount.getAmount());
        menuItemIngredientRepository.save(menuItemIngredient);
        return baseMapper.entityToResponseDto(jpaRepository.findById(menuItemId).get());

    }

    @Override
    public List<MenuItemResponseDto> findTopIngredients(Integer n) {
        return orderMenuItemRepository.findTopMenuItems(n)
                .stream()
                .map(id -> baseMapper.entityToResponseDto(jpaRepository.getOne(id)))
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItemResponseDto> findAllByFilter(MenuItemFilter menuItemFilter) {
        return menuItemRepository.findAllByFilter(menuItemFilter)
                .stream()
                .map(baseMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    private MenuItemIngredient getMenuItemIngredient(@NotNull Long menuItemId, @NotNull Long ingredientId, MenuItemIngredientId menuItemIngredientId) {
        return menuItemIngredientRepository
                                        .findByMenuItemIngredientId(menuItemIngredientId)
                                        .orElseThrow(() -> {
                                            throw new MenuItemException("Menu item with id: " + menuItemId + " does not have an ingredient with id: " + ingredientId);
                                        });
    }

    private MenuItem getMenuItem(@NotNull Long menuItemId) {
        return jpaRepository.findById(menuItemId)
                .orElseThrow(() -> new MenuItemException("Menu item with id: " + menuItemId + " does not exist!"));
    }

    private double calculateCalories(MenuItemIngredient menuItemIngredient) {
        return menuItemIngredient.getIngredient().getCalories() * menuItemIngredient.getAmount() / menuItemIngredient.getAmount();
    }

}
