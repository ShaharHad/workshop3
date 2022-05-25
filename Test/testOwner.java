import DataAccess.OwnerDA;
import Domain.Owner;
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
class testOwner
{
    Owner ownerLeah;
    Owner ownerMaxim;
    OwnerDa o = OwnerDa.getInstance();

    @BeforeAll
    public void beforeAll()
    {
        ownerLeah = new Owner("leahma", "12345Q", "leah", "referee", "Barcelona");
        ownerMaxim = new MemberData("maxi2", "123456", "maxim", "coach", "Manchester");
    }

    List<Member> testMembersProvider()
    {
        List<Member> members = new ArrayList<>();
        members.add(memberLeah);
        members.add(memberMaxim);
        return members;
    }


    @ParameterizedTest
    @MethodSource("testMembersProvider")
    void testGet(Owner ownerT)
    {
        Member member = m.get(ownerT.getUserName());
        assertEquals(member.getUserName(), memberT.getUserName());
    }

    public void testLogin()
    {

    }

}