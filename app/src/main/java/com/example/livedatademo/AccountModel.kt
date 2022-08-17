package com.example.livedatademo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountModel(accNo:String="0111222333", owner:String="Benedict", balance:Double=0.0): ViewModel() {
    private var _accNo : String =""
    private var owner : String = ""
    private var _balance = MutableLiveData<Double>()

    var accNo:String
        get() = _accNo
        set(value) {_accNo = value}

    var balance: MutableLiveData<Double>
        get() = _balance
        set(amount) {_balance.value = amount.value}


    init {
        this.accNo = accNo
        this.owner = owner
        this.balance.value = balance
    }

    fun deposit(amount: Double) {
        //? means nullable
        balance.value = balance.value?.plus(amount)
    }

    fun withdraw(amount:Double){
        balance.value = balance.value?.minus(amount)
    }

    fun setOwner(owner: String){
        this.owner = owner
    }

    fun getOwner(): String {
        return this.owner
    }



}