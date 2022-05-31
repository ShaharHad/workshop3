package DataAccess;

import Domain.Manager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ManagerDATest
{

    Manager managerMaor;
    Manager managerNaor;
    ManagerDA m = ManagerDA.getInstance();
    Map<String, String> keyParamsMaor;
    Map<String, String> keyParamsNaor;

    @BeforeAll
    public void beforeAll()
    {
        managerMaor = new Manager("naora", "12345Q", "naor", "Barcelona");
        managerNaor = new Manager("maora", "5g5gT", "maor", "Barcelona");
        keyParamsMaor = new HashMap<>();
        keyParamsNaor = new HashMap<>();
        keyParamsMaor.put("userName", "naora");
        keyParamsNaor.put("userName", "maora");
    }

    private Stream<Arguments> paramsProvider()
    {
        return Stream.of(
                Arguments.of(managerMaor, keyParamsMaor),
                Arguments.of(managerNaor, keyParamsNaor)
        );
    }

    @ParameterizedTest
    @MethodSource("paramsProvider")
    void testGet(Manager managerT, Map<String, String> keyParamsT)
    {
        Manager manager = m.get(keyParamsT);
        assertEquals(manager.getUserName(), managerT.getUserName());
    }

}