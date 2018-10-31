package handson.example.springshopsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import handson.example.springshopsearch.model.item.Item;
import handson.example.springshopsearch.model.item.ItemRepository;

@Controller
@RequestMapping("/items")
public class ItemController {

	List<Item> list;

	@GetMapping
	public String listItem(Model model) {

		List<Item> list = itemRepository.findAll();
		model.addAttribute("items", list);
		return "list_item"; //このビューを返すという指定
	}

	@GetMapping("add")
	public String getForm() {
		return "item_form";
	}

	@GetMapping("edit/{id:[0-9]+}") //IDを受け取る
	public String getEditForm(Model model, @PathVariable("id") Long id) {
		//addAttributeで1つのアイテムを返す
		model.addAttribute("item", itemRepository.getOne(id));
		return "edit";
	}

	//Autowired…スプリングのDIという機能。シングルトンの実現
	@Autowired
	ItemRepository itemRepository;

	@PostMapping("/add")
	public String registerItem(Item item) {
		itemRepository.save(item);
		return "redirect:/items";
	}

	//編集した内容を上書きする
	@PostMapping("edit/{id:[0-9]+}")
	public String editItem(Item item) {
		itemRepository.save(item);
		return "redirect:/items";
	}

	@GetMapping("{id:[0-9]+}")
	public String getDetail(Model model, @PathVariable("id") Long id) {
		model.addAttribute("item", itemRepository.getOne(id));
		return "detail";
	}
}