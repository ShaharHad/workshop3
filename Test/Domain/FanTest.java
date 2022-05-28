package Domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FanTest
{

    Fan fan1;
    Fan fan2;
    Map<String, String> keyParamsShahar;
    Map<String, String> keyParamsInbar;

    //need to add the users below manually
    @BeforeAll
    public void beforeAll()
    {
        fan1 = new Fan("fan1", "fan1", "fan1");
        fan2 = new Fan("fan2", "fan2", "fan2");
        keyParamsShahar = new HashMap<>();
        keyParamsInbar = new HashMap<>();
        keyParamsShahar.put("userName", fan1.getUserName());
        keyParamsInbar.put("userName", fan2.getUserName());

    }


    private Stream<Arguments> paramsProvider()
    {
        return Stream.of(
                Arguments.of(fan1),
                Arguments.of(fan2)
        );
    }



    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void loginTest(Fan f)
    {
        try
        {
            boolean success = f.login(f.getPassword());
            assertTrue(success);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void badLoginTest(Fan f)
    {
        try
        {
            boolean success = f.login(f.getRole());
            assertFalse(success);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void emptyLoginTest(Fan f)
    {
        try
        {
            boolean success = f.login(null);
            assertFalse(success);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


}