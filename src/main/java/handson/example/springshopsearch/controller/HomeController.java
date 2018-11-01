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
import handson.example.springshopsearch.service.HomeService;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("about")
	public String getAbout() {
		return "about";
	}

	//インスタンス生成
	@Autowired
	HomeService homeService;

	List<Item> list;

	@GetMapping
	public String inde(
			Model model,
			@RequestParam(name = "keyword", required = false) Optional<String> keyword,
			@RequestParam(name = "keyword", required = false) Optional<String> keyword2,
			@RequestParam(name = "radio", required = false) Optional<String> radio) {
		if (keyword.isPresent()) {
			if (radio.isPresent()) {
				switch (radio.get()) {
				//名前検索
				case "name":
					list = homeService.findOnlyName(keyword.get());
					break;
				//商品説明検索
				case "discription":
					list = homeService.findOnlyDiscription(keyword.get());
					break;
				//名前と商品説明検索
				case "all":
					list = homeService.findNameAndDiscription(keyword.get(), keyword2.get());
					break;
				}
			}
		} else {
			//検索テキストがNULLだった場合は全検索
			list = homeService.findAll();
		}

		model.addAttribute("items", list);
		return "index";
	}
}
