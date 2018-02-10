package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import database.User;
import database.UserRepository;

@Controller
//@RestController
public class GreetingController {

	@Autowired
	private UserRepository users;
	
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
//		System.out.println("greeting");
		
		for (User user : users.findAll()) {
			System.out.println(user.getName());			
		}
		
		return "greeting";
	}

	@RequestMapping(path="/greetingSubmit")
	public String greetingSubmit(@RequestParam(value = "newName", required = false, defaultValue = "World") String name,
			Model model) {
//		System.out.println("greetingSubmit");
//		System.out.println(name);

		User user = new User();
		user.setName(name);
		user.setPassword("pwd");
		users.save(user);
	
		model.addAttribute("name", name);

		return "greeting";
	}

}