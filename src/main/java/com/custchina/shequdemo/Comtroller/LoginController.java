package com.custchina.shequdemo.Comtroller;




import com.custchina.shequdemo.Service.TouristService;
import com.custchina.shequdemo.model.Tourist;
import com.custchina.shequdemo.utils.FileTool;
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

    @PostMapping("/tourist")
    public String login(@RequestParam("usercode") String usercode, @RequestParam("password") String password, Model model, HttpSession session) {
        Tourist tourist = touristService.find(usercode, password);
        System.out.println(tourist);
        if (tourist != null) {
            session.setAttribute("tourist", tourist);
            return "redirect:/";
        }
        model.addAttribute("error", "请重新登录");
        return "success";
    }

    @PostMapping("/res")
    public String res(@RequestParam(value = "file", required = false) MultipartFile multipartFile, @RequestParam("usercode") String usercode, @RequestParam("password") String password,
                      @RequestParam("username") String username, @RequestParam("email") String email) {
//        String pa =  publishController.class.getClassLoader().getResource("static/images").toString();
//        String path = StrUtil.subSuf(pa,6);

//    String path ="F:\\image";
    String path ="C:\\shequdemo\\shequdemo\\src\\main\\resources\\static\\images";
        File f = new File(path);
        String originalFilename = multipartFile.getOriginalFilename();
        String a = FileTool.filePath(f, originalFilename);
        System.out.println(a);
        String s = a.substring(a.lastIndexOf("\\") -7);

        FileTool.FILEUPLOAD(multipartFile, a);

        Tourist tourist = new Tourist();
        tourist.setUser_code(usercode);
        tourist.setUser_email(email);
        tourist.setUser_name(username);
        tourist.setUser_password(password);
        tourist.setUser_state(1);
        tourist.setUser_photo(s);

        int insert = touristService.insert(tourist);
        System.out.println(insert);
        return "redirect:/";
    }

}
