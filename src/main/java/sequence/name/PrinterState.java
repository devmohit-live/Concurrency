package sequence.name;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PrinterState {
    // Directly using enum as state can cause problem as Enum.Val1 != EnumVal2(both are treated as different objs)
    PrinterTurn turn;
}
