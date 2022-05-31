package DataAccess;

import Domain.Coach;
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
    Coach coachAgam;
    Coach coachMazal;
    CoachDA c = CoachDA.getInstance();
    Map<String, String> keyParamsAgam;
    Map<String, String> keyParamsMazal;


    //need to add the 2 users below to the DB manually
    @BeforeAll
    public void beforeAll()
    {
        coachAgam = new Coach("agamma", "12345Q", "agam", "main", "master", "Eagles");
        coachMazal = new Coach("mazalma", "5g5gT", "mazal", "main", "master", "Eagles");
        keyParamsAgam = new HashMap<>();
        keyParamsMazal = new HashMap<>();
        keyParamsAgam.put("userName", "agamma");
        keyParamsMazal.put("userName", "mazalma");
    }

    private Stream<Arguments> paramsProvider()
    {
        return Stream.of(
                Arguments.of(coachAgam, keyParamsAgam),
                Arguments.of(coachMazal, keyParamsMazal)
        );
    }

    @ParameterizedTest
    @MethodSource("paramsProvider")
    /** A test designed to check for the identification of a judge's instance in DB*/
    void testGet(Coach coachT, Map<String, String> keyParamsT)
    {
        Coach coach = c.get(keyParamsT);
        assertEquals(coach.getUserName(), coachT.getUserName());
    }

}