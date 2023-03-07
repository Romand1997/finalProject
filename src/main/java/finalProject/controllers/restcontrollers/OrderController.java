package finalProject.controllers.restcontrollers;

import finalProject.model.Item;
import finalProject.model.Order;
import finalProject.services.ItemService;
import finalProject.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(value = "/orderByUser")
    public Order getOrderByUser() {
        Order order = orderService.getOrderByUser();
        return order;
    }

    @GetMapping(value = "/amount")
    public int getAmountOfItemInOrder() {
        return orderService.getAmountOfItemInOrder();
    }

    @PostMapping(value = "/deleteItem")
    public Item deleteItemFromOrder(@RequestBody Item item) {
        return orderService.deleteItemFromOrder(item);
    }

    @PutMapping(value = "/clearOrder")
    public Order clearOrder(@RequestBody Order order) {
        return orderService.clearOrder(order);
    }

    @PostMapping(value = "addItem")
    public Item addItemToOrder(@RequestBody Item item) {
        return orderService.addItemToOrder(item);
    }

    @DeleteMapping
    public Boolean deleteOrder(@RequestBody Order order) {
        return orderService.deleteOrder(order);
    }
}
