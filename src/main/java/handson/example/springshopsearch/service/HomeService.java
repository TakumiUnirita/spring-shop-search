package handson.example.springshopsearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import handson.example.springshopsearch.model.item.Item;
import handson.example.springshopsearch.model.item.ItemRepository;

@Service
@Transactional
public class HomeService {
	//インスタンス生成
	@Autowired
	ItemRepository itemRepository;

	//全検索
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	//名前検索
	public List<Item> findOnlyName(String keyword) {
		return itemRepository.findByNameContainsOrderByIdAsc(keyword);
	}

	//商品説明検索
	public List<Item> findOnlyDiscription(String keyword) {
		return itemRepository.findByDescriptionContainsOrderByIdAsc(keyword);
	}

	//名前検索と商品説明検索
	public List<Item> findNameAndDiscription(String keyword, String keyword2) {
		return itemRepository.findByNameOrDescriptionContainsOrderByIdAsc(keyword, keyword2);
	}
}
