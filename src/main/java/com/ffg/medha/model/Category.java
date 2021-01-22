package com.ffg.medha.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Category")
@XmlEnum
public enum Category {
    @XmlEnumValue("OBC")
    OBC("OBC"),
    @XmlEnumValue("SC")
    SC("SC"),
    @XmlEnumValue("ST")
    ST("ST"),
    @XmlEnumValue("General")
    GENERAL("General");

    private final String value;

    Category(String v){ value = v; }

    public static Category fromValue(String v){
        for(Category c : Category.values()){
            if(c.value.equals(v)){
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String value(){
        return value;
    }
}
