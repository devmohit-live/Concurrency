package taskScheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MathPayload implements Payload {
    private int a;
    private int b;

    @Override
    public Object getPayload() {
        return this;
    }
}
