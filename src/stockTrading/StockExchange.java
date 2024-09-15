package stockTrading;

import lombok.SneakyThrows;
import stockTrading.exceptions.StockAlreadyPresent;
import stockTrading.exceptions.StockNotFound;
import stockTrading.models.Order;
import stockTrading.models.Stock;
import stockTrading.models.User;
import stockTrading.service.OrderService;
import stockTrading.service.StockService;
import stockTrading.service.UserService;
import stockTrading.workers.StockWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StockExchange {

    UserService userService;
    OrderService orderService;
    StockService stockService;



    StockExchange(OrderService orderService, StockService stockService, UserService userService) {
        this.orderService = orderService;
        this.stockService = stockService;
        this.userService = userService;
    }

    public void registerUser(User user) {
        userService.addUser(user);
    }



    public void addOrder(Order order) {
        orderService.addOrder(order);
    }

    public void addStock(Stock stock) {
        stockService.addStock(stock);
    }

}
