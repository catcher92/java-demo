package com.catcher92.demo.generic;

class IndexOutBalanceElement {}

class BondBalanceElement extends IndexOutBalanceElement {}

class FundBalanceElement extends IndexOutBalanceElement {}

interface IndexOutBalanceProcessor<T extends IndexOutBalanceElement> {
    void process(T element);
}

abstract class AbsIndexOutBalanceProcessor<T extends IndexOutBalanceElement> implements IndexOutBalanceProcessor<T> {
    @Override
    public void process(T element) {
        getSerialNo(element);
    }

    protected abstract void getSerialNo(T element);
}

class BondBalanceProcessor extends AbsIndexOutBalanceProcessor<BondBalanceElement> {
    @Override
    protected void getSerialNo(BondBalanceElement bond) {
        // 具体的实现
    }
}

class FundBalanceProcessor extends AbsIndexOutBalanceProcessor<FundBalanceElement> {
    @Override
    protected void getSerialNo(FundBalanceElement fund) {
        // 具体的实现
    }
}


public class Balance {
}
