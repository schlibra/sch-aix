package com.sch.sudoku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.InputType;
import android.widget.EditText;
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

import java.util.*;

@SimpleObject(external = true)
@DesignerComponent(category = ComponentCategory.EXTENSION,nonVisible = true,version = 1,iconName = "images/extension.png")
@SuppressWarnings("all")
public class Sudoku extends AndroidNonvisibleComponent {
    public static boolean already_init_sudoku = false;
    private ComponentContainer container;
    private ComponentTable buttonTable;
    private ComponentTable buttonTable1;
    private ComponentTable buttonTable2;
    private ComponentTable cbTable;
    private CreateComponent componentCreator;
    private ButtonManager buttonManager;
    private ButtonManager buttonManager2;
    private ButtonManager buttonManager3;
    private MyButton nullMB;
    private MyButton chooseButton;
    private MyButton restartButton;
    private int[][] timu;
    public Sudoku(ComponentContainer container){
        super(container.$form());
        this.container = container;
    }
    @SimpleFunction(description = "init sudoku game")
    public void init(){
        ButtonManager bm1 = new ButtonManager(container);
        ButtonManager bm2 = new ButtonManager(container);
        Create(bm1, bm2);
    }
    // @SimpleFunction(description = "create sudoku game,bm is buttonManager bm2 is ControlPanel's buttonManager,you should create that component before you use this function")
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
        buttonManager3 = new ButtonManager(container);
        buttonManager3.setOnMyButtonClickListener(new OnMyButtonClickListener() {
            @Override
            public void OnMyButtonClick(MyButton button) {
                if(button.equals(restartButton)){
                    restart();
                    restoreButtonBackgroundColor();
                }
            }
        });
        restartButton = buttonManager3.CreateButton();
        buttonManager3.SelectComponent(restartButton);
        buttonManager3.Width(container.Width());
        buttonManager3.Text("重新开始");
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
        inputLevelDialog();
        // 创建操作区
//        int freeHeight = container.Height() - container.Width();// 计算剩余高度
        int controlHeight = container.Width() / 5 * 2;// 计算操作区所需的高度
//        int centerHeight = container.Height() - container.Width() - controlHeight - getVirtualBarHeight() / 5 * 3;// 题目区和操作区之间的占位高度
//        Toast.makeText(container.$context(),"VirtualBarHeight："+String.valueOf(getVirtualBarHeight()),Toast.LENGTH_SHORT).show();
        VerticalArrangement toolLayout = new VerticalArrangement(container);// 占位布局(工具布局)
//        toolLayout.Width(container.Width());
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
    }
    private void load(int level){
        timu = new SudokuCreater().create(level).getArr();
        setGame();
    }
    private void restart(){
        setGame();
    }
    private void setGame(){
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
                    buttonManager.TextColor(Color.BLACK);
                    buttonManager.Editable(false);
                    buttonManager.FontBold(true);
                }
            }
        }
    }
    // @SimpleFunction(description = "let this function control the button's event")
    public void EventR(Component mb){//上方按钮事件
//        Toast.makeText(container.$context(),String.valueOf((MyButton)mb)+"，"+String.valueOf(chooseButton),Toast.LENGTH_SHORT).show();
        restoreButtonBackgroundColor();
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
        isErrorAll();
    }
    // @SimpleFunction(description = "let this function control ControlButton's event")
    public void EventR2(Component mb){//下方按钮事件
        restoreButtonBackgroundColor();

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
        buttonManager.SelectComponent(chooseButton);
        buttonManager.BackgroundColor(Color.YELLOW);
        if(!isErrorAll() && isFinished()){
            AlertDialog.Builder builder = new AlertDialog.Builder(container.$context());
            builder.setTitle("提示");
            builder.setMessage("恭喜，你成功了！");
            builder.setPositiveButton("结束", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    container.$form().finishActivity();
                }
            });
            builder.setNegativeButton("再来一局", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    inputLevelDialog();
                }
            });
            builder.create().show();
        }
    }
    private void buttonClick(MyButton button){//测试用的，现在应该用不到了吧
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
    private boolean isError9(ComponentTable table){//检查9个宫内是否有错误，有错误时返回true
        boolean result = false;
        Component[] tempTable1 = new Component[9];
        int index = 0;
        for(int i = 1;i <= table.GetLength1();i++){
            for (int j = 1; j <= table.GetLength2(); j++) {
                tempTable1[index++] = table.get(i,j);
            }
        }
        Loop:for (int i = 0; i < index; i++) {
            for (int j = 0; j < index; j++) {
                if(i != j){
                    buttonManager.SelectComponent((MyButton)tempTable1[i]);
                    String a = buttonManager.Text();
                    buttonManager.SelectComponent((MyButton)tempTable1[j]);
                    String b = buttonManager.Text();
                    if(!a.isEmpty() && !b.isEmpty() && a.equals(b)) {
                        result = true;
                        markError(tempTable1[i], tempTable1[j]);
                    }
                }
            }
        }
        return result;
    }
    private boolean isErrorAll(){//检查所有格是否有错误，有错误时返回true
        boolean result = false;
        ComponentTable p1 = buttonTable.divideTable_3_3();
        Component[] p2 = new Component[9];
        int index = 0;
        for (int i = 1;i <= p1.GetLength1();i++){
            for(int j = 1;j <= p1.GetLength2();j++){
                p2[index++] = p1.get(i,j);
            }
        }
        for(int i = 0;i < index;i++){
            if(isError9((ComponentTable)p2[i])){
                result = true;//这里不能写return，因为要检查所有错误
            }
        }

        for (int i = 1;i <= 9;i++){
            for(int j = 1;j <= 9;j++){
                for (int k = 1;k <= 9;k++){
                    if (j != k){
                        buttonManager.SelectComponent((MyButton)buttonTable.get(i,j));
                        String a = buttonManager.Text();
                        buttonManager.SelectComponent((MyButton)buttonTable.get(i,k));
                        String b = buttonManager.Text();
                        if (!a.isEmpty() && !b.isEmpty() && a.equals(b)){
                            markError(buttonTable.get(i,j),buttonTable.get(i,k));
                            result = true;
                        }
                        buttonManager.SelectComponent((MyButton)buttonTable.get(j,i));
                        String c = buttonManager.Text();
                        buttonManager.SelectComponent((MyButton)buttonTable.get(k,i));
                        String d = buttonManager.Text();
                        if (!c.isEmpty() && !d.isEmpty() && c.equals(d)){// [j,i] [k,i]
                            markError(buttonTable.get(j,i),buttonTable.get(k,i));
                            result = true;
                        }
                    }
                }
            }
        }
        return result;
    }
    private void markError(Component a,Component b){//标记错误的格子为红色
        buttonManager.SelectComponent((MyButton)a);
        buttonManager.BackgroundColor(Color.RED);
        buttonManager.SelectComponent((MyButton)b);
        buttonManager.BackgroundColor(Color.RED);
    }
    private boolean isFinished(){//判断是否全部填完，全部填完时返回true
        for (int i = 1;i <= 9;i++){
            for (int j = 1;j <= 9;j++){
                MyButton mb = (MyButton)buttonTable.get(i,j);
                buttonManager.SelectComponent(mb);
                if(buttonManager.Text().isEmpty()){
                    return false;//这里不需要全部判断完，检查到一个空格时就可以返回false并结束后续循环
                }
            }
        }
        return true;
    }
    private void restoreButtonBackgroundColor(){
        for(int i = 1;i <= buttonTable.GetLength1();i++){
            for(int j = 1;j <= buttonTable.GetLength2();j++){
                MyButton mb = (MyButton)buttonTable.get(i,j);
                buttonManager.SelectComponent(mb);
                buttonManager.RestoreBackgroundColor();
            }
        }
    }
    private void inputLevelDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(container.$context()).create();
        alertDialog.setTitle("提示");
        alertDialog.setMessage("输入游戏难度(10~80)");
        final EditText input = new EditText(container.$context());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        alertDialog.setView(input);
        alertDialog.setCancelable(false);
        alertDialog.setButton(-1, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int a = 0;
                a = Integer.valueOf(input.getText().toString());
                load(a);
            }
        });
        alertDialog.show();
    }
}
