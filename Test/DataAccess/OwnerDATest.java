package DataAccess;

import Domain.Owner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OwnerDATest
{
    Owner ownerLeah;
    Owner ownerMaxim;
    OwnerDA o = OwnerDA.getInstance();
    Map<String, String> keyParamsLeah;
    Map<String, String> keyParamsMaxim;
    Map<String, String> updateParamsLeah;
    Map<String, String> updateParamsMaxim;

    @BeforeAll
    public void beforeAll()
    {
        ownerLeah = new Owner("leahma", "12345Q", "", "Eagles");
        ownerMaxim = new Owner("maxi2", "5g5gT", "maxim", "");
        keyParamsLeah = new HashMap<>();
        keyParamsMaxim = new HashMap<>();
        keyParamsLeah.put("userName", ownerLeah.getUserName());
        keyParamsMaxim.put("userName", ownerMaxim.getUserName());
        updateParamsLeah = new HashMap<>();
        updateParamsMaxim = new HashMap<>();
        updateParamsLeah.put("name", "leah");
        updateParamsMaxim.put("teamName", "Liverpool");
    }

    private Stream<Arguments> paramsProvider()
    {
        return Stream.of(
                Arguments.of(ownerLeah, keyParamsLeah),
                Arguments.of(ownerMaxim, keyParamsMaxim)
        );
    }

    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void testSaveSuccess(Owner ownerT, Map<String, String> keyParamsT)
    {
        try { o.save(ownerT); } catch (Exception e) { System.out.println("problem in save method"); }
        Owner owner;
        try { owner = o.get(keyParamsT); }
        catch (Exception e) { throw new RuntimeException(e); }
        assertNotNull(owner, "user wasn't saved correctly");
    }

    private Stream<Arguments> paramsUpdateProvider()
    {
        return Stream.of(
                Arguments.of(ownerLeah, keyParamsLeah, updateParamsLeah),
                Arguments.of(ownerMaxim, keyParamsMaxim, updateParamsMaxim)
        );
    }

    @ParameterizedTest
    @MethodSource({"paramsUpdateProvider"})
    void testUpdateSuccess(Owner ownerT, Map<String, String> keyParamsT, Map<String, String> updateParams)
    {
        try {
            o.update(ownerT, updateParams);
            Owner owner = o.get(keyParamsT);
            for (String key : updateParams.keySet())
            {
                assertEquals(updateParams.get(key), owner.get(key));
            }

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @MethodSource("paramsProvider")
    void testDeleteSuccess(Owner ownerT,  Map<String, String> keyParamsT)
    {
        try { o.delete(ownerT); } catch (Exception e) { throw new RuntimeException(e); }
        try
        {
            Owner owner = o.get(keyParamsT);
            assertNull(owner, "user wasn't deleted correctly");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @MethodSource("paramsProvider")
    void testGet(Owner ownerT, Map<String, String> keyParamsT)
    {
        Owner owner = o.get(keyParamsT);
        assertEquals(owner.getUserName(), ownerT.getUserName());
    }

}