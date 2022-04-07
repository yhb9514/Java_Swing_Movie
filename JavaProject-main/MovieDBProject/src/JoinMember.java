import Member.MemberDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinMember extends JFrame {
    public JoinMember(){
        JPanel p = new JPanel();
        Label l1 = new Label("아이디");
        Label l2 = new Label("이름");
        Label l3 = new Label("비밀번호");
        Label l4 = new Label("핸드폰 번호");
        add(l1);
        add(l2);
        add(l3);
        add(l4);

        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();
        TextField t4 = new TextField();
        add(t1);
        add(t2);
        add(t3);
        add(t4);
        t3.setEchoChar('*');

        JButton j1 = new JButton("저장");
        JButton j2 = new JButton("취소");
        JButton j3 = new JButton("ID 중복체크");
        add(j1);
        add(j2);
        add(j3);

        l1.setBounds(40, 10, 40, 40);
        l2.setBounds(40, 50, 40, 40);
        l3.setBounds(40, 90, 60, 40);
        l4.setBounds(40, 130, 40, 40);

        t1.setBounds(120, 10, 200, 30);
        t2.setBounds(120, 50, 200, 30);
        t3.setBounds(120, 90, 200, 30);
        t4.setBounds(120, 130, 280, 30);

        j1.setBounds(125, 330, 80, 30);
        j2.setBounds(240, 330, 80, 30);
        j3.setBounds(350, 10, 100, 30);


        add(p);
        setSize(500, 500);
        setTitle("회원 가입");
        setLocationRelativeTo(p);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        j1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String id = t1.getText();
                String name = t2.getText();
                String pwd = t3.getText();
                String phoneNumber = t4.getText();
                MemberDao dao = new MemberDao();
                dao.insertMember(id, name, pwd, phoneNumber);
                JOptionPane.showMessageDialog(null, "회원 가입 되셨습니다.");
                Login login = new Login();
                dispose();
            }


        });

        j2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                dispose();
            }
        });

        j3.addActionListener(new ActionListener() {
            MemberDao dao = new MemberDao();

            @Override
            public void actionPerformed(ActionEvent e) {
                String id = t1.getText();
                    if (dao.checkId(id) == true) {
                        JOptionPane.showMessageDialog(null, "사용중인 아이디");
                        t1.setText("");

                    } else {
                        JOptionPane.showMessageDialog(null, "사용 가능한 아이디");
                    }
            }
        });

    }

}
