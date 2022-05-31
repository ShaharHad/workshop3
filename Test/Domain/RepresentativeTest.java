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
class RepresentativeTest
{

    Representative representative1;
    Representative representative2;
    Map<String, String> keyParamsRepresentative1;
    Map<String, String> keyParamsRepresentative2;


    @BeforeAll
    public void beforeAll()
    {
        representative1 = new Representative("representative1", "representative1", "representative1");
        representative2 = new Representative("representative2", "representative2", "representative2");
        keyParamsRepresentative1 = new HashMap<>();
        keyParamsRepresentative2 = new HashMap<>();
        keyParamsRepresentative1.put("userName", representative1.getUserName());
        keyParamsRepresentative2.put("userName", representative2.getUserName());

    }


    private Stream<Arguments> paramsProvider()
    {
        return Stream.of(
                Arguments.of(representative1),
                Arguments.of(representative2)
        );
    }



    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void loginTest(Representative r)
    {
        try
        {
            boolean success = r.login(r.getPassword());
            assertTrue(success);
        }
        catch (Exception e) { System.out.println(e.getMessage()); }
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void badLoginTest(Representative r)
    {
        try
        {
            boolean success = r.login(r.getRole());
            assertFalse(success);
        }
        catch (Exception e) { System.out.println(e.getMessage()); }
    }

    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void emptyLoginTest(Representative r)
    {
        try
        {
            boolean success = r.login(null);
            assertFalse(success);
        }
        catch (Exception e)
        { assertEquals(e.getMessage(), "One of the parameters is null"); }
    }

    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void getRepresentative(Representative r)
    {
        Representative representativeDB = Representative.getRepresentativeFromDB(r.getUserName());
        assertEquals(representativeDB.getUserName(), r.getUserName());
        assertEquals(representativeDB.getPassword(), r.getPassword());
        assertEquals(representativeDB.getName(), r.getName());
        assertEquals(representativeDB.getRole(), r.getRole());
    }

}