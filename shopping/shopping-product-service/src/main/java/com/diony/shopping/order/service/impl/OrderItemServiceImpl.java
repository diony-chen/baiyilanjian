package com.diony.shopping.order.service.impl;

import com.diony.shopping.order.entity.OrderItem;
import com.diony.shopping.order.mapper.OrderItemMapper;
import com.diony.shopping.order.service.OrderItemService;
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
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
