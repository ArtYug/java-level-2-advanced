package com.geekbrains.server;

import com.geekbrains.CommonConstants;
import com.geekbrains.server.authorization.AuthService;
import com.geekbrains.server.authorization.InMemoryAuthServiceImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// Homework 8 java advanced level
//         1. Разобраться с кодом.
//         2. if (messageFromServer.contains("зашел в чат")) { Заменить эту строку на команду /enter,
//         в случае если сервером была прислана такая команда + никнейм,
//         нужно отобразить сообщение у других пользователей "Пользователь nickname зашел в чат"
//         3. *****Отправку сообщений по выбору клиента в списке клиентов
//         (Приватные сообщения(подкрасить информацию о том,
//         от какого клиента пришло сообщение и что это сообщение пришло только вам))
public class Server {
    private final AuthService authService;

    private List<ClientHandler> connectedUsers;

    public Server() {
        authService = new InMemoryAuthServiceImpl();
        try (ServerSocket server = new ServerSocket(CommonConstants.SERVER_PORT)) {
            authService.start();
            connectedUsers = new ArrayList<>();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException exception) {
            System.out.println("Ошибка в работе сервера");
            exception.printStackTrace();
        } finally {
            if (authService != null) {
                authService.end();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNickNameBusy(String nickName) {
        for (ClientHandler handler : connectedUsers) {
            if (handler.getNickName().equals(nickName)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastMessage(String message) {
        for (ClientHandler handler : connectedUsers) {
            handler.sendMessage(message);
        }
    }

    public synchronized void addConnectedUser(ClientHandler handler) {
        connectedUsers.add(handler);
    }

    public synchronized void disconnectUser(ClientHandler handler) {
        connectedUsers.remove(handler);
    }

    public String getClients() {
        StringBuilder builder = new StringBuilder("/clients ");
        for (ClientHandler user : connectedUsers) {
            builder.append(user.getNickName()).append("\n");
        }

        return builder.toString();
    }
}
