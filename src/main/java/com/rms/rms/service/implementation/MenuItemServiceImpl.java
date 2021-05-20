package com.rms.rms.service.implementation;

import com.rms.rms.dto.menu.item.MenuItemCreateDto;
import com.rms.rms.dto.menu.item.MenuItemResponseDto;
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
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
        return menuItemMapper.entityToResponseDto(menuItemRepository.save(menuItemMapper.createDtoToEntity(menuItem)));
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
        existsByNameWithDifferentId(id, menuItem.getName());
        MenuItem menuItemEntity = menuItemMapper.updateDtoToEntity(menuItem);
        menuItemEntity.setId(id);
        return menuItemMapper.entityToResponseDto(menuItemRepository.save(menuItemEntity));
    }

    @Override
    public MenuItemResponseDto addIngredientToMenuItem(@NotNull Long menuItemId, @Valid MenuItemIngredient menuItemIngredient) {
        MenuItem menuItemWithId = (MenuItem) validateParametersAndGetObjectBasedOnString(menuItemId, menuItemIngredient.getIngredient().getId(), "MenuItem");
        menuItemIngredient.setMenuItemIngredientId(new MenuItemIngredientId(menuItemId, menuItemIngredient.getIngredient().getId()));
        menuItemIngredient.setMenuItem(menuItemWithId);
        menuItemIngredientRepository.save(menuItemIngredient);
        return menuItemMapper.entityToResponseDto(menuItemRepository.findById(menuItemId).get());
    }

    @Override
    public void deleteIngredientFromMenuItem(@NotNull Long menuItemId, @NotNull Long ingredientId) {
        validateParametersAndGetObjectBasedOnString(menuItemId, ingredientId, "");
        menuItemIngredientRepository.deleteByMenuItemIngredientId(new MenuItemIngredientId(ingredientId, menuItemId));
    }

    @Override
    public MenuItemResponseDto updateIngredientAmountOfMenuItem(@NotNull Long menuItemId, @NotNull Long ingredientId, @NotNull Integer amount) {
        MenuItemIngredient exist = (MenuItemIngredient) validateParametersAndGetObjectBasedOnString(menuItemId, ingredientId, "MenuItemIngredient");
        exist.setAmount(amount);
        menuItemIngredientRepository.save(exist);
        return null;
    }

    private Object validateParametersAndGetObjectBasedOnString(Long menuItemId, Long ingredientId, String returnObject) {
        Optional<MenuItem> menuItemWithId = menuItemRepository.findById(menuItemId);
        if(menuItemWithId.isEmpty()) {
            throw new MenuItemException("Menu item with id: " + menuItemId + " does not exists!");
        }
        Optional<MenuItemIngredient> exist = menuItemIngredientRepository
                .findByMenuItemIngredientIdIngredientIdAndMenuItemIngredientIdMenuItemId(ingredientId, menuItemId);

        if(exist.isEmpty()) {
            throw new MenuItemException("Menu item with id: " + menuItemId + " does not have an ingredient of id: " + ingredientId);
        }
        return returnObject.equals("MenuItem") ? exist.get() : menuItemId;
    }

    private void existsByNameWithDifferentId(Long id, String name) {
        menuItemRepository.findByName(name)
                .ifPresent(value -> {
                    if(!value.getId().equals(id))
                        throw new MenuItemException("Menu item with name: " + name + " already exists!");
                });
    }

}
