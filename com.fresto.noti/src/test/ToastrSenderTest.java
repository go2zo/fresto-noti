package test;

import com.fresto.noti.websockets.ToastrSender;

public class ToastrSenderTest {

	public static void main(String[] args) {
		ToastrSender sender = new ToastrSender("tcp://localhost:6666");
		sender.info("AAAAA");

	}

}
