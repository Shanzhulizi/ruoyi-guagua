package com.ruoyi.guagua.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.guagua.dto.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ruoyi.guagua.mapper.UserMapper;
import com.ruoyi.guagua.domain.User;
import com.ruoyi.guagua.service.IUserService;

/**
 * 用户Service业务层处理
 * 
 * @author lm
 * @date 2025-06-07
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService 
{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public User selectUserById(Long id)
    {
        return userMapper.selectUserById(id);
    }

    /**
     * 查询用户列表
     * 
     * @param user 用户
     * @return 用户
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 新增用户
     * 
     * @param user 用户
     * @return 结果
     */
    @Override
    public int insertUser(User user)
    {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user.setCreateTime(DateUtils.getNowDate());
        return userMapper.insertUser(user);
    }

    /**
     * 修改用户
     * 
     * @param user 用户
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        user.setUpdateTime(DateUtils.getNowDate());
        return userMapper.updateUser(user);
    }

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteUserByIds(Long[] ids)
    {
        return userMapper.deleteUserByIds(ids);
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteUserById(Long id)
    {
        return userMapper.deleteUserById(id);
    }


   /**
     * 用户登录
     * @param
     * @return
     */
    @Override
    public User Login(String username, String password) {


        //1、根据用户名查询数据库中的数据
        User user = userMapper.selectUserByUsername(username);
        System.out.println("password "+password);
        System.out.println("userPassword "+user.getPassword());


        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new RuntimeException("用户不存在");
        }
        if (user.getStatus() == 0) {
            //账号被锁定
            throw new RuntimeException("账号被禁用");
        }
        if (user.getIsDeleted() == 1) {
            //账号被删除
            throw new RuntimeException("账号已删除");
        }

        //密码比对
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        String encodedPassword = encoder.encode(password);
        boolean match = passwordEncoder.matches(password, user.getPassword());
        System.out.println("match:"+match);
        if (!match) {
            //密码错误
            throw new RuntimeException("密码错误");
        }

        //3、返回实体对象
        return user;

    }

}
