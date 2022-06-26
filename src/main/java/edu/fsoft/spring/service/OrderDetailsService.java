package edu.fsoft.spring.service;

import edu.fsoft.spring.formobj.OrderDetailsFormObj;
import edu.fsoft.spring.interfaceService.IOrderDetailsService;
import edu.fsoft.spring.model.Order;
import edu.fsoft.spring.model.OrderDetails;
import edu.fsoft.spring.model.Product;
import edu.fsoft.spring.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService implements IOrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public void saveOrderDetails(OrderDetailsFormObj form) {
        OrderDetails orderDetails = null;
        Order order = new Order();
        Product product = new Product();
        if (form.getId() > 0) {
            orderDetails = orderDetailsRepository.findById(form.getId()).get();
            order.setId((form.getOrder_id()));
            product.setId(form.getProduct_id());
            orderDetails.setOrder(order);
            orderDetails.setProduct(product);
            orderDetails.setQuantity(form.getQuantity());
            orderDetails.setUnitPrice(form.getUnitPrice());
        }else {
            orderDetails = new OrderDetails();
            order.setId((form.getOrder_id()));
            product.setId(form.getProduct_id());
            orderDetails.setOrder(order);
            orderDetails.setProduct(product);
            orderDetails.setQuantity(form.getQuantity());
            orderDetails.setUnitPrice(form.getUnitPrice());
        }
        orderDetailsRepository.save(orderDetails);
    }
}
