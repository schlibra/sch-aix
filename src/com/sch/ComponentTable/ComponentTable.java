package com.sch.ComponentTable;

import android.content.Context;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.Notifier;

@SimpleObject(external = true)
@DesignerComponent(description = "Component Table", category = ComponentCategory.EXTENSION, version = 1, nonVisible = true)
@SuppressWarnings("all")
public class ComponentTable extends AndroidNonvisibleComponent {
    private ComponentContainer container;
    private Context context;
//    private boolean isDebug = false;
    public Component[][] value;
    public int length1 = 0;
    public int length2 = 0;
    private String thisName = "";

    public ComponentTable(ComponentContainer container) {
        super(container.$form());
        this.container = container;
        this.context = container.$context();
        this.thisName = this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1);
    }

    @SimpleFunction(description = "create a component table")
    public void createComponentTable(int length1, int length2) {
        this.value = new Component[length1][length2];
        this.length1 = length1;
        this.length2 = length2;
    }

    @SimpleFunction(description = "put a component into table")
    public void put(int index1, int index2, Component component) {
        if (index1 >= 1 && index2 >= 1) {
            if (index1 <= this.length1 && index2 <= this.length2) {
                this.value[index1 - 1][index2 - 1] = component;
            } else {
                this.errorDialog("在" + this.thisName + "的put方法中，索引值太大,您输入的索引值是[" + index1 + "," + index2 + "]，但是您的组件表大小是[" + this.length1 + "," + this.length2 + "]");
            }
        } else {
            this.errorDialog("在" + this.thisName + "的put方法中，索引值太小,您输入的索引值是[" + index1 + "," + index2 + "]，这两个值都不能小于1");
        }

    }

    @SimpleFunction(description = "get a component from table")
    public Component get(int index1, int index2) {
        Component result = null;
        if (index1 >= 1 && index2 >= 1) {
            if (index1 <= this.length1 && index2 <= this.length2) {
                result = this.value[index1 - 1][index2 - 1];
            } else {
                this.errorDialog("在" + this.thisName + "的get方法中，索引值太大,您输入的索引值是[" + index1 + "," + index2 + "]，但是您的组件表大小是[" + this.length1 + "," + this.length2 + "]");
            }
        } else {
            this.errorDialog("在" + this.thisName + "的get方法中，索引值太小,您输入的索引值是[" + index1 + "," + index2 + "]，这两个值都不能小于1");
        }

        return result;
    }

    @SimpleFunction(description = "copy table from another ComponentTable")
    public void copy(Component component) {
        ComponentTable componentTable = (ComponentTable)component;
        this.value = componentTable.value;
        this.length1 = componentTable.length1;
        this.length2 = componentTable.length2;
    }

    @SimpleProperty
    public int GetLength1() {
        return this.length1;
    }

    @SimpleProperty
    public int GetLength2() {
        return this.length2;
    }

    @SimpleFunction
    public ComponentTable divideTable_2_2() {
        ComponentTable tempTable = new ComponentTable(this.container);
        tempTable.createComponentTable(2, 2);
        if (this.length1 % 2 == 0 && this.length2 % 2 == 0) {
            ComponentTable table1 = new ComponentTable(this.container);
            ComponentTable table2 = new ComponentTable(this.container);
            ComponentTable table3 = new ComponentTable(this.container);
            ComponentTable table4 = new ComponentTable(this.container);
            table1.createComponentTable(this.length1 / 2, this.length2 / 2);
            table2.createComponentTable(this.length1 / 2, this.length2 / 2);
            table3.createComponentTable(this.length1 / 2, this.length2 / 2);
            table4.createComponentTable(this.length1 / 2, this.length2 / 2);
            for(int i = 0; i < this.length1 / 2; i++) {
                for(int j = 0; j < this.length2 / 2; j++) {
                    table1.value[i][j] = this.value[i][j];
                    table2.value[i][j] = this.value[i][j + this.length2 / 2];
                    table3.value[i][j] = this.value[i + this.length1 / 2][j];
                    table4.value[i][j] = this.value[i + this.length1 / 2][j + this.length2 / 2];
                }
            }
            tempTable.put(1, 1, table1);
            tempTable.put(1, 2, table2);
            tempTable.put(2, 1, table3);
            tempTable.put(2, 2, table4);
        } else {
            this.errorDialog("您的组件表大小为[" + this.length1 + "," + this.length2 + "]，它无法被平均拆分成2×2");
        }

        return tempTable;
    }

    @SimpleFunction
    public ComponentTable divideTable_3_3() {
        ComponentTable tempTable = new ComponentTable(this.container);
        tempTable.createComponentTable(3, 3);
        if (this.length1 % 3 == 0 && this.length2 % 3 == 0) {
            ComponentTable table1 = new ComponentTable(this.container);
            ComponentTable table2 = new ComponentTable(this.container);
            ComponentTable table3 = new ComponentTable(this.container);
            ComponentTable table4 = new ComponentTable(this.container);
            ComponentTable table5 = new ComponentTable(this.container);
            ComponentTable table6 = new ComponentTable(this.container);
            ComponentTable table7 = new ComponentTable(this.container);
            ComponentTable table8 = new ComponentTable(this.container);
            ComponentTable table9 = new ComponentTable(this.container);
            table1.createComponentTable(this.length1 / 3, this.length2 / 3);
            table2.createComponentTable(this.length1 / 3, this.length2 / 3);
            table3.createComponentTable(this.length1 / 3, this.length2 / 3);
            table4.createComponentTable(this.length1 / 3, this.length2 / 3);
            table5.createComponentTable(this.length1 / 3, this.length2 / 3);
            table6.createComponentTable(this.length1 / 3, this.length2 / 3);
            table7.createComponentTable(this.length1 / 3, this.length2 / 3);
            table8.createComponentTable(this.length1 / 3, this.length2 / 3);
            table9.createComponentTable(this.length1 / 3, this.length2 / 3);

            for(int i = 0; i < this.length1 / 3; i++) {
                for(int j = 0; j < this.length2 / 3; j++) {
                    table1.value[i][j] = this.value[i][j];
                    table2.value[i][j] = this.value[i][j + this.length2 / 3];
                    table3.value[i][j] = this.value[i][j + this.length2 / 3 * 2];
                    table4.value[i][j] = this.value[i + this.length1 / 3][j];
                    table5.value[i][j] = this.value[i + this.length1 / 3][j + this.length2 / 3];
                    table6.value[i][j] = this.value[i + this.length1 / 3][j + this.length2 / 3 * 2];
                    table7.value[i][j] = this.value[i + this.length1 / 3 * 2][j];
                    table8.value[i][j] = this.value[i + this.length1 / 3 * 2][j + this.length2 / 3];
                    table9.value[i][j] = this.value[i + this.length1 / 3 * 2][j + this.length2 / 3 * 2];
                }
            }

            tempTable.put(1, 1, table1);
            tempTable.put(1, 2, table2);
            tempTable.put(1, 3, table3);
            tempTable.put(2, 1, table4);
            tempTable.put(2, 2, table5);
            tempTable.put(2, 3, table6);
            tempTable.put(3, 1, table7);
            tempTable.put(3, 2, table8);
            tempTable.put(3, 3, table9);
        } else {
            this.errorDialog("您的组件表大小为[" + this.length1 + "," + this.length2 + "]，它无法被平均拆分成3×3");
        }

        return tempTable;
    }



    private void errorDialog(String var1) {
        (new Notifier(this.container)).ShowMessageDialog(var1, "出错啦！", "知道了");
    }

}
