package sg.edu.nus.comp.cs5331ssti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index(Model model,
                        @RequestParam(value="value", required=false, defaultValue="Nothing...") String value) {
        model.addAttribute("value", value);
        return "index";
    }

    @PostMapping(value = "/index")
    public String indexPost(Model model,
                        @RequestParam(value="value", required=false, defaultValue="Nothing...") String value) {
        model.addAttribute("value", value);

        return "index";
    }

    @GetMapping({"/admin"})
    public String admin(Model model,
                        @RequestParam(value="value", required=false, defaultValue="") String value) throws IOException {
        model.addAttribute("value", value);

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("templates/index.ftlh");
        String newValue = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        model.addAttribute("value", newValue);

        return "admin";
    }

    @PostMapping(value = "/admin")
    public String adminPost(Model model,
                            @RequestParam(value="value", required=false, defaultValue="") String value) throws IOException {

        File file = new File(this.getClass().getClassLoader().getResource(".").getFile() + "templates/index.ftlh");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(value);
        bw.close();

        model.addAttribute("value", value);

        return "admin";
    }

    @PostMapping(value = "/adminReset")
    public String adminPostReset(Model model,
                            @RequestParam(value="value", required=false, defaultValue="") String value) throws IOException {

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("templates/index2.ftlh");
        String newValue = new String(in.readAllBytes(), StandardCharsets.UTF_8);

        File file = new File(this.getClass().getClassLoader().getResource(".").getFile() + "templates/index.ftlh");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(newValue);
        bw.close();

        model.addAttribute("value", newValue);

        return "admin";
    }

}