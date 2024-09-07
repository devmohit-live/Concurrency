package taskScheduler;


import lombok.Getter;

public interface Payload<T> {
    T getPayload();
}
