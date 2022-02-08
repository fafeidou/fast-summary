package com.example.tx.invalid.other;

public interface ServiceC {

    //    @Transactional(rollbackFor = Exception.class)
    public void transfer(int from, int to, int amount);
}
