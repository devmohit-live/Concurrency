package sequence.numbers.evenoddzero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NumberState {
    private Turn turn;
    private int number;
}
