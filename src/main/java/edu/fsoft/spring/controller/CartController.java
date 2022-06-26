package edu.fsoft.spring.controller;

import edu.fsoft.spring.formobj.GlobalData;
import edu.fsoft.spring.formobj.OrderDetailsFormObj;
import edu.fsoft.spring.formobj.ProductFormObj;
import edu.fsoft.spring.model.*;
import edu.fsoft.spring.repository.AccountRepository;
import edu.fsoft.spring.repository.ProductRepository;
import edu.fsoft.spring.service.AccountService;
import edu.fsoft.spring.service.OrderDetailsService;
import edu.fsoft.spring.service.OrderService;
import edu.fsoft.spring.service.ProductService;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    AccountRepository accountRepository;

    public boolean checkProduct(int id) {
        for(int i = 0; i < GlobalData.cart.size(); i++) {
            if(GlobalData.cart.get(i).getId() == id)
                return true;
        }
        if(productService.getProductById(id).get().getQuantity() == 0)
            return true;
        return false;
    }

    @GetMapping("/addToCart/{id}")
    @ResponseBody
    public String addToCart(@PathVariable int id){
        if(!checkProduct(id)) {
            GlobalData.cart.add(productService.getProductById(id).get());
            return "success";
        }
        else return "fail";
    }

    @GetMapping("/cart")
    public String cartGet(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        Order order = new Order();
        model.addAttribute("order", order);

        return "cart";
    }

    @RequestMapping(value = "/cart/get-account/{phoneNumber}", method = RequestMethod.POST)
    @ResponseBody
    public Account cartPost(@PathVariable String phoneNumber){
        Account account = accountRepository.findByPhone(phoneNumber);
        return account;
    }

    @GetMapping("/cart/removeItem/{id}")
    @ResponseBody
    public String removeItem(@PathVariable int id){
        if (GlobalData.cart!=null){
            for (Product p : GlobalData.cart) {
                if (p.getId() == id){
                    GlobalData.cart.remove(p);
                    break;
                }
            }
        }
        System.out.println(GlobalData.cart);
        return "success";
    }

//    @GetMapping("/checkout")
//    public String checkout(Model model){
//        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
//        return "checkout";
//    }

//    @GetMapping("/cart/addOrder")
//    public String checkout1(Model model){
//        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
//        System.out.println(GlobalData.cart.size());
//        return "redirect:/home";
//    }

    @RequestMapping(value = "/cart/addOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel saveOrder(CreateNewOrderDto dto) {
        if (GlobalData.cart.size() == 0)
            return new ResponseModel(false, "Cart is empty!");
        else {
            Order order = new Order();
            Account account = accountRepository.findByPhone(dto.getCustomerPhone());
            float discount = dto.getPoint() * 1000;
            orderService.saveOrder(order);
            float total = 0;

            List<Integer> list = dto.getListItems();
            for (int i = 0; i < GlobalData.cart.size(); i++) {
                OrderDetailsFormObj orderDetails = new OrderDetailsFormObj();
                orderDetails.setOrder_id(order.getId());
                orderDetails.setProduct_id(GlobalData.cart.get(i).getId());
                orderDetails.setQuantity(list.get(i).intValue());
                orderDetails.setUnitPrice(GlobalData.cart.get(i).getPrice());
                orderDetailsService.saveOrderDetails(orderDetails);
                total = total + orderDetails.getQuantity() * orderDetails.getUnitPrice();
                Product product = productService.getProductById(GlobalData.cart.get(i).getId()).get();
                product.setQuantity(product.getQuantity() - list.get(i).intValue());
                productRepository.save(product);
            }
            total = total - discount;
            int plusPoint = (int) total / 100000;
            if (account != null) {
                order.setCustomerPhone(account.getPhone());
                account.setPoint(account.getPoint() + plusPoint);
                account.setPoint(account.getPoint() - dto.getPoint());
            }
            else order.setCustomerPhone(null);
            order.setTotal(total);
            orderService.saveOrder(order);
            GlobalData.cart.clear();
            return new ResponseModel(true, "Add successfully!");
        }
    }


}
