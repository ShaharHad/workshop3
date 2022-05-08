package DataAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface DataAccess<T>
{
    void save(String username);
    void update();
    void delete();
}
