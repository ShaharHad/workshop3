package DataAccess;

import Domain.Coach;
import Domain.Owner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CoachDATest
{
//    Coach coachLeah;
//    Coach coachMaxim;
//    CoachDA c = CoachDA.getInstance();
//    Map<String, String> keyParamsLeah;
//    Map<String, String> keyParamsMaxim;
//    Map<String, String> keyParamsEmpty;
//    Map<String, String> updateParamsLeah;
//    Map<String, String> updateParamsMaxim;
//    Map<String, String> updateParamsEmpty;
//
//
//    @BeforeAll
//    public void beforeAll()
//    {
//        coachLeah = new Coach("leahma", "12345Q", "", "main", "master", "Eagles");
//        coachMaxim = new Coach("maxi2", "5g5gT", "maxim", "main", "master", "Eagles");
//        keyParamsLeah = new HashMap<>();
//        keyParamsMaxim = new HashMap<>();
//        keyParamsEmpty = new HashMap<>();
//        keyParamsLeah.put("userName", coachLeah.getUserName());
//        keyParamsMaxim.put("userName", coachMaxim.getUserName());
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
//                Arguments.of(coachLeah, keyParamsLeah),
//                Arguments.of(coachMaxim, keyParamsMaxim)
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("paramsProvider")
//    void testGet(Coach coachT, Map<String, String> keyParamsT)
//    {
//        try { c.save(coachT); } catch (Exception e) { System.out.println("problem in save method"); }
//        Coach coach = c.get(keyParamsT);
//        assertEquals(coach.getUserName(), coachT.getUserName());
//    }
//
//
//
//    @Test
//    void getInstance() {
//    }
}