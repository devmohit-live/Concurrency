package stockTrading.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MatchingOrder {
    Order order1;
    Order order2;
}
