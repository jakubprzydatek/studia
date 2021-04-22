package com.company;

import java.io.*;

public class MainSerial
{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        OrderList<Integer> serializedOrderList = new OrderList<>();
        serializedOrderList.add(5);
        serializedOrderList.add(6);
        serializedOrderList.add(28);
        serializedOrderList.add(1);
        serializedOrderList.add(7);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("object-graph.bin"))) {
            outputStream.writeObject(serializedOrderList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        OrderList<Integer> deserializedOrderList = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("object-graph.bin"))) {
            deserializedOrderList = (OrderList<Integer>) inputStream.readObject();
            System.out.println(deserializedOrderList.getRoot().getValue());
            deserializedOrderList.printList();
        }
    }
}
