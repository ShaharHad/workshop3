package DataAccess;

import java.util.Map;
//**DataAccess interface    *//
public interface DataAccess<T>
{
    void save(T t) throws Exception;
    void update(T t, Map<String, String> newParams) throws Exception;
    void delete(T t) throws Exception;
    T get(Map<String, String> keyParams);

}
