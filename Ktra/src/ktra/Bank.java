/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ktra;

/**
 *
 * @author Admin
 */
public class Bank {

    double[] accounts;

    public Bank(int n, double initBalance) {
        accounts = new double[n];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = initBalance;
        }
    }

    public int size() {
        return accounts.length;
    }

    public synchronized double getTotalBalance() {
        double total = 0;
        for (int i = 0; i < accounts.length; i++) {
            total += accounts[i];
        }
        return total;
    }

    public synchronized void transfer(int from, int to, double amount) {
        try {
            while (accounts[from] < amount) {
                System.out.println(Thread.currentThread().getName() + "doi du tien");
                wait();
                System.out.println(Thread.currentThread().getName() + "tiep tuc giao dich");
            }
            accounts[from] -= amount;
            accounts[to] += amount;
            System.out.println("Chuyen " + amount + " tu account " + from + " sang account " + to);
            System.out.println("Tong tien cua cac account: "
                    + getTotalBalance());
            notifyAll();
        } catch (InterruptedException ex) {
            System.out.println("giao dich bi gian doan");
        }
    }
}
