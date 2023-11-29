package view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;

public class MFrame extends JPanel {
    private JPanel panel1;
    private JButton importButton;
    private JButton exportButton;
    private JButton filterButton;
    private JButton applyButton;
    private JComboBox weekPreviewComboBox;
    private JButton addButton;
    private JButton editButton;
    private JTable tabela;
    private JButton deleteColumnButton;
    private JButton deleteSelectedRowButton;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton checkAvailabilityButton;
    private JRadioButton secondRadioButton;
    private JRadioButton firstRadioButton;
    private JButton formatImplementationButton;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(0, 0));
        panel1.add(panel2, BorderLayout.NORTH);
        importButton = new JButton();
        importButton.setText("Import");
        panel2.add(importButton, BorderLayout.CENTER);
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, BorderLayout.NORTH);
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, BorderLayout.SOUTH);
        final Spacer spacer3 = new Spacer();
        panel2.add(spacer3, BorderLayout.EAST);
        final Spacer spacer4 = new Spacer();
        panel2.add(spacer4, BorderLayout.WEST);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        panel1.add(panel3, BorderLayout.SOUTH);
        exportButton = new JButton();
        exportButton.setText("Export");
        panel3.add(exportButton, BorderLayout.CENTER);
        final Spacer spacer5 = new Spacer();
        panel3.add(spacer5, BorderLayout.NORTH);
        final Spacer spacer6 = new Spacer();
        panel3.add(spacer6, BorderLayout.SOUTH);
        final Spacer spacer7 = new Spacer();
        panel3.add(spacer7, BorderLayout.EAST);
        final Spacer spacer8 = new Spacer();
        panel3.add(spacer8, BorderLayout.WEST);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout(0, 0));
        panel1.add(panel4, BorderLayout.CENTER);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new BorderLayout(0, 0));
        panel4.add(panel5, BorderLayout.NORTH);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.add(panel6, BorderLayout.EAST);
        filterButton = new JButton();
        filterButton.setText("Filter");
        panel6.add(filterButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        applyButton = new JButton();
        applyButton.setText("Apply");
        panel6.add(applyButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new BorderLayout(0, 0));
        panel5.add(panel7, BorderLayout.WEST);
        firstRadioButton = new JRadioButton();
        firstRadioButton.setText("First");
        panel7.add(firstRadioButton, BorderLayout.NORTH);
        secondRadioButton = new JRadioButton();
        secondRadioButton.setText("Second");
        panel7.add(secondRadioButton, BorderLayout.CENTER);
        weekPreviewComboBox = new JComboBox();
        panel5.add(weekPreviewComboBox, BorderLayout.SOUTH);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel5.add(panel8, BorderLayout.CENTER);
        textField1 = new JTextField();
        panel8.add(textField1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Apply Filtered");
        panel8.add(label1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel8.add(panel9, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkAvailabilityButton = new JButton();
        checkAvailabilityButton.setText("Check Availability");
        panel9.add(checkAvailabilityButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        formatImplementationButton = new JButton();
        formatImplementationButton.setText("Format Implementation");
        panel9.add(formatImplementationButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new BorderLayout(0, 0));
        panel4.add(panel10, BorderLayout.SOUTH);
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new BorderLayout(0, 0));
        panel10.add(panel11, BorderLayout.WEST);
        addButton = new JButton();
        addButton.setText("Add");
        panel11.add(addButton, BorderLayout.WEST);
        editButton = new JButton();
        editButton.setText("Edit");
        panel11.add(editButton, BorderLayout.EAST);
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new BorderLayout(0, 0));
        panel10.add(panel12, BorderLayout.EAST);
        deleteColumnButton = new JButton();
        deleteColumnButton.setText("Delete Column");
        panel12.add(deleteColumnButton, BorderLayout.WEST);
        deleteSelectedRowButton = new JButton();
        deleteSelectedRowButton.setText("Delete Selected Row");
        panel12.add(deleteSelectedRowButton, BorderLayout.EAST);
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new BorderLayout(0, 0));
        panel10.add(panel13, BorderLayout.CENTER);
        comboBox1 = new JComboBox();
        panel13.add(comboBox1, BorderLayout.CENTER);
        final JScrollPane scrollPane1 = new JScrollPane();
        panel4.add(scrollPane1, BorderLayout.CENTER);
        tabela = new JTable();
        tabela.setAutoCreateRowSorter(false);
        scrollPane1.setViewportView(tabela);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }


    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JButton getImportButton() {
        return importButton;
    }

    public void setImportButton(JButton importButton) {
        this.importButton = importButton;
    }

    public JButton getExportButton() {
        return exportButton;
    }

    public void setExportButton(JButton exportButton) {
        this.exportButton = exportButton;
    }

    public JButton getFilterButton() {
        return filterButton;
    }

    public void setFilterButton(JButton filterButton) {
        this.filterButton = filterButton;
    }

    public JButton getApplyButton() {
        return applyButton;
    }

    public void setApplyButton(JButton applyButton) {
        this.applyButton = applyButton;
    }

    public JComboBox getWeekPreviewComboBox() {
        return weekPreviewComboBox;
    }

    public void setWeekPreviewComboBox(JComboBox weekPreviewComboBox) {
        this.weekPreviewComboBox = weekPreviewComboBox;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }

    public JTable getTabela() {
        return tabela;
    }

    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }

    public JButton getDeleteColumnButton() {
        return deleteColumnButton;
    }

    public void setDeleteColumnButton(JButton deleteColumnButton) {
        this.deleteColumnButton = deleteColumnButton;
    }

    public JButton getDeleteSelectedRowButton() {
        return deleteSelectedRowButton;
    }

    public void setDeleteSelectedRowButton(JButton deleteSelectedRowButton) {
        this.deleteSelectedRowButton = deleteSelectedRowButton;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public void setComboBox1(JComboBox comboBox1) {
        this.comboBox1 = comboBox1;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JButton getCheckAvailabilityButton() {
        return checkAvailabilityButton;
    }

    public void setCheckAvailabilityButton(JButton checkAvailabilityButton) {
        this.checkAvailabilityButton = checkAvailabilityButton;
    }

    public JRadioButton getSecondRadioButton() {
        return secondRadioButton;
    }

    public void setSecondRadioButton(JRadioButton secondRadioButton) {
        this.secondRadioButton = secondRadioButton;
    }

    public JRadioButton getFirstRadioButton() {
        return firstRadioButton;
    }

    public void setFirstRadioButton(JRadioButton firstRadioButton) {
        this.firstRadioButton = firstRadioButton;
    }

    public JButton getFormatImplementationButton() {
        return formatImplementationButton;
    }

    public void setFormatImplementationButton(JButton formatImplementationButton) {
        this.formatImplementationButton = formatImplementationButton;
    }
}