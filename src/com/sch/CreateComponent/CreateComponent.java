package com.sch.CreateComponent;

import android.content.Context;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;

@SimpleObject(external = true)
@DesignerComponent(description = "create component",category = ComponentCategory.EXTENSION,nonVisible = true,version = 1)
public class CreateComponent extends AndroidNonvisibleComponent {
    private ComponentContainer container;
    private Context context;

    public CreateComponent(ComponentContainer container) {
        super(container.$form());
        this.container = container;
        this.context = container.$context();
    }

    //in Screen
    //User Interface
    @SimpleFunction(description = "create Button to screen")
    public Button createButton() {
        return new Button(this.container);
    }

    @SimpleFunction(description = "create CheckBox to screen")
    public CheckBox createCheckBox() {
        return new CheckBox(this.container);
    }

    @SimpleFunction(description = "create DatePicker to screen")
    public DatePicker createDatePicker() {
        return new DatePicker(this.container);
    }

    @SimpleFunction(description = "create Image to screen")
    public Image createImage() {
        return new Image(this.container);
    }

    @SimpleFunction(description = "create Label to screen")
    public Label createLabel() {
        return new Label(this.container);
    }

    @SimpleFunction(description = "create ListPicker to screen")
    public ListPicker createListPicker() {
        return new ListPicker(this.container);
    }

    @SimpleFunction(description = "create ListView to screen")
    public ListView createListView() {
        return new ListView(this.container);
    }

    @SimpleFunction(description = "create Notifier to screen")
    public Notifier createNotifier() {
        return new Notifier(this.container);
    }

    @SimpleFunction(description = "create PasswordTextBox to screen")
    public PasswordTextBox createPasswordTextBox() {
        return new PasswordTextBox(this.container);
    }

    @SimpleFunction(description = "create Slider to screen")
    public Slider createSlider() {
        return new Slider(this.container);
    }

    @SimpleFunction(description = "create Spinner to screen")
    public Spinner createSpinner() {
        return new Spinner(this.container);
    }

    @SimpleFunction(description = "create TextBox to screen")
    public TextBox createTextBox() {
        return new TextBox(this.container);
    }

    @SimpleFunction(description = "create TimePicker to screen")
    public TimePicker createTimePicker() {
        return new TimePicker(this.container);
    }

    @SimpleFunction(description = "create WebViewer to screen")
    public WebViewer createWebViewer() {
        return new WebViewer(this.container);
    }

    //Layout
    @SimpleFunction(description = "create HorizontalArrangement to screen")
    public HorizontalArrangement createHorizontalArrangement() {
        return new HorizontalArrangement(this.container);
    }

    @SimpleFunction(description = "create HorizontalScrollArrangement to screen")
    public HorizontalScrollArrangement createHorizontalScrollArrangement() {
        return new HorizontalScrollArrangement(this.container);
    }

    @SimpleFunction(description = "create TableArrangement to screen")
    public TableArrangement createTableArrangement() {
        return new TableArrangement(this.container);
    }

    @SimpleFunction(description = "create VerticalArrangement to screen")
    public VerticalArrangement createVerticalArrangement() {
        return new VerticalArrangement(this.container);
    }

    @SimpleFunction(description = "create VerticalScrollArrangement to screen")
    public VerticalScrollArrangement createVerticalScrollArrangement() {
        return new VerticalScrollArrangement(this.container);
    }


    //in Layout
    //User Interface
    @SimpleFunction(description = "create Button to screen")
    public Button createButtonToLayout(HVArrangement arrangement) {
        return new Button(arrangement);
    }

    @SimpleFunction(description = "create CheckBox to screen")
    public CheckBox createCheckBoxToLayout(HVArrangement arrangement){
        return new CheckBox(arrangement);
    }
    @SimpleFunction(description = "create DatePicker to screen")
    public DatePicker createDatePickerToLayout(HVArrangement arrangement){
        return new DatePicker(arrangement);
    }
    @SimpleFunction(description = "create Image to screen")
    public Image createImageToLayout(HVArrangement arrangement){
        return new Image(arrangement);
    }
    @SimpleFunction(description = "create Label to screen")
    public Label createLabelToLayout(HVArrangement arrangement){
        return new Label(arrangement);
    }
    @SimpleFunction(description = "create ListPicker to screen")
    public ListPicker createListPickerToLayout(HVArrangement arrangement){
        return new ListPicker(arrangement);
    }
    @SimpleFunction(description = "create ListView to screen")
    public ListView createListViewToLayout(HVArrangement arrangement){
        return new ListView(arrangement);
    }
    @SimpleFunction(description = "create Notifier to screen")
    public Notifier createNotifierToLayout(HVArrangement arrangement){
        return new Notifier(arrangement);
    }
    @SimpleFunction(description = "create PasswordTextBox to screen")
    public PasswordTextBox createPasswordTextBoxToLayout(HVArrangement arrangement){
        return new PasswordTextBox(arrangement);
    }
    @SimpleFunction(description = "create Slider to screen")
    public Slider createSliderToLayout(HVArrangement arrangement){
        return new Slider(arrangement);
    }
    @SimpleFunction(description = "create Spinner to screen")
    public Spinner createSpinnerToLayout(HVArrangement arrangement){
        return new Spinner(arrangement);
    }
    @SimpleFunction(description = "create TextBox to screen")
    public TextBox createTextBoxToLayout(HVArrangement arrangement){
        return new TextBox(arrangement);
    }
    @SimpleFunction(description = "create TimePicker to screen")
    public TimePicker createTimePickerToLayout(HVArrangement arrangement){
        return new TimePicker(arrangement);
    }
    @SimpleFunction(description = "create WebViewer to screen")
    public WebViewer createWebViewerToLayout(HVArrangement arrangement){
        return new WebViewer(arrangement);
    }
    //Layout
    @SimpleFunction(description = "create HorizontalArrangement to screen")
    public HorizontalArrangement createHorizontalArrangementToLayout(HVArrangement arrangement){
        return new HorizontalArrangement(arrangement);
    }
    @SimpleFunction(description = "create HorizontalScrollArrangement to screen")
    public HorizontalScrollArrangement createHorizontalScrollArrangementToLayout(HVArrangement arrangement){
        return new HorizontalScrollArrangement(arrangement);
    }
    @SimpleFunction(description = "create TableArrangement to screen")
    public TableArrangement createTableArrangementToLayout(HVArrangement arrangement){
        return new TableArrangement(arrangement);
    }
    @SimpleFunction(description = "create VerticalArrangement to screen")
    public VerticalArrangement createVerticalArrangementToLayout(HVArrangement arrangement){
        return new VerticalArrangement(arrangement);
    }
    @SimpleFunction(description = "create VerticalScrollArrangement to screen")
    public VerticalScrollArrangement createVerticalScrollArrangementToLayout(HVArrangement arrangement){
        return new VerticalScrollArrangement(arrangement);
    }

}
