package kitchenpos.dto;

import kitchenpos.domain.Product;

import java.math.BigDecimal;

public class ProductRequest {
    private Long id;
    private String name;
    private long price;

    public ProductRequest() {
    }

    public ProductRequest(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public Product toEntity() {
        return new Product(id, name, new BigDecimal(price));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }
}
