package com.smartflow.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.smartflow.dto.GetMESMenuListOutputDTO;
import com.smartflow.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.Claim;
import com.smartflow.dto.GetTokenForLoginDTO;
import com.smartflow.dto.UpdatePasswordDTO;
import com.smartflow.model.Menu;
import com.smartflow.model.User;
import com.smartflow.util.JwtTokenUtil;
import com.smartflow.util.MD5Util;
import com.smartflow.util.StringUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/api/user")
public class LoginController extends BaseController {
    private final static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @Autowired
    AreaService areaService;

    @Autowired
    MESMenuService mesMenuService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<String, Object> getMenus(HttpServletRequest request) throws Exception {
        System.out.println("GET——request" + new Date());
        Map<String, Object> json = new HashMap<>();
        try {
            //客户端将token封装在请求头中，格式为(Bearer后加空格)：Authorization：Bearer +token
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("Missing or invalid Authorization header.");
            }
            //去除Bearer 后部门
            String token = authHeader.substring(7);
            //		Claims claims = Jwts.parser().setSigningKey().parseClaimsJws(token).getBody();

            List<String> roleNameList = new ArrayList<>();
            String[] visitArray = null;

            User sessionUser = (User) request.getSession().getServletContext().getAttribute("user");
            String sessionToken = (String) request.getSession().getServletContext().getAttribute("token");
            User user = JwtTokenUtil.verifyToken(token);
            System.out.println("前台传入的token解密后的参数：");
            System.err.println(user.toString());
            System.err.println(token);
            System.out.println("Session中的参数：");
            System.err.println(sessionUser.toString());
            System.err.println(sessionToken);
//			if (sessionUser!=null && sessionToken!=null ) {
            if (sessionToken != null) {
                if (sessionToken.equals(token)) {
//					if(user.getUserName().equals(sessionUser.getUserName()) && user.getPassword().equals(sessionUser.getPassword()) && user.getId()==sessionUser.getId()){
                    //List<String> roleNameList = userService.getRoleNameFromByUserId(user.getId());
                    List<Map<String, Object>> roleList = userService.getAllocatedRole(user.getId());
                    StringBuffer sb = new StringBuffer();
                    Set<Map<String, Object>> menuLists = new HashSet<>();
                    if (roleList != null && roleList.size() != 0) {
                        for (Map<String, Object> role : roleList) {
                            Integer roleId = (Integer) role.get("key");
                            String roleName = (String) role.get("label");
                            roleNameList.add(roleName);
                            String visit = roleService.getVisitByRoleId(roleId);
                            if (visit != null) {
                                sb.append(visit + ",");
                            }
                            List<Map<String, Object>> menuList = menuService.getPDAMenuByRoleId(roleId);
                            for (Map<String, Object> map : menuList) {
                                menuLists.add(map);
                            }
                        }
                    }
                    sb.append(1);
                    System.out.println(sb.toString());
                    String[] visit1 = sb.toString().split(",");
                    List<String> visitList = Arrays.asList(visit1);
                    Set<String> set = new HashSet<>(visitList);
                    visitArray = set.toArray(new String[0]);

                    List<GetMESMenuListOutputDTO> mesMenuList = mesMenuService.getMESMenuList();

                    Map<String, Object> permissions = new HashMap<>();
                    Map<String, Object> map = new HashMap<>();
                    map.put("role", roleNameList);
                    map.put("visit", visitArray);
                    map.put("PDAMenu", menuLists);
                    map.put("MESMenuList", mesMenuList);
                    permissions.put("permissions", map);
                    permissions.put("username", user.getAccount());
                    permissions.put("userId", user.getId());
                    json.put("user", permissions);
                    json = this.setJson(200, "Token验证成功", json);
                    json.put("success", true);
//					}else{
//						json = this.setJson(0, "登陆凭证Token中的用户和Session中的用户不一致", -1);
//						json.put("success", false);
//					}					
                } else {
                    json = this.setJson(0, "登陆凭证Token错误，与Session中的Token不一致", -1);
                    json.put("success", false);
                }
            } else {
                json = this.setJson(0, "登陆验证失败，请重新登陆", -1);
                json.put("success", false);
            }
        } catch (Exception e) {
            json = this.setJson(0, "Token验证失败：" + e.getMessage(), -1);
            json.put("success", false);
            logger.error(e);
            e.printStackTrace();
        }
        System.out.println("GET——response" + new Date());
        return json;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/GetTokenForLogin", method = RequestMethod.POST)
    public Map<String, Object> getTokenForLogin(@RequestBody GetTokenForLoginDTO getTokenForLoginDTO, HttpServletRequest request) throws Exception {
        Map<String, Object> json = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        try {
            //查询数据库是否有登陆的用户名（根据Account字段查询）
            List<User> userList = userService.getUserByUserName(getTokenForLoginDTO.getUsername());
            String token = null;
            if (userList != null && !userList.isEmpty()) {
                for (User user : userList) {
                    if (MD5Util.verify(getTokenForLoginDTO.getPassword(), user.getPassword())) {
                        if (user.getState() == 1) {
                            token = JwtTokenUtil.createToken(user);
                            //保存token
                            request.getSession().getServletContext().setAttribute("user", user);
                            request.getSession().getServletContext().setAttribute("token", token);
                            System.err.println(user.toString());
                            System.err.println(token);
                            Map<String, Object> tokenMap = new HashMap<>();
                            tokenMap.put("Access_Token", token);
                            tokenMap.put("Expires_In", JwtTokenUtil.ExpireTime.toString());
                            tokenMap.put("Token_Type", "Authorization");

                            map.put("ReturnCode", 0);
                            map.put("Message", "Ok");

//							map.put("Access_Token", token);
                            map.put("token", tokenMap);
                            json = setJson(200, "登陆成功~", map);

                        } else if (user.getState() == -1) {
                            map.put("ReturnCode", -1);
                            map.put("Message", "登陆失败：该用户已被删除");
                            json = setJson(200, "登陆失败：该用户已被删除", map);
                        } else if (user.getState() == 0) {
                            map.put("ReturnCode", -1);
                            map.put("Message", "登陆失败：该用户已被禁用");
                            json = setJson(200, "登陆失败：该用户已被禁用", map);
                        }
                    } else {
                        map.put("ReturnCode", -1);//ReturnCode 0表示成功    非0表示失败
                        map.put("Message", "登陆失败：密码输入错误");
                        json = setJson(200, "登陆失败：密码输入错误", map);
                    }
                }
            } else {
                map.put("ReturnCode", -1);
                map.put("Message", "登陆失败：用户名不存在");
                json = setJson(200, "登陆失败：用户名不存在", map);
            }
        } catch (Exception e) {
            map.put("ReturnCode", -1);
            map.put("Message", "登陆失败：" + e.getMessage());
            json = setJson(0, "登陆失败：" + e.getMessage(), map);
            logger.error(e);
            e.printStackTrace();
        }
        return json;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/logout/{Id}", method = RequestMethod.GET)
    public Map<String, Object> logout(@PathVariable Integer Id, HttpServletRequest request) {
        Map<String, Object> json = new HashMap<>();
        try {
            request.getSession().getServletContext().removeAttribute("user");
            request.getSession().getServletContext().removeAttribute("token");
            userService.updateLastLoginTime(Id);
            json = setJson(200, "退出登录！", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;

    }


    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/loginOfPDA", method = RequestMethod.POST)
    public Map<String, Object> loginOfPDA(@RequestBody GetTokenForLoginDTO getTokenForLoginDTO, HttpServletRequest request) {
        Map<String, Object> json = new HashMap<>();
        try {
            //查询数据库是否有登陆的用户名
            List<User> userList = userService.getUserByUserName(getTokenForLoginDTO.getUsername());
            if (userList != null && !userList.isEmpty()) {
                for (User user : userList) {
                    if (MD5Util.verify(getTokenForLoginDTO.getPassword(), user.getPassword())) {
                        if (user.getState() == 1) {
                            List<Map<String, Object>> roleList = userService.getAllocatedRole(user.getId());
                            List<Map<String, Object>> menuLists = new ArrayList<>();
                            Set<Map<String, Object>> menuSet = new HashSet<>();
                            if (roleList != null && !roleList.isEmpty()) {
                                for (Map<String, Object> role : roleList) {
                                    Integer roleId = (Integer) role.get("key");
                                    List<Map<String, Object>> menuList = menuService.getPDAMenuByRoleId(roleId);
                                    for (Map<String, Object> menu : menuList) {//去重复并且排序
                                        if (menuSet.add(menu)) {//set能添加进去就代表不是重复元素
                                            menuLists.add(menu);
                                        }
                                    }
                                }
                            }
                            Map<String, Object> map = new HashMap<>();
                            Map<String, Object> permissions = new HashMap<>();
                            map.put("PDAMenu", menuLists);
                            permissions.put("permissions", map);
                            permissions.put("username", user.getAccount());
                            permissions.put("userId", user.getId());
                            json.put("user", permissions);
                            json = setJson(200, "登陆成功~", json);
                            json.put("success", true);
                        } else if (user.getState() == -1) {
                            json = setJson(200, "登陆失败：该用户已被删除", -1);
                            json.put("success", false);
                        } else if (user.getState() == 0) {
                            json = setJson(200, "登陆失败：该用户已被禁用", -1);
                            json.put("success", false);
                        }
                    } else {
                        json = setJson(200, "登陆失败：密码输入错误", -1);
                        json.put("success", false);
                    }
                }
            } else {
                json = setJson(200, "登陆失败：用户名不存在", -1);
                json.put("success", false);
            }
        } catch (Exception e) {
            json = setJson(0, "登陆失败：" + e.getMessage(), -1);
            json.put("success", false);
            logger.error(e);
            e.printStackTrace();
        }
        return json;
    }
	
	/*@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/test1",method=RequestMethod.GET)
	public Map<String, Object> test1(HttpServletRequest request){
		Map<String, Object> json = new HashMap<>();	
		try{
			Integer count =  areaService.count(null, null, null);
			json = setJson(200, "测试"+count, 1);
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;	
	}
	
	@CrossOrigin(origins="*",maxAge=3600)
	@RequestMapping(value="/test2",method=RequestMethod.GET)
	public Map<String, Object> test2(HttpServletRequest request){
		Map<String, Object> json = new HashMap<>();	
		try{
//			Integer count =  areaService.count(null, null, null);
			json = setJson(200, "测试", 1);
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;	
	}*/

    /**
     * 修改密码
     *
     * @param updatePasswordDTO
     * @param result
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/UpdatePassword", method = RequestMethod.POST)
    public Map<String, Object> updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO, BindingResult result) {
        Map<String, Object> json = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        try {
            if (result.hasErrors()) {
                String errorMessage = result.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
                dataMap.put("ReturnCode", 0);
                dataMap.put("ReturnMessage", errorMessage);
                json = this.setJson(200, errorMessage, dataMap);
                return json;
            }
            if (!updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getConfirmPassword())) {
                dataMap.put("ReturnCode", 0);
                dataMap.put("ReturnMessage", "两次输入的密码不一致,请重新输入");
                json = this.setJson(200, "两次输入的密码不一致,请重新输入！", dataMap);
                return json;
            }
            String db_password = userService.getPasswordByUserId(updatePasswordDTO.getUserId());
            if (!db_password.equals(updatePasswordDTO.getOldPassword())) {
                dataMap.put("ReturnCode", 0);
                dataMap.put("ReturnMessage", "旧密码输入错误！");
                json = this.setJson(200, "旧密码输入错误！", dataMap);
                return json;
            } else if (updatePasswordDTO.getOldPassword().equals(updatePasswordDTO.getNewPassword())) {
                dataMap.put("ReturnCode", 0);
                dataMap.put("ReturnMessage", "新密码与旧密码相同！");
                json = this.setJson(200, "新密码与旧密码相同！", dataMap);
                return json;
            } else {
                userService.updatePassword(updatePasswordDTO.getNewPassword(), updatePasswordDTO.getUserId());
                dataMap.put("ReturnCode", 1);
                dataMap.put("ReturnMessage", "密码修改成功！");
                json = this.setJson(200, "密码修改成功！", dataMap);
            }
        } catch (Exception e) {
            dataMap.put("ReturnCode", 1);
            dataMap.put("ReturnMessage", "密码修改失败：" + e.getMessage());
            json = this.setJson(0, "密码修改失败：" + e.getMessage(), dataMap);
            logger.error(e);
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 重置密码（默认密码重置成1qaz@wsx）
     *
     * @param UserId
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/ResetPassword/{UserId}", method = RequestMethod.GET)
    public Map<String, Object> resetPassword(@PathVariable Integer UserId) {
        Map<String, Object> json = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        try {
            if (UserId != null && !"".equals(UserId)) {
                userService.updatePassword(MD5Util.md5("88888888"), UserId);
                dataMap.put("ReturnCode", 1);
                dataMap.put("ReturnMessage", "重置密码成功！");
                json = this.setJson(200, "重置密码成功！", dataMap);
            } else {
                dataMap.put("ReturnCode", 0);
                dataMap.put("ReturnMessage", "用户id不能为空！");
                json = this.setJson(200, "用户id不能为空！", dataMap);
            }
        } catch (Exception e) {
            dataMap.put("ReturnCode", 0);
            dataMap.put("ReturnMessage", "重置密码失败：" + e.getMessage());
            json = this.setJson(0, "重置密码失败：" + e.getMessage(), dataMap);
        }
        return json;
    }
}
