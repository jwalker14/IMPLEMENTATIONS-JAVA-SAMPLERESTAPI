import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MyApplication extends Application{
    @Override
    public Set<Class<?>> getClasses(){
        System.out.println("BOOTING");
        HashSet h = new HashSet<Class<?>>();
        h.add(HelloWorld.class);
        h.add(Customer.class);
        return h;
    }
}