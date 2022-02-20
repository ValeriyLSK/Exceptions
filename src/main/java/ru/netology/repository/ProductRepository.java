package ru.netology.repository;

import ru.netology.domain.Product;
import ru.netology.exceptions.NotFoundException;

public class ProductRepository {
    private Product[] products = new Product[0];

    public void save (Product newProduct) {
        int length = products.length + 1;
        Product[] tmp = new Product[length];
        System.arraycopy(products, 0, tmp, 0, products.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = newProduct;
        products = tmp;
    }

    public Product[] findAll () {
        return products;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        int length = products.length - 1;
        Product[] tmp = new Product[length];
        int index = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[index] = product;
                index++;
            }
        }
        products = tmp;
    }

    public Product[] findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return new Product[]{product};
            }
        }
        return null;
    }
}