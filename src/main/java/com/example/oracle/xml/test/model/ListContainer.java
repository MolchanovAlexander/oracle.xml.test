package com.example.oracle.xml.test.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlValue;
import lombok.Setter;

import java.util.List;
@Setter
@XmlRootElement(name = "list")
public class ListContainer {

    private List<StringElement> strings;

    @XmlElement(name = "string")
    public List<StringElement> getStrings() {
        return strings;
    }
    @Setter
    public static class StringElement {
        private String name;
        private String value;

        @XmlAttribute
        public String getName() {
            return name;
        }

        @XmlValue
        public String getValue() {
            return value;
        }
    }
}
