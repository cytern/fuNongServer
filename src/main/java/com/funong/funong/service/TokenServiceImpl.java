package com.funong.funong.service;

import com.funong.funong.mapper.TokenMapper;
import com.funong.funong.pojo.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenServiceImpl implements TokenService{
    @Autowired
    private TokenMapper tokenMapper;
    private void setTokenMapper(TokenMapper tokenMapper){
        this.tokenMapper = tokenMapper;
    }
    @Override
    public Token getTokenByToken(String token) {
        return tokenMapper.getTokenByToken(token);
    }

    @Override
    public List<Token> getAllToken() {
        return tokenMapper.getAllToken();
    }

    @Override
    public int addToken(Token token) {
        return tokenMapper.addToken(token);
    }

    @Override
    public int deleteTokenByToken(String token) {
        return tokenMapper.deleteTokenByToken(token);
    }
}
