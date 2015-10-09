package com.neon.intellij.plugins.gitlab.view.configurable;

import com.intellij.openapi.ui.ComboBox;
import com.neon.intellij.plugins.gitlab.model.EditableView;
import com.neon.intellij.plugins.gitlab.model.intellij.ConfigurableState;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;

import javax.swing.*;

public class SettingsView extends JPanel implements EditableView<ConfigurableState, Object[] > {

    private final JLabel labelHost = new JLabel( "GitLab Host" );
    private final JTextField textHost = new JTextField();

    private final JLabel labelAuthType = new JLabel( "Auth type" );
    private final JComboBox textAuthType = new ComboBox();

    private final JLabel labelAPI = new JLabel( "GitLab API Key" );
    private final JTextField textAPI = new JTextField();

    private final JLabel labelUsername = new JLabel( "GitLab Username (for LDAP only)" );
    private final JTextField textUsername = new JTextField();

    private final JLabel labelPassword = new JLabel( "GitLab password (for LDAP only)" );
    private final JTextField textPassword = new JTextField();

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
        this.add( labelHost, new TableLayoutConstraints( 0, 0, 0, 0, TableLayout.FULL, TableLayout.FULL ) );
        this.add( textHost, new TableLayoutConstraints( 0, 1, 0, 1, TableLayout.FULL, TableLayout.FULL ) );
        this.add( labelAPI, new TableLayoutConstraints( 0, 2, 0, 2, TableLayout.FULL, TableLayout.FULL ) );
        this.add( textAPI, new TableLayoutConstraints( 0, 3, 0, 3, TableLayout.FULL, TableLayout.FULL ) );
        this.add( labelUsername, new TableLayoutConstraints( 0, 4, 0, 4, TableLayout.FULL, TableLayout.FULL ) );
        this.add( textUsername, new TableLayoutConstraints( 0, 5, 0, 5, TableLayout.FULL, TableLayout.FULL ) );
        this.add( labelPassword, new TableLayoutConstraints( 0, 6, 0, 6, TableLayout.FULL, TableLayout.FULL ) );
        this.add( textPassword, new TableLayoutConstraints( 0, 7, 0, 7, TableLayout.FULL, TableLayout.FULL ) );
        this.add( checkIgnoreCertificateErrors, new TableLayoutConstraints( 0, 8, 0, 8, TableLayout.FULL, TableLayout.FULL ) );
    }

    @Override
    public void fill( ConfigurableState state ) {
        textHost.setText( state == null ? "" : state.getHost() );
        textAPI.setText( state == null ? "" : state.getToken() );
        checkIgnoreCertificateErrors.setSelected( state == null ? true : state.getIgnoreCertificateErrors() );
    }

    @Override
    public Object[] save() {
        return new Object[] { textHost.getText(), textAPI.getText(), checkIgnoreCertificateErrors.isSelected() };
    }

}
