package sg.edu.nus.comp.cs5331ssti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


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
    public ModelAndView indexPost(@ModelAttribute("article") Article article, Model model) {
        ModelAndView mv = new ModelAndView ("index");
        mv.addObject("article", article);
        return mv;
    }
}