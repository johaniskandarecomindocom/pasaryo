package com.ecomindo.onboarding.pasaryo.services;

import com.ecomindo.onboarding.pasaryo.model.Order;

public interface OrderService {
	public Order insert(String title, String description, String author);
}
