package Domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RepresentativeControllerTest
{

    RepresentativeController rpc = RepresentativeController.getInstance();
    Team teamBarcelona;
    Team teamArsenal;
    java.sql.Date sqlDate;

    @BeforeAll
    void beforeAll()
    {
        Owner ownerBarcelona = new Owner("joanGG", "Bar123", "Joan Gamper", "Barcelona");
        try
        {
            teamBarcelona = new Team(ownerBarcelona, "Barcelona", "Camp Nou");
        } catch (Exception e)
        {
            assertEquals(e.getMessage(), "one of the params is null!");
        }
        Owner ownerArsenal = new Owner("stanKr", "stArs", "Stan Kroenke", "Arsenal");
        try
        {
            teamArsenal = new Team(ownerArsenal, "Arsenal", "Emirates Stadium");
        } catch (Exception e)
        {
            assertEquals(e.getMessage(), "one of the params is null!");
        }
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            java.util.Date utilDate = format.parse("04-08-2019");
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) { System.out.println(e.getMessage()); }

    }

    @Test
    void testSearchGameSuccess()
    {
        Game gameDB = rpc.searchGame(teamArsenal, teamBarcelona, "Camp Nou", sqlDate);
        assertNotNull(gameDB, "should find game");
    }

    @Test
    void testSearchGameFailure()
    {
        Game gameDB = rpc.searchGame(teamArsenal, teamBarcelona, "Emirates Stadium", sqlDate);
        assertNull(gameDB, "should not find game");
    }

    @Test
    void testUpdateGameSuccess()
    {
        Game gameDB = rpc.searchGame(teamArsenal, teamBarcelona, "Camp Nou", sqlDate);
        assertNotNull(gameDB, "should find game");
        Map<String, String> newParams = new HashMap<>();
        newParams.put("seasonID", "2");
        Status status = rpc.updateGame(gameDB, newParams);
        assertEquals(status, Status.Success);
    }

    @Test
    void testUpdateGameFailure()
    {
        Game gameDB = rpc.searchGame(teamArsenal, teamBarcelona, "Camp Nou", sqlDate);
        assertNotNull(gameDB, "should find game");
        Map<String, String> newParams = new HashMap<>();
        Status status = rpc.updateGame(gameDB, newParams);
        assertEquals(status, Status.Failure);
    }

}