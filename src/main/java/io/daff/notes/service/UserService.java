package io.daff.notes.service;

import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.LoginForm;
import io.daff.notes.entity.form.PasswordResetForm;
import io.daff.notes.entity.form.UserForm;
import io.daff.notes.entity.form.UserQueryForm;
import io.daff.notes.entity.vo.UserVo;

/**
 * @author daffupman
 * @since 2021/3/1
 */
public interface UserService {

    Page<UserVo> query(UserQueryForm userQueryForm);

    Long saveOrUpdate(UserForm userForm);

    boolean remove(Long id);

    boolean resetPassword(PasswordResetForm passwordResetForm);

    void login(LoginForm loginForm);
}
