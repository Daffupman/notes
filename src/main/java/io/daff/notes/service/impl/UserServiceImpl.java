package io.daff.notes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import io.daff.enums.Codes;
import io.daff.enums.Messages;
import io.daff.exception.BaseException;
import io.daff.exception.BusinessException;
import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.LoginForm;
import io.daff.notes.entity.form.PasswordResetForm;
import io.daff.notes.entity.form.UserForm;
import io.daff.notes.entity.form.UserQueryForm;
import io.daff.notes.entity.po.User;
import io.daff.notes.entity.vo.LoginVo;
import io.daff.notes.entity.vo.UserVo;
import io.daff.notes.mapper.UserMapper;
import io.daff.notes.service.UserService;
import io.daff.notes.util.CopyUtil;
import io.daff.notes.util.PageUtil;
import io.daff.notes.util.SimpleRedisUtil;
import io.daff.notes.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private SnowFlake snowFlake;
    @Resource
    private SimpleRedisUtil simpleRedisUtil;
    @Resource
    private ObjectMapper objectMapper;

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
    public LoginVo login(LoginForm loginForm) {
        User userByLoginName = userMapper.selectByLoginName(loginForm.getLoginName());
        if (userByLoginName == null) {
            log.info("用户不存在：{}", loginForm.getLoginName());
            throw new BusinessException("用户还没有注册");
        }
        boolean success = loginForm.getPassword().equals(userByLoginName.getPassword());
        if (!success) {
            log.info("用户登录名和密码不匹配");
            throw new BaseException(Codes.AUTHENTICATION_FAILED, "登录名或密码错误！");
        }

        log.info("生成单点登录的token，并放入redis中");
        Long token = snowFlake.nextId();
        LoginVo loginVo = CopyUtil.copy(userByLoginName, LoginVo.class);
        loginVo.setToken(token.toString());
        try {
            simpleRedisUtil.set(token.toString(), objectMapper.writeValueAsString(loginVo), 30 * 60);
        } catch (JsonProcessingException e) {
            log.error("序列化异常", e);
            throw new BaseException(Codes.SYSTEM_ERROR, Messages.SYSTEM_ERROR);
        }

        return loginVo;
    }

}
