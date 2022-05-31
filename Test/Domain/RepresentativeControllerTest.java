package Domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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




    private Stream<Arguments> nullParam()
    {
        Game gameDB = rpc.searchGame(teamArsenal, teamBarcelona, "Camp Nou", sqlDate);
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(gameDB, null)
        );
    }

    @ParameterizedTest
    @MethodSource({"nullParam"})
    void assignEmptyParamTest(Game game, List<Referee> lst)
    {
        Status status = rpc.assignRefsToGame(game, lst);
        assertEquals(status, Status.Failure);
    }


    private Stream<Arguments> paramsProvider()
    {
        Map<String, String> mReferee1 = new HashMap<>();
        mReferee1.put("referee1UN", "referee1");
        Map<String, String> mReferee2 = new HashMap<>();
        mReferee2.put("referee2UN", "referee2");
        Map<String, String> mMainReferee = new HashMap<>();
        mMainReferee.put("mainRefereeUN", "referee4");
        return Stream.of(
                Arguments.of(mReferee1),
                Arguments.of(mReferee2),
                Arguments.of(mMainReferee)
        );
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void removeRefFromGameSuccessTest(Map<String, String> m)
    {
        Game gameDB = rpc.searchGame(teamArsenal, teamBarcelona, "Camp Nou", sqlDate);
        assertNotNull(gameDB);
        Status status = rpc.removeRefFromGame(gameDB, m);
        assertEquals(status, Status.Success);
    }


    @Test
    void assignRefsToGameTest()
    {
        Game gameDB = rpc.searchGame(teamArsenal, teamBarcelona, "Camp Nou", sqlDate);
        assertNotNull(gameDB);
        List<Referee> list = rpc.searchAvailableReferee(sqlDate);
        Status status = rpc.assignRefsToGame(gameDB, list);
        assertEquals(status, Status.Success);

        // if we dont assign any referee to game we get status failure
        list = rpc.searchAvailableReferee(sqlDate);
        status = rpc.assignRefsToGame(gameDB, list);
        assertEquals(status, Status.Failure);
    }




}