
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
//                                                                    Homework 6 java advanced level
//                           1. Написать консольный вариант клиент\серверного приложения, в котором пользователь может
//                              писать сообщения как на клиентской стороне, так и на серверной. Т.е. если на клиентской
//                              стороне написать «Привет», нажать Enter, то сообщение должно передаться на сервер и там
//                              отпечататься в консоли. Если сделать то же самое на серверной стороне, сообщение,
//                              соответственно, передаётся клиенту и печатается у него в консоли. Есть одна особенность,
//                              которую нужно учитывать: клиент или сервер может написать несколько сообщений подряд.
//                              Такую ситуацию необходимо корректно обработать.
//                              Разобраться с кодом с занятия — он является фундаментом проекта-чата.
//                              ВАЖНО! Сервер общается только с одним клиентом, т.е. не нужно запускать цикл, который будет ожидать
//                              второго/третьего/n-го клиента.


public class ServerHome {
    private DataInputStream in;
    private DataOutputStream out;
    private final Scanner scanner;

    public ServerHome() {
        scanner = new Scanner(System.in);
        try {
            openConnection();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void openConnection() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Сервер запущен");
        Socket socket = serverSocket.accept();
        System.out.println("Клиент подключился");
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
            scanner.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.exit(1);
    }

    public static void main(String[] args) {
        new ServerHome();
    }
}