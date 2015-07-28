package dao;
import play.db.jpa.JPA;
import java.util.List;

/**
 * Created by tarun on 8/4/15.
 */


public class BaseDAO {


    public static Object save(Object o) {
        JPA.em().persist(o);
        return o;
    }

    public static <T> T findById(Class<T> generic, Integer id) {
        return JPA.em().find(generic, id);
    }


    public static Object update(Object o) {
        return JPA.em().merge(o);
    }

    public static void delete(Object o) {
        o = JPA.em().merge(o);
        JPA.em().remove(o);
    }
}