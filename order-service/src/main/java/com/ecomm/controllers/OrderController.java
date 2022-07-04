package com.ecomm.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.models.OrderItem;
import com.ecomm.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    Logger log = LogManager.getLogger(OrderController.class);
    
    @Autowired OrderService orderService;
    
    @GetMapping("/getMessage")
    public String getMessage() {
        return "Hi Guru, Welcome to Microservice Testing and service is working fine...!";
    }
    
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderItem> findOrder(@PathVariable("orderId") String orderId) {
		log.info("Finding order for order id :" + orderId);
		OrderItem orderItem = orderService.getOrder(orderId);
		return new ResponseEntity<OrderItem>(orderItem, HttpStatus.OK);
	}

	/*
	 * @GetMapping("/orders") public ResponseEntity<List<OrderItem>> getAllOrders()
	 * { log.info("Finding all orders "); List<OrderItem> orders =
	 * orderService.getOrders(); return new ResponseEntity<List<OrderItem>>(orders,
	 * HttpStatus.OK); }
	 */
    
	/*
	 * @PostMapping("/addOrder") public ResponseEntity<OrderItem>
	 * addOrder(@RequestBody OrderItem orderItem) { try { log.info("Add an order");
	 * 
	 * String res = orderService.addOrder(orderItem);
	 * if(StringUtils.equalsIgnoreCase(res, "Success")) return new
	 * ResponseEntity<OrderItem>(new OrderItem(), HttpStatus.OK); else return new
	 * ResponseEntity<OrderItem>(new OrderItem(), HttpStatus.EXPECTATION_FAILED); }
	 * catch(Exception e) { throw new
	 * EcommException("Due to some technianl reason, unable to insert an Order."); }
	 * }
	 */
    
}
