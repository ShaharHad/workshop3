package DataAccess;


import java.sql.Connection;

public class taste {
    public static void main(String[] args)
    {
        Connection conn = DBConnector.getConnector();
    }
}
