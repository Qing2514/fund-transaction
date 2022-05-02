import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;

@CloudApplication
public class Client {
    public static void main(String[] args) {
        CloudBootstrap.run(Client.class, args);
    }
}
