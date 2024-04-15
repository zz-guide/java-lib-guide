package org.zz.mapper;

import org.zz.domain.Order;

import java.util.List;

public interface OrderMapper {
    public int insert(Order order);
    public int batchInsert(List<Order> orders);
    public Order getById(Long id);
    public List<Order> getListByUserId(Long userId);
    public List<Order> getList();
    public List<Order> getListWithLeftJoin();
}
