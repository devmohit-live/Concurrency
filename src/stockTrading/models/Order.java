package stockTrading.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Scanner;

@ToString
@Getter
@Setter
@RequiredArgsConstructor
public class Order implements Cloneable{
    final String orderID;
    final String userID;
    final OrderType type;
    final String stockSymbol;
    final double price;
    int quantity;
    LocalDateTime acceptedTimestamp;
    OrderStatus orderStatus;
    String threadName; // For testing

    public Order(String orderID, String userID, OrderType type, String stockSymbol, double price, int quantity, OrderStatus orderStatus) {
        this.orderID = orderID;
        this.userID = userID;
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.price = price;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
    }

    @Override
    public Order clone() {
        try {
            Order clone = (Order) super.clone();
            clone.setOrderStatus(OrderStatus.PENDING);
            clone.setAcceptedTimestamp(null);
            clone.setQuantity(10);
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
