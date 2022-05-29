package Domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CoachTest
{

    Coach coach1;
    Coach coach2;
    Map<String, String> keyParamsCoach1;
    Map<String, String> keyParamsCoach2;

    //need to add the users below manually
    @BeforeAll
    public void beforeAll()
    {
        coach1 = new Coach("coach1", "coach1", "coach1", "main", "master", "barcelona");
        coach2 = new Coach("coach2", "coach2", "coach2", "secondary", "master", "barcelona");
        keyParamsCoach1 = new HashMap<>();
        keyParamsCoach2 = new HashMap<>();
        keyParamsCoach1.put("userName", coach1.getUserName());
        keyParamsCoach2.put("userName", coach2.getUserName());

    }

    private Stream<Arguments> paramsProvider()
    {
        return Stream.of(
                Arguments.of(coach1),
                Arguments.of(coach2)
        );
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void loginTest(Coach c)
    {
        try
        {
            boolean success = c.login(c.getUserName(), c.getPassword());
            assertTrue(success);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void badLoginTest(Coach c)
    {
        try
        {
            boolean success = c.login(c.getRole(), c.getTeamName());
            assertFalse(success);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void emptyLoginTest(Coach c)
    {
        try
        {
            boolean success = c.login(null, null);
            assertFalse(success);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void getOwnerFromDBTest(Coach c)
    {
        Coach coachDB = Coach.getCoachFromDB(c.getUserName());
        assertEquals(coachDB.getUserName(), c.getUserName());
        assertEquals(coachDB.getPassword(), c.getPassword());
        assertEquals(coachDB.getName(), c.getName());
        assertEquals(coachDB.getTeamName(), c.getTeamName());
        assertEquals(coachDB.getRole(), c.getRole());
        assertEquals(coachDB.getTraining(), c.getTraining());
        assertEquals(coachDB.getRoleInTeam(), c.getRoleInTeam());
    }


}