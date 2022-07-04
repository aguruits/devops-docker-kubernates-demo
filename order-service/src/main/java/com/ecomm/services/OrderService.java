package com.ecomm.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomm.entities.Order;
import com.ecomm.models.OrderItem;
import com.ecomm.models.Product;
import com.ecomm.models.UserAccount;
import com.ecomm.repositories.OrderRepository;

@Service
@Transactional
public class OrderService {
	@Autowired
    OrderRepository orderRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	Logger log = LogManager.getLogger(OrderService.class);
    
    public List<OrderItem> getOrders() {
    	List<Order> orders = orderRepository.findAll();
       
    	List<OrderItem> orderItems = new ArrayList<>();
    	
        return orderItems;
    }

    public OrderItem getOrder(String orderId) {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(orderId);
		
		Optional<Order> orderOptional = orderRepository.findByOrderId(orderId);
		if(orderOptional.isPresent()) {
			Long userId = orderOptional.get().getUserId();
			Long productId = orderOptional.get().getProductId();
			
			orderItem.setOrderId(orderId);
			orderItem.setProductId(productId);
			orderItem.setUserId(userId);
			orderItem.setQuantity(orderOptional.get().getQuantity());
			orderItem.setProductPrice(orderOptional.get().getProductPrice());
			
			UserAccount userAccount = userService.getUserAccountDetails(userId);
			orderItem.setUserAccount(userAccount);
			
			Product product = productService.getProductDetails(productId);
			orderItem.setProduct(product);
		}
		
		return orderItem;
	}
    
    
}
