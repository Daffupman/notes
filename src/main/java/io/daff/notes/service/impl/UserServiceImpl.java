package io.daff.notes.service.impl;

import com.github.pagehelper.PageInfo;
import io.daff.enums.Codes;
import io.daff.exception.BaseException;
import io.daff.exception.BusinessException;
import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.LoginForm;
import io.daff.notes.entity.form.PasswordResetForm;
import io.daff.notes.entity.form.UserForm;
import io.daff.notes.entity.form.UserQueryForm;
import io.daff.notes.entity.po.User;
import io.daff.notes.entity.vo.UserVo;
import io.daff.notes.mapper.UserMapper;
import io.daff.notes.service.UserService;
import io.daff.notes.util.CopyUtil;
import io.daff.notes.util.PageUtil;
import io.daff.notes.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author daffupman
 * @since 2021/3/1
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private SnowFlake snowFlake;

    @Override
    public Page<UserVo> query(UserQueryForm userQueryForm) {

        PageUtil.startPage(userQueryForm, UserVo.class);
        List<User> users = userMapper.selectLikeByForm(userQueryForm);

        PageInfo<User> userPageInfo = new PageInfo<>(users);
        List<UserVo> userVoVos = CopyUtil.copyList(userPageInfo.getList(), UserVo.class);
        return Page.newPage(userPageInfo.getTotal(), userVoVos);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveOrUpdate(UserForm userForm) {

        User user = CopyUtil.copy(userForm, User.class);
        User userByLoginName = userMapper.selectByLoginName(userForm.getLoginName());
        int rows;
        if (userForm.getId() == null) {
            // save
            if (userByLoginName != null) {
                throw new BusinessException("登录名重复");
            }
            user.setId(snowFlake.nextId());
            rows = userMapper.batchInsert(Collections.singletonList(user));
        } else {
            // update
            user.setLoginName(null);
            user.setPassword(null);
            rows = userMapper.batchUpdate(Collections.singletonList(user));
        }
        return rows > 0 ? user.getId() : 0L;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(Long id) {
        int rows = userMapper.deleteByPrimaryKey(id);
        return rows > 0;
    }

    @Override
    public boolean resetPassword(PasswordResetForm passwordResetForm) {
        User user = CopyUtil.copy(passwordResetForm, User.class);
        return userMapper.batchUpdate(Collections.singletonList(user)) == 1;
    }

    @Override
    public void login(LoginForm loginForm) {
        User userByLoginName = userMapper.selectByLoginName(loginForm.getLoginName());
        if (userByLoginName == null) {
            throw new BusinessException("用户还没有注册");
        }
        boolean success = loginForm.getPassword().equals(userByLoginName.getPassword());
        if (!success) {
            throw new BaseException(Codes.AUTHENTICATION_FAILED, "登录名或密码错误！");
        }
    }

}
