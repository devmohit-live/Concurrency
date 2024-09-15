package stockTrading.workers;

import lombok.AllArgsConstructor;
import stockTrading.models.Order;

import java.util.List;

@AllArgsConstructor
public class WorkerState {
    List<Order> orderBook;
    List<Order> completedOrders;
}
