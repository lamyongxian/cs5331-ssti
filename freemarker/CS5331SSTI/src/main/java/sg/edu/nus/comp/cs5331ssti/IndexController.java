package sg.edu.nus.comp.cs5331ssti;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {

    private Article article = new Article();

    @GetMapping({"/", "/index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView ("index");
        mv.addObject("article", this.article);
        return mv;
    }

    @PostMapping(value = "/index")
    public ModelAndView indexPost(Article article) {
        ModelAndView mv = new ModelAndView ("index");
        mv.addObject("article", article);
        return mv;
    }

}