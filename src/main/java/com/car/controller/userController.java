package com.car.controller;
import com.car.entity.Result;
import com.car.entity.User;
import com.car.service.userService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Tag(name = "用户接口")
@RestController
@RequestMapping("/user")
public class userController {


    @Autowired
    private userService userService;

    @Value("${file.upload-path}")
    private String imgUrl;

//注册
    @Operation(summary = "用户注册")
    @PostMapping(value = "/register")
    public Result register(@RequestBody User user){
        User flag = userService.findUser(user.getUsername());
        if (flag != null){
            return Result.error("该账号已存在");
        }
        boolean f = userService.addUser(user);
        if(f == false){
            return Result.error("系统错误，注册失败");
        }
        return Result.success("注册成功");
    }

//登录
    @Operation(summary = "用户登录")
    @GetMapping(value = "/login")
    public Result login(@RequestParam("username")String username,
                        @RequestParam("password")String password){
        User flag = userService.findUser(username,password);
        if (flag != null){
            return Result.success("登录成功");
        }
        return Result.error("登录失败，账号密码错误");
    }

    @GetMapping(value = "/getAll")
    public Result getAll(@RequestParam("username")String username){
        User user = userService.getUserMsg(username);
        if (user != null){
            return Result.success(user);
        }
        return Result.error("用户信息获取失败，请联系管理员");
    }


    //    找回密码
    @Operation(summary = "找回密码")
    @GetMapping(value = "/backPassword")
    public Result findPassWordByUsername(@RequestParam("username")String username){
        String password = userService.findPassWordByUsername(username);
        if (password != null){
            return Result.success(password);
        }
        return Result.error("账号不存在");
    }

    @Operation(summary = "头像上传")
    @GetMapping(value = "/uploadImg")
    public Result uploadImg(@RequestParam("username")String username){
        String password = userService.findPassWordByUsername(username);
        if (password != null){
            return Result.success(password);
        }
        return Result.success("账号不存在");
    }


    //后端接收图片文件
    @GetMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.indexOf(".");
        String formatFileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm")).toString();
        //设置存储图片名
        String newFileName = formatFileName + originalFilename.substring(index);
        try {
            //将文件保存指定目录
            file.transferTo(new File(imgUrl + newFileName));
            //上传文件名字段
            return Result.success(newFileName, "图片上传成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("图片传输出现了错误！");
    }

    //根据文件名字段，发送文件到前端
    @GetMapping("/images/{relativePath}")
    public Result getImage(@PathVariable String relativePath) {
        try {
            if ("".equals(relativePath) || relativePath == null) {
                return Result.error("该图片资源丢失，请重新上传！");
            }
            // 构建文件路径
            String imagePath = imgUrl + relativePath;
            Resource resource = new PathResource(imagePath);
            if (resource.exists()) {
                // 读取文件内容并返回
                byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
                //返回文件流
                return Result.success(imageBytes);
            }
            return Result.error("头像文件好像丢失了...，请重新上传吧");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/modifyUserMSG")
    public Result modifyUserMSG(@RequestBody User user) {
            userService.modifyUserMsg(user);
            return Result.success("修改成功");
    }
     @PostMapping("/modifyUserPassWord")
     public Result modifyUserPassWord(@RequestBody User user) {
         return userService.modifyUserPassword(user);
     }
    @GetMapping("/deleteUser")
    public Result deleteUser(@RequestParam("username") String username) {
        boolean flag = userService.deleteUser(username);
        if(flag){
            return Result.success("注销成功");
        }
        return Result.success("注销失败");
    }

}
