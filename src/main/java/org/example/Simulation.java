package org.example;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import restaraunt.Food;
import restaraunt.Officiant;
import restaraunt.Order;

public class Simulation {
    static String[] food_names = {"Borsch", "Varenyky", "Holubtsi", "Deruny", "Syrniki"};
    static Double[] prices = {70.0, 50.0, 45.0, 45.0, 55.0};
    static int[] GenerateOrderNumbers(int number_of_foods) {
        Random rnd = new Random();
        int[] foods = new int[number_of_foods];
        for (int i = 0; i < number_of_foods; i++){
            foods[i] = rnd.nextInt(5);
        }
        Arrays.sort(foods);
        return foods;
    }

    static List<Food> GetOrderList() {
        List<Food> foodList = new ArrayList<Food>();
        Random rnd = new Random();
        int number_of_foods = rnd.nextInt(20) + 1;
        int[] food_numbers = GenerateOrderNumbers(number_of_foods);
        for (int i = 0; i < number_of_foods; i++) {
            foodList.add(new Food(food_names[food_numbers[i]], prices[food_numbers[i]]));
        }
        return foodList;
    }

    public static List<Order> CompleteOrder(List<Order> list, int table_number) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).table_number == table_number && list.get(i).status == false){
                list.set(i, new Order(table_number, list.get(i).food, list.get(i).officiant));
            }
        }
        return list;
    }

    public static Officiant[] CreateOfficiants() {
        Officiant[] officiants = new Officiant[2];
        officiants[0] = new Officiant("FirstName1", "LastName1");
        officiants[1] = new Officiant("FirstName2", "LastName2");
        return officiants;
    }

    public static void Simulate() {
        Officiant[] officiants = CreateOfficiants();
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(3, GetOrderList(), officiants[0]));
        orders.add(new Order(5, GetOrderList(), officiants[1]));
        orders = CompleteOrder(orders, 5);
        orders.add(new Order(1, GetOrderList(), officiants[0]));
        orders = CompleteOrder(orders, 3);
        orders = CompleteOrder(orders, 1);
        orders.add(new Order(4, GetOrderList(), officiants[0]));
        orders = CompleteOrder(orders, 4);
        orders.add(new Order(4, GetOrderList(), officiants[1]));
        orders.add(new Order(1, GetOrderList(), officiants[1]));
        orders.add(new Order(3, GetOrderList(), officiants[1]));
        Order.PrintOrders(orders);
        Order.PrintPricesByOfficiant(orders, officiants[0]);
        Order.PrintPricesByOfficiant(orders, officiants[1]);
        Order.PrintSumOfPrices(orders);
    }
}
