package handson.example.springshopsearch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import handson.example.springshopsearch.model.item.Item;
import handson.example.springshopsearch.model.item.ItemRepository;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("about")
	public String getAbout() {
		return "about";
	}

	@Autowired
	ItemRepository itemRepository;

	List<Item> list;

	/*商品名検索*/
	@GetMapping
	public String inde(
			Model model,
			@RequestParam(name = "keyword", required = false) Optional<String> keyword,
			@RequestParam(name = "keyword", required = false) Optional<String> keyword2,
			@RequestParam(name = "radio", required = false) Optional<String> radio) {
		if (keyword.isPresent()) {
			if (radio.isPresent()) {
				switch (radio.get()) {
				case "name":
					list = itemRepository.findByNameContainsOrderByIdAsc(keyword.get());
					break;
				case "discription":
					list = itemRepository.findByDescriptionContainsOrderByIdAsc(keyword.get());
					break;
				case "all":
					list = itemRepository.findByNameOrDescriptionContainsOrderByIdAsc(keyword.get(),keyword2.get());
					break;
				}
			}
		} else {
			list = itemRepository.findAll();
		}
		model.addAttribute("items", list);
		return "index";
	}
}
