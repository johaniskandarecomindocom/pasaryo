package com.ecomindo.onboarding.pasaryo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomindo.onboarding.pasaryo.dao.OrderDao;
import com.ecomindo.onboarding.pasaryo.model.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao ordersDao;

	@Override
	public Order insert(String title, String description, String author) {
		try {
			Order orders = ordersDao.save(new Order(title, description, author));
			return orders;
		} catch (Exception e) {
			throw e;
		}
	}

}
