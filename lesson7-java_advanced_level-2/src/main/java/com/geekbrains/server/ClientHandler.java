package com.geekbrains.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {
    private final Server server;
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        authentication();
                        readMessages();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    } finally {
                        closeConnection();
                    }
                }
            }).start();
        } catch (IOException exception) {
            throw new RuntimeException("Проблемы при создании обработчика");
        }
    }

    public void authentication() throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            if (message.startsWith(ServerCommandConstants.AUTHORIZATION)) {
                String[] authInfo = message.split(" ");
                String nickName = server.getAuthService().getNickNameByLoginAndPassword(authInfo[1], authInfo[2]);
                if (nickName != null) {
                    if (!server.isNickNameBusy(nickName)) {
                        sendMessage("/auth " + nickName);
                        this.nickName = nickName;
                        server.broadcastMessage(nickName + " зашел в чат");
                        server.addConnectedUser(this);
                        return;
                    } else {
                        sendMessage("Учетная запись уже используется");
                    }
                } else {
                    sendMessage("Неверные логин или пароль");
                }
            }
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            String messageInChat = inputStream.readUTF();
//            System.out.println("от " + nickName + ": " + messageInChat);
            if (messageInChat.equals(ServerCommandConstants.SHUTDOWN)) {
                return;
            } else if (messageInChat.startsWith(ServerCommandConstants.SEND_MESSAGE_TO_SPECIFIC_LOGIN)) {
                while (true) {
                    if (messageInChat.startsWith(ServerCommandConstants.SEND_MESSAGE_TO_SPECIFIC_LOGIN)) {
                        String[] authInfo = messageInChat.split(" ");
                        String nick = authInfo[1];
                        ArrayList<String> arrayList = new ArrayList();
                        if (server.isNickNameBusy(nick)) {
                            String[] strings;
                            strings = messageInChat.split(" ",0);
                            for (int i = 0; i < strings.length; i++) {
                                if (i != 0 && i != 1) {
                                    String tmp = strings[i];
                                    arrayList.add(tmp);
                                }
                            }
                            StringBuilder s = new StringBuilder();
                            for (String sd : arrayList) {
                                s.append(sd).append(" ");
                            }
                             server.broadcastMessage2(nick, nickName + " :" + s);
                            break;
                        } else {
                            System.out.println("after /w write nickname");
                            return;
                        }
                    }
                }
            } else {
                server.broadcastMessage(nickName + ": " + messageInChat);
            }
        }
    }

    public void sendMessage(String message) {
        try {
            outputStream.writeUTF(message);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void closeConnection() {
        server.disconnectUser(this);
        server.broadcastMessage(nickName + " вышел из чата");
        try {
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
