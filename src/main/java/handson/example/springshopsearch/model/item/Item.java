package handson.example.springshopsearch.model.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //モデルとして扱う
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    @Id //主キー
    @GeneratedValue(strategy = GenerationType.IDENTITY) //インクリメントでIDを振る
    @Column(name = "id") //カラム名を明示的に指定
    public Long id;

    @Column(name = "name")
    public String name;

    @Min(value = 0) //最小値は0
    @Column(name = "price")
    public int price;

    @Column(name = "description", columnDefinition = "TEXT") //明示的にテキスト型に指定
    public String description;
}