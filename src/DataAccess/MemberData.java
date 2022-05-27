package DataAccess;

import Domain.Member;

public class MemberData extends Member
{

    public MemberData(String username, String password, String name, String role)
    {
        super(username, password, role, name);
    }
}
