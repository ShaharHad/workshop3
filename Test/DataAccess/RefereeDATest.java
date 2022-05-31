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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RefereeDATest
{
    ////////////////////////////////////////////
    Referee referee3;
    Referee referee4;
    Referee referee5;
    Referee referee6;
    Map<String, String> keyParamsReferee;
    Owner guestOwnerEagles;
    Team guestTeamEagles;
    Owner homeOwnerFalcons;
    Team homeTeamFalcons;
    Game gameEaglesFalcons;






    ///////////////////////////////////////////

    Referee refereeLeah;
    Referee refereeMaxim;
    RefereeDA r = RefereeDA.getInstance();
    Map<String, String> keyParamsLeah;
    Map<String, String> keyParamsMaxim;
    Map<String, String> keyParamsEmpty;
    Map<String, String> updateParamsLeah;
    Map<String, String> updateParamsMaxim;
    Map<String, String> updateParamsEmpty;

    @BeforeAll
    public void beforeAll() throws Exception {
        refereeLeah = new Referee("leahma", "12345Q", "", "Grade 8");
        refereeMaxim = new Referee("maxi2", "5g5gT", "maxim", "");
        keyParamsLeah = new HashMap<>();
        keyParamsMaxim = new HashMap<>();
        keyParamsLeah.put("userName", refereeLeah.getUserName());
        keyParamsMaxim.put("userName", refereeMaxim.getUserName());
        keyParamsEmpty = new HashMap<>();
        updateParamsLeah = new HashMap<>();
        updateParamsMaxim = new HashMap<>();
        updateParamsEmpty = new HashMap<>();
        updateParamsLeah.put("name", "leah");
        updateParamsLeah.put("training", "Grade 6");
        updateParamsMaxim.put("training", "Grade 1");


        keyParamsReferee = new HashMap<>();
        referee3 = new Referee("referee3", "referee3", "referee3", "Grade 8");
        referee4 = new Referee("referee4", "referee4", "referee4", "");
        referee5 = new Referee("referee5", "referee5", "referee5", "Grade 8");
        referee6 = new Referee("referee6", "referee6", "referee6", "");
        keyParamsReferee.put("date", "2021-01-01");

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
        } catch (
                ParseException e) { System.out.println(e.getMessage()); }
        gameEaglesFalcons.setField("Mercedes-Benz Stadium");

    }

    private Stream<Arguments> noRefereeParams()
    {
        return Stream.of(
                Arguments.of(null, keyParamsEmpty),
                Arguments.of(null, keyParamsEmpty)
        );
    }

    @ParameterizedTest
    @MethodSource({"noRefereeParams"})
    void noRefereeSaveTest(Referee refereeT)
    {
        try { r.save(refereeT); }
        catch (Exception e) { assertEquals(e.getMessage(), "member is null"); }
    }

    private Stream<Arguments> noRefereeUpdateParams()
    {
        return Stream.of(
                Arguments.of(null, updateParamsLeah),
                Arguments.of(refereeMaxim, updateParamsEmpty)
        );
    }

    @ParameterizedTest
    @MethodSource({"noRefereeUpdateParams"})
    void noRefereeUpdateTest(Referee refereeT, Map<String, String> updateParamsT)
    {
        try { r.update(refereeT, updateParamsT); }
        catch (Exception e) { assertEquals(e.getMessage(), "one of the parameters is null"); }
    }

    private Stream<Arguments> noMemberParams()
    {
        return Stream.of(
                Arguments.of(refereeLeah, updateParamsLeah),
                Arguments.of(refereeMaxim, updateParamsMaxim)
        );
    }

    @ParameterizedTest
    @MethodSource({"noMemberParams"})
    void noMemberUpdateTest(Referee refereeT, Map<String, String> updateParamsT)
    {
        try { r.update(refereeT, updateParamsT); }
        catch (Exception e) { assertEquals(e.getMessage(), "member doesn't exist"); }
    }


    private Stream<Arguments> saveSuccessParams()
    {
        return Stream.of(
                Arguments.of(refereeLeah, keyParamsLeah),
                Arguments.of(refereeMaxim, keyParamsMaxim)
        );
    }

    @ParameterizedTest
    @MethodSource({"saveSuccessParams"})
    void testSaveSuccess(Referee refereeT, Map<String, String> keyParamsT)
    {
        try { r.save(refereeT); } catch (Exception e) { System.out.println("problem in save method"); }
        Referee owner;
        try { owner = r.get(keyParamsT); }
        catch (Exception e) { throw new RuntimeException(e); }
        assertNotNull(owner, "user wasn't saved correctly");
    }


    private Stream<Arguments> updateSuccessParams()
    {
        return Stream.of(
                Arguments.of(refereeLeah, keyParamsLeah, updateParamsLeah),
                Arguments.of(refereeMaxim, keyParamsMaxim, updateParamsMaxim)
        );
    }

    @ParameterizedTest
    @MethodSource({"updateSuccessParams"})
    void testUpdateSuccess(Referee refereeT, Map<String, String> keyParamsT, Map<String, String> updateParams)
    {
        try {
            r.update(refereeT, updateParams);
            Referee referee = r.get(keyParamsT);
            for (String key : updateParams.keySet())
            {
                assertEquals(updateParams.get(key), referee.get(key));
            }

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @MethodSource("noRefereeParams")
    void testDeleteNoSuccess(Referee refereeT,  Map<String, String> keyParamsT)
    {
        try { r.delete(refereeT); }
        catch (Exception e) { assertEquals(e.getMessage(), "referee is null"); }
    }

    @ParameterizedTest
    @MethodSource("saveSuccessParams")
    void testDeleteNoTable(Referee refereeT,  Map<String, String> keyParamsT)
    {
        try { r.delete(refereeT); }
        catch (Exception e) { assertEquals(e.getMessage(), "role is null"); }
    }

    @ParameterizedTest
    @MethodSource("saveSuccessParams")
    void testDeleteSuccess(Referee refereeT,  Map<String, String> keyParamsT)
    {
        try { r.delete(refereeT); } catch (Exception e) { throw new RuntimeException(e); }
        try
        {
            Referee referee = r.get(keyParamsT);
            assertNull(referee, "user wasn't deleted correctly");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllAvailableRefereeTest()
    {
        RefereeDA rda = RefereeDA.getInstance();
        List<Referee> list = new ArrayList<>();
        try
        {
            list = rda.getAllAvailableReferee(keyParamsReferee);
            assert list.size() > 0;

        }
        catch (Exception e)
        {
            assert list.size() > 0;
        }
    }

    @Test
    void getAllAvailableRefereeNullTest()
    {
        RefereeDA rda = RefereeDA.getInstance();
        List<Referee> list = new ArrayList<>();
        try
        {
            list = rda.getAllAvailableReferee(new HashMap<>());
            assertNull(list);

        }
        catch (Exception e)
        {
            assertNull(list);
        }
    }


}