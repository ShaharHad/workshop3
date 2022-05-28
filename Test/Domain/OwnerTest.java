package Domain;

import DataAccess.OwnerDA;
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
class OwnerTest
{
    Owner ownerAria;
    Owner ownerShai;
    Map<String, String> keyParamsShai;
    Map<String, String> keyParamsAria;
    Map<String, String> keyParams;

    //need to add the users below manually
    @BeforeAll
    public void beforeAll()
    {
        ownerAria = new Owner("ariasa", "12345q", "aria", "Eagles");
        ownerShai = new Owner("shaisa", "5g5gt", "shai", "Eagles");
        keyParamsAria = new HashMap<>();
        keyParamsShai = new HashMap<>();
        keyParams = new HashMap<>();
        keyParamsAria.put("userName", ownerAria.getUserName());
        keyParamsShai.put("userName", ownerShai.getUserName());
        keyParams.put("userName", ownerAria.getUserName());
        keyParams.put("password", ownerAria.getPassword());
        keyParams.put("name", ownerAria.getName());
        keyParams.put("teamName", ownerAria.getTeamName());


    }

    private Stream<Arguments> paramsProvider()
    {
        return Stream.of(
                Arguments.of(ownerAria),
                Arguments.of(ownerShai)
        );
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void loginTest(Owner o)
    {
        try
        {
            boolean success = o.login(o.getUserName(), o.getPassword());
            assertTrue(success);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void badLoginTest(Owner o)
    {
        try
        {
            boolean success = o.login(o.getRole(), o.getTeamName());
            assertFalse(success);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void emptyLoginTest(Owner o)
    {
        try
        {
            boolean success = o.login(null, null);
            assertFalse(success);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void getOwnerFromDBTest(Owner o)
    {
        Owner ownerDB = Owner.getOwnerFromDB(o.getUserName());
        assertEquals(ownerDB.getUserName(), o.getUserName());
        assertEquals(ownerDB.getPassword(), o.getPassword());
        assertEquals(ownerDB.getName(), o.getName());
        assertEquals(ownerDB.getTeamName(), o.getTeamName());
    }



    @Test
    void getTest()
    {
        for (String key : keyParams.keySet())
        {
            assertEquals(keyParams.get(key), ownerAria.get(key));
        }

    }
}