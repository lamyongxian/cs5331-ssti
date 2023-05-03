package sg.edu.nus.comp.cs5331ssti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private Article article;

    @GetMapping({"/", "/index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView ("index");
        mv.addObject("article", this.article);
        return mv;
    }

    @PostMapping(value = "/index")
    public ModelAndView indexPost(Article article, HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView ("index");
        mv.addObject("article", article);

        return mv;
    }
}