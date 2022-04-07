import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectMenu extends JFrame {

    public SelectMenu() {
        JPanel p = new JPanel();

        JButton j1 = new JButton("예약하기");
        JButton j2 = new JButton("예약 확인");
        JButton j3 = new JButton("로그 아웃");
        add(j1);
        add(j2);
        add(j3);

        Font font1 = new Font("맑은 고딕",Font.BOLD, 30);
        j1.setFont(font1);
        j2.setFont(font1);

        j1.setBounds(30, 200, 200, 50);
        j2.setBounds(250, 200, 200, 50);
        j3.setBounds(135, 400, 200, 25);

        add(p);
        setSize(500, 500);
        setTitle("메뉴 선택");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        j1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovieList movieList = new MovieList();
                dispose();
            }
        });

        j2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReservationPage reservationPage = new ReservationPage();
                dispose();
            }
        });

        j3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "로그 아웃 되었습니다.");
                dispose();
                Login login = new Login();
            }
        });



    }
    

}
