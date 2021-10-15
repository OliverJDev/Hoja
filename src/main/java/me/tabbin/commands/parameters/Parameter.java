package me.tabbin.commands.parameters;

import lombok.Getter;
import lombok.Setter;

public abstract class Parameter<T> {

    @Getter @Setter
    private PTypeI<T> type;
    private String description;
    private String name;

    public Parameter(PTypeI<T> type){
        setType(type);
    }

}
