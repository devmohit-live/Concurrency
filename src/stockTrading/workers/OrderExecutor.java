package stockTrading.workers;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import stockTrading.models.MatchingOrder;
import stockTrading.models.Order;
import stockTrading.models.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class OrderExecutor implements Runnable {
    WorkerState state;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            MatchingOrder matchingOrder;
            synchronized (state) {
                while (true) {
                    while (state.orderBook.isEmpty()) {
                        state.wait();
                    }
                    matchingOrder = getMatchingOrder();
                    if (matchingOrder == null) {
                        state.wait();
                        // goes back to line 20 and again matching orders will be found
                    } else {
                        System.out.println("Matching Order Found");
                        state.orderBook.remove(matchingOrder.getOrder1());
                        state.orderBook.remove(matchingOrder.getOrder2());
                        matchingOrder.getOrder1().setThreadName(Thread.currentThread().getName());
                        matchingOrder.getOrder2().setThreadName(Thread.currentThread().getName());
                        break;
                    }
                }
            }
            if (matchingOrder != null) {
                executeMatchingOrder(matchingOrder);
                addPartialExecutedOrders(matchingOrder); // this is a synchronized fx on order-book
                addCompletedOrders(matchingOrder);
            }

        }
    }


    private MatchingOrder getMatchingOrder() {
        for (Order order1 : state.orderBook) {
            for (Order order2 : state.orderBook) {
                if (isMatchingOrder(order1, order2)) {
                    return new MatchingOrder(order1, order2);
                }
            }
        }
        return null;
    }

    private boolean isMatchingOrder(Order order1, Order order2) {
        return (
                order1.getStockSymbol().equals(order2.getStockSymbol()) &&
//                                Disabled for testing purpose
//                        !order1.getOrderID().equals(order2.getOrderID()) &&
//                        !order1.getUserID().equals(order2.getUserID()) &&
                        !order1.isOrderExpired() &&
                        !order2.isOrderExpired() &&
                        order1.getType() != order2.getType() &&
                        order1.getPrice() == order2.getPrice() &&
                        isOrderValidForExecution(order1) &&
                        isOrderValidForExecution(order2)
        );
    }


    private boolean isOrderValidForExecution(Order order) {
        return order.getOrderStatus() == OrderStatus.PARTIALLY_ACCEPTED || order.getOrderStatus() == OrderStatus.PENDING;
    }

    @SneakyThrows
    private void executeMatchingOrder(MatchingOrder matchingOrder) {
        Order order1 = matchingOrder.getOrder1();
        Order order2 = matchingOrder.getOrder2();

        int minQty = Math.min(order1.getQuantity(), order2.getQuantity());
        order1.setQuantity(order1.getQuantity() - minQty);
        markOrderAsExecuted(order1);
        order2.setQuantity(order2.getQuantity() - minQty);
        markOrderAsExecuted(order2);
        Thread.sleep(5000);
        System.out.println("Thread " + Thread.currentThread().getName() + "  Order Executed Successfully by" + order1 + " " + order2);
    }

    private void markOrderAsExecuted(Order order) {
        if (order.getQuantity() == 0) {
            order.setOrderStatus(OrderStatus.ACCEPTED);

        } else {
            order.setOrderStatus(OrderStatus.PARTIALLY_ACCEPTED);
        }
        order.setAcceptedTimestamp(LocalDateTime.now());
    }

    private void addPartialExecutedOrders(MatchingOrder matchingOrder) {
        synchronized (state) {
            addOrdersOnOrderStatus(matchingOrder, OrderStatus.PARTIALLY_ACCEPTED, state.orderBook);
            state.notifyAll();
        }
    }

    private void addCompletedOrders(MatchingOrder matchingOrder) {
        synchronized (state) {
            addOrdersOnOrderStatus(matchingOrder, OrderStatus.ACCEPTED, state.completedOrders);
            // no need to notify here.
        }
    }


    private void addOrdersOnOrderStatus(MatchingOrder matchingOrder, OrderStatus criteria, List<Order> orders) {
        if (matchingOrder.getOrder1().getOrderStatus() == criteria) {
            orders.add(matchingOrder.getOrder1());
        } else if (matchingOrder.getOrder2().getOrderStatus() == criteria) {
            orders.add(matchingOrder.getOrder2());
        }
    }
}
