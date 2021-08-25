package com.diony.shopping.cart.service.impl;

import com.diony.shopping.cart.entity.CartItem;
import com.diony.shopping.cart.mapper.CartItemMapper;
import com.diony.shopping.cart.service.CartItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author diony_chen
 * @since 2021-06-17
 */
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements CartItemService {

}
