package com.neon.intellij.plugins.gitlab.model.intellij;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Transient;
import com.neon.intellij.plugins.gitlab.model.gitlab.GLAuthType;
import org.jetbrains.annotations.Nullable;

@State(
        name = "ConfigurableState",
        storages = {
                @Storage( id = "default", file = "$APP_CONFIG$/gitlab-integration-settings.xml" )
        }
)
public class ConfigurableState implements PersistentStateComponent< ConfigurableState > {

    @Transient
    private static final Logger LOG = Logger.getInstance("gitlab");

    public String host;

    public String token;

    public String username;

    public String password;

    public GLAuthType authType;

    public Boolean ignoreCertificateErrors = true;


    public ConfigurableState() {

    }


    public static ConfigurableState getInstance() {
        return ServiceManager.getService( ConfigurableState.class );
    }

    @Nullable
    @Override
    public ConfigurableState getState() {
        return this;
    }

    @Override
    public void loadState( ConfigurableState configurableState ) {
        XmlSerializerUtil.copyBean( configurableState, this );
    }

    public String getHost() {
        return host == null ? "" : host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getToken() {
        return token == null ? "" : token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username == null ? "" : username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GLAuthType getAuthType() {
        return authType == null ? GLAuthType.GENERAL : authType;
    }

    public void setAuthType(GLAuthType authType) {
        this.authType = authType;
    }


    public Boolean getIgnoreCertificateErrors() {
        return ignoreCertificateErrors;
    }

    public void setIgnoreCertificateErrors(Boolean ignoreCertificateErrors) {
        this.ignoreCertificateErrors = ignoreCertificateErrors;
    }
}
