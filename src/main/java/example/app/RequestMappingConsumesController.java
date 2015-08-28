package example.app;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "request-mapping/consume", consumes = MediaType.APPLICATION_XML_VALUE)
@Controller
public class RequestMappingConsumesController {

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void consume() {
    }

    @RequestMapping(params = "not", consumes = "!"+MediaType.TEXT_XML_VALUE)
    public String consumeNot() {
        return "request-mapping/consumeNot";
    }

    @RequestMapping(consumes = {MediaType.TEXT_PLAIN_VALUE, MediaType.TEXT_HTML_VALUE})
    public String consumeMulti() {
        return "request-mapping/consumeMulti";
    }


}
