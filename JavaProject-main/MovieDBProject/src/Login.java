import Member.MemberDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {


    public Login() {


        JPanel p = new JPanel();
        Label l1 = new Label("영화 예약 로그인",SwingConstants.CENTER);
        Label l2 = new Label("아이디");
        Label l3 = new Label("비밀번호");
        add(l1);
        add(l2);
        add(l3);

        TextField t1 = new TextField();
        TextField t2 = new TextField();

        add(t1);
        add(t2);
        t2.setEchoChar('*');

        JButton j1 = new JButton("로그인");
        JButton j2 = new JButton("회원가입");
        add(j1);
        add(j2);

        Font font1 = new Font("맑은 고딕",Font.BOLD, 30);
        l1.setFont(font1);

        l1.setBounds(120,10,10000,100);
        l2.setBounds(40, 200, 40, 40);
        l3.setBounds(40, 250, 40, 40);

        t1.setBounds(120, 200, 200, 30);
        t2.setBounds(120, 250, 200, 30);

        j1.setBounds(125, 330, 80, 30);
        j2.setBounds(240, 330, 100, 30);

        add(p);
        setSize(500, 500);
        setTitle("로그인");
        setLocationRelativeTo(p);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        j1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = t1.getText();
                String pwd = t2.getText();
                MemberDao dao = new MemberDao();
                int result = dao.login(id, pwd);
                if (result == 1) {
                    if(id.equals("manager")) {
                        JOptionPane.showMessageDialog(null, "관리자로 로그인 되었습니다.");
                        ManagerPage managerpage = new ManagerPage();
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
                        SelectMenu selectMenu = new SelectMenu();
                        dispose();
                    }
                }else if (result == 0)
                    JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다.");
            }
        });

        j2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JoinMember joinMember = new JoinMember();
                dispose();
            }
        });
    }
}
