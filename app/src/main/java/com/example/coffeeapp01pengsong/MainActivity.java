package com.example.coffeeapp01pengsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.SplittableRandom;

public class MainActivity extends AppCompatActivity {
    //create variables
    int noofCoffee = 0;
    int priceofCoffee = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //method for submit order Button
    public void submitOrder(View view){
        //get users name
        EditText nameField = (EditText)findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        //figure out of user wants whipped cream
        CheckBox whippedCreamCheckBox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        //figure out if user wants chocolate toppings
        CheckBox chocolateCheckBox = (CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        //calculate the price
        int price = calculatePrice(hasWhippedCream,hasChocolate);
        //open a new intent and send off the message to the new intent
        //create an order summary
        String message = createrOrderSummary(name,price,hasWhippedCream,hasChocolate);
         //create an Intent to send the message to the new activity that has been created
        Intent intent = new Intent(this, DisplayOrderDetails.class);
        intent.putExtra("name",name);
        intent.putExtra("message",message);
        intent.putExtra("price",Integer.toString(price));
        startActivity(intent);
    }
    //method to create a message
    public String createrOrderSummary(String name,int price,boolean addWhippedCream, boolean addChocolate){
        String priceMessage = "Name:"+name+"\n"+
                              "Add Whipped Cream?"+addWhippedCream+"\n" +
                              "Add Chocolate?"+addChocolate+"\n" +
                "Quantity:"+noofCoffee+"\n"+
                "Total:"+price+"\n"+
                "Thank you!";
        return priceMessage;
    }
    //method to calculate the price
    public int calculatePrice(boolean addWhippedCream, boolean addChocolate){
        int basePrice = 4;
        if (addWhippedCream == true)
            basePrice = basePrice+1;
        if(addChocolate)
            basePrice = basePrice+2;
        return basePrice * noofCoffee;
    }
    public void display(int number){
        TextView quantityTextView = (TextView)findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+number);
    }

    //code for the increment button"+"
    public void increment(View view){
        noofCoffee = noofCoffee+1;
        if (noofCoffee >=10){
            noofCoffee = 10;
        }
        display(noofCoffee);
    }
    //code for the decrement button"-"
    public void decrement(View view){
        noofCoffee = noofCoffee-1;
        if (noofCoffee<=0){
            noofCoffee = 0;
        }
        display(noofCoffee);
    }
}