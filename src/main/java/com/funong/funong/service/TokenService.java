package com.funong.funong.service;

import com.funong.funong.pojo.Token;

import java.util.List;

public interface TokenService {
    Token getTokenByToken(String token);
    List<Token> getAllToken();
    int addToken(Token token);
    int deleteTokenByToken(String token);
}
