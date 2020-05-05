package com.funong.funong.mapper;

import com.funong.funong.pojo.Token;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface TokenMapper {
    Token getTokenByToken(String token);
    List<Token> getAllToken();
    int addToken(Token token);
    int deleteTokenByToken(String token);
}
