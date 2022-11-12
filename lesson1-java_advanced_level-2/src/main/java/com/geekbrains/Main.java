package com.geekbrains;

import com.geekbrains.market.Market;
import com.geekbrains.person.customer.Customer;
import com.geekbrains.person.seller.Seller;
import com.geekbrains.product.Product;

import java.util.ArrayList;
import java.util.List;

//            HomeWork1 Java advanced level
//        1. Разобраться в коде
//        2. Добавить поиск по имени и фамилии продавца(если у этого продавца нет нужного товара, то идем к следующему)
//        3. *** Рефакторинг com.geekbrains.person.seller.Seller#sellProducts


public class Main {

    public static void main(String[] args) {
        Market market = new Market();

        Seller firstSeller = createFirstSeller();
        Seller secondSeller = createSecondSeller();
        Seller thirdSeller = createThirdSeller();

        market.addSeller(firstSeller);
        market.addSeller(secondSeller);
        market.addSeller(thirdSeller);

        Customer customer = createFirstCustomer();
        customer.findProductOnMarket(market);
        customer.info();

        Customer customer1 = createSecondCustomer();
        customer1.findSellerOnMarket(market, "Алексей", "Ушаков");
        customer1.info();
    }

    private static Customer createFirstCustomer() {
        Product firstProduct = new Product();
        firstProduct.setName(MarketConstants.TOMATOES_PRODUCT_NAME);
        firstProduct.setQuantity(3);

        Product secondProduct = new Product();
        secondProduct.setName(MarketConstants.CUCUMBER_PRODUCT_NAME);
        secondProduct.setQuantity(2);

        return new Customer(List.of(firstProduct, secondProduct), 50);
    }

    private static Customer createSecondCustomer() {
        Product firstProduct = new Product();
        firstProduct.setName(MarketConstants.TOMATOES_PRODUCT_NAME);
        firstProduct.setQuantity(3);

        Product secondProduct = new Product();
        secondProduct.setName(MarketConstants.CUCUMBER_PRODUCT_NAME);
        secondProduct.setQuantity(2);

        Product thirdProduct = new Product();
        thirdProduct.setName(MarketConstants.MELON_PRODUCT_NAME);
        thirdProduct.setQuantity(1);

        return new Customer(List.of(firstProduct, secondProduct, thirdProduct), 56);
    }

    private static Seller createFirstSeller() {
        Seller seller = new Seller();
        seller.setName("Виталий");
        seller.setLastName("Еремин");
        seller.setCash(0);

        Product firstProduct = new Product();
        firstProduct.setName(MarketConstants.TOMATOES_PRODUCT_NAME);
        firstProduct.setPrice(10);
        firstProduct.setQuantity(8);

        Product secondProduct = new Product();
        secondProduct.setName(MarketConstants.CUCUMBER_PRODUCT_NAME);
        secondProduct.setPrice(8);
        secondProduct.setQuantity(50);

        List<Product> products = new ArrayList<>();
        products.add(firstProduct);
        products.add(secondProduct);
        seller.setProducts(products);

        return seller;
    }

    private static Seller createSecondSeller() {
        Seller seller = new Seller();
        seller.setName("Алексей");
        seller.setLastName("Ушаков");
        seller.setCash(0);

        Product firstProduct = new Product();
        firstProduct.setName(MarketConstants.TOMATOES_PRODUCT_NAME);
        firstProduct.setPrice(8);
        firstProduct.setQuantity(40);

        Product secondProduct = new Product();
        secondProduct.setName(MarketConstants.CUCUMBER_PRODUCT_NAME);
        secondProduct.setPrice(5);
        secondProduct.setQuantity(30);

        List<Product> products = new ArrayList<>();
        products.add(firstProduct);
        products.add(secondProduct);
        seller.setProducts(products);

        return seller;
    }

    private static Seller createThirdSeller() {
        Seller seller = new Seller();
        seller.setName("Вася ");
        seller.setLastName("Пупкин");
        seller.setCash(0);

        Product thirdProduct = new Product();
        thirdProduct.setName(MarketConstants.MELON_PRODUCT_NAME);
        thirdProduct.setPrice(10);
        thirdProduct.setQuantity(8);

        List<Product> products = new ArrayList<>();
        products.add(thirdProduct);
        seller.setProducts(products);

        return seller;
    }
}
