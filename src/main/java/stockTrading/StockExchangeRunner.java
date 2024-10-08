package stockTrading;

import lombok.SneakyThrows;
import stockTrading.models.*;
import stockTrading.service.OrderService;
import stockTrading.service.StockService;
import stockTrading.service.UserService;
import stockTrading.workers.StockWorker;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StockExchangeRunner {

    @SneakyThrows
    public void run() {

        Map<String, StockWorker> map = new ConcurrentHashMap<>(); //db
        UserService userService = new UserService();
        OrderService orderService = new OrderService(map);
        StockService stockService = new StockService(map);

        StockExchange stockExchange = new StockExchange(orderService, stockService,userService);

        // User
        User user1 = new User("1", "mohit", "2345", "2345t");
        User user2 = new User("2", "shobhit", "2345", "2345t");
        User user3 = new User("3", "raj", "2345", "2345t");
        // user registration
        stockExchange.registerUser(user1);
        stockExchange.registerUser(user2);
        stockExchange.registerUser(user3);

        // Stock Registration
        Stock REL = new Stock("REL", "Reliance Pvt Ltd.");
        stockExchange.addStock(REL);
        Stock angleOne = new Stock("AngleOne", "AngleOne Pvt Ltd.");
        stockExchange.addStock(angleOne);

        Order order1 = new Order("o1", user1.getUserID(), OrderType.BUY, REL.getSymbol(), 1000, 10, OrderStatus.PENDING);
        Order order2 = new Order("o2", user2.getUserID(), OrderType.SELL, REL.getSymbol(), 1000, 10, OrderStatus.PENDING);
        Order order3 = new Order("o3", user1.getUserID(), OrderType.BUY, angleOne.getSymbol(), 2000, 10, OrderStatus.PENDING);
        Order order4 = new Order("o4", user2.getUserID(), OrderType.SELL, angleOne.getSymbol(), 2000, 10, OrderStatus.PENDING);
        Order order7 = new Order("o6", user3.getUserID(), OrderType.BUY, REL.getSymbol(), 500, 10, OrderStatus.PENDING);
        Order order8 = new Order("o8", user3.getUserID(), OrderType.BUY, REL.getSymbol(), 600, 6, OrderStatus.PENDING);
        Order order9 = new Order("o8", user3.getUserID(), OrderType.SELL, angleOne.getSymbol(), 600, 6, OrderStatus.PENDING);
        Order order10 = new Order("o9", user2.getUserID(), OrderType.BUY, angleOne.getSymbol(), 2500, 10, OrderStatus.PENDING);
        Order order11 = new Order("o9", user2.getUserID(), OrderType.SELL, angleOne.getSymbol(), 2500, 10, OrderStatus.PENDING);

        order7.setExpiryTimestamp(LocalDateTime.now().plusSeconds(1));
        order8.setExpiryTimestamp(LocalDateTime.now().plusSeconds(2));
        order9.setExpiryTimestamp(LocalDateTime.now().plusSeconds(3));
        order10.setExpiryTimestamp(LocalDateTime.now().plusSeconds(1));
        order11.setExpiryTimestamp(LocalDateTime.now().plusSeconds(1));


        stockExchange.addOrder(order7);
        stockExchange.addOrder(order1);
        stockExchange.addOrder(order3);
        stockExchange.addOrder(order2);
        stockExchange.addOrder(order8);
        stockExchange.addOrder(order4);


        stockExchange.addOrder(order1.clone());
        stockExchange.addOrder(order1.clone());
        stockService.getStockWorkersCount(REL.getSymbol());
        stockExchange.addOrder(order1.clone());
        stockService.increaseWorkersCount(REL.getSymbol(), 2);
        stockService.getStockWorkersCount(REL.getSymbol());

        stockExchange.addOrder(order1.clone());
        stockExchange.addOrder(order1.clone());
        stockExchange.addOrder(order1.clone());

        stockExchange.addOrder(order2.clone());
        stockExchange.addOrder(order2.clone());
        stockExchange.addOrder(order2.clone());
        stockExchange.addOrder(order2.clone());

        stockExchange.addOrder(order3.clone());
        stockService.getStockWorkersCount(angleOne.getSymbol());
        stockExchange.addOrder(order3.clone());
        stockExchange.addOrder(order4.clone());
        stockExchange.addOrder(order4.clone());
        stockExchange.addOrder(order4.clone());
        stockExchange.addOrder(order9);
        stockExchange.addOrder(order10);
        stockExchange.addOrder(order11);


        Thread.sleep(30000);
        System.out.println("--------- Reporting -------------");
        orderService.getCompletedOrder(REL.getSymbol());
        orderService.getPendingAndPartialOrder(REL.getSymbol());

        System.out.println();
        orderService.getCompletedOrder(angleOne.getSymbol());
        orderService.getPendingAndPartialOrder(angleOne.getSymbol());

    }
}
