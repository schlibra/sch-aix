package com.sch.sudoku;

import android.graphics.Color;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;
import com.sch.ComponentTable.ComponentTable;
import com.sch.CreateComponent.CreateComponent;
import com.sch.mybutton.ButtonManager;
import com.sch.mybutton.MyButton;
import com.sch.mybutton.OnMyButtonClickListener;

import java.util.Arrays;

@SimpleObject(external = true)
@DesignerComponent(category = ComponentCategory.EXTENSION,nonVisible = true,version = 1)
@SuppressWarnings("all")
public class Sudoku extends AndroidNonvisibleComponent {
    private ComponentContainer container;
    private ComponentTable buttonTable;
    private ComponentTable buttonTable1;
    private ComponentTable buttonTable2;
    private ComponentTable cbTable;
    private CreateComponent componentCreator;
    private ButtonManager buttonManager;
    private ButtonManager buttonManager2;
    private MyButton nullMB;
    private MyButton chooseButton;
    public Sudoku(ComponentContainer container){
        super(container.$form());
        this.container = container;

    }
    @SimpleFunction(description = "create sudoku game,bm is buttonManager bm2 is ControlPanel's buttonManager,you should create that component before you use this function")
    public void Create(Component bm,Component bm2){
        buttonManager = (ButtonManager) bm;
        buttonManager.setOnMyButtonClickListener(new OnMyButtonClickListener() {
            @Override
            public void OnMyButtonClick(MyButton button) {
                EventR(button);
            }
        });
        buttonManager2 = (ButtonManager) bm2;
        buttonManager2.setOnMyButtonClickListener(new OnMyButtonClickListener() {
            @Override
            public void OnMyButtonClick(MyButton button) {
                EventR2(button);
            }
        });
        buttonTable = new ComponentTable(this.container);
        buttonTable1 = new ComponentTable(this.container);
        buttonTable2 = new ComponentTable(this.container);
        cbTable = new ComponentTable(this.container);
        componentCreator = new CreateComponent(this.container);
        buttonTable.createComponentTable(9,9);
        nullMB = new MyButton(this.container);
        nullMB.Visible(false);
        chooseButton = nullMB;
        VerticalArrangement verticalArrangement = componentCreator.createVerticalArrangement();
        verticalArrangement.Width(this.container.Width());
        verticalArrangement.Height(this.container.Width());
        for(int i = 1;i <= 9;i++){
            HorizontalArrangement tempLayout = componentCreator.createHorizontalArrangementToLayout(verticalArrangement);
            tempLayout.Width(this.container.Width());
            tempLayout.Height(this.container.Width()/9);
            for(int j = 1;j <= 9;j++){
                MyButton tempButton = buttonManager.CreateButtonToLayout(tempLayout);
                buttonManager.SelectComponent(tempButton);
                buttonManager.Height(this.container.Width()/9);
                buttonManager.Width(this.container.Width()/9);
                buttonManager.BackgroundColor(Color.argb(255,255,255,255));
                buttonManager.Text("");
                buttonManager.TextColor(Component.COLOR_BLACK);
                buttonManager.ShowFeedback(false);
                buttonManager.Shape(ButtonManager.Rectangular());
                buttonManager.FontBold(true);
                buttonTable.put(i,j,tempButton);
            }
        }
        ComponentTable dividedComponentTable = buttonTable.divideTable_3_3();
        buttonTable1.copy(dividedComponentTable);
        ComponentTable[] tempComponentTable = new ComponentTable[9];
        for(int i = 0;i < 9;i++){
            tempComponentTable[i] = (ComponentTable)buttonTable1.get(i/3+1,i%3+1);
        }
        for(int i = 0;i < 9;i+=2){
            buttonTable2.copy(tempComponentTable[i]);
            for(int j = 1;j <= buttonTable2.GetLength1();j++){
                for(int k = 1;k <= buttonTable2.GetLength2();k++){
                    buttonManager.SelectComponent((MyButton)buttonTable2.get(j,k));
                    buttonManager.BackgroundColor(Component.COLOR_GRAY);
                    buttonManager.BackupBackgroundColor();
                }
            }
        }
        for(int i = 1;i < 8;i+=2){
            buttonTable2.copy(tempComponentTable[i]);
            for(int j = 1;j <= buttonTable2.GetLength1();j++){
                for(int k = 1;k <= buttonTable2.GetLength2();k++){
                    buttonManager.SelectComponent((MyButton)buttonTable2.get(j,k));
                    buttonManager.BackgroundColor(Component.COLOR_LTGRAY);
                    buttonManager.BackupBackgroundColor();
                }
            }
        }
        int[][] timu = new SudokuCreater().getArr();
        for(int i = 0;i<9;i++){
            for(int j=0;j<9;j++){
                MyButton a = (MyButton)buttonTable.get(i+1,j+1);
                buttonManager.SelectComponent(a);
                if(timu[i][j] == 0){//题目中可以被玩家改变的部分
                    buttonManager.Text("");
                    buttonManager.TextColor(Color.DKGRAY);
                    buttonManager.Editable(true);
                    buttonManager.FontBold(false);
                }else{
                    buttonManager.Text(String.valueOf(timu[i][j]));
                }
            }
        }
        // 创建操作区
        int freeHeight = container.Height() - container.Width();// 计算剩余高度
        int controlHeight = container.Width() / 5 * 2;// 计算操作区所需的高度
//        int centerHeight = container.Height() - container.Width() - controlHeight - getVirtualBarHeight() / 5 * 3;// 题目区和操作区之间的占位高度
//        Toast.makeText(container.$context(),"VirtualBarHeight："+String.valueOf(getVirtualBarHeight()),Toast.LENGTH_SHORT).show();
        VerticalArrangement toolLayout = new VerticalArrangement(container);// 占位布局(工具布局)
        toolLayout.Width(container.Width());
        toolLayout.Height(-2);
//        toolLayout.BackgroundColor(Color.GRAY);
        VerticalArrangement controlPanel = new VerticalArrangement(container);
        controlPanel.Width(container.Width());
        controlPanel.Height(controlHeight);
//        Button Test = new Button(controlPanel);
//        Test.Width(container.Width());
//        Test.Height(controlHeight);
//        Test.Text("Control Panel");
        HorizontalArrangement controlPanelLine1 = new HorizontalArrangement(controlPanel);
        HorizontalArrangement controlPanelLine2 = new HorizontalArrangement(controlPanel);
        HorizontalArrangement[] havL = {controlPanelLine1,controlPanelLine2};
        controlPanelLine1.Width(container.Width());
        controlPanelLine2.Width(container.Width());
        controlPanelLine1.Height(container.Width() / 5);
        controlPanelLine2.Height(container.Width() / 5);
        cbTable.createComponentTable(2,5);
        for(int i = 1;i<=cbTable.GetLength1();i++){
            for(int j = 1;j<=cbTable.GetLength2();j++){
                MyButton tempMB = buttonManager2.CreateButtonToLayout(havL[i-1]);
                int Text = j + (i + 1) % 2 * 5;
                buttonManager2.SelectComponent(tempMB);
                buttonManager2.Text(Text==10?"×":String.valueOf(Text));
                buttonManager2.Width(container.Width() / 5);
                buttonManager2.Height(container.Width() / 5);
                buttonManager2.Shape(ButtonManager.Rectangular());
            }
        }
//        Button n1 = new Button(controlPanelLine1);
//        Button n2 = new Button(controlPanelLine1);
//        Button n3 = new Button(controlPanelLine1);
//        Button n4 = new Button(controlPanelLine1);
//        Button n5 = new Button(controlPanelLine1);
//        Button n6 = new Button(controlPanelLine2);
//        Button n7 = new Button(controlPanelLine2);
//        Button n8 = new Button(controlPanelLine2);
//        Button n9 = new Button(controlPanelLine2);
//        Button clean = new Button(controlPanelLine2);
//        n1.Width(container.Width()/5);
//        n1.Height(container.Width()/5);
//        n1.Text("1");
//        n2.Width(container.Width()/5);
//        n2.Height(container.Width()/5);
//        n2.Text("2");
//        n3.Width(container.Width()/5);
//        n3.Height(container.Width()/5);
//        n3.Text("3");
//        n4.Width(container.Width()/5);
//        n4.Height(container.Width()/5);
//        n4.Text("4");
//        n5.Width(container.Width()/5);
//        n5.Height(container.Width()/5);
//        n5.Text("5");
//        n6.Width(container.Width()/5);
//        n6.Height(container.Width()/5);
//        n6.Text("6");
//        n7.Width(container.Width()/5);
//        n7.Height(container.Width()/5);
//        n7.Text("7");
//        n8.Width(container.Width()/5);
//        n8.Height(container.Width()/5);
//        n8.Text("8");
//        n9.Width(container.Width()/5);
//        n9.Height(container.Width()/5);
//        n9.Text("9");
//        clean.Width(container.Width()/5);
//        clean.Height(container.Width()/5);
//        clean.Text("×");
    }
    @SimpleFunction(description = "let this function control the button's event")
    public void EventR(Component mb){
//        Toast.makeText(container.$context(),String.valueOf((MyButton)mb)+"，"+String.valueOf(chooseButton),Toast.LENGTH_SHORT).show();
        if(chooseButton.equals(nullMB)){
            buttonManager.SelectComponent((MyButton)mb);
            buttonManager.BackgroundColor(Color.YELLOW);
            chooseButton = (MyButton)mb;
        }else{
            if(chooseButton.equals(mb)){
                buttonManager.SelectComponent((MyButton)mb);
                buttonManager.RestoreBackgroundColor();
                chooseButton = nullMB;
            }else{
                buttonManager.SelectComponent(chooseButton);
                buttonManager.RestoreBackgroundColor();
                buttonManager.SelectComponent((MyButton)mb);
                buttonManager.BackgroundColor(Color.YELLOW);
                chooseButton = (MyButton)mb;
            }
        }
    }
    @SimpleFunction(description = "let this function control ControlButton's event")
    public void EventR2(Component mb){
        buttonManager2.SelectComponent((MyButton)mb);
        if(chooseButton.equals(nullMB)){
            Toast.makeText(container.$context(),"请选择要操作的格子",Toast.LENGTH_SHORT).show();
        }else{
            buttonManager.SelectComponent(chooseButton);
            if(buttonManager.Editable()){
                String text = buttonManager2.Text().equals("×")?"":buttonManager2.Text();

                buttonManager.Text(text);
            }else{
                Toast.makeText(container.$context(),"当前选择的格子无法进行操作",Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void buttonClick(MyButton button){
        Toast.makeText(this.container.$context(),"你点击的按钮的索引为："+getIndex(button,buttonTable)+"\n按钮的文本为："+button.Text(),Toast.LENGTH_SHORT).show();
    }
    private String getIndex(Component component,ComponentTable componentTable){
        String result = "";
        for(int i = 1;i <= componentTable.GetLength1();i++){
            for(int j = 1;j<=componentTable.GetLength2();j++){
                if(((MyButton)componentTable.get(i,j)).equals(component)){
                    result = Arrays.toString(new int[]{i,j});
                    break;
                }
            }
        }
        return result;
    }
//    private int getVirtualBarHeight(){
//        int vh = 0;
//        WindowManager windowManager = (WindowManager)container.$context().getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
//        DisplayMetrics dm = new DisplayMetrics();
//        try{
//            @SuppressWarnings("rawtypes")
//            Class c = Class.forName("android.view.Display");
//            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
//            method.invoke(display,dm);
//            vh = dm.heightPixels - display.getHeight();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return vh;
//    }
}
