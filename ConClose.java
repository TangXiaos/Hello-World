package cn.partfive;

import sun.security.util.Length;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Function ： 学生信息管理系统
 * Author ： Xiaojie Jia
 * Created by Tangs on 2018/5/20.
 */


   // 创建student表，表中有name，id，math ，os，java五列


    public class ConClose extends JFrame{

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3366/study";    //连接本机默认数据库
        String user = "***";
        String pass = "***";


        private Connection con;
        private JRadioButton add,delete,select,update,querry,sort;   //设置单选按钮
        private ButtonGroup g;  //
        private JButton jb,jb1,jb2;     //执行按钮
        private JTextField jt1,jt2,jt3,jt4,jt5;     //文本框设置
        private JPanel jp1,jp2,jp3;     //初始化面板
        private JTextArea ja;
        private JScrollPane js;
        private JLabel jl,jl2,jid,jname,jmath,jos,jjava;
        public void lunch() throws Exception {

            add=new JRadioButton("增加");
            delete=new JRadioButton("删除");
            select=new JRadioButton("查找");
            update=new JRadioButton("修改");
            querry=new JRadioButton("遍历");
            sort=new JRadioButton("排序");
            jb=new JButton("Start");
            jb1=new JButton("重置");
            jb2=new JButton("更新");
            add.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(add.isSelected()) {
                        JOptionPane.showMessageDialog(null,"请按照id, name, os, math, java添加");
                    }

                }
            });
            delete.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if(delete.isSelected()) {
                        JOptionPane.showMessageDialog(null,"请输入id进行删除");
                    }
                }
            });
            select.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if(select.isSelected()) {
                        JOptionPane.showMessageDialog(null,"请输入id / name查询学生信息");
                    }
                }
            });
            update.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if(update.isSelected()) {
                        JOptionPane.showMessageDialog(null,"点击Start显示该学生信息，修改后点击‘更新’完成更新");

                    }
                }
            });
            querry.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if(querry.isSelected()) {
                        JOptionPane.showMessageDialog(null,"点击Start开始遍历所有学生信息");
                    }
                }
            });
            sort.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {

                    if(sort.isSelected()) {
                        JOptionPane.showMessageDialog(null,"开始按学号顺序遍历所有学生信息");
                    }
                }
            });
            jb.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e){

                    if(add.isSelected()) {
                        Student stu = new Student();
                        stu.setId(Long.parseLong(jt1.getText()));
                        stu.setName(jt2.getText());
                        stu.setMath(Integer.parseInt(jt3.getText()));
                        stu.setOs(Integer.parseInt(jt4.getText()));
                        stu.setJava(Integer.parseInt(jt5.getText()));
                        try {
                            insert(stu);
                            dispall();
                            jt1.setText("");
                            jt2.setText("");
                            jt3.setText("");
                            jt4.setText("");
                            jt5.setText("");
                        } catch (SQLException e1) {

                            e1.printStackTrace();
                        }
                    }
                    if(delete.isSelected()) {
                        try {
                            delete(Long.parseLong(jt1.getText()));
                            dispall();
                            jt1.setText("");
                        } catch (Exception e1) {

                            e1.printStackTrace();
                        }
                    }
                    if(select.isSelected()) {
                        if(jt2.getText().equals("")){
                            try {
                                FindById(Long.parseLong(jt1.getText()));
                                jt1.setText("");
                            } catch (Exception e1) {

                                e1.printStackTrace();
                            }
                        }
                        else if(jt1.getText().equals("")) {
                            try {
                                FindByName(jt2.getText());
                                jt2.setText("");
                            } catch (SQLException e1) {

                                e1.printStackTrace();
                            }
                        }
                    }
                    if(update.isSelected()) {

                        try {
                            update(Long.parseLong(jt1.getText()));
                        } catch (Exception e1) {

                            e1.printStackTrace();
                        }
                    }
                    if(querry.isSelected()) {
                        try {
                            dispall();

                        } catch (SQLException e1) {

                            e1.printStackTrace();
                        }
                    }
                    if(sort.isSelected()) {
                        try {
                            sort();
                        } catch (Exception e1) {

                            e1.printStackTrace();
                        }
                    }
                }
            });
            jb1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    jt1.setText("");
                    jt2.setText("");jt3.setText("");
                    jt4.setText("");jt5.setText("");
                    ja.setText("");
                    g.clearSelection();
                }
            });
            jb2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if(!jt1.getText().equals("")&&!jt2.getText().equals("")&&!jt3.getText().equals("")&&!jt4.getText().equals("")&&!jt5.getText().equals("")) {
                        if(JOptionPane.showConfirmDialog(null,"开始更新某项数据？","更新",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION) {
                            PreparedStatement pstmt;
                            try {
                                pstmt = con.prepareStatement("update student set math=? ,os=? ,java=? ,name=?where id=?");
                                pstmt.setInt(1,Integer.parseInt(jt3.getText()));
                                pstmt.setInt(2,Integer.parseInt(jt4.getText()));
                                pstmt.setInt(3,Integer.parseInt(jt5.getText()));
                                pstmt.setString(4, jt2.getText());
                                pstmt.setLong(5,Long.parseLong(jt1.getText()));
                                pstmt.executeUpdate();
                                ja.append("\n");
                                dispall();
                                jt1.setText("");jt2.setText("");
                                jt3.setText("");jt4.setText("");
                                jt5.setText("");
                                pstmt.close();} catch (SQLException e1) {

                                e1.printStackTrace();
                            }
                        }
                    }
                }
            });
            jt1=new JTextField(8);jt2=new JTextField(5);
            jt3=new JTextField(5);jt4=new JTextField(5);
            jt5=new JTextField(5);
            g=new ButtonGroup();
            jp1=new JPanel();
            jp2=new JPanel();
            jp3=new JPanel();
            jl=new JLabel("结果：");
            jl2=new JLabel("功能：");
            jid=new JLabel("id：");
            jname=new JLabel("name：");
            jmath=new JLabel("math：");
            jos=new JLabel("os：");
            jjava=new JLabel("java：");
            ja=new JTextArea(20,50);
            ja.setLineWrap(true);
            js=new JScrollPane(ja);
            Container c=getContentPane();
            c.setLayout(new BorderLayout());



            g.add(add);
            g.add(delete);
            g.add(select);
            g.add(update);
            g.add(querry);
            g.add(sort);
            jp1.add(jid);
            jp1.add(jt1);
            jp1.add(jname);
            jp1.add(jt2);
            jp1.add(jmath);
            jp1.add(jt3);
            jp1.add(jos);
            jp1.add(jt4);
            jp1.add(jjava);
            jp1.add(jt5);
            jp1.add(jb);
            jp1.setLocation(100,100);
            jp2.add(jl2);
            jp2.add(add);
            jp2.add(delete);
            jp2.add(select);
            jp2.add(update);
            jp2.add(querry);
            jp2.add(sort);
            jp2.add(jb1);
            jp2.add(jb2);
            jp2.setLocation(300,200);
            jp3.add(jl);
            jp3.add(js);
            c.add(jp1,BorderLayout.NORTH);
            c.add(jp2,BorderLayout.CENTER);
            c.add(jp3,BorderLayout.SOUTH);

            c.setLayout(new FlowLayout(FlowLayout.CENTER,30,30));
            setTitle("学生信息管理系统");
            setSize(800, 550);
            setLocation(500,400);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        //建立和数据库之间的链接
        public void init()throws Exception{
            if(con!=null)return;
            Class.forName(driver);
            con=DriverManager.getConnection(url,user,pass);
        }

        //关闭程序链接
        public void close()throws Exception{
            if(con!=null)
                con.close();
        }

        //创建数据库表，只执行一次
        public void initTable()throws SQLException{     //创建表，只执行一次
            Statement stmt=con.createStatement();
            stmt.executeUpdate("create table STUDENT (id BIGINT, name VARCHAR(20), os INTEGER, math INTEGER, java INTEGER)");
            stmt.close();
        }

        //增加学生信息
        public void insert(Student stu)throws SQLException{
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from STUDENT where id="+stu.getId());
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "此人已在列表", "错误提示",JOptionPane.ERROR_MESSAGE);
            }
            else {
                PreparedStatement pstmt = con.prepareStatement("insert into STUDENT (id, name, os, math, java)values(?,?,?,?,?)");
                pstmt.setLong(1, stu.getId());
                pstmt.setString(2, stu.getName());
                pstmt.setInt(3, stu.getMath());
                pstmt.setInt(4, stu.getOs());
                pstmt.setInt(5, stu.getJava());
                pstmt.executeUpdate();
                ja.append("\n");
                pstmt.close();}
        }

        //
        public void delete(long id)throws Exception{
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from STUDENT where id="+id);
            if(rs.next())
                stmt.executeUpdate("delete from STUDENT where id="+id);
            else
                JOptionPane.showMessageDialog(null, "查无此人", "错误提示",JOptionPane.ERROR_MESSAGE);
            ja.append("\n");
            stmt.close();
        }


        //遍历学生信息
        public void dispall()throws SQLException{
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from student");
            ja.append("     id               name      math     os      java\n");
            while(rs.next()) {
                StringBuffer sb = new StringBuffer();
//                sb.append(String.format("%-15s",rs.getString("id"));
                sb.append(String.format("%-15s",rs.getString("id")));
                sb.append(String.format("%-10s",rs.getString("name")));
                sb.append(String.format("%-10s",rs.getString("math")));
                sb.append(String.format("%-10s",rs.getString("os")));
                sb.append(String.format("%-10s",rs.getString("java"))+"\n");
                ja.append(sb.toString());
            }
            ja.append("\n");
            rs.close();
            stmt.close();
        }


        //通过id查找学生
        public void FindById(long id)throws Exception{
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from STUDENT where id="+id);
            if(rs.next()) {
                StringBuffer sb = new StringBuffer();
                sb.append(rs.getString("id")+",");
                sb.append(rs.getString("name")+",");
                sb.append(rs.getString("math")+",");
                sb.append(rs.getString("os")+",");
                sb.append(rs.getString("java"));
                ja.append(sb.toString()+"\n");
            }
            else
                JOptionPane.showMessageDialog(null, "查无此人", "错误提示",JOptionPane.ERROR_MESSAGE);

        }


        //通过姓名查找学生
        public void FindByName(String name)throws SQLException{
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select * from student");
            ResultSetMetaData rsmd=rs.getMetaData();
            int col=rsmd.getColumnCount();
            int flag=0;
            while(rs.next()) {
                for(int j=0;j<col;j++) {
                    if(rs.getString(j+1).equals(name)) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(rs.getString("id")+",");
                        sb.append(rs.getString("name")+",");
                        sb.append(rs.getString("math")+",");
                        sb.append(rs.getString("os")+",");
                        sb.append(rs.getString("java"));
                        ja.append(sb.toString()+"\n");
                        flag=1;
                    }
                }

            }
            if(flag==0) {
                JOptionPane.showMessageDialog(null, "查无此人", "错误提示",JOptionPane.ERROR_MESSAGE);
            }
        }


        //
        public void update(long id)throws Exception{
            if(!jt1.getText().equals("")) {
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from STUDENT where id="+id);
                if(rs.next()) {
                    jt2.setText(rs.getString("name"));
                    jt3.setText(rs.getString("math"));
                    jt4.setText(rs.getString("os"));
                    jt5.setText(rs.getString("java"));
                }
                else {
                    JOptionPane.showMessageDialog(null, "查无此人", "错误提示",JOptionPane.ERROR_MESSAGE);
                }
            }
        }


        //通过id号排序(从小到大)
        public void sort()throws Exception{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from student order by id");
            while(rs.next()) {
                StringBuffer sb = new StringBuffer();
                sb.append(rs.getString("id")+",");
                sb.append(rs.getString("name")+",");
                sb.append(rs.getString("math")+",");
                sb.append(rs.getString("os")+",");
                sb.append(rs.getString("java")+"\n");

                ja.append(sb.toString());
            }
            ja.append("\n");

            rs.close();
            stmt.close();
        }

        public static void main(String[] args)throws Exception {
            ConClose demo=new ConClose();
                demo.init();
                demo.lunch();
        }

    }
