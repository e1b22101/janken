package oit.is.z2486.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchInfoMapper {
  @Select("SELECT user1, user2, user1Hand, isActive FROM matchinfo;")
  ArrayList<MatchInfo> selectAllByMatchInfo();

  @Insert("INSERT INTO matchinfo (user1, user2, user1Hand, isActive) VALUES (#{userId}, #{opponentId}, #{myHand}, #{isActive})")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatchInfo(int userId, int opponentId, String myHand, Boolean isActive);
}
