package infraestructure;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import domain.users.RegistrationData;
import domain.users.User;
import domain.users.UserService;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

public class UsersAPI {

    private UserService userService;

    public UsersAPI(UserService userService) {
        this.userService = userService;
    }

    public String create(Request request, Response response) {
        RegistrationData data = registrationDataFromRequest(request);
        User user = userService.createUser(data);

        response.status(HttpStatus.CREATED_201);
        response.type("application/json");

        return UserJson.jsonFor(user);
    }

    private RegistrationData registrationDataFromRequest(Request request) {
        JsonObject object = Json.parse(request.body()).asObject();
        return new RegistrationData(object.getString("name", ""),
                                    object.getString("password", ""));
    }
}
