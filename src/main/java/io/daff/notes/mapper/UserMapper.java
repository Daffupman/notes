package io.daff.notes.mapper;

import io.daff.notes.entity.form.UserQueryForm;
import io.daff.notes.entity.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    int batchInsert(List<User> records);

    int deleteByIds(List<Long> ids);

    List<User> select(User record);

    List<User> selectByIds(List<Long> ids);

    int batchUpdate(List<User> records);

    List<User> selectLikeByForm(UserQueryForm userQueryForm);

    User selectByLoginName(String loginName);
}