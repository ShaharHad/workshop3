import DataAccess.MemberDA;
import DataAccess.MemberData;
import Domain.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class testMemberDA
{
    Member memberLeah;
    Member memberMaxim;
    MemberDA m = MemberDA.getInstance();

    @BeforeAll
    public void beforeAll()
    {
        memberLeah = new MemberData("leahma", "12345Q", "leah", "referee");
        memberMaxim = new MemberData("maxi2", "", "maxim", "coach");
    }

    @ParameterizedTest
    @MethodSource("testMembersProvider")
    void testSaveSuccess(Member memberT)
    {
        try { m.save(memberT); } catch (Exception e) { System.out.println("problem in save method"); }
        Member member;
        try
        {
            member = m.get(memberLeah.getUserName());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        assertNotNull(member, "user wasn't saved correctly");
    }

    List<Member> testMembersProvider()
    {
        List<Member> members = new ArrayList<>();
        members.add(memberLeah);
        members.add(memberMaxim);
        return members;
    }

    @Test
    void testUpdateSuccess()
    {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("password", "5g5gT");
        try {
            m.update(memberMaxim, testMap);
            Member member = m.get(memberMaxim.getUserName());
            assertEquals(member.getPassword(), "5g5gT");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @MethodSource("testMembersProvider")
    void testGet(Member memberT)
    {
        Member member = m.get(memberT.getUserName());
        assertEquals(member.getUserName(), memberT.getUserName());
    }

    @ParameterizedTest
    @MethodSource("testMembersProvider")
    void testDeleteSuccess(Member memberT)
    {
        try { m.delete(memberT); } catch (Exception e) { throw new RuntimeException(e); }
        try
        {
            Member member = m.get(memberT.getUserName());
            assertNull(member, "user wasn't deleted correctly");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}