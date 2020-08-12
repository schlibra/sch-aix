package com.sch.mybutton;

import android.view.View;
import android.widget.TextClock;
import android.widget.Toast;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;
import com.sun.org.apache.xpath.internal.operations.Bool;

@SuppressWarnings("all")
@SimpleObject(external = true)
@DesignerComponent(category = ComponentCategory.EXTENSION,description = "Button manager",nonVisible = true,version = 1)
public class ButtonManager extends AndroidNonvisibleComponent implements View.OnClickListener,View.OnLongClickListener {
    private ComponentContainer container;
    private MyButton[] list = {};
    private MyButton SelectedComponent;
    private OnMyButtonClickListener listener;
    public ButtonManager(ComponentContainer container){
        super(container.$form());
        this.container = container;
    }
//    public ButtonManager(){//让按钮直接调用这个工具类
//        super(null);
//    }
    @SimpleFunction
    public MyButton CreateButton(){
        return createButton(this.container);
    }
    @SimpleFunction
    public MyButton CreateButtonToLayout(HVArrangement arrangement){
        return createButton((ComponentContainer) arrangement);
    }
    private MyButton createButton(ComponentContainer container){
        MyButton mb;
        mb = new MyButton(container);
        mb.getView().setOnClickListener(this);
        mb.getView().setOnLongClickListener(this);
        addToList(mb);
        return mb;
    }
    private void addToList(MyButton mb){
        MyButton[] temp = new MyButton[list.length+1];
        for(int i = 0;i < list.length;i++){
            temp[i] = list[i];
        }
        temp[list.length] = mb;
        list = temp;
    }
    //事件部分
    @SimpleEvent
    public void Click(MyButton button){
        EventDispatcher.dispatchEvent(this,"Click",button);
    }
    public void onClick(View view){
        for(int i = 0;i < list.length;i++){
            if(list[i].getView().equals(view)){
                Click(list[i]);
                break;
            }
        }
    }
    @SimpleEvent
    public void LongClick(MyButton button){
        EventDispatcher.dispatchEvent(this,"LongClick",button);
    }
    public boolean onLongClick(View view){
        for(int i = 0;i < list.length;i++){
            if(list[i].getView().equals(view)){
                LongClick(list[i]);
                break;
            }
        }
        return true;
    }
    //过程部分
    @SimpleFunction
    public void BackupBackgroundColor(){
            SelectedComponent.DefaultBackgroundColor(SelectedComponent.BackgroundColor());
    }
    public void RestoreBackgroundColor(){
        SelectedComponent.BackgroundColor(SelectedComponent.DefaultColor());
    }
    //属性部分
    @SimpleFunction
    public void SelectComponent(MyButton component){
        this.SelectedComponent = component;
    }
    @SimpleProperty
    public void Text(String text){
        setProperty("Text",text);
    }
    @SimpleProperty
    public String Text(){
        return (String)getProperty("Text");
    }
    @SimpleProperty
    public void Width(int width){
        setProperty("Width",width);
    }
    @SimpleProperty
    public int Width(){
        return (Integer)getProperty("Width");
    }
    @SimpleProperty
    public void Height(int height){
        setProperty("Height",height);
    }
    @SimpleProperty
    public int Height(){
        return (Integer)getProperty("Height");
    }
    @SimpleProperty
    public void TextColor(int color){
        setProperty("TextColor",color);
    }
    @SimpleProperty
    public int TextColor(){
        return (Integer)getProperty("TextColor");
    }
    @SimpleProperty
    public void BackgroundColor(int color){
        setProperty("BackgroundColor",color);
    }
    @SimpleProperty
    public int BackgroundColor(){
        return (Integer)getProperty("BackgroundColor");
    }
    @SimpleProperty
    public void ShowFeedback(boolean feedback){
        setProperty("ShowFeedback",feedback);
    }
    @SimpleProperty
    public boolean ShowFeedback(){
        return (Boolean)getProperty("ShowFeedback");
    }
    @SimpleProperty
    public void Visible(boolean visible){
        setProperty("Visible",visible);
    }
    @SimpleProperty
    public boolean Visible(){
        return (Boolean)getProperty("Visible");
    }
    @SimpleProperty
    public void Shape(int value){
        setProperty("Shape",value);
    }
    @SimpleProperty
    public int Shape(){
        return (Integer)getProperty("Shape");
    }
    @SimpleProperty
    public void DefaultBackgroundColor(int value){
        setProperty("DefaultBackgroundColor",value);
    }
    @SimpleProperty
    public int DefaultBackgroundColor(){
        return (Integer)getProperty("DefaultBackgroundColor");
    }
    @SimpleProperty
    public void Editable(boolean value){
        setProperty("Editable",value);
    }
    @SimpleProperty
    public boolean Editable(){
        return (Boolean)getProperty("Editable");
    }
    @SimpleProperty
    public void FontBold(boolean value){
        setProperty("FontBold",value);
    }
    @SimpleProperty
    public boolean FontBold(){
        return (Boolean)getProperty("FontBold");
    }
    public void FontItalic(boolean value){
        setProperty("FontItalic",value);
    }
    public boolean FontItalic(){
        return (Boolean)getProperty("FontItalic");
    }
    private void setProperty(String property,Object value){
        try {
            switch (property) {
                case "Text":
                    SelectedComponent.Text((String) value);
                    break;
                case "Width":
                    SelectedComponent.Width((Integer) value);
                    break;
                case "Height":
                    SelectedComponent.Height((Integer) value);
                    break;
                case "TextColor":
                    SelectedComponent.TextColor((Integer) value);
                    break;
                case "BackgroundColor":
                    SelectedComponent.BackgroundColor((Integer) value);
                    break;
                case "ShowFeedback":
                    SelectedComponent.ShowFeedback((Boolean)value);
                    break;
                case "Visible":
                    SelectedComponent.Visible((Boolean)value);
                    break;
                case "Shape":
                    SelectedComponent.Shape((Integer) value);
                    break;
                case "DefaultBackgroundColor":
                    SelectedComponent.DefaultBackgroundColor((Integer) value);
                    break;
                case "Editable":
                    SelectedComponent.Editable((Boolean)value);
                    break;
                case "FontBold":
                    SelectedComponent.FontBold((Boolean)value);
                    break;
                case "FontItalic":
                    SelectedComponent.FontItalic((Boolean)value);
                    break;
                default:
                    Toast.makeText(this.container.$context(), "未知属性，无法设置属性", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
//            Toast.makeText(this.container.$context(),"没有选择组件，无法设置属性",Toast.LENGTH_SHORT).show();
            Toast.makeText(this.container.$context(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private Object getProperty(String property){
        Object result = null;
        try{
            switch (property){
                case "Text":
                    result = SelectedComponent.Text();
                    break;
                case "Width":
                    result = SelectedComponent.Width();
                    break;
                case "Height":
                    result = SelectedComponent.Height();
                    break;
                case "TextColor":
                    result = SelectedComponent.TextColor();
                    break;
                case "BackgroundColor":
                    result = SelectedComponent.BackgroundColor();
                    break;
                case "ShowFeedback":
                    result = SelectedComponent.ShowFeedback();
                    break;
                case "Visible":
                    result = SelectedComponent.Visible();
                    break;
                case "Shape":
                    result = SelectedComponent.Shape();
                    break;
                case "DefaultBackgroundColor":
                    result = SelectedComponent.DefaultColor();
                    break;
                case "Editable":
                    result = SelectedComponent.Editable();
                    break;
                case "FontBold":
                    result = SelectedComponent.FontBold();
                    break;
                case "FontItalic":
                    result = SelectedComponent.FontItalic();
                    break;
                default:
                    Toast.makeText(this.container.$context(),"未知属性，无法读取属性",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
//            Toast.makeText(this.container.$context(),"没有选择组件，无法读取属性",Toast.LENGTH_SHORT).show();
            Toast.makeText(this.container.$context(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return result;
    }
    public void setOnMyButtonClickListener(OnMyButtonClickListener listener){
        this.listener = listener;
    }
    @SimpleFunction
    public static int Default(){
        return 0;
    }
    @SimpleFunction
    public static int Rounded(){
        return 1;
    }
    @SimpleFunction
    public static int Rectangular(){
        return 2;
    }
    @SimpleFunction
    public static int Oval(){
        return 3;
    }
}
