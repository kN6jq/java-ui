package com.tool;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import com.tool.db.DBUtil;
import org.apache.commons.lang3.ArrayUtils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    private JPanel panel1;
    private JLabel seachLabel;
    private JTextField searchFd;
    private JButton searchBtn;
    private JTable table1;
    private JButton addBtn;
    private JScrollPane table;
    private String uuid;
    private String sql = "select id,company,product,version,type,info from weaks";
    private Object[] obj = null;
    // 得到显示器屏幕的宽高
    public int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    // 定义窗体的宽高
    public int windowsWedth = 600;
    public int windowsHeight = 600;
    public Controller() {
        // 口令搜索
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = searchFd.getText();

                ((DefaultTableModel) table1.getModel()).getDataVector().clear();   //清除表格数据
                ((DefaultTableModel) table1.getModel()).fireTableDataChanged();//通知模型更新
//                DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
//                dtm.setRowCount(0);
                table1.updateUI();
                sql = "select id,company,product,version,type,info from weaks where product like ? ";



                String[] headers = {"id","公司","产品","版本","类型","账号密码"};
                // TODO: place custom component creation code here


                Object[][] row = {};
                DBUtil dbUtil = new DBUtil();
                ResultSet rs = dbUtil.selectLike(sql, new Object[]{text});
                while (true){
                    try {
                        if (!rs.next()) {
                            break;
                        } else {
                            row = ArrayUtils.add(row,new Object[]{rs.getInt("id"), rs.getString("company")
                                    , rs.getString("product"), rs.getString("version")
                                    , rs.getString("type"), rs.getString("info")});
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                DefaultTableModel model = new DefaultTableModel(row,headers);
                table1.setModel(model);
                dbUtil.closeConnection();

            }

        });
        // 口令新增
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uuid = IdUtil.randomUUID();
                Add add = new Add();
                add.run(uuid);
            }
        });
    }



    public void run(String uuid) {


        this.uuid = uuid;
        JFrame frame = new JFrame("Controller");
        frame.setContentPane(this.panel1); //当前页面
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds((width - windowsWedth) / 2,(height - windowsHeight) / 2, windowsWedth, windowsHeight);
        frame.setSize(850,800);
        frame.pack();
        frame.setVisible(true);
    }

//    private void createUIComponents() {
//        // TODO: place custom component creation code here
//    }


    public void createUIComponents() throws SQLException {
        String[] headers = new String[]{"id","公司","产品","版本","类型","账号密码"};
        // TODO: place custom component creation code here

        DefaultTableModel model = new DefaultTableModel(0,6);
        model.setColumnIdentifiers(headers);
        model.setRowCount(0);
        table1 = new JTable(model);
        DBUtil dbUtil = new DBUtil();
        ResultSet rs = dbUtil.select(sql,null);
        while (rs.next()){
            Object[] row = {rs.getInt("id"), rs.getString("company")
                    , rs.getString("product"), rs.getString("version")
                    , rs.getString("type"), rs.getString("info")};
            model.addRow(row);
        }

        dbUtil.closeConnection();
    }
}
