package oit.is.z2486.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper {
  @Select("SELECT id, user1, user2, user1Hand, user2Hand, isActive FROM matches;")
  ArrayList<Match> selectAllByMatch();

  @Select("SELECT * FROM matches WHERE id = #{id};")
  ArrayList<Match> selectMatchById(@Param("id") int id);

  @Insert("INSERT INTO matches (user1, user2, user1Hand, user2Hand) VALUES (#{userId}, #{opponentId}, #{myHand}, #{cpuHand})")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatch(int userId, int opponentId, String myHand, String cpuHand);
}
