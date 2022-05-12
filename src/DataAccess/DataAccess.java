package DataAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface DataAccess<T>
{
    public void save(String username , String password);
    public void update();
    public void delete();
    public boolean IsExist(String user_name) ;

}
