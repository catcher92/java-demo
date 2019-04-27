package com.catcher92.demo.recursion;


/**
 * Created by caoxuedong on 2017/1/4.
 */
public class Test {


    private static double fact(int n){
        if (n==1) return 1;
        return n*fact(n-1);
    }

    // 尾递归不容易导致栈溢出
    private static double fact2(int n){
        return fact_inner(n, 1);
    }

    private static double fact_inner(int n,double result){
        if (n==1) return result;
        return fact_inner(n-1, n*result);
    }

    public static void main(String[] args) {
        System.out.println(fact(5));
        System.out.println(fact2(5));
    }
}
