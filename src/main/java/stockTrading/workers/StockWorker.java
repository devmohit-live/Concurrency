package stockTrading.workers;

import stockTrading.models.Order;
import stockTrading.models.OrderStatus;
import stockTrading.models.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockWorker implements IStockWorker {
    public static final String NAME = "-ExpiryExecutor-";
    final Stock stock;
    final WorkerState state;
    final List<OrderExecutor> workers;
    final List<ExpiredOrderExecutor> expiredWorkers;

    public StockWorker(Stock stock) {
        this.stock = stock;
        this.state = new WorkerState(new ArrayList<>(), new ArrayList<>());
        this.workers = new ArrayList<>();
        this.expiredWorkers = new ArrayList<>();
    }

    @Override
    public void addOrder(Order order) {
        synchronized (state) {
            state.orderBook.add(order);
            state.notifyAll();
//            System.out.printf("Added Order %s for the stock %s\n ",order,stock);

        }
    }

    @Override
    public void cancelOrder(Order order) {
        synchronized (state) {
            state.orderBook.remove(order);
            order.setOrderStatus(OrderStatus.CANCELLED);
            state.completedOrders.add(order);
        }
        System.out.println("Cancelled order " + order);
    }

    @Override
    public void modifyOrder(Order updatedOrder) {
        synchronized (state) {
            Optional<Order> actualOrder = state.orderBook.stream().filter(order -> order.getOrderID().equals(updatedOrder.getOrderID())).findFirst();
            if (actualOrder.isPresent()) {
                state.orderBook.remove(actualOrder.get());
                state.orderBook.add(updatedOrder);
                state.notifyAll();
            }
        }
        System.out.println("Updated order " + updatedOrder);
    }

    /*
     Add multiple executors for this symbol in case there are a lot of orders
 */
    public void increaseWorkerCountBy(int count) {
        // NOTE: no use as we are synchronizing on orderBook : so even tough there are multiple workers only 1 at a time will be able to execute an order
        for (int i = 0; i < count; i++) {
            OrderExecutor newWorker = new OrderExecutor(state);
            workers.add(newWorker);
            new Thread(newWorker).start();
        }
    }

    public void increaseExpiryWorkerCountBy(int count) {
        // NOTE: no use as we are synchronizing on orderBook : so even tough there are multiple workers only 1 at a time will be able to execute an order
        for (int i = 0; i < count; i++) {
            ExpiredOrderExecutor newExpiryWorker = new ExpiredOrderExecutor(state);
            String name = stock.getSymbol() + NAME + this.expiredWorkers.size();
            expiredWorkers.add(newExpiryWorker);
            new Thread(newExpiryWorker, name).start();
        }
    }


    @Override
    public int getWorkersCount() {
        return workers.size();
    }

    public List<Order> getCompletedOrders() {
        return new ArrayList<>(state.completedOrders);
    }

    public List<Order> getOrderBook() {
        return new ArrayList<>(state.orderBook);
    }
}
