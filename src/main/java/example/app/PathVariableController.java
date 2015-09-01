package example.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("path-variable")
@Controller
public class PathVariableController {

    @RequestMapping(path = "string/{word}", method = RequestMethod.GET)
    public String string(@PathVariable String word, Model model) {
        model.addAttribute("word", word);
        return "path-variable/string";
    }

    @RequestMapping(path = "stringWithName/{word}", method = RequestMethod.GET)
    public String stringWithName(@PathVariable("word") String string, Model model) {
        model.addAttribute("word", string);
        return "path-variable/stringWithName";
    }

    @RequestMapping(path = "integer/{id}", method = RequestMethod.GET)
    public String integer(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        return "path-variable/integer";
    }

}
