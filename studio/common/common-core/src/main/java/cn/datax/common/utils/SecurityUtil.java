package cn.datax.common.utils;


import com.platform.exception.BadRequestException;


import cn.datax.service.system.api.dto.JwtUserDto;
import cn.datax.service.system.api.feign.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityUtil {

    @Autowired
    private UserServiceFeign userServiceFeign;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取用户
     *
     * @return user
     */
    public static JwtUserDto getDataUser() {
        UserServiceFeign userServiceFeign = SpringContextHolder.getBean(UserServiceFeign.class);
        return userServiceFeign.loginByUsername(getCurrentUsername());
    }

    public static String getCurrentUsername() {
        return "admin";

    }


    /**
     * 获取用户ID
     *
     * @return id
     */
    public static String getUserId() {
        return "1";
    }

    /**
     * 获取用户部门
     *
     * @return id
     */
    public static String getUserDeptId() {
        return "1";
    }

    /**
     * 获取用户名称
     *
     * @return username
     */
    public static String getUserName() {

        return "admin";
    }

    /**
     * 获取用户昵称
     *
     * @return nickname
     */
    public static String getNickname() {
        return "admin";
    }

    /**
     * 获取用户角色
     *
     * @return username
     */
    public static List<String> getUserRoleIds() {
        JwtUserDto user = getDataUser();
        if (user != null) {
            List<String> roles = new ArrayList<>(user.getRoles());
            return roles;
        }
        return null;
    }

    /**
     * 获取用户
     *
     * @return user
     */
    public static boolean isAdmin() {
        return true;
    }
}
