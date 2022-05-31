package DataAccess;

import Domain.Game;
import Domain.Owner;
import Domain.Referee;
import Domain.Team;
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
class GameDATest
{
    Game gameEaglesFalcons, gameLiverpoolChelsea;
    Game nullTeamsGame, singleTeamNullGame;
    GameDA g = GameDA.getInstance();
    Team homeTeamFalcons, homeTeamChelsea;
    Team guestTeamEagles, guestTeamLiverpool;
    Owner homeOwnerFalcons, homeOwnerChelsea;
    Owner guestOwnerEagles, guestOwnerLiverpool;

    Map<String, String> keyParamsEaglesFalcons;
    Map<String, String> keyParamsLiverpoolChelsea;

    Map<String, String> keyParamsCheckRefereeInGame;

    @BeforeAll
    void beforeAll()
    {
        guestOwnerEagles = new Owner("Jeff123", "eagles4Ev", "Jeffrey Lurie", "Eagles");
        try {
            guestTeamEagles = new Team(guestOwnerEagles, "Eagles", "Lincoln Financial Field");
        } catch(Exception e) { assertEquals(e.getMessage(), "one of the params is null!"); }
        homeOwnerFalcons = new Owner("artyBl", "falconsRule", "Arthur Blank", "Falcons");
        try {
            homeTeamFalcons = new Team(homeOwnerFalcons, "Falcons", "Mercedes-Benz Stadium");
        } catch(Exception e) { assertEquals(e.getMessage(), "one of the params is null!"); }
        try {
            gameEaglesFalcons = new Game(guestTeamEagles, homeTeamFalcons);
        } catch(Exception e) { assertEquals(e.getMessage(), "one of the params is null!"); }
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
        java.util.Date utilDate = format.parse("12-09-2021");
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        gameEaglesFalcons.setDate(sqlDate);
        } catch (ParseException e) { System.out.println(e.getMessage()); }
        gameEaglesFalcons.setField("Mercedes-Benz Stadium");

        guestOwnerLiverpool = new Owner("johnny4", "liverLive", "John W. Henry", "Liverpool");
        try {
            guestTeamLiverpool = new Team(guestOwnerLiverpool, "Liverpool", "Anfield");
        } catch(Exception e) { assertEquals(e.getMessage(), "one of the params is null!"); }
        homeOwnerChelsea = new Owner("romRom", "che123", "Roman Abramovich", "Chelsea");
        try {
            homeTeamChelsea = new Team(homeOwnerChelsea, "Chelsea", "Stamford Bridge");
        } catch(Exception e) { assertEquals(e.getMessage(), "one of the params is null!"); }
        try {
            gameLiverpoolChelsea = new Game(guestTeamLiverpool, homeTeamChelsea);
        } catch(Exception e) { assertEquals(e.getMessage(), "one of the params is null!"); }
        try {
            java.util.Date utilDate = format.parse("02-01-2022");
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            gameLiverpoolChelsea.setDate(sqlDate);
        } catch (ParseException e) { System.out.println(e.getMessage()); }
        gameLiverpoolChelsea.setField("Stamford Bridge");

        try {
            nullTeamsGame = new Game(null, null);
        } catch(Exception e) { assertEquals(e.getMessage(), "one of the params is null!"); }

        try {
            singleTeamNullGame = new Game(guestTeamEagles, null);
        } catch(Exception e) { assertEquals(e.getMessage(), "one of the params is null!"); }

        keyParamsEaglesFalcons = new HashMap<>();
        keyParamsEaglesFalcons.put("guestTeam", "Eagles");
        keyParamsEaglesFalcons.put("homeTeam", "Falcons");
        keyParamsEaglesFalcons.put("fieldName", "Mercedes-Benz Stadium");
        keyParamsEaglesFalcons.put("date", "2021-09-12");

        keyParamsLiverpoolChelsea = new HashMap<>();
        keyParamsLiverpoolChelsea.put("guestTeam", "Liverpool");
        keyParamsLiverpoolChelsea.put("homeTeam", "Chelsea");
        keyParamsLiverpoolChelsea.put("fieldName", "Stamford Bridge");
        keyParamsLiverpoolChelsea.put("date", "2022-01-02");

