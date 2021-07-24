package mg.rjc.catalogue.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class Product {

    @Id
    private Long id;
    private String name;
    private Double price;
    private int quantity;
    private boolean available;
    private boolean selected;
}
