package com.ruoyi.guagua.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.guagua.dto.LoginDTO;
import com.ruoyi.guagua.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.guagua.domain.User;
import com.ruoyi.guagua.service.IUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户Controller
 *
 * @author lm
 * @date 2025-06-07
 */
@Slf4j
@RestController
@RequestMapping("/user/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;
    // 引入 redis 工具
    @Autowired
    private RedisCache redisCache;
//用若依的token，因为我们是和若依一起的
    @Autowired
    private TokenService tokenService;

    /**
     * ********************************************************
     * 以下是我新增的方法
     */
    /**
     * 验证码的判断逻辑：
     * 在生成验证码的时候，就已经把验证码的uuid和code放到了redis里面，有效期5分钟
     *
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginDTO loginDTO) {
        System.out.println("dto?");
        System.out.println(loginDTO);
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
//        验证码判断逻辑
        String code = loginDTO.getCode();
        String uuid = loginDTO.getUuid();
        //拼接出redis的key
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey); // 验证码只能用一次
        if (captcha == null) {
            // 验证码过期
            log.info("验证码过期");
            return AjaxResult.error("验证码过期");
        }
        if (!code.equalsIgnoreCase(captcha)) {
            // 验证码错误
            log.info("验证码错误");
            return AjaxResult.error("验证码错误");
        }

        // 调用登录服务
        User user;
        try {
            user = userService.Login(username, password);
        } catch (RuntimeException e) {
            log.info("是这里吗");
            return AjaxResult.error(e.getMessage());
        }


        // 这里的方法是若依自带，适配的是若依的后台用户，改造一下
        // 1. 构造 SysUser（若依默认使用它）
        SysUser sysUser = new SysUser();
        sysUser.setUserId(user.getId());
        sysUser.setUserName(user.getUsername());
        sysUser.setPassword(user.getPassword());


        LoginUser loginUser =new LoginUser();
        loginUser.setUserId(user.getId());
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setUser(sysUser); // 这里要传入上面构造好的 SysUser

        // 生成 token
        String token = tokenService.createToken(loginUser);

        // 返回 token
        AjaxResult ajax = AjaxResult.success();
        System.out.println(token);
        ajax.put("token", token);
        return ajax;
    }

    @PostMapping("/register")
    public AjaxResult register(@RequestBody User user) {
        try {
            userService.insertUser(user);
            return AjaxResult.success("用户新增成功");
        } catch (Exception e) {
            return AjaxResult.error("用户新增失败：" + e.getMessage());
        }
    }



    /**
     * ********************************************************
     * 以下是若依提供的方法
     */

    /**
     * 查询用户列表
     */
    @PreAuthorize("@ss.hasPermi('user:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(User user) {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @PreAuthorize("@ss.hasPermi('user:user:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, User user)
    {
        List<User> list = userService.selectUserList(user);
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        util.exportExcel(response, list, "用户数据");
    }

    /**
     * 获取用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('user:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(userService.selectUserById(id));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('user:user:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody User user) {
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('user:user:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody User user) {
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('user:user:remove')")
    @Log(title = "用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(userService.deleteUserByIds(ids));
    }
}
