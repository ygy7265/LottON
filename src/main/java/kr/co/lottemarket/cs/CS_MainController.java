package kr.co.lottemarket.cs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CS_MainController {

	@GetMapping(value = { "/cs", "/cs/index"})
	public String index() {
		return "/cs/index";
	}
}
