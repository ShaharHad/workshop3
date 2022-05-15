package DataAccess;

public interface DataAccess<T>
{
    public void save(String username , String password) throws Exception;
    public void update(String username, String password) throws Exception; // update password
    public void delete(String username) throws Exception;
    public Boolean IsExist(String user_name) ;

}
