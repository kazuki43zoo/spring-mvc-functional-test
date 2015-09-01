package example.app;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("request-param")
@Controller
public class RequestParamController {

    @RequestMapping(path = "string", method = RequestMethod.GET)
    public void string(@RequestParam String word, Model model) {
        model.addAttribute("word", word);
    }

    @RequestMapping(path = "stringWithName", method = RequestMethod.GET)
    public void stringWithName(@RequestParam("word") String string, Model model) {
        model.addAttribute("word", string);
    }

    @RequestMapping(path = "stringWithRequired", method = RequestMethod.GET)
    public void stringWithRequired(@RequestParam(required = false) String string, Model model) {
        if (string == null) {
            string = "word is null";
        }
        model.addAttribute("word", string);
    }

    @RequestMapping(path = "stringWithOptional", method = RequestMethod.GET)
    public void stringWithOptional(@RequestParam Optional<String> word, Model model) {
        model.addAttribute("word", word.orElse("word is null"));
    }

    @RequestMapping(path = "stringWithOptionalAndDefaultValue", method = RequestMethod.GET)
    public void stringWithOptionalAndDefaultValue(@RequestParam(defaultValue = "*") Optional<String> word, Model model) {
        model.addAttribute("word", word.get());
    }

    @RequestMapping(path = "stringWithDefaultValue", method = RequestMethod.GET)
    public void stringWithDefaultValue(@RequestParam(defaultValue = "*") String string, Model model) {
        model.addAttribute("word", string);
    }

    @RequestMapping(path = "stringWithRequiredAndDefaultValue", method = RequestMethod.GET)
    public void stringWithRequiredAndDefaultValue(@RequestParam(required = true, defaultValue = "*") String string, Model model) {
        model.addAttribute("word", string);
    }

    @RequestMapping(path = "integer", method = RequestMethod.GET)
    public void integer(@RequestParam Integer id, Model model) {
        model.addAttribute("id", id);
    }

    @RequestMapping(path = "collection", method = RequestMethod.GET)
    public void collection(@RequestParam List<String> words, Model model) {
        model.addAttribute("words", String.valueOf(words));
    }

    @RequestMapping(path = "collectionWithRequired", method = RequestMethod.GET)
    public void collectionWithRequired(@RequestParam(required = false) List<String> words, Model model) {
        model.addAttribute("words", String.valueOf(words));
    }

    @RequestMapping(path = "collectionWithDefault", method = RequestMethod.GET)
    public void collectionWithDefault(@RequestParam(defaultValue = "1,2,3") List<String> words, Model model) {
        model.addAttribute("words", String.valueOf(words));
    }

    @RequestMapping(path = "collectionWithOptional", method = RequestMethod.GET)
    public void collectionWithOptional(@RequestParam Optional<List<String>> words, Model model) {
        model.addAttribute("words", String.valueOf(words.orElse(Arrays.asList("a", "b", "c"))));
    }

    @RequestMapping(path = "array", method = RequestMethod.GET)
    public void array(@RequestParam Integer[] ids, Model model) {
        model.addAttribute("ids", Arrays.toString(ids));
    }

    @RequestMapping(path = "arrayWithRequired", method = RequestMethod.GET)
    public void arrayWithRequired(@RequestParam(required = false) Integer[] ids, Model model) {
        model.addAttribute("ids", Arrays.toString(ids));
    }

    @RequestMapping(path = "allParams", method = RequestMethod.GET)
    public void allParams(@RequestParam MultiValueMap<String,String> params, Model model) {
        model.addAttribute("params", String.valueOf(params));
    }

}
