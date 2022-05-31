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
class ManagerTest
{
    Manager manager1;
    Manager manager2;
    Map<String, String> keyParamsManager1;
    Map<String, String> keyParamsManager2;

    //need to add the users below manually
    @BeforeAll
    public void beforeAll()
    {
        manager1 = new Manager("manager1", "manager1", "manager1", "Eagles");
        manager2 = new Manager("manager2", "manager2", "manager2", "Eagles");
        keyParamsManager1 = new HashMap<>();
        keyParamsManager2 = new HashMap<>();
        keyParamsManager1.put("userName", manager1.getUserName());
        keyParamsManager2.put("userName", manager2.getUserName());
    }


    private Stream<Arguments> paramsProvider()
    {
        return Stream.of(
                Arguments.of(manager1),
                Arguments.of(manager2)
        );
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void loginTest(Manager m)
    {
        try
        {
            boolean success = m.login(m.getUserName(), m.getPassword());
            assertTrue(success);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void badLoginTest(Manager m)
    {
        try
        {
            boolean success = m.login(m.getRole(), m.getTeamName());
            assertFalse(success);
        }
        catch (Exception e)
        {
            assertEquals(e.getMessage(), "user doesn't exist");
        }
    }

    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void emptyLoginTest(Manager m)
    {
        boolean success = false;
        try
        {
            success = m.login(null, m.getPassword());
            assertFalse(success);
        }
        catch (Exception e) {
            assertFalse(success);
        }
        try
        {
            success = m.login(m.getUserName(), null);
            assertFalse(success);
        }
        catch (Exception e)
        {
            assertFalse(success);
        }
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void getOwnerFromDBTest(Manager m)
    {
        Manager managerDB = Manager.getManagerFromDB(m.getUserName());
        assertEquals(managerDB.getUserName(), m.getUserName());
        assertEquals(managerDB.getPassword(), m.getPassword());
        assertEquals(managerDB.getName(), m.getName());
        assertEquals(managerDB.getTeamName(), m.getTeamName());
        assertEquals(managerDB.getRole(), m.getRole());
    }



}