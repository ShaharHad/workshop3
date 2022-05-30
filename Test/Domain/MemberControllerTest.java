package Domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemberControllerTest
{
    MemberController mc;

    Player playerShahar;
    Coach coach1;
    Fan fan1;
    Manager manager1;
    Referee referee1;
    Owner ariasa;
    Representative representative1;

    Map<String, String> keyParamsShahar;
    Map<String, String> keyParamsCoach1;
    Map<String, String> keyParamsFan1;
    Map<String, String> keyParamsManager1;
    Map<String, String> keyParamsReferee1;
    Map<String, String> keyParamsAriasa;
    Map<String, String> keyParamsRepresentative1;

    //need to add the users below manually
    @BeforeAll
    public void beforeAll()
    {
        mc = MemberController.getInstance();

        playerShahar = new Player("hshah", "12345Q", "shahar", new Date(2017, 10, 22) , "defence", "Madrid");
        coach1 = new Coach("coach1", "coach1", "coach1", "main", "master", "barcelona");
        fan1 = new Fan("fan1", "fan1", "fan1");
        manager1 = new Manager("manager1", "manager1", "manager1", "Eagles" );
        referee1 = new Referee("referee1", "referee1", "referee1", "complete");
        ariasa = new Owner("ariasa", "12345q", "aria", "Eagles");
        representative1 = new Representative("representative1", "representative1", "representative1");

        keyParamsShahar = new HashMap<>();
        keyParamsCoach1 = new HashMap<>();
        keyParamsFan1 = new HashMap<>();
        keyParamsManager1 = new HashMap<>();
        keyParamsReferee1 = new HashMap<>();
        keyParamsAriasa = new HashMap<>();
        keyParamsRepresentative1 = new HashMap<>();

        keyParamsShahar.put("userName", playerShahar.getUserName());
        keyParamsCoach1.put("userName", coach1.getUserName());
        keyParamsFan1.put("userName", fan1.getUserName());
        keyParamsManager1.put("userName", manager1.getUserName());
        keyParamsReferee1.put("userName", referee1.getUserName());
        keyParamsAriasa.put("userName", ariasa.getUserName());
        keyParamsRepresentative1.put("userName", representative1.getUserName());

    }


    private Stream<Arguments> paramsProvider()
    {
        return Stream.of(
                Arguments.of(playerShahar),
                Arguments.of(coach1),
                Arguments.of(fan1),
                Arguments.of(manager1),
                Arguments.of(referee1),
                Arguments.of(ariasa),
                Arguments.of(representative1)
        );
    }




    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void userLoginTest(Member m)
    {
        Status success = Status.Failure;
        try
        {
            success = mc.userLogin(m.getUserName(), m.getPassword());
            assertEquals(success, Status.Success);
        }
        catch (Exception e)
        {
            assertEquals(success, Status.Success);
        }

    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void badUserLoginTest1(Member m)
    {
        Status success = Status.Failure;

        try
        {
            success = mc.userLogin(m.getRole(), m.getRole());
            assertEquals(success, Status.Failure);
        }
        catch (Exception e)
        {
            assertEquals(success, Status.Failure);
        }
        try
        {
            success = mc.userLogin(m.getUserName(), m.getRole());
            assertEquals(success, Status.Failure);
        }
        catch (Exception e)
        {
            assertEquals(success, Status.Failure);
        }
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void emptyUserLoginTest(Member m)
    {
        Status success = Status.Failure;
        try {
            success = mc.userLogin(m.getUserName(), null);
            assertEquals(success, Status.Failure);
        }
        catch (Exception e) {
            assertEquals(success, Status.Failure);
        }
        try
        {
            success = mc.userLogin(null, m.getPassword());
            assertEquals(success, Status.Failure);
        }
        catch (Exception e)
        {
            assertEquals(success, Status.Failure);
        }
    }

}