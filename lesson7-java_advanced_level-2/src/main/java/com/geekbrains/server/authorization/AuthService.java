package com.geekbrains.server.authorization;

public interface AuthService {
    void start();
    String getNickNameByLoginAndPassword(String login, String password);
    String checkNickName(String nickName);
    void end();
}
