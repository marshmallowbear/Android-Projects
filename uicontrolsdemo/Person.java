package com.iss.uicontrolsdemo;

import java.util.HashMap;

public class Person extends HashMap<String, String> {
    public Person(String name, String nickname) {
        put("name", name);
        put("nickname", nickname);
    }
}
