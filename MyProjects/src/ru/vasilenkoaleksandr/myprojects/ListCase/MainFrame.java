package ru.vasilenkoaleksandr.myprojects.ListCase;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 22.06.2016.
 */
public class MainFrame extends JFrame {
    private static MainFrame instance = new MainFrame();
    private List cases = new ArrayList<SimpleCase>();
    private JList caseList;

    public static MainFrame getInstance(){
        return instance;
    }

    private MainFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Список дел");
        setMinimumSize(new Dimension(220,300));
        setLayout(new BorderLayout(5,2));

        //панелька добавления/удаления элементов
        JPanel addEditPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,2,2));
        JButton addButton = new JButton("add");
        addButton.addActionListener(e -> addItemToCaseList());
        JButton deleteButton = new JButton("del");
        deleteButton.addActionListener(e -> delItemFromCaseList());
        addEditPanel.add(addButton);
        addEditPanel.add(deleteButton);

        add(addEditPanel,BorderLayout.NORTH);


        //лист дел
        caseList = new JList();
        caseList.setBorder(new BevelBorder(BevelBorder.LOWERED));
        caseList.setCellRenderer(new SimpleCaseRender());
        JScrollPane jScrollPane = new JScrollPane(caseList);

        add(jScrollPane,BorderLayout.CENTER);
        //add(caseList);
        setItemsToCaseList();
        setVisible(true);
    }

    private void delItemFromCaseList() {
        int index = caseList.getSelectedIndex();
        cases.remove(index);
        setItemsToCaseList();
    }

    private void addItemToCaseList() {
        cases.add(new SimpleCase("Дата","Имя","Содержание"));
        setItemsToCaseList();
    }

    private void setItemsToCaseList() {
        if(!cases.isEmpty()){
            Object[] arrCases = cases.toArray();
            caseList.setListData(arrCases);
        }
    }

    public void setContentList(){

    }
}