        keyParamsCheckRefereeInGame = new HashMap<>();
        keyParamsCheckRefereeInGame.put("guestTeam", "Eagles");
        keyParamsCheckRefereeInGame.put("homeTeam", "Falcons");
        keyParamsCheckRefereeInGame.put("fieldName", "Mercedes-Benz Stadium");
        keyParamsCheckRefereeInGame.put("date", "2022-11-11");

    }

    private Stream<Arguments> nullTeamsParam()
    {
        return Stream.of(
                Arguments.of(nullTeamsGame),
                Arguments.of(singleTeamNullGame)
        );
    }

    @ParameterizedTest
    @MethodSource("nullTeamsParam")
    void testNullTeamsGame(Game gameT)
    {
        try
        { g.save(gameT); }
        catch (Exception e) {
            assertEquals(e.getMessage(), "game is null");
        }
    }

    private Stream<Arguments> gamesSaveParams()
    {
        return Stream.of(
                Arguments.of(gameEaglesFalcons, keyParamsEaglesFalcons),
                Arguments.of(gameLiverpoolChelsea, keyParamsLiverpoolChelsea)
        );
    }

    @ParameterizedTest
    @MethodSource("gamesSaveParams")
    void testSaveGame(Game gameT, Map<String, String> keyParams)
    {
        try { g.save(gameT); }
        catch (Exception e) {
            assertEquals(e.getMessage(), "Error connecting to the database");
        }
        Game gameDB = g.get(keyParams);
        assertEquals(gameDB.getGuestGroup().getTeamName(), gameT.getGuestGroup().getTeamName());
        assertEquals(gameDB.getHomeGroup().getTeamName(), gameT.getHomeGroup().getTeamName());
        assertEquals(gameDB.getDate(), gameT.getDate());
        assertEquals(gameDB.getField(), gameT.getField());
    }

    private Stream<Arguments> gamesUpdateParams()
    {
        Map<String, String> updateEaglesFalcons = new HashMap<>();
        updateEaglesFalcons.put("hour", "11");
        updateEaglesFalcons.put("date", "11-11-2022");

        Map<String, String> updateLiverpoolChelsea = new HashMap<>();
        updateLiverpoolChelsea.put("hour", "15");

        return Stream.of(
                Arguments.of(gameEaglesFalcons, updateEaglesFalcons),
                Arguments.of(gameLiverpoolChelsea, updateLiverpoolChelsea)
        );
    }

    @ParameterizedTest
    @MethodSource("gamesUpdateParams")
    void testUpdateGame(Game gameT, Map<String, String> newParamsT)
    {
        try {
            g.update(gameT, newParamsT);
        } catch (Exception e) {
            assertEquals("Error connecting to the database", e.getMessage());
        }
    }


    private Stream<Arguments> gamesRefereeUpdateParams()
    {
        Map<String, String> updateEaglesFalcons = new HashMap<>();
        updateEaglesFalcons.put("referee1UN", "referee1");
        updateEaglesFalcons.put("referee2UN", "referee2");
        updateEaglesFalcons.put("mainRefereeUN", "referee3");

        return Stream.of(
                Arguments.of(gameEaglesFalcons, updateEaglesFalcons)
        );
    }

    @ParameterizedTest
    @MethodSource("gamesRefereeUpdateParams")
    void testUpdateRefereeGame(Game gameT, Map<String, String> newParamsT)
    {
        try {
            g.update(gameT, newParamsT);
        } catch (Exception e) {
            assertEquals("Error connecting to the database", e.getMessage());
        }
    }

    private Stream<Arguments> gamesDelParams()
    {
        return Stream.of(
                Arguments.of(gameLiverpoolChelsea)
        );
    }

    @ParameterizedTest
    @MethodSource("gamesDelParams")
    void testDelete(Game gameT)
    {
        try {
            g.delete(gameT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private Stream<Arguments> gamesWithReferee()
    {
        return Stream.of(
                Arguments.of(keyParamsCheckRefereeInGame)
        );
    }

    @ParameterizedTest
    @MethodSource("gamesWithReferee")
    void checkRefereeInGame(Map<String, String> map)
    {

        Game game = g.get(map);
        if (game == null)
        {
            System.out.println("no game found in the system");
        }
        else
        {
            RefereeDA rda = RefereeDA.getInstance();
            Map<String, String> referee1InGame = new HashMap<>();
            Map<String, String> referee2InGame = new HashMap<>();
            Map<String, String> mainRefereeInGame = new HashMap<>();
            referee1InGame.put("userName", "referee1");
            referee2InGame.put("userName", "referee2");
            mainRefereeInGame.put("userName", "referee3");
            if(game.getReferee1() != null)
            {
                Referee ref1 = rda.get(referee1InGame);
                assertEquals(ref1.getUserName(), "referee1");
            }
            if(game.getReferee2() != null)
            {
                Referee ref2 = rda.get(referee2InGame);
                assertEquals(ref2.getUserName(), "referee2");
            }
            if(game.getMainReferee() != null)
            {
                Referee mainRef = rda.get(mainRefereeInGame);
                assertEquals(mainRef.getUserName(), "referee3");
            }
        }
    }
}