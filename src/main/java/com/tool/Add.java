package com.tool;

import com.tool.db.DBUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Add {
    private JPanel panel1;
    private JButton submit;
    private JTextField company;
    private JTextField product;
    private JTextField version;
    private JTextField type;
    private JTextField info;
    public int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    // 定义窗体的宽高
    public int windowsWedth = 600;
    public int windowsHeight = 600;
    private String sql = "insert into weaks(company,product,version,type,info) values (?,?,?,?,?)";
    private Object[] obj = null;

    public Add() {
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String companyText = company.getText();
                String infoText = info.getText();
                String versionText = version.getText();
                String productText = product.getText();
                String typeText = type.getText();
                DBUtil dbUtil = new DBUtil();
                int i = dbUtil.update(sql, new Object[]{companyText, productText, versionText, typeText, infoText});
                if (i != 0){
                    JOptionPane.showMessageDialog(null,"添加成功");
                }else {
                    JOptionPane.showMessageDialog(null,"添加失败");
                }
            }
        });
    }

    public void run(String uuid) {
        JFrame frame = new JFrame("Add");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds((width - windowsWedth) / 2,(height - windowsHeight) / 2, windowsWedth, windowsHeight);
        frame.setSize(850,800);
        frame.pack();
        frame.setVisible(true);
    }
}
