package Domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RefereeTest
{

    Referee referee1;
    Referee referee2;
    Map<String, String> keyParamsReferee1;
    Map<String, String> keyParamsReferee2;
    Map<String, String> keyParams;
    boolean refereeMain;

    //need to add the users below manually
    @BeforeAll
    public void beforeAll()
    {
        referee1 = new Referee("referee1", "referee1", "referee1", "complete");
        referee2 = new Referee("referee2", "referee2", "referee2", "complete");
        keyParamsReferee1 = new HashMap<>();
        keyParamsReferee2 = new HashMap<>();
        keyParams = new HashMap<>();
        keyParamsReferee1.put("userName", referee1.getUserName());
        keyParamsReferee2.put("userName", referee2.getUserName());
        keyParams.put("userName", referee1.getUserName());
        keyParams.put("password", referee1.getPassword());
        keyParams.put("name", referee1.getName());
        keyParams.put("training", referee1.getTraining());
        refereeMain = referee1.isMainReferee();

    }

    private Stream<Arguments> paramsProvider()
    {
        return Stream.of(
                Arguments.of(referee1),
                Arguments.of(referee2)
        );
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void loginTest(Referee r)
    {
        try
        {
            boolean success = r.login(r.getUserName(), r.getPassword());
            assertTrue(success);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void badLoginTest(Referee r)
    {
        try
        {
            boolean success = r.login(r.getRole(), r.getUserName());
            assertFalse(success);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void emptyLoginTest(Referee r)
    {
        try
        {
            boolean success = r.login(null, r.getPassword());
            assertFalse(success);
            success = r.login(r.getUserName(), null);
            assertFalse(success);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void getOwnerFromDBTest(Referee r)
    {
        Referee refereeDB = Referee.getRefFromDB(r.getUserName());
        assertEquals(refereeDB.getUserName(), r.getUserName());
        assertEquals(refereeDB.getPassword(), r.getPassword());
        assertEquals(refereeDB.getName(), r.getName());
        assertEquals(refereeDB.getTraining(), r.getTraining());
        assertEquals(refereeDB.getRole(), r.getRole());
    }



    @Test
    void getTest()
    {
        for (String key : keyParams.keySet())
        {
            assertEquals(keyParams.get(key), referee1.get(key));
        }
        assertNull(referee1.get("jibrish"));
        assertNull(referee1.get(null));

    }

    @Test
    void isMainRefereeTest()
    {
        assertEquals(refereeMain, referee1.isMainReferee());
    }

}