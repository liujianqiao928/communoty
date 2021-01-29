package com.custchina.shequdemo.Comtroller;




import com.custchina.shequdemo.Service.TouristService;
import com.custchina.shequdemo.excaption.CustomizeErrorCode;
import com.custchina.shequdemo.excaption.CustomizeException;
import com.custchina.shequdemo.model.Tourist;
import com.custchina.shequdemo.utils.FileTool;
import com.custchina.shequdemo.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class LoginController {
    @Autowired
    private TouristService touristService;


    @RequestMapping("/tologin")
    public String tolog(){
        return "login";
    }
    @RequestMapping("/tores")
    public String tores(){
        return "resgist";
    }
    @PostMapping("/tourist")
    public String login(@RequestParam("usercode") String usercode, @RequestParam("password") String password, Model model, HttpSession session) {
        model.addAttribute("usercode",usercode);
        model.addAttribute("password",password);
        Tourist t = new Tourist();
        if (usercode == null || usercode == ""||password == null || password == ""){
            if (usercode == null || usercode == "" ) {
                model.addAttribute("error","账号不为空");

            }
            if (password == null || password == "") {
                model.addAttribute("error","密码不能为空");

            }
            return "login";
        }else {

            Tourist tourist = touristService.find(usercode, MD5Utils.getStrMD5(password));
            System.out.println(tourist);

            if (tourist != null) {
                session.setAttribute("tourist", tourist);
                return "redirect:/";
            }
            model.addAttribute("error", "账号 密码错误");
            return "login";
        }
    }
    @PostMapping("/res")
    public String res(@RequestParam(value = "file", required = false) MultipartFile multipartFile, @RequestParam("usercode") String usercode, @RequestParam("mm") String password,
                      @RequestParam("username") String username, @RequestParam("email") String email,Model model) {
//        String pa =  publishController.class.getClassLoader().getResource("static/images").toString();
//        String path = StrUtil.subSuf(pa,6);
//        model.addAttribute("zh", usercode);
//        model.addAttribute("nc", username);
//        model.addAttribute("email", email);
//        model.addAttribute("mm", password);
//        model.addAttribute("file", multipartFile);
//          if (usercode == null || usercode == "") {
//                model.addAttribute("error", "账号不为空");
//            return "resgist";
//
//            }  if (username == null || username == "") {
//                model.addAttribute("error", "昵称不为空");
//            return "resgist";
//
//            }  if (email == null || email == "") {
//                model.addAttribute("error", "邮箱不为空");
//            return "resgist";
//
//            }    if (password == null || password == "") {
//                model.addAttribute("error", "密码不能为空");
//            return "resgist";
//
//            }
//



        //    String path ="F:\\image
        String path ="下载路径";
        File f = new File(path);
        String originalFilename = multipartFile.getOriginalFilename();
        String a = FileTool.filePath(f, originalFilename);
        System.out.println(a);
        String s = a.substring(a.lastIndexOf("\\") - 7);

        FileTool.FILEUPLOAD(multipartFile, a);

        Tourist tourist = new Tourist();
        tourist.setUser_code(usercode);
        tourist.setUser_email(email);
        tourist.setUser_name(username);
        tourist.setUser_password(MD5Utils.getStrMD5(password));
        tourist.setUser_state(1);
        tourist.setUser_photo(s);
        tourist.setExp("魂师");
        int insert = touristService.insert(tourist);
        System.out.print(insert);
//            if (insert > 0) {
//                model.addAttribute("error","注册成功请登录");
//                return "resgist";
//            }
//            System.out.println(insert);
//            model.addAttribute("error","注册失败 ，请重新注册");
        return "redirect:tologin";
    }


}
