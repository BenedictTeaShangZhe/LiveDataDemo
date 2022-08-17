package com.example.livedatademo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedatademo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var myAccountModel:AccountModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_main)
        myAccountModel = ViewModelProvider(this).get(AccountModel::class.java)

        if(myAccountModel.getOwner()=="")
        {
            myAccountModel.accNo="001122"
            myAccountModel.setOwner("Benedict")
            myAccountModel.balance.value = 10.0
        }

        Display()

        myAccountModel.balance.observe(this, Observer {
            //Once balance is changed, it will stored in the object called "it". Below replace "it" with newBalance
            newBalance -> binding.tvBalance.text = newBalance.toString()
        })


        binding.btnDeposit.setOnClickListener() {
            val depositNum: Double = binding.tfAmount.text.toString().toDouble()
            myAccountModel.deposit(depositNum)

        }

        binding.btnWitdraw.setOnClickListener() {
            val withdrawNum: Double = binding.tfAmount.text.toString().toDouble()
            myAccountModel.withdraw(withdrawNum)
        }

    }

    private fun Display(){
        binding.tvAccNo.text = myAccountModel.accNo
        binding.tvName.text = myAccountModel.getOwner()
        binding.tvBalance.text = myAccountModel.balance.value.toString()
    }
}