import com.fresto.noti.NotificationHub;
import com.fresto.noti.NotificationStreamer;
import com.fresto.noti.NotificationWriter;

public class noti {
	
	public static void main(String[] args) {
		new NotificationHub().start();
		new NotificationStreamer().start();
		new NotificationWriter().start();
	}

}
