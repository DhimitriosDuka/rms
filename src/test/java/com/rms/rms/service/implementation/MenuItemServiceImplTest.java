package com.rms.rms.service.implementation;

import com.rms.rms.dto.menu.item.MenuItemCreateDto;
import com.rms.rms.dto.menu.item.MenuItemResponseDto;
import com.rms.rms.dto.menu.item.MenuItemUpdateDto;
import com.rms.rms.entity.MenuItem;
import com.rms.rms.entity.MenuItemIngredient;
import com.rms.rms.exception.MenuItemException;
import com.rms.rms.repository.MenuItemIngredientRepository;
import com.rms.rms.utils.MenuItemUtil;
import org.hibernate.JDBCException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuItemServiceImplTest extends BaseServiceTest<MenuItemCreateDto, MenuItemUpdateDto, MenuItemResponseDto, MenuItem>{

    @Mock
    private MenuItemIngredientRepository menuItemIngredientRepository;

    @InjectMocks
    private MenuItemServiceImpl menuItemService;

    private MenuItem menuItem;
    private MenuItemResponseDto menuItemResponseDto;
    private MenuItemUpdateDto menuItemUpdateDto;
    private MenuItemIngredient menuItemIngredient;

    @Override
    @BeforeEach
    void init() {
        MenuItemUtil menuItemUtil = new MenuItemUtil();
        menuItem = menuItemUtil.initEntity(1L);
        menuItemResponseDto = menuItemUtil.convertToMenuItemResponseDto(menuItem);
        menuItemUpdateDto = menuItemUtil.convertToMenuItemUpdateDto(menuItem);
        menuItemIngredient = menuItemUtil.initMenuItemIngredient(menuItem);
    }

    @Test
    void When_Update_ReturnUpdatedMenuItem() {

        when(jpaRepository.findById(menuItem.getId())).thenReturn(Optional.of(menuItem));
        when(baseMapper.updateDtoToEntity(menuItemUpdateDto)).thenReturn(menuItem);
        when(jpaRepository.save(menuItem)).thenReturn(menuItem);
        when(baseMapper.entityToResponseDto(menuItem)).thenReturn(menuItemResponseDto);

        assertEquals(menuItemService.update(menuItem.getId(), menuItemUpdateDto).getId(), menuItemResponseDto.getId());

    }

    @Test
    void When_Update_ThrowRuntimeExceptionMenuItemWithIdDoesNotExit() {

        when(jpaRepository.findById(menuItem.getId())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> menuItemService.update(menuItem.getId(), new MenuItemUpdateDto()));

    }

    @Test
    void When_Update_ThrowJDBCExceptionItemWithNameExists(){

        when(jpaRepository.findById(menuItem.getId())).thenReturn(Optional.of(menuItem));
        when(baseMapper.updateDtoToEntity(menuItemUpdateDto)).thenReturn(menuItem);
        when(jpaRepository.save(menuItem)).thenThrow(JDBCException.class);

        assertThrows(JDBCException.class, () -> menuItemService.update(menuItem.getId(), menuItemUpdateDto));

    }

    @Test
    void When_AddIngredientToMenuItem_ThrowMenuItemExceptionMenuItemDoesNotExist() {

        when(jpaRepository.findById(menuItem.getId())).thenReturn(Optional.empty());
        assertThrows(MenuItemException.class, () -> menuItemService.addIngredientToMenuItem(menuItem.getId(), menuItemIngredient));

    }

    @Test
    void When_AddIngredientToMenuItem_ThrowJDBCExceptionMenuItemAlreadyHasIngredient() {

        when(jpaRepository.findById(menuItem.getId())).thenReturn(Optional.of(menuItem));
        when(menuItemIngredientRepository.save(menuItemIngredient)).thenThrow(JDBCException.class);

        assertThrows(JDBCException.class, () -> menuItemService.addIngredientToMenuItem(menuItem.getId(), menuItemIngredient));

    }

    @Test
    void When_DeleteIngredientFromMenuItem_DeleteSuccessfully() {

        when(jpaRepository.findById(menuItem.getId())).thenReturn(Optional.of(menuItem));
        when(menuItemIngredientRepository.findByMenuItemIngredientId(menuItemIngredient.getMenuItemIngredientId())).thenReturn(Optional.of(menuItemIngredient));
        menuItemService.deleteIngredientFromMenuItem(menuItem.getId(), menuItemIngredient.getIngredient().getId());
        verify(menuItemIngredientRepository).deleteByMenuItemIngredientId(menuItemIngredient.getMenuItemIngredientId());

    }

    @Test
    void When_DeleteIngredientFromMenuItem_ThrowMenuItemException() {

        when(jpaRepository.findById(menuItem.getId())).thenReturn(Optional.of(menuItem));
        when(menuItemIngredientRepository.findByMenuItemIngredientId(menuItemIngredient.getMenuItemIngredientId())).thenReturn(Optional.empty());
        assertThrows(MenuItemException.class, () -> menuItemService.deleteIngredientFromMenuItem(menuItem.getId(), menuItemIngredient.getMenuItem().getId()));

    }

}