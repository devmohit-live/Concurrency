package stockTrading;

import lombok.SneakyThrows;
import stockTrading.models.*;

import java.util.Scanner;

public class StockExchangeRunner {

    @SneakyThrows
    public void run() {
        StockExchange stockExchange = new StockExchange();

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
        Order order8 = new Order("o6", user3.getUserID(), OrderType.BUY, REL.getSymbol(), 600, 6, OrderStatus.PENDING);


        stockExchange.addOrder(order7);
        stockExchange.addOrder(order1);
        stockExchange.addOrder(order3);
        stockExchange.addOrder(order2);
        stockExchange.addOrder(order8);
        stockExchange.addOrder(order4);


        stockExchange.addOrder(order1.clone());
        stockExchange.addOrder(order1.clone());
        stockExchange.getStockWorkersCount(REL.getSymbol());
        stockExchange.addOrder(order1.clone());
        stockExchange.increaseWorkersCount(REL.getSymbol(), 2);
        stockExchange.getStockWorkersCount(REL.getSymbol());

        stockExchange.addOrder(order1.clone());
        stockExchange.addOrder(order1.clone());
        stockExchange.addOrder(order1.clone());

        stockExchange.addOrder(order2.clone());
        stockExchange.addOrder(order2.clone());
        stockExchange.addOrder(order2.clone());
        stockExchange.addOrder(order2.clone());

        stockExchange.addOrder(order3.clone());
        stockExchange.getStockWorkersCount(angleOne.getSymbol());
        stockExchange.addOrder(order3.clone());
        stockExchange.addOrder(order4.clone());
        stockExchange.addOrder(order4.clone());
        stockExchange.addOrder(order4.clone());


        Thread.sleep(30000);
        System.out.println("--------- Reporting -------------");
        stockExchange.getCompletedOrder(REL.getSymbol());
        stockExchange.getPendingAndPartialOrder(REL.getSymbol());

        System.out.println();
        stockExchange.getCompletedOrder(angleOne.getSymbol());
        stockExchange.getPendingAndPartialOrder(angleOne.getSymbol());

    }
}
