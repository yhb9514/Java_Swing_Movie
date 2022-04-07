import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Member.MemberDto;

public class ManagerPage extends JFrame {

    ManagerPage(){
        JPanel jpmp = new JPanel();

        Label title = new Label("관리자 페이지",SwingConstants.CENTER);
        add(title);


        Font font1 = new Font("맑은 고딕",Font.BOLD, 15);
        Font font2 = new Font("맑은 고딕",Font.BOLD, 30);
        title.setFont(font2);


        title.setBounds(150,10,1000,65);



        JButton j1 = new JButton("회원 관리");
        JButton j2 = new JButton("영화 정보 관리");
        JButton j3 = new JButton("예약 관리");
        JButton j4 = new JButton("로그 아웃");
        add(j1);
        add(j2);
        add(j3);
        add(j4);

        j1.setBounds(165, 100, 150, 40);
        j2.setBounds(165, 150, 150, 40);
        j3.setBounds(165, 200, 150, 40);
        j4.setBounds(165, 370, 150, 30);

        add(jpmp);
        setSize(500, 450 );
        setLocationRelativeTo(jpmp);
        setResizable(false);
        setTitle("관리자 페이지");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        j1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MemberListPage memberlistpage = new MemberListPage();

            }
        });
        j2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MovieAddPage movieAddPage = new MovieAddPage();
            }
        });
        j3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ManageReservationPage manageReservationPage = new ManageReservationPage();
            }
        });

        j4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
            }
        });




    }

}