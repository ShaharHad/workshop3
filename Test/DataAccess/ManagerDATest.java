package DataAccess;

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


public class ManagerDATest {
    ManagersDA m = ManagersDA.getInstance();
    @ParameterizedTest
    @MethodSource("paramsProvider")

    void testGet(Manager mangerT, Map<String, String> keyParamsT)
    {
        try { m.save(mangerT); } catch (Exception e) { System.out.println("problem in save method"); }
        Manager owner = m.get(keyParamsT);
        assertEquals(owner.getUserName(), mangerT.getUserName());
    }
}
