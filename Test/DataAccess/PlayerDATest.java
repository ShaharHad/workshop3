//package DataAccess;
//
//import Domain.Owner;
//import Domain.Player;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//
//
//public class PlayerDATest {
//    PlayerDA p = PlayerDA.getInstance();
//    Owner ownerLeah;
//    Owner ownerMaxim;
//    OwnerDA o = OwnerDA.getInstance();
//    Map<String, String> keyParamsLeah;
//    Map<String, String> keyParamsMaxim;
//    Map<String, String> keyParamsEmpty;
//    Map<String, String> updateParamsLeah;
//    Map<String, String> updateParamsMaxim;
//    Map<String, String> updateParamsEmpty;
//
//    @BeforeAll
//    public void beforeAll()
//    {
//        ownerLeah = new Owner("leahma", "12345Q", "", "Eagles");
//        ownerMaxim = new Owner("maxi2", "5g5gT", "maxim", "");
//        keyParamsLeah = new HashMap<>();
//        keyParamsMaxim = new HashMap<>();
//        keyParamsEmpty = new HashMap<>();
//        keyParamsLeah.put("userName", ownerLeah.getUserName());
//        keyParamsMaxim.put("userName", ownerMaxim.getUserName());
//        updateParamsLeah = new HashMap<>();
//        updateParamsMaxim = new HashMap<>();
//        updateParamsEmpty = new HashMap<>();
//        updateParamsLeah.put("name", "leah");
//        updateParamsMaxim.put("teamName", "Liverpool");
//    }
//
//    private Stream<Arguments> paramsProvider()
//    {
//        return Stream.of(
//                Arguments.of(ownerLeah, keyParamsLeah),
//                Arguments.of(ownerMaxim, keyParamsMaxim)
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("paramsProvider")
//    void testGet(Player playerT, Map<String, String> keyParamsT)
//    {
//        try { p.save(playerT); } catch (Exception e) { System.out.println("problem in save method"); }
//        Player player = p.get(keyParamsT);
//        assertEquals(player.getUserName(), playerT.getUserName());
//    }
//}
