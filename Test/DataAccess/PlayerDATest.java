package DataAccess;

import Domain.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class PlayerDATest {

    Player playerShahar;
    Player playerInbar;
    PlayerDA p = PlayerDA.getInstance();
    Map<String, String> keyParamsShahar;
    Map<String, String> keyParamsInbar;

    //need to add the 2 users below to the DB manually
    @BeforeAll
    public void beforeAll()
    {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        java.sql.Date sqlDate;
        try {
            java.util.Date utilDate = format.parse("22-10-2017");
            sqlDate = new java.sql.Date(utilDate.getTime());
            playerShahar = new Player("hshah", "12345Q", "shahar", sqlDate,  "defence", "Madrid");
        } catch (ParseException e) { System.out.println(e.getMessage()); }
        try {
            java.util.Date utilDate = format.parse("22-10-2017");
            sqlDate = new java.sql.Date(utilDate.getTime());
            playerInbar = new Player("inbarza", "5g5gT", "inbar", sqlDate, "attack", "Madrid");
        } catch (ParseException e) { System.out.println(e.getMessage()); }
        keyParamsShahar = new HashMap<>();
        keyParamsInbar = new HashMap<>();
        keyParamsShahar.put("userName", "hshah");
        keyParamsInbar.put("userName", "inbarza");
    }

    private Stream<Arguments> paramsProvider()
    {
        return Stream.of(
                Arguments.of(playerShahar, keyParamsShahar),
                Arguments.of(playerInbar, keyParamsInbar)
        );
    }

    @ParameterizedTest
    @MethodSource("paramsProvider")
    void testGet(Player playerT, Map<String, String> keyParamsT)
    {
        Player player = p.get(keyParamsT);
        assertEquals(player.getUserName(), playerT.getUserName());
    }

}