package com.geekbrains.person.customer;

import com.geekbrains.ListSellerException;
import com.geekbrains.market.Market;
import com.geekbrains.person.Person;
import com.geekbrains.person.seller.Seller;
import com.geekbrains.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Customer extends Person {
    private List<Product> expectedPurchaseList;
    private List<Product> purchaseList;

    public Customer(List<Product> expectedPurchaseList, int cash) {
        this.purchaseList = new ArrayList<>();
        this.expectedPurchaseList = expectedPurchaseList;
        this.setCash(cash);

    }

    public void addPurchase(Product product) {
        if (purchaseList == null) {
            purchaseList = new ArrayList<>();
        }
        purchaseList.add(product);
    }

    public void findProductOnMarket(Market market) {
        for (Product product : getExpectedPurchaseList()) {
            for (Seller seller : market.getSellers()) {
                boolean isBought = seller.sellProducts(this, product);
                if (isBought) {
                    break;
                }
            }
        }
    }

    public void findSellerOnMarket(Market market, String name, String lastName) {
        Stream<Seller> stream = Stream.of(market.getSellers().toArray(new Seller[0]));
        boolean match = stream.anyMatch(s -> s.getName().equals(name) && s.getLastName().equals(lastName));
        if (match) {
            for (Product product : getExpectedPurchaseList()) {
                for (Seller seller : market.getSellers()) {
                    if (seller.getName().equals(name)) {
                        boolean isBought = seller.sellProducts2(this, product, name, lastName, market);
                        if (isBought) {
                            break;
                        }
                    }
                }
            }

        } else {
            throw new ListSellerException("no such seller exists");
        }
    }
    public void findProductOnMarket2(Market market, Product expectProduct) {
        for (Seller seller : market.getSellers()) {
            boolean isBought = seller.sellProducts(this, expectProduct);
            if (isBought) {
                break;
            }
        }
    }
    public void info() {
        StringBuilder result = new StringBuilder("Я купил ");
        if (purchaseList.size() == 0) {
            result.append("ничего");

        } else {
            for (Product product : purchaseList) {
                result.append(product.getName());
                result.append(" в количестве ");
                result.append(product.getQuantity());
                result.append(" ");
            }
        }

        result.append(". У меня осталось: ");
        result.append(getCash());
        result.append(" рублей");
        System.out.println(result);
    }

    public List<Product> getExpectedPurchaseList() {
        return expectedPurchaseList;
    }


    public List<Product> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Product> purchaseList) {
        this.purchaseList = purchaseList;
    }
}


