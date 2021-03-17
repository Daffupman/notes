package io.daff.notes.controller;

import io.daff.entity.Response;
import io.daff.notes.entity.Page;
import io.daff.notes.entity.form.LoginForm;
import io.daff.notes.entity.form.PasswordResetForm;
import io.daff.notes.entity.form.UserForm;
import io.daff.notes.entity.form.UserQueryForm;
import io.daff.notes.entity.vo.UserVo;
import io.daff.notes.service.UserService;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author daffupman
 * @since 2021/3/1
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("用户列表")
    @ApiImplicitParams({})
    @ApiResponses({})
    @GetMapping
    public Response<Page<UserVo>> query(@Valid UserQueryForm userQueryForm) {

        userQueryForm.validate();
        Page<UserVo> usersPageInfo = userService.query(userQueryForm);
        return Response.ok(usersPageInfo);
    }

    @ApiOperation("保存用户")
    @ApiImplicitParams({})
    @ApiResponses({})
    @PutMapping
    public Response<Long> saveOrUpdate(@RequestBody @Valid UserForm userForm) {

        userForm.validate();
        userForm.setPassword(DigestUtils.md5DigestAsHex(userForm.getPassword().getBytes()));
        Long userId = userService.saveOrUpdate(userForm);
        return Response.ok(userId);
    }

    @ApiOperation("删除用户")
    @ApiImplicitParams({})
    @ApiResponses({})
    @DeleteMapping("/{id}")
    public Response<Boolean> remove(@PathVariable Long id) {

        boolean userId = userService.remove(id);
        return Response.ok(userId);
    }

    @ApiOperation("重置密码")
    @ApiImplicitParams({})
    @ApiResponses({})
    @PatchMapping("/resetting")
    public Response<Boolean> resetPassword(@RequestBody @Valid PasswordResetForm passwordResetForm) {

        passwordResetForm.validate();
        passwordResetForm.setPassword(DigestUtils.md5DigestAsHex(passwordResetForm.getPassword().getBytes()));
        boolean success = userService.resetPassword(passwordResetForm);
        return Response.ok(success);
    }

    @ApiOperation("登录")
    @ApiImplicitParams({})
    @ApiResponses({})
    @PostMapping("/login")
    public Response<Boolean> login(@RequestBody @Valid LoginForm loginForm) {

        loginForm.setPassword(DigestUtils.md5DigestAsHex(loginForm.getPassword().getBytes()));
        userService.login(loginForm);
        return Response.ok();
    }
}
