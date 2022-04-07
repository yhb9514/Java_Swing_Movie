import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import Reservation.ReservationDao;
import Reservation.ReservationDto;


public class ReservationPage extends JFrame {

    List list;

    ReservationPage() {
        // 화면설계하기
        JPanel jmp = new JPanel();

        Label l1 = new Label("예약 페이지", SwingConstants.CENTER);
        Label l2 = new Label("예약 목록");
        Label l3 = new Label("예약번호");
        Label l4 = new Label("아이디");
        Label l5 = new Label("영화번호");
        Label l6 = new Label("영화이름");
        Label l7 = new Label("상영날짜");
        Label l8 = new Label("예약날짜");
        Label l9 = new Label("아이디:");


        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l7);
        add(l8);
        add(l9);

        Font font1 = new Font("맑은 고딕", Font.BOLD, 25);
        Font font2 = new Font("맑은 고딕", Font.BOLD, 16);
        l1.setFont(font1);
        l2.setFont(font2);

        TextField t1 = new TextField();
        add(t1);

        JButton confirm = new JButton("예약 확인"); //삭제
        JButton back = new JButton("뒤로가기"); //삭제

        add(confirm);
        add(back);


        list = new List();
        add(list);
        Font font3 = new Font("맑은 고딕", 0, 15);
        list.setFont(font3);


        l1.setBounds(300, 30, 200, 40);
        l2.setBounds(50, 200, 80, 30);
        l3.setBounds(50, 230, 60, 30);
        l4.setBounds(170, 230, 60, 30);
        l5.setBounds(290, 230, 60, 30);
        l6.setBounds(410, 230, 60, 30);
        l7.setBounds(530, 230, 60, 25);
        l8.setBounds(650, 230, 60, 25);
        l9.setBounds(470, 180, 50, 30);

        t1.setBounds(530, 180, 100, 30);

        confirm.setBounds(650, 180, 100, 30);
        back.setBounds(650, 670, 100, 30);


        list.setBounds(50, 260, 700, 400);


        add(jmp);
        setSize(800, 770);
        setTitle("예약 확인");
        setResizable(false);
        setLocationRelativeTo(jmp);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        confirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "조회 완료!!");
                list.removeAll();
                ReservationDao dao = new ReservationDao();
                String id = t1.getText();
                dao.confirm(id);
                ArrayList<ReservationDto> allData = dao.confirm(id);
                for(ReservationDto dto : allData) {
                    int reservationNumber = dto.getReservationNumber();
                    int movieNumber = dto.getMovieNumber();
                    String movieName = dto.getMovieName();
                    String screenDate = dto.getScreenDate();
                    String reservationDate = dto.getReservationDate();
                    list.add(reservationNumber+"                    "
                            +id+"                         "
                            +movieNumber+"              "
                            +movieName + "              "
                            +screenDate + "             "
                            +reservationDate);
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SelectMenu selectMenu = new SelectMenu();

            }
        });

    }

}