package DataAccess;

public interface DataAccess<T>
{
    public void save(String username , String password) throws Exception;
    public void update() throws Exception;
    public void delete(String username) throws Exception;
    public Boolean IsExist(String user_name) ;

}
