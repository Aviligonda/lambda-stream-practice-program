package com.bridzelab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class NumberPlayList {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        // simple foreach implicit lambda function
        list.forEach(
                numbers -> System.out.println("Lambda Implicit ForEach  is :" + numbers)
        );
        // consumer class
        class MyConsumer implements Consumer<Integer> {
            public void accept(Integer i) {
                System.out.println("Consumer Class  is :" + i);
            }
        }
        MyConsumer action = new MyConsumer();
        list.forEach(action);
        // through iterating
        Iterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            System.out.println("Through Iterator  is :" + i);
        }
        /// using anonymous class
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer i) {
                System.out.println("ForEach anonymous class is :" + i);
            }
        });
        // explicit lambda function
        Consumer<Integer> listAction = n -> {
            System.out.println("Lambda Foreach explicit is :" + n);
        };
        list.forEach(listAction);
        // input is integer value outPut is Double value
        Function<Integer, Double> toDoubleFunction = Integer::doubleValue;
        list.forEach(
                n -> System.out.println("Lambda forEach double value is :" + toDoubleFunction.apply(n))
        );
        // Print only Even Numbers
        Predicate<Integer> even = n -> n > 0 && n % 2 == 0;
        list.forEach(
                n -> System.out.println("For each value is :" + n + " check for even :" + even.test(n))
        );
        // using stream.forEach loop
        list.stream().forEach(
                n -> System.out.println("Stream forEach value is :" + n)
        );
        // store the double value using stream.map
        List<Double> doubleList = list.stream().map(toDoubleFunction).toList();
        System.out.println("Stream double list is :" + doubleList);
        // filter the data (greater than of 2 )print the integers
        List<Integer> integerList = list.stream().filter(n -> n > 2).toList();
        System.out.println("Filter the data :" + integerList);
        // filter the even numbers find peek value and the first
        Integer first = list.stream()
                .filter(even)
                .peek(n -> System.out.println("peek value is :" + n))
                .findFirst()
                .orElse(null);
        System.out.println("first even number is :" + first);
        // find max of even number
        Integer max = list.stream().filter(even).max((n1, n2) -> n1 - n2).orElse(null);
        System.out.println("Maximum of even numbers is :" + max);
        // find min of even number
        Integer min = list.stream().filter(even).min((n1, n2) -> n1 - n2).orElse(null);
        System.out.println("Maximum of even numbers is :" + min);
        //sum of integers value is
        Integer sum = list.stream()
                .reduce(0, Integer::sum);
        long count = list.stream().count();
        System.out.println(count);
        System.out.println("Sum of the integers is :" + sum);
        System.out.println("Avg of the integer values is :" + sum / count);
        //finding matches
        boolean allEven = list.stream().allMatch(even);
        boolean oneEven = list.stream().anyMatch(even);
        boolean noneMultiple = list.stream().noneMatch(i -> i > 0 && i % 5 == 0);
        System.out.println("All even numbers :" + allEven + "\nOne even :" + oneEven + "\nNone of the :" + noneMultiple);
    }

}

