package handson.example.springshopsearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import handson.example.springshopsearch.model.item.Item;
import handson.example.springshopsearch.model.item.ItemRepository;

@Service
@Transactional
public class ItemService {
	//インスタンス生成
	@Autowired
	ItemRepository itemRepository;

	//全検索する処理
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	//商品の登録処理
	public Item registerItem(Item item) {
		return itemRepository.save(item);
	}

	//商品の編集処理
	public Item editItem(Item item) {
		return itemRepository.save(item);
	}

	//商品毎の一意のIDを取得処理
	public Item getId(@PathVariable("id") Long id) {
		return itemRepository.getOne(id);
	}
}
