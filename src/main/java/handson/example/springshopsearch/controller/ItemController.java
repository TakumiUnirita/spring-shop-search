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
import handson.example.springshopsearch.service.ItemService;

@Controller
@RequestMapping("/items")
public class ItemController {

	List<Item> list;

	//商品を全検索する。
	@GetMapping
	public String listItem(Model model) {
		List<Item> list = itemService.findAll();
		model.addAttribute("items", list);
		return "list_item"; //このビューを返すという指定
	}

	//商品登録画面へ遷移する。
	@GetMapping("add")
	public String getForm() {
		return "item_form";
	}

	//商品編集画面へ遷移する。
	@GetMapping("edit/{id:[0-9]+}") //IDを受け取る
	public String getEditForm(Model model, @PathVariable("id") Long id) {
		//addAttributeで1つのアイテムを返す
		model.addAttribute("item", itemService.getId(id));
		return "edit";
	}

	//Autowired…スプリングのDIという機能。シングルトンの実現
	@Autowired
	ItemService itemService;

	//商品登録
	@PostMapping("/add")
	public String registerItem(Item item) {
		itemService.registerItem(item);
		return "redirect:/items";
	}

	//編集した内容を上書きする
	@PostMapping("edit/{id:[0-9]+}")
	public String editItem(Item item) {
		itemService.editItem(item);
		return "redirect:/items";
	}

	//商品確認画面
	@GetMapping("{id:[0-9]+}")
	public String getDetail(Model model, @PathVariable("id") Long id) {
		model.addAttribute("item", itemService.getId(id));
		return "detail";
	}
}