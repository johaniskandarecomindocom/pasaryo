package com.ecomindo.onboarding.pasaryo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecomindo.onboarding.pasaryo.dto.OptionsDTO;
import com.ecomindo.onboarding.pasaryo.model.Order;

public interface OrderDao extends JpaRepository<Order, Long> {
	List<Order> findByAuthor(String author);

	List<Order> findByTitle(String title);

	@Query("Select new com.ecomindo.onboarding.pasaryo.dto.OptionsDTO(concat(p.title, ' - ', p.author) as label, p.id as value) from Order p")
	List<OptionsDTO> findDDLTitle();
}
