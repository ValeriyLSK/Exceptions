package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product book = new Book(1, "Меч Предназначения", 500, "Анджей Сапковский");
    Product smartphone = new Smartphone(2, "iPhone", 50000, "Apple");
    Product product = new Product(3, "Xbox", 27000);
    Product book2 = new Book(4,"Меч Эльфов", 300, "Бернхард Хеннен");


    @Test
    void shouldAddAll() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        Product[] expected = {book, smartphone, product};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBy() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        String name = "Меч";
        Product[] expected = {book};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBySameNames() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        manager.add(book2);
        String name = "Меч";
        Product[] expected = {book, book2};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByNameNotResult() {
        manager.add(book);
        manager.add(smartphone);
        manager.add(product);
        manager.add(book2);
        String name = "Башня Ласточки";
        Product[] expected = {};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }
}