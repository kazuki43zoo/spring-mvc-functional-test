package example.app;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping("init-binder")
@Controller
public class InitBinderController {


    @ModelAttribute("text")
    public String setUpDummyText() {
        return "dummy";
    }

    @InitBinder("dateWithCustomFormatter")
    public void initBinderUsingCustomFormatter(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    @InitBinder("dateWithCustomEditor")
    public void initBinderUsingCustomEditor(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(path = "searchUsingCustomFormatter", method = RequestMethod.GET)
    public void searchUsingCustomFormatter(@RequestParam Date dateWithCustomFormatter, Model model) {
        model.addAttribute("date", dateWithCustomFormatter);
    }

    @RequestMapping(path = "searchUsingCustomEditor", method = RequestMethod.GET)
    public void searchUsingCustomEditor(@RequestParam Date dateWithCustomEditor, Model model) {
    }


}
