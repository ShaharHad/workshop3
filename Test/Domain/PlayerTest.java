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
class PlayerTest
{
    Player playerShahar;
    Player PlayerInbar;
    Map<String, String> keyParamsShahar;
    Map<String, String> keyParamsInbar;

    //need to add the users below manually
    @BeforeAll
    public void beforeAll()
    {
        playerShahar = new Player("hshah", "12345Q", "shahar", new Date(2017, 10, 22) , "defence", "Madrid");
        PlayerInbar = new Player("inbarza", "5g5gT", "inbar", new Date(2017, 10, 22),"attack", "Madrid");
        keyParamsShahar = new HashMap<>();
        keyParamsInbar = new HashMap<>();
        keyParamsShahar.put("userName", playerShahar.getUserName());
        keyParamsInbar.put("userName", PlayerInbar.getUserName());



    }


    private Stream<Arguments> paramsProvider()
    {
        return Stream.of(
                Arguments.of(playerShahar),
                Arguments.of(PlayerInbar)
        );
    }




    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void loginTest(Player p)
    {
        try
        {
            boolean success = p.login(p.getUserName(), p.getPassword());
            assertTrue(success);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void badLoginTest1(Player p)
    {
        try
        {
            boolean success = p.login(p.getRole(), p.getTeamName());
            assertFalse(success);
        }
        catch (Exception e)
        {
            assertEquals(e.getMessage(), "user doesn't exist");
        }
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void emptyLoginTest(Player p)
    {
        boolean success = false;
        try {
            success = p.login(null, p.getPassword());
            assertFalse(success);
        }
        catch (Exception e) {
            assertFalse(success);
        }
        try
        {
            success = p.login(p.getUserName(), null);
            assertFalse(success);
        }
        catch (Exception e)
        {
            assertFalse(success);
        }
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void getPlayerFromDBTest(Player p)
    {
        Player playerDB = Player.getPlayerFromDB(p.getUserName());
        assertEquals(playerDB.getUserName(), p.getUserName());
        assertEquals(playerDB.getPassword(), p.getPassword());
        assertEquals(playerDB.getName(), p.getName());
        assertEquals(playerDB.getTeamName(), p.getTeamName());
        assertEquals(playerDB.getRoleInTeam(), p.getRoleInTeam());
        assertEquals(playerDB.getRole(), p.getRole());
    }

}