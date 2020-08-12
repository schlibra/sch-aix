package com.sch.mybutton;

import android.view.View.OnClickListener;
import com.google.appinventor.components.runtime.ButtonBase;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.Notifier;

public class MyButton extends ButtonBase{
    private int DefaultBackgroundColor = 0;
    private boolean Editable = false;
    public MyButton(ComponentContainer container) {
        super(container);
    }

    @Override
    public void click() {
        try {
            new ButtonManager(this.container).Click(this);
        }catch(Exception e){
            new Notifier(this.container).oneButtonAlert(this.container.$context(),e.getMessage(),e.getClass().getName().substring(e.getClass().getName().lastIndexOf(".")+1),"Close");
        }
    }

    @Override
    public boolean longClick() {
        boolean result = false;
        try{
            result = new ButtonManager(this.container).onLongClick(this.getView());
        }catch (Exception e){
            new Notifier(this.container).oneButtonAlert(this.container.$context(),e.getMessage(),e.getClass().getName().substring(e.getClass().getName().lastIndexOf(".")+1),"Close");
        }
        return result;
    }
    public int DefaultColor(){
        return this.DefaultBackgroundColor;
    }
    public void DefaultBackgroundColor(int value){
        this.DefaultBackgroundColor = value;
    }
    public boolean Editable(){
        return this.Editable;
    }
    public void Editable(boolean value){
        this.Editable = value;
    }
}
