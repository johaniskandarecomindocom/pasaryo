package com.ecomindo.onboarding.pasaryo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomindo.onboarding.pasaryo.dao.OrderDao;
import com.ecomindo.onboarding.pasaryo.dto.OptionsDTO;
import com.ecomindo.onboarding.pasaryo.dto.ResponseDTO;
import com.ecomindo.onboarding.pasaryo.model.Order;
import com.ecomindo.onboarding.pasaryo.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderDao ordersDao;

	@Autowired
	OrderService ordersService;
	
	@GetMapping("/get-orders")
	public ResponseEntity<List<Order>> getAllOrders(@RequestParam(required = false) String title) {
		try {
			List<Order> orders = new ArrayList<Order>();

			if (title == null)
				ordersDao.findAll().forEach(orders::add);
			else
				ordersDao.findByTitle(title).forEach(orders::add);

			if (orders.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get-orders2")
	public ResponseDTO getAllOrders2(@RequestParam(required = false) String title) {
		ResponseDTO response = new ResponseDTO();
		try {

			List<Order> orders = new ArrayList<Order>();

			if (title == null)
				ordersDao.findAll().forEach(orders::add);
			else
				ordersDao.findByTitle(title).forEach(orders::add);

			response.setCode("200");
			if (orders.isEmpty()) {
				response.setMessage(HttpStatus.NO_CONTENT.toString());
			}

			response.setData(orders);
			return response;
		} catch (Exception e) {
			response.setCode("500");
			return response;
		}
	}

	@GetMapping("/get-ddl-orders")
	public ResponseDTO getDdlOrders() {
		ResponseDTO response = new ResponseDTO();
		try {

			List<OptionsDTO> opt = ordersDao.findDDLTitle();
			response.setCode("200");
			if (opt.isEmpty()) {
				response.setMessage(HttpStatus.NO_CONTENT.toString());
			}

			response.setData(opt);
			return response;
		} catch (Exception e) {
			response.setCode("500");
			return response;
		}
	}

	@PostMapping("/insert")
	public ResponseDTO insert(@RequestBody Order orders) {
		ResponseDTO response = new ResponseDTO();
		try {
			Order order = ordersService.insert(orders.getTitle(), orders.getDescription(), orders.getAuthor());
			
			response.setCode("200");
			response.setMessage("Insert Success");
			response.setData(order);
			
			return response;
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage("Insert Failed");
			return response;
		}
	}

}
