package infraestructure;

import com.eclipsesource.json.JsonObject;
import domain.users.User;

public class UserJson {
    public static String jsonFor(User user) {
        return new JsonObject()
                .add("id", user.getId())
                .add("name", user.getName())
                .toString();
    }
}
