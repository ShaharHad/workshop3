package DataAccess;

import Domain.Member;
import Domain.Owner;
import Domain.Referee;
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
class MemberDATest
{
    Member memberLeah;
    Member memberMaxim;
    Member memberFan;
    MemberDA m = MemberDA.getInstance();
    Map<String, String> keyParamsLeah;
    Map<String, String> keyParamsMaxim;
    Map<String, String> keyParamsFan;
    Map<String, String> keyParamsEmpty;

    @BeforeAll
    public void beforeAll()
    {
        memberLeah = new MemberData("leahma", "12345Q", "leah", "referee");
        memberMaxim = new MemberData("maxi2", "", "maxim", "coach");
        memberFan = new MemberData("sha3", "sha3", "arnon", "fan");
        keyParamsLeah = new HashMap<>();
        keyParamsMaxim = new HashMap<>();
        keyParamsEmpty = new HashMap<>();
        keyParamsFan = new HashMap<>();
        keyParamsLeah.put("userName", memberLeah.getUserName());
        keyParamsFan.put("userName", memberFan.getUserName());
        keyParamsMaxim.put("userName", memberMaxim.getUserName());
    }

    private Stream<Arguments> paramsProvider()
    {
        return Stream.of(
                Arguments.of(memberLeah, keyParamsLeah),
                Arguments.of(memberFan, keyParamsFan),
                Arguments.of(memberMaxim, keyParamsMaxim)
        );
    }


    @ParameterizedTest
    @MethodSource({"paramsProvider"})
    void testSaveSuccess(Member memberT, Map<String, String> keyParamsT)
    {
        try { m.save(memberT); } catch (Exception e) { System.out.println("problem in save method"); }
        Member member;
        try { member = m.get(keyParamsT); }
        catch (Exception e) { throw new RuntimeException(e); }
        assertNotNull(member, "user wasn't saved correctly");
    }

    private Stream<Arguments> noMemberParams()
    {
        return Stream.of(
                Arguments.of(null, keyParamsMaxim),
                Arguments.of(null, keyParamsLeah)
        );
    }

    @ParameterizedTest
    @MethodSource({"noMemberParams"})
    void noMemberSaveTest(Member memberT)
    {
        try { m.save(memberT); }
        catch (Exception e) { assertEquals(e.getMessage(), "member is null"); }
    }


    @Test
    void testUpdateSuccess()
    {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("password", "5g5gT");
        try {
            m.update(memberMaxim, testMap);
            Member member = m.get(keyParamsMaxim);
            assertEquals("5g5gT", member.getPassword());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<Arguments> noMemberUpdateParams()
    {
        return Stream.of(
                Arguments.of(null, keyParamsLeah),
                Arguments.of(memberMaxim, keyParamsEmpty)
        );
    }

    @ParameterizedTest
    @MethodSource({"noMemberUpdateParams"})
    void noMemberUpdateTest(Member memberT, Map<String, String> updateParamsT)
    {
        try { m.update(memberT, updateParamsT); }
        catch (Exception e) { assertEquals(e.getMessage(), "one of the parameters is null"); }
    }


    @ParameterizedTest
    @MethodSource("paramsProvider")
    void testGet(Member memberT, Map<String, String> keyParamsT)
    {
        Member member = m.get(keyParamsT);
        assertEquals(member.getUserName(), memberT.getUserName());
    }

    @ParameterizedTest
    @MethodSource("paramsProvider")
    void testDeleteSuccess(Member memberT,  Map<String, String> keyParamsT)
    {
        try { m.delete(memberT); } catch (Exception e) { throw new RuntimeException(e); }
        try
        {
            Member member = m.get(keyParamsT);
            assertNull(member, "user wasn't deleted correctly");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}