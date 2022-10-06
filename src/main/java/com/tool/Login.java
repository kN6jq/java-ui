package com.tool;

import cn.hutool.core.util.IdUtil;
import com.tool.db.DBUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private JPanel panel1;
    private JTextField user;
    private JTextField pass;
    private JButton loginin;

    private JFrame frame;
    public int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    // 定义窗体的宽高
    public int windowsWedth = 600;
    public int windowsHeight = 600;
    public Login() {
        loginin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = user.getText();
                String password = pass.getText();
                if (username == null || username.equals("")){
                    JOptionPane.showMessageDialog(null,"用户名不能为空");
                    return;
                }
                if (password == null || password.equals("")){
                    JOptionPane.showMessageDialog(null,"密码不能为空");
                    return;
                }
                DBUtil db = new DBUtil();
                Object[] objs = {username};
                ResultSet set = db.select("select * from auths where username = ?", objs);
                try {
                    if (set.next()){
                        String password1 = set.getString("password");
                        if (password.equals(password1)){
                            frame.dispose();
                            JOptionPane.showMessageDialog(null,"登录成功");
                            String uuid = IdUtil.randomUUID();
                            Controller controller = new Controller();
                            controller.run(uuid);
                        }else {
                            JOptionPane.showMessageDialog(null,"登录失败");
                            return;
                        }
                    }else {
                        JOptionPane.showMessageDialog(null,"用户名不存在或系统错误");
                        return;
                    }
                } catch (SQLException err) {
                    err.printStackTrace();
                }

                db.closeConnection();
            }
        });
    }
    public void run(){
        frame = new JFrame("Login");
        frame.setContentPane(this.panel1);
        frame.setTitle("工具管理");
        frame.setSize(300,300);
        frame.setBounds((width - windowsWedth) / 2,(height - windowsHeight) / 2, windowsWedth, windowsHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.run();
    }
}
