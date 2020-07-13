import com.eclipsesource.json.JsonObject;
import domain.users.RegistrationData;
import domain.users.User;
import domain.users.UserService;
import infraestructure.UsersAPI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import spark.Request;
import spark.Response;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UsersAPIShould {
    private static final RegistrationData REGISTRATION_DATA = new RegistrationData("John", "1234");
    private static final User USER = new User(UUID.randomUUID().toString(), "John", "1234");

    @Mock
    Request request;
    @Mock
    Response response;
    @Mock
    UserService userService;

    private UsersAPI usersAPI;

    @Before
    public void initialize() {
        usersAPI = new UsersAPI(userService);

        // given
        given(request.body()).willReturn(jsonContaining(REGISTRATION_DATA));
        given(userService.createUser(REGISTRATION_DATA)).willReturn(USER);
    }

    @Test
    public void
    create_new_user() {
        // when
        usersAPI.create(request, response);

        // then
        verify(userService).createUser(REGISTRATION_DATA);
    }

    @Test
    public void
    return_json_representing_a_newly_created_user() {
        //when
        String result = usersAPI.create(request, response);

        // then
        verify(response).status(201);
        verify(response).type("application/json");
        assertThat(result).isEqualTo(jsonContaining(USER));
    }

    private String jsonContaining(RegistrationData registrationData) {
        return new JsonObject()
                .add("name", registrationData.getName())
                .add("password", registrationData.getPassword()).toString();
    }

    private String jsonContaining(User user) {
        return new JsonObject()
                .add("id", user.getId())
                .add("name", user.getName()).toString();
    }
}

