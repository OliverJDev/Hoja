package me.tabbin.commands.parameters;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Parameter<T> {

    private PTypeI<T> type;
    private String description;
    private String name;
    private T defaultValue;
    private boolean concat = false;

    public Parameter(PTypeI<T> type){
        setType(type);
    }

    public Parameter(T defaultValue, PTypeI<T> type, String name) {
        setDefaultValue(defaultValue);
        setType(type);
        setName(name);
    }


}
