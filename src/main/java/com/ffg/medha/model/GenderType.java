package com.ffg.medha.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "GenderType")
@XmlEnum
public enum GenderType {

    @XmlEnumValue("Female")
    FEMALE("Female"),
    @XmlEnumValue("Male")
    MALE("Male"),
    @XmlEnumValue("Other")
    OTHER("Other");
    private final String value;

    GenderType(String v){ value = v; }

    public static GenderType fromValue(String v){
        for(GenderType c : GenderType.values()){
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
