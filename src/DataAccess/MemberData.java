package DataAccess;

import Domain.Member;
//**class MemberData is a class that connects the domain layer to the service layer*/
public class MemberData extends Member
{
    /**
     * constructor of  MemberData class -extends Member
     * @param username $username
     * @param password $password
     * @param name $name
     * @param role $role
     */
    public MemberData(String username, String password, String name, String role)
    {
        super(username, password, name, role);
    }
}
