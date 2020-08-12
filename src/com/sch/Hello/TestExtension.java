package com.sch.Hello;

import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;

public class TestExtension extends AndroidNonvisibleComponent implements TestInterface {
    public TestExtension(ComponentContainer container){
        super(container.$form());
    }
}
