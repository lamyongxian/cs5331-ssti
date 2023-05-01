package sg.edu.nus.comp.cs5331ssti;

import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.owasp.esapi.ESAPI;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Controller
public class AdminSafeController {

    @GetMapping({"/adminsafe"})
    public String adminSafe(Model model,
                        @RequestParam(value="value", required=false, defaultValue="") String value) throws IOException {
        model.addAttribute("value", value);

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("templates/index.ftlh");
        String newValue = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        model.addAttribute("value", newValue);

        return "adminsafe";
    }

    @PostMapping(value = "/adminsafe")
    public String adminSafePost(Model model,
                            @RequestParam(value="value", required=false, defaultValue="") String value) throws IOException {

        // Sanitize template input
        String safeValue = "";
        try {
            safeValue = ESAPI.validator().getValidSafeHTML("index_ftlh", value, 4096, false);
        } catch (ValidationException e) {
            model.addAttribute("msg", e.getMessage());
        } catch (IntrusionException e) {
            model.addAttribute("msg", e.getMessage());
        }

        File file = new File(this.getClass().getClassLoader().getResource(".").getFile() + "templates/index.ftlh");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(safeValue);
        bw.close();

        model.addAttribute("value", safeValue);
        return "adminsafe";
    }

    @PostMapping(value = "/adminsafereset")
    public String adminSafePostReset(Model model,
                            @RequestParam(value="value", required=false, defaultValue="") String value) throws IOException {

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("templates/index2.ftlh");
        String newValue = new String(in.readAllBytes(), StandardCharsets.UTF_8);

        File file = new File(this.getClass().getClassLoader().getResource(".").getFile() + "templates/index.ftlh");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(newValue);
        bw.close();

        model.addAttribute("value", newValue);

        return "adminsafe";
    }
}