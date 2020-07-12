package infraestructure;
import static spark.Spark.*;

public class BanquitoAPI {
    public static void create() {
        get("/hello", (req, res) -> "Hello World");
    }
}
