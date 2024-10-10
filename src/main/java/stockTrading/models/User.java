package stockTrading.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class User {
    String userID;
    String userName;
    String phoneNumber;
    String emailId;
}
