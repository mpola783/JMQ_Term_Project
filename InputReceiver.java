import java.util.ArrayList;

public interface InputReceiver {

	static ArrayList<InputReceiver> inputReceiverList = new ArrayList<InputReceiver>();

	public static void registerReceiver(InputReceiver receiver) {
		inputReceiverList.add(receiver);
	}

	public static void deregisterReceiver(InputReceiver receiver) {
		inputReceiverList.remove(receiver);
	}

	public static void callKeyPressed(char key) {
		for (InputReceiver receiver : inputReceiverList)
			receiver.keyPressed(key);
	}

	public static void callKeyReleased(char key) {
		for (InputReceiver receiver : inputReceiverList)
			receiver.keyReleased(key);
	}

	public abstract void keyPressed(char key);

	public abstract void keyReleased(char key);

}
