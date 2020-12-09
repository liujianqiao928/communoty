package com.custchina.shequdemo.Comtroller;
import com.custchina.shequdemo.Service.QuestionService;
import com.custchina.shequdemo.cache.tagCache;
import com.custchina.shequdemo.dto.QuestionDto;
import com.custchina.shequdemo.model.Question;
import com.custchina.shequdemo.model.Tourist;
import com.custchina.shequdemo.utils.FileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class publishController {
    //    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Long id,Model model){
        QuestionDto question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("imgs",question.getImgs());
        model.addAttribute("id",question.getId());
        model.addAttribute("tags", tagCache.get());
        return "publish";
    }
    @GetMapping("/publish")
    public String lublish(Model model){
        model.addAttribute("tags", tagCache.get());
        return "publish";
    }
    @PostMapping("/publish")
    public String dopublish(@RequestParam("title")String title, @RequestParam("description")String description,
                            @RequestParam("tag")String tag
            , @RequestParam("id")Long id,
                            HttpServletRequest request, Model model , HttpSession session){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", tagCache.get());


        if (title == null || title == "") {
            model.addAttribute("error","标题不为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error","标千不能为空");
            return "publish";
        }


        Tourist tourists = (Tourist) request.getSession().getAttribute("tourist");
        if (tourists == null){
            model.addAttribute("error","用户名未登录");
            return "redirect:/";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(tourists.getUser_id());
        question.setId(id);
        Tourist tourist = (Tourist) session.getAttribute("tourist");

//        System.out.println(tourist.getUser_photo());
        question.setImgs(tourist.getUser_photo());
        questionService.createOrupdate(question);

        return "redirect:/";
    }
}
