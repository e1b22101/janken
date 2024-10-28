package oit.is.z2486.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
  @Select("SELECT id, name FROM users;")
  ArrayList<User> selectAllByUser();

  @Select("SELECT * FROM users WHERE id = #{id};")
  ArrayList<User> findUserById(@Param("id") int id);

  @Select("SELECT * FROM users WHERE name = #{name};")
  ArrayList<User> findUserByName(@Param("name") String name);

  @Select("SELECT * FROM users WHERE id = #{id}")
  User selectById(int id);

  @Select("SELECT id, name FROM users WHERE name = #{name} LIMIT 1")
  User selectByName(String name);
}
