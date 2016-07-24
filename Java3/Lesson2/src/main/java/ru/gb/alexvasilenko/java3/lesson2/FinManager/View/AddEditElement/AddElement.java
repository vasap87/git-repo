package ru.gb.alexvasilenko.java3.lesson2.FinManager.View.AddEditElement;

import ru.gb.alexvasilenko.java3.lesson2.FinManager.Model.Finance;

import javax.swing.*;

/**
 * Created by Alex Vasilenko on 19.07.2016.
 * Abstract class to add/edit element
 */
public abstract class AddElement extends JFrame {
    public AddElement(Finance fin){

    }

    public AddElement(){

    }

    public abstract void addFinPart();

}
