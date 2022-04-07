 import Member.MemberDao;
import Movie.MovieDao;
import Movie.MovieDto;
import Reservation.ReservationDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;


public class MovieList extends JFrame {
    List list;

    public MovieList() {
        JPanel p = new JPanel();
        Label l1 = new Label("영화 상영 리스트",SwingConstants.CENTER);
        Label l2 = new Label("영화 번호");
        Label l3 = new Label("영화 이름");
        Label l4 = new Label("러닝 타임");
        Label l5 = new Label("영화 장르");
        Label l6 = new Label("상영 날짜");
        Label l7 = new Label("예약 확인");

        Label l8 = new Label("영화 번호 : ");
        Label l9 = new Label("영화 이름 : ");
        Label l10 = new Label("러닝 타임 : ");
        Label l11 = new Label("영화 장르 : ");
        Label l12 = new Label("상영 날짜 : ");
        Label l13 = new Label("아이디 : ");

        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l7);
        add(l8);
        add(l9);
        add(l10);
        add(l11);
        add(l12);
        add(l13);

        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();
        TextField t4 = new TextField();
        TextField t5 = new TextField();
        TextField t6 = new TextField();

        add(t1);
        add(t2);
        add(t3);
        add(t4);
        add(t5);
        add(t6);

        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        t5.setEditable(false);

        Font font1 = new Font("맑은 고딕",Font.BOLD, 30);
        l1.setFont(font1);
        Font font2 = new Font("맑은 고딕", Font.BOLD,15);
        l7.setFont(font2);

        JButton j1 = new JButton("예약 하기");
        JButton j2 = new JButton("뒤로가기");
        add(j1);
        add(j2);

        list = new List(7,false);
        add(list);

        l1.setBounds(20,10,10000,100);
        l2.setBounds(40,100,60,40);
        l3.setBounds(120,100,60,40);
        l4.setBounds(200,100,60,40);
        l5.setBounds(280,100,60,40);
        l6.setBounds(360,100,60,40);

        l7.setBounds(35, 225, 70, 25);
        l8.setBounds(35, 255, 60, 25);
        l9.setBounds(35, 285, 60, 25);
        l10.setBounds(35, 315, 60, 25);
        l11.setBounds(35, 345, 60, 25);
        l12.setBounds(35, 375, 60, 25);
        l13.setBounds(300, 255, 60, 25);


        t1.setBounds(150, 255, 100, 25);
        t2.setBounds(150, 285, 100, 25);
        t3.setBounds(150, 315, 100, 25);
        t4.setBounds(150, 345, 100, 25);
        t5.setBounds(150, 375, 100, 25);
        t6.setBounds(300, 285, 100, 25);

        j1.setBounds(70, 425, 100, 25);
        j2.setBounds(300, 425,100, 25);


        list.setBounds(35, 145, 400, 80);

        add(p);
        setSize(500, 500);
        setTitle("영화 상영 리스트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        displayAll();

        list.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String str = list.getSelectedItem();
                StringTokenizer st = new StringTokenizer(str);
                t1.setText(st.nextToken());
                t2.setText(st.nextToken());
                t3.setText(st.nextToken());
                t4.setText(st.nextToken());
                t5.setText(st.nextToken());
            }
        });

        j1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
                Date date = new Date();

                String id = t6.getText();
                int movieNumber = Integer.parseInt(t1.getText());
                String movieName = t2.getText();
                String screenDate = t5.getText();
                String reservationDate = format1.format(date);

                ReservationDao rDao = new ReservationDao();
                MemberDao mDao = new MemberDao();
                rDao.insert(id, movieNumber, movieName, screenDate, reservationDate);

                if (mDao.checkId(id) == true) {
                    JOptionPane.showMessageDialog(null, "예약 되셨습니다.");
                    ReservationPage reservationPage = new ReservationPage();
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "아이디가 잘못 되었습니다.");
                }
            }

        });
        j2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SelectMenu selectMenu = new SelectMenu();
            }
        });

    }

    private void displayAll() {

        MovieDao dao = new MovieDao();
        ArrayList<MovieDto> allData = dao.select();
        for (MovieDto dto : allData) {
            int movieNumber = dto.getMovieNumber();
            String movieName = dto.getMovieName();
            int runningTime = dto.getRunningTime();
            String genre = dto.getGenre();
            String screenDate = dto.getScreenDate();
            list.add(movieNumber + "            " +
                    movieName + "               " +
                    runningTime + "                " +
                    genre + "                  " +
                    screenDate);
        }

    }

}
