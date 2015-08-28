package example.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(path = "request-mapping")
@Controller
public class RequestMappingPathController {

    @RequestMapping(value = "value")
    public void value() {
    }

    @RequestMapping("valueOmit")
    public void valueOmit() {
    }

    @RequestMapping(path = "path")
    public void path() {
    }

}
