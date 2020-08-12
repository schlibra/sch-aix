package com.sch.Hello;

import android.content.Context;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
//@SuppressWarnings("all")
@SimpleObject(external = true)
@DesignerComponent(category = ComponentCategory.EXTENSION,description = "Hello",nonVisible = true,version = 1)
public class Hello extends AndroidNonvisibleComponent {
    private Context context;
    public Hello(ComponentContainer container){
        super(container.$form());
        this.context = container.$context();
    }
    @SimpleFunction
    public void Test(){
        TestEvent("Test");
    }
    @SimpleEvent
    public void TestEvent(String msg){
        EventDispatcher.dispatchEvent(this,"TestEvent",msg);
    }
    @SimpleFunction
    public void Alert(String message){
        Toast.makeText(this.context,message,Toast.LENGTH_SHORT).show();;
    }
}
