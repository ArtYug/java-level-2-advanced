package com.geekbrains.person.seller;

import com.geekbrains.market.Market;
import com.geekbrains.person.Person;
import com.geekbrains.person.customer.Customer;
import com.geekbrains.product.Product;

import java.util.List;

public class Seller extends Person {
    private String name;
    private String lastName;
    private List<Product> products;

    public boolean sellProducts(Customer customer, Product expectedProduct) {
        for (Product product : products) {
            // Проверяем по имени товара что у продавца есть продукт
            if (product.getName().equals(expectedProduct.getName())) {
                // Проверяем что количество товара >= чем мы хотим купить
                if (product.getQuantity() >= expectedProduct.getQuantity()) {
                    // Проверяем что кэш покупателя позволяет купить товар
                    long requiredCash = (long) product.getPrice() * expectedProduct.getQuantity();
                    if (customer.getCash() >= requiredCash) {
                        // Уменьшаем количество продукта у продавца
                        product.setQuantity(product.getQuantity() - expectedProduct.getQuantity());

                        //Создаем новый объект для покупателя, чтобы ссылка не дублировалась
                        Product customerProduct = new Product();
                        customerProduct.setQuantity(expectedProduct.getQuantity());
                        customerProduct.setName(expectedProduct.getName());

                        //Добавляем количество продуктов у покупателя
                        customer.addPurchase(customerProduct);
                        //Увеличиваем кэш продавца
                        setCash(getCash() + requiredCash);
                        //Уменьшаем кэш покупателя
                        customer.setCash(customer.getCash() - requiredCash);
                        //Сообщаем потребителю метода, что покупка совершена

                        return true;
                    }

                }
            }
        }
        return false;
    }

    public boolean sellProducts2(Customer customer, Product expectedProduct, String name, String lastname, Market market) {
        for (Seller seller : market.getSellers()) {
            if (seller.getName().equals(name) && seller.getLastName().equals(lastname)) {
                for (Product product : products) {
                    if (product.getName().equals(expectedProduct.getName())) {
                        if (product.getQuantity() >= expectedProduct.getQuantity()) {
                            long requiredCash = (long) product.getPrice() * expectedProduct.getQuantity();
                            if (customer.getCash() >= requiredCash) {
                                product.setQuantity(product.getQuantity() - expectedProduct.getQuantity());
                                Product customerProduct = new Product();
                                customerProduct.setQuantity(expectedProduct.getQuantity());
                                customerProduct.setName(expectedProduct.getName());
                                customer.addPurchase(customerProduct);
                                setCash(getCash() + requiredCash);
                                customer.setCash(customer.getCash() - requiredCash);
                                return true;
                            }
                        }
                    }
                }
                if (seller.getName().equals(name)) {
                    if (!products.contains(expectedProduct.getName())) {
                        Product productThatSellerDoesNotHave;
                        productThatSellerDoesNotHave = expectedProduct;
                        customer.findProductOnMarket2(market, productThatSellerDoesNotHave);
                    }
                }
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}



