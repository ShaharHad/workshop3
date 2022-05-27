package DataAccess;

import Domain.Coach;
import Domain.Manager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class CoachDATest {
    CoachesDA c = CoachesDA.getInstance();


    @ParameterizedTest
    @MethodSource("paramsProvider")
    void testGet(Coach coachT, Map<String, String> keyParamsT)
    {
        try { c.save(coachT); } catch (Exception e) { System.out.println("problem in save method"); }
        Coach coach = c.get(keyParamsT);
        assertEquals(coach.getUserName(), coachT.getUserName());
    }

}
