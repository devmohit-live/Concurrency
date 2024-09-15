package stockTrading.workers;

import stockTrading.models.Order;

public interface IStockWorker {
    void addOrder(Order order);

    void cancelOrder(Order order);

    void modifyOrder(Order order);

    void increaseWorkerCountBy(int count);

    int getWorkersCount();
}
