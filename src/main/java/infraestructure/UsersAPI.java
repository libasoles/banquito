package infraestructure;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import domain.users.RegistrationData;
import domain.users.UserService;
import spark.Request;
import spark.Response;

public class UsersAPI {

    private UserService userService;

    public UsersAPI(UserService userService) {
        this.userService = userService;
    }

    public String create(Request request, Response response) {
        RegistrationData data = registrationDataFromRequest(request);
        userService.createUser(data);
        return "";
    }

    private RegistrationData registrationDataFromRequest(Request request) {
        JsonObject object = Json.parse(request.body()).asObject();
        return new RegistrationData(object.getString("name", ""),
                                    object.getString("password", ""));
    }
}
