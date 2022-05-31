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
    Owner ownerNonExist;
    OwnerDA o = OwnerDA.getInstance();
    Map<String, String> keyParamsLeah;
    Map<String, String> keyParamsMaxim;
    Map<String, String> keyParamsEmpty;
    Map<String, String> updateParamsLeah;
    Map<String, String> updateParamsMaxim;
    Map<String, String> updateParamsEmpty;

    @BeforeAll
    public void beforeAll()
    {
        ownerLeah = new Owner("leahma", "12345Q", "", "Eagles");
        ownerMaxim = new Owner("maxi2", "5g5gT", "maxim", "");
        ownerNonExist = new Owner("oror", "63wlgaet", "or", "Real-Madrid");
        keyParamsLeah = new HashMap<>();
        keyParamsMaxim = new HashMap<>();
        keyParamsEmpty = new HashMap<>();
        keyParamsLeah.put("userName", ownerLeah.getUserName());
        keyParamsMaxim.put("userName", ownerMaxim.getUserName());
        updateParamsLeah = new HashMap<>();
        updateParamsMaxim = new HashMap<>();
        updateParamsEmpty = new HashMap<>();
        updateParamsLeah.put("name", "leah");
        updateParamsMaxim.put("teamName", "Liverpool");
    }

    private Stream<Arguments> noOwnerParams()
    {
        return Stream.of(
                Arguments.of(null, keyParamsEmpty),
                Arguments.of(null, keyParamsEmpty)
        );
    }

    @ParameterizedTest
    @MethodSource({"noOwnerParams"})
    void noOwnerSaveTest(Owner ownerT)
    {
        try { o.save(ownerT); }
        catch (Exception e) { assertEquals(e.getMessage(), "member is null"); }
    }

    private Stream<Arguments> noOwnerUpdateParams()
    {
        {
            return Stream.of(
            Arguments.of(null, updateParamsLeah),
            Arguments.of(ownerLeah, updateParamsEmpty)
            );
        }
    }

    @ParameterizedTest
    @MethodSource({"noOwnerUpdateParams"})
    void noOwnerUpdateTest(Owner ownerT, Map<String, String> updateParamsT)
    {
        try { o.update(ownerT, updateParamsT); }
        catch (Exception e) { assertEquals(e.getMessage(), "one of the parameters is null"); }
    }

    private Stream<Arguments> noMemberDBUpdateParams()
    {
        {
            return Stream.of(
                    Arguments.of(ownerNonExist, updateParamsLeah)
            );
        }
    }

    @ParameterizedTest
    @MethodSource({"noMemberDBUpdateParams"})
    void noMemberDBUpdateTest(Owner ownerT, Map<String, String> updateParamsT)
    {
        try { o.update(ownerT, updateParamsT); }
        catch (Exception e) { assertEquals(e.getMessage(), "member doesn't exist"); }
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
}