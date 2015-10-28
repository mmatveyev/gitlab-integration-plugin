package com.neon.intellij.plugins.gitlab.view.configurable;

import com.intellij.openapi.ui.ComboBox;
import com.neon.intellij.plugins.gitlab.model.EditableView;
import com.neon.intellij.plugins.gitlab.model.gitlab.GLAuthType;
import com.neon.intellij.plugins.gitlab.model.gitlab.GLAuthTypeModel;
import com.neon.intellij.plugins.gitlab.model.intellij.ConfigurableState;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

public class SettingsView extends JPanel implements EditableView<ConfigurableState, ConfigurableState > {

    private final JLabel labelHost = new JLabel( "GitLab Host" );
    private final JTextField textHost = new JTextField();

    private final JLabel labelAuthType = new JLabel( "Auth type" );
    private final ComboBox selectAuthType = new ComboBox();

    private final JLabel labelAPI = new JLabel( "GitLab API Key" );
    private final JTextField textAPI = new JTextField();

    private final JLabel labelUsername = new JLabel( "GitLab Username" );
    private final JTextField textUsername = new JTextField();

    private final JLabel labelPassword = new JLabel( "GitLab password" );
    private final JPasswordField textPassword = new JPasswordField();

    private final JCheckBox checkIgnoreCertificateErrors = new JCheckBox( "Ignore Certificate Errors", true );

    public SettingsView( ) {
        setupLayout();
    }

    private void setupLayout() {

        this.setLayout( new TableLayout(
                new double[] { TableLayout.FILL },
                new double[] {
                    TableLayout.MINIMUM, TableLayout.MINIMUM, TableLayout.MINIMUM, TableLayout.MINIMUM, TableLayout.MINIMUM,
                        TableLayout.MINIMUM, TableLayout.MINIMUM, TableLayout.MINIMUM, TableLayout.MINIMUM, TableLayout.MINIMUM
                }
        ) );
        Vector<GLAuthType> authType = new Vector<>(2);
        authType.addElement(GLAuthType.LDAP);
        authType.addElement(GLAuthType.GENERAL);
        selectAuthType.setModel(new DefaultComboBoxModel<>(authType));
        selectAuthType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                SettingsView window = SettingsView.this;
               Object item = e.getItem();
                if (item instanceof GLAuthType) {
                    if (item == GLAuthType.LDAP) {
                        window.remove(labelAPI);
                        window.remove(textAPI);
                        addLDAPFields();
                    } else {
                        window.remove(labelUsername);
                        window.remove(textUsername);
                        window.remove(labelPassword);
                        window.remove(textPassword);
                        addApi();
                    }
                }
                window.revalidate();
                window.repaint();
            }
        });
        this.addCommonFields();
    }

    @Override
    public void fill( ConfigurableState state ) {
        if (state == null) {
            checkIgnoreCertificateErrors.setSelected(true);
            return;
        }
        textHost.setText(state.getHost());
        textAPI.setText(state.getToken());
        textUsername.setText(state.getUsername());
        textPassword.setText(state.getPassword());
        selectAuthType.setSelectedItem(state.getAuthType());
        if (state.getAuthType() == GLAuthType.GENERAL) {
            this.addHost();
        } else {
            this.addLDAPFields();
        }
        checkIgnoreCertificateErrors.setSelected(state.getIgnoreCertificateErrors() );
        this.addApi();
    }

    @Override
    public ConfigurableState save() {
        ConfigurableState state = new ConfigurableState();
        state.setAuthType((GLAuthType) selectAuthType.getSelectedItem());
        state.setHost(textHost.getText());
        state.setIgnoreCertificateErrors(checkIgnoreCertificateErrors.isSelected());
        state.setUsername(textUsername.getText());
        state.setPassword(new String(textPassword.getPassword()));
        return state;
    }

    public void addCommonFields() {
        this.addHost();
        this.addAuthType();
        this.add( checkIgnoreCertificateErrors, new TableLayoutConstraints( 0, 10, 0, 10, TableLayout.FULL, TableLayout.FULL ) );
    }

    public void addLDAPFields() {
        this.addUsername();
        this.addPassword();
    }

    public void addHost() {
        this.add( labelHost, new TableLayoutConstraints( 0, 0, 0, 0, TableLayout.FULL, TableLayout.FULL ) );
        this.add( textHost, new TableLayoutConstraints( 0, 1, 0, 1, TableLayout.FULL, TableLayout.FULL ) );
    }

    public void addAuthType() {
        this.add( labelAuthType, new TableLayoutConstraints( 0, 2, 0, 2, TableLayout.FULL, TableLayout.FULL ) );
        this.add(selectAuthType, new TableLayoutConstraints( 0, 3, 0, 3, TableLayout.FULL, TableLayout.FULL ) );
    }

    public void addApi() {
        this.add( labelAPI, new TableLayoutConstraints( 0, 4, 0, 4, TableLayout.FULL, TableLayout.FULL ) );
        this.add( textAPI, new TableLayoutConstraints( 0, 5, 0, 5, TableLayout.FULL, TableLayout.FULL ) );
    }

    public void addUsername() {
        this.add( labelUsername, new TableLayoutConstraints( 0, 6, 0, 6, TableLayout.FULL, TableLayout.FULL ) );
        this.add( textUsername, new TableLayoutConstraints( 0, 7, 0, 7, TableLayout.FULL, TableLayout.FULL ) );
    }

    public void addPassword() {
        this.add( labelPassword, new TableLayoutConstraints( 0, 8, 0, 8, TableLayout.FULL, TableLayout.FULL ) );
        this.add( textPassword, new TableLayoutConstraints( 0, 9, 0, 9, TableLayout.FULL, TableLayout.FULL ) );
    }

}
