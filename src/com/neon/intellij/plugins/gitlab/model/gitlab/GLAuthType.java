package com.neon.intellij.plugins.gitlab.model.gitlab;

public enum GLAuthType implements java.io.Serializable {

    GENERAL("General", "general"),
    LDAP("LDAP", "ldap");

    public final String name;
    public final String value;

    GLAuthType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return name;
    }

}
