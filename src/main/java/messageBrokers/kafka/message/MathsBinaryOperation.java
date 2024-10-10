package messageBrokers.kafka.message;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MathsBinaryOperation {
    int first;
    int second;
}
