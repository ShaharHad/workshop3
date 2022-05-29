package DataAccess;

import Domain.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Date;
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
        playerShahar = new Player("hshah", "12345Q", "shahar", new Date(2017,10,22),  "defence", "Madrid");
        playerInbar = new Player("inbarza", "5g5gT", "inbar", new Date(2017,10,22), "attack", "Madrid");
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