package stockTrading.workers;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import stockTrading.models.Order;
import stockTrading.models.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ExpiredOrderExecutor implements Runnable {
    WorkerState workerState;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Thread.sleep(5000);
            List<Order> expiredOrders;
            synchronized (workerState) {
                while (true) {
                    while (workerState.orderBook.isEmpty()) {
                        workerState.wait();
                    }
                    expiredOrders = getExpiredOrder();

                    if (expiredOrders.isEmpty()) {
                        workerState.wait();
                    } else {
                        workerState.orderBook.removeAll(expiredOrders);
                        updateExpiredOrders(expiredOrders);
                        workerState.completedOrders.addAll(expiredOrders);
                        System.out.println(Thread.currentThread().getName() + " Removed Expired Orders: " + expiredOrders);
                        break;
                    }
                }
                workerState.notifyAll();
            }
        }
    }


    public List<Order> getExpiredOrder() {
        List<Order> list = new ArrayList<>(workerState.orderBook);
        return list.stream().filter(Order::isOrderExpired).collect(Collectors.toList());
    }

    public List<Order> updateExpiredOrders(List<Order> expiredOrders) {
        expiredOrders.stream().forEach(order -> order.setOrderStatus(OrderStatus.REJECTED));
        return expiredOrders;
    }
}
