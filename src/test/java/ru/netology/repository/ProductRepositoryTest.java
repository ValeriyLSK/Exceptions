package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    Product book = new Book(1, "Меч Предназначения", 500, "Анджей Сапковский");
    Product smartphone = new Smartphone(2, "iPhone", 50000, "Apple");
    Product product = new Product(3, "Xbox", 27000);


    @Test
    void shouldSave() {
        repository.save(book);
        Product[] expected = {book};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldFindAll() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
        Product[] expected = {book, smartphone, product};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveById() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
        repository.removeById(2);
        Product[] expected = {book, product};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldFindById() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
        repository.findById(2);
        Product[] expected = {smartphone};
        Product[] actual = repository.findById(2);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByIdOutOfBounds() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
        Assertions.assertThrows(NotFoundException.class, () -> repository.removeById(4));
    }

    @Test
    void shouldRemoveByIdWithExceptions() {
        repository.save(book);
        repository.save(smartphone);
        repository.save(product);
        repository.removeById(2);
        Product[] expected = {book, product};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}