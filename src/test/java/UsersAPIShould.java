import com.eclipsesource.json.JsonObject;
import domain.users.RegistrationData;
import domain.users.UserService;
import infraestructure.UsersAPI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import spark.Request;
import spark.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UsersAPIShould {
    private static final RegistrationData REGISTRATION_DATA = new RegistrationData("John", "1234");

    @Mock Request request;
    @Mock Response response;
    @Mock UserService userService;

    private UsersAPI usersAPI;

    @Before public void initialize() {
        usersAPI = new UsersAPI(userService);
    }

    @Test public void
    create_new_user() {
        // given
        given(request.body()).willReturn(jsonContaining(REGISTRATION_DATA));

        // when
        usersAPI.create(request, response);

        // then
        verify(userService).createUser(REGISTRATION_DATA);
    }

    private String jsonContaining(RegistrationData registrationData) {
        return new JsonObject()
                .add("name", registrationData.getName())
                .add("password", registrationData.getPassword()).toString();
    }
}

