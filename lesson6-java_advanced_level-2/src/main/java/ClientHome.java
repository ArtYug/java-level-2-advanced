import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHome {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private final Scanner scanner;
    private final static String SERVER_ADDRESS = "localhost";
    private final static int SERVER_PORT = 8080;


    public ClientHome() {
        scanner = new Scanner(System.in);
        try {
            openConnection();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                while (true) {
                    String messageFromServer = in.readUTF();
                    System.out.println(messageFromServer);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            } finally {
                closeConnection();
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    String text = scanner.nextLine();
                    sendMessage(text);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }).start();
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        System.exit(1);
    }

    public static void main(String[] args) {
        new ClientHome();
    }
}
