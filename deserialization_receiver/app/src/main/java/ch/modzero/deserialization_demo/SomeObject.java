package ch.modzero.deserialization_demo;

import java.io.Serializable;

/**
 * Created by work on 15.06.17.
 */

class SomeObject implements Serializable {
    private String name;
    public SomeObject (final String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
