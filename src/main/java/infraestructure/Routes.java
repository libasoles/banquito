package infraestructure;
import domain.users.UserService;

import static spark.Spark.*;

public class Routes {
    private UsersAPI usersAPI = new UsersAPI(new UserService());

    public void create() {
        get("/hello", (req, res) -> "Hello World");
        post("/users", (req, res) -> usersAPI.create(req, res));
    }
}
