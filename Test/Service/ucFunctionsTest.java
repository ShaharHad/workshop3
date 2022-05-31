package Service;

import Domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ucFunctionsTest
{

    Team teamBarcelona;
    Team teamArsenal;
    java.sql.Date sqlDate;
    Owner ownerBarcelona;
    Owner ownerArsenal;
    Game game;

    @BeforeAll
    void beforeAll()
    {

        ownerBarcelona = new Owner("joanGG", "Bar123", "Joan Gamper", "Barcelona");
        try
        {
            teamBarcelona = new Team(ownerBarcelona, "Barcelona", "Camp Nou");
        } catch (Exception e)
        {
            assertEquals(e.getMessage(), "one of the params is null!");
        }
        ownerArsenal = new Owner("stanKr", "stArs", "Stan Kroenke", "Arsenal");
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
        try{
            game = new Game(teamArsenal, teamBarcelona);
            game.setDate(sqlDate);
            game.setField("Camp Nou");
        }
        catch (Exception e){
            assertEquals(e.getMessage(), "one of the params is null!");
        }

    }


    private Stream<Arguments> paramsProvider()
    {
        return Stream.of(
                Arguments.of(ownerBarcelona),
                Arguments.of(ownerArsenal)
        );
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void userLoginSuccessTest(Member m)
    {
        Status success;
        ucFunctions uf = new ucFunctions();
        success = uf.userLogin(m.getUserName(), m.getPassword());
        assertEquals(success, Status.Success);
    }

        @Test
    void userLoginFailureTest()
    {
        Status success;
        ucFunctions uf = new ucFunctions();
        success = uf.userLogin("noUserName", "noPassowrd");
        assertEquals(success, Status.Failure);
        success = uf.userLogin(ownerArsenal.getUserName(), "noPassowrd");
        assertEquals(success, Status.Failure);
    }

    @Test
    void scheduleGame()
    {
        ucFunctions uf = new ucFunctions();
        uf.scheduleGame(game, sqlDate);
    }

    private Stream<Arguments> refParamsProvider()
    {
        // in map the keys are the referee's column name in games table and the value are the referee's usernames in the game's table
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
    @MethodSource({"refParamsProvider"})
    void removeRef(Map<String, String> removeParam)
    {
        ucFunctions uf = new ucFunctions();
        Status success = uf.removeRef(teamArsenal, teamBarcelona, "Camp Nou", sqlDate, removeParam);
        assertEquals(success, Status.Success);
    }


    @Test
    void assignRef()
    {
        ucFunctions uf = new ucFunctions();
        Status success = uf.assignRef(teamArsenal, teamBarcelona, "Camp Nou", sqlDate);
        assertEquals(success, Status.Success);

        // if no referee get assign to the game we get Status.Failure
        success = uf.assignRef(teamArsenal, teamBarcelona, "Camp Nou", sqlDate);
        assertEquals(success, Status.Failure);
    }


}