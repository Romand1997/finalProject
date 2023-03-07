package finalProject.services;

import finalProject.model.Item;
import finalProject.model.Order;
import finalProject.model.User;
import finalProject.repositories.ItemRepository;
import finalProject.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserService userService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long order_id) {
        return orderRepository.findById(order_id).orElse(null);
    }

    public Order getOrderByUserId(Long user_id) {
        return orderRepository.findByUser_Id(user_id);
    }

    public Order getOrderByUser() {
        Order order = null;
        User user = userService.getUser();
        if (user != null) {
            order = orderRepository.findByUser_Id(user.getId());
            order.setUser(null);
        }
        return order;
    }

    public int getAmountOfItemInOrder() {
        int amount = 0;
        Order order = getOrderByUser();
        if (order != null) {
            amount = order.getItems().size();
        }
        return amount;
    }

    public Order updateOrder(Order order) {
        User user = userService.getUser();
        Order testOrder = getOrderByUserId(user.getId());
        order.setId(testOrder.getId());
        order.setUser(order.getUser());
        return orderRepository.save(order);
    }

    public Item addItemToOrder(Item item) {
        Order order = new Order();
        User user = userService.getUser();
        Order testOrder = getOrderByUserId(user.getId());
        if (testOrder != null) {
            order = testOrder;
        } else {
            order.setUser(user);
        }
        List<Item> items = order.getItems();
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        order.setItems(items);
        try {
            orderRepository.save(order);
        } catch (Exception e) {
            item = null;
        }
        return item;
    }

    public Item deleteItemFromOrder(Item item) {

        User user = userService.getUser();
        Order order = getOrderByUserId(user.getId());
        List<Item> items = order.getItems();
        Item newItem = itemRepository.findById(item.getId()).orElse(null);
        items.remove(newItem);
        order.setItems(items);
        try {
            orderRepository.save(order);
        } catch (Exception e) {
            item = null;
        }
        return item;
    }

    public Order clearOrder(Order order) {

        User user = userService.getUser();
        order.setUser(user);
        List<Item> items = new ArrayList<>();
        order.setItems(items);
        orderRepository.save(order);
        return order;
    }

    public boolean deleteOrder(Order order) {
        boolean bol = true;
        try {
            orderRepository.delete(order);
        } catch (Exception e) {
            bol = false;
        }
        return bol;
    }
}
