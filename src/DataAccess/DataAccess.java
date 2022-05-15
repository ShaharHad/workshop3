package DataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface DataAccess<T>
{
    void save(String username);
    void delete(String username);
    ResultSet getRecord(String username);
}
