package edu.fsoft.spring.controller;

import edu.fsoft.spring.formobj.GlobalData;
import edu.fsoft.spring.formobj.OrderDetailsFormObj;
import edu.fsoft.spring.model.Order;
import edu.fsoft.spring.service.OrderDetailsService;
import edu.fsoft.spring.service.OrderService;
import edu.fsoft.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailsService orderDetailsService;

//    @RequestMapping(value = "/cart/addOrder", method = RequestMethod.POST)
//    public String saveOrder(@ModelAttribute("quantity") List<Integer> quantity) {
//        System.out.println(quantity);
//        Order order = new Order();
//        orderService.saveOrder(order);
//        float total = 0;
//        for (int i = 0; i < GlobalData.cart.size(); i++) {
//            OrderDetailsFormObj orderDetails = new OrderDetailsFormObj();
//            orderDetails.setOrder_id(order.getId());
//            orderDetails.setProduct_id(GlobalData.cart.get(i).getId());
//            orderDetails.setQuantity(quantity.get(i));
//            orderDetails.setUnitPrice(GlobalData.cart.get(i).getPrice());
//            orderDetailsService.saveOrderDetails(orderDetails);
//            total = total + orderDetails.getQuantity() * orderDetails.getUnitPrice();
//        }
//        order.setTotal(total);
//        orderService.saveOrder(order);
//        GlobalData.cart.clear();
//        return "redirect:/home";
//    }
}
