package Domain;
import DataAccess.OwnerDA;
import jdk.jfr.Event;

import java.sql.SQLException;

public class Owner extends Member {
    String teamName;
    OwnerDA ODA = OwnerDA.getInstance();

    public Owner(String username, String password, String name, String teamName) {
        super(username, password, name, "owner");
        this.teamName = teamName;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public boolean login(String username, String password) throws Exception {
        if (username == null || password == null) {
            throw new Exception("one of the parameters is null");
        }
        Member member = ODA.get(username);
        if (member == null) {
            throw new Exception("user not exist");
        } else
        {
            return member.getPassword().equals(password);
        }
    }
}


