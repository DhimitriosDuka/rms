package com.rms.rms.service.implementation;

import com.rms.rms.dto.menu.item.MenuItemCreateDto;
import com.rms.rms.dto.menu.item.MenuItemResponseDto;
import com.rms.rms.dto.menu.item.MenuItemUpdateAmountDto;
import com.rms.rms.dto.menu.item.MenuItemUpdateDto;
import com.rms.rms.entity.MenuItem;
import com.rms.rms.entity.MenuItemIngredient;
import com.rms.rms.entity.embedded.MenuItemIngredientId;
import com.rms.rms.exception.MenuItemException;
import com.rms.rms.mapper.MenuItemMapper;
import com.rms.rms.repository.MenuItemIngredientRepository;
import com.rms.rms.repository.MenuItemRepository;
import com.rms.rms.service.MenuItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;
    private final MenuItemIngredientRepository menuItemIngredientRepository;

    @Override
    public MenuItemResponseDto save(@Valid MenuItemCreateDto menuItem) {
        menuItemRepository.findByName(menuItem.getName())
                .ifPresent(value -> {
                        throw new MenuItemException("Menu item with name: " + menuItem.getName() + " already exists!");
                });
        MenuItem entityMenuItem = menuItemMapper.createDtoToEntity(menuItem);
        entityMenuItem.setAvailable(Boolean.TRUE);
        entityMenuItem.setCalories(0.0);
        return menuItemMapper.entityToResponseDto(menuItemRepository.save(entityMenuItem));
    }

    @Override
    public List<MenuItemResponseDto> findAllAvailable() {
        return menuItemRepository.findAllByAvailableTrue()
                .stream()
                .map(menuItemMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public MenuItemResponseDto findById(@NotNull Long id) {
        return Optional.of(menuItemRepository.findById(id))
                .get()
                .map(menuItemMapper::entityToResponseDto)
                .orElseThrow(() -> new MenuItemException("Menu item with id: " + id + " does not exists!"));
    }

    @Override
    public MenuItemResponseDto update(@NotNull Long id, @Valid MenuItemUpdateDto menuItem) {

        menuItemRepository.findById(id)
                .orElseThrow(() -> new MenuItemException("Menu item with id: " + id + " does not exist!"));

        menuItemRepository.findByName(menuItem.getName())
                .ifPresent(item -> {
                    if(!item.getId().equals(id)) {
                        throw new MenuItemException("Menu item with name: " + menuItem.getName() + " already exists!");
                    }
                });

        MenuItem entityMenuItem = menuItemMapper.updateDtoToEntity(menuItem);
        entityMenuItem.setId(id);
        return menuItemMapper.entityToResponseDto(menuItemRepository.save(entityMenuItem));

    }

    @Override
    public MenuItemResponseDto addIngredientToMenuItem(@NotNull Long menuItemId, @Valid MenuItemIngredient menuItemIngredient) {

        MenuItem menuItem = getMenuItem(menuItemId);

        Long ingredientId = menuItemIngredient.getIngredient().getId();
        MenuItemIngredientId menuItemIngredientId = new MenuItemIngredientId(ingredientId, menuItemId);
        menuItemIngredientRepository
                                    .findByMenuItemIngredientId(menuItemIngredientId)
                                    .ifPresent(entity -> {
                                        throw new MenuItemException("Menu item with id: " + menuItemId + " already has an ingredient with id: " + ingredientId);
                                    });

        menuItemIngredient.setMenuItemIngredientId(menuItemIngredientId);
        menuItemIngredient.setMenuItem(menuItem);
        menuItemIngredientRepository.save(menuItemIngredient);
        return menuItemMapper.entityToResponseDto(menuItemRepository.findById(menuItemId).get());

    }

    @Override
    public void deleteIngredientFromMenuItem(@NotNull Long menuItemId, @NotNull Long ingredientId) {

        MenuItem menuItem = getMenuItem(menuItemId);
        MenuItemIngredientId menuItemIngredientId = new MenuItemIngredientId(ingredientId, menuItemId);
        getMenuItemIngredient(menuItemId, ingredientId, menuItemIngredientId);
        menuItemIngredientRepository.deleteByMenuItemIngredientId(menuItemIngredientId);

    }

    @Override
    public MenuItemResponseDto updateIngredientAmountOfMenuItem(@NotNull Long menuItemId, @NotNull Long ingredientId, @NotNull MenuItemUpdateAmountDto amount) {

        MenuItemIngredientId menuItemIngredientId = new MenuItemIngredientId(ingredientId, menuItemId);
        MenuItemIngredient menuItemIngredient = getMenuItemIngredient(menuItemId, ingredientId, menuItemIngredientId);
        menuItemIngredient.setAmount(amount.getAmount());
        menuItemIngredientRepository.save(menuItemIngredient);
        return menuItemMapper.entityToResponseDto(menuItemRepository.findById(menuItemId).get());

    }

    private MenuItemIngredient getMenuItemIngredient(@NotNull Long menuItemId, @NotNull Long ingredientId, MenuItemIngredientId menuItemIngredientId) {
        return menuItemIngredientRepository
                                        .findByMenuItemIngredientId(menuItemIngredientId)
                                        .orElseThrow(() -> {
                                            throw new MenuItemException("Menu item with id: " + menuItemId + " does not have an ingredient with id: " + ingredientId);
                                        });
    }

    private MenuItem getMenuItem(@NotNull Long menuItemId) {
        return menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new MenuItemException("Menu item with id: " + menuItemId + " does not exist!"));
    }

}
