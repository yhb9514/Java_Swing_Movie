import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import Member.MemberDao;
import Member.MemberDto;
import Movie.MovieDao;
import Movie.MovieDto;

public class MovieAddPage extends JFrame{

    List list;

    MovieAddPage() {
        // 화면설계하기
        JPanel jmp = new JPanel();

        Label l1 = new Label("영화 관리 페이지",SwingConstants.CENTER);
        Label l2 = new Label("영화 목록");
        Label l3 = new Label("영화제목 : ");
        Label l4 = new Label("러닝타임 : ");
        Label l5 = new Label("장   르 : ");
        Label l6 = new Label("상영날짜 : ");
        Label l8 = new Label("영화제목");
        Label l9 = new Label("러닝타임");
        Label l10 = new Label("장  르");
        Label l11 = new Label("상영날짜");


        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l8);
        add(l9);
        add(l10);
        add(l11);

        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();
        TextField t4 = new TextField();

        add(t1);
        add(t2);
        add(t3);
        add(t4);

        Font font1 = new Font("맑은 고딕", Font.BOLD, 25);
        Font font2 = new Font("맑은 고딕", Font.BOLD, 16);
        l1.setFont(font1);
        l2.setFont(font2);

        JButton save = new JButton("저 장"); //저장
        JButton delete = new JButton("삭 제"); //삭제
        JButton update = new JButton("수 정"); //수정
        JButton search = new JButton("조 회"); //조회
        JButton reset = new JButton("지우기"); //지우기
        JButton back = new JButton("뒤로가기");

        add(save);
        add(delete);
        add(update);
        add(search);
        add(reset);
        add(back);

        list = new List();
        add(list);
        Font font3 = new Font("맑은 고딕", 0, 15);
        list.setFont(font3);


        l1.setBounds(300, 30, 200, 40);
        l2.setBounds(50, 200, 80, 30);
        l3.setBounds(350, 100, 50, 30);
        l4.setBounds(350, 130, 50, 30);
        l5.setBounds(550, 100, 60, 30);
        l6.setBounds(550, 130, 60, 30);
        l8.setBounds(80, 230, 60, 25);
        l9.setBounds(250, 230, 60, 25);
        l10.setBounds(450, 230, 60, 25);
        l11.setBounds(600, 230, 60, 25);


        t1.setBounds(410, 100, 100, 30);
        t2.setBounds(410, 130, 100, 30);
        t3.setBounds(610, 100, 100, 30);
        t4.setBounds(610, 130, 100, 30);

        save.setBounds(240, 180, 100, 30);
        delete.setBounds(340, 180, 100, 30);
        update.setBounds(440, 180, 100, 30);
        search.setBounds(540, 180, 100, 30);
        reset.setBounds(640, 180, 100, 30);
        back.setBounds(650,670,100,30);

        list.setBounds(50, 260, 700, 400);


        add(jmp);
        setSize(800, 770);
        setTitle("영화 관리");
        setResizable(false);
        setLocationRelativeTo(jmp);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        displayAll();


        //리스트 아이템 선택시 텍스트 필드로 값 넣기
        list.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String str = list.getSelectedItem();
                StringTokenizer st = new StringTokenizer(str);
                t1.setText(st.nextToken());
                t2.setText(st.nextToken());
                t3.setText(st.nextToken());
                t4.setText(st.nextToken());
            }
        });
        //저장하기
        save.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String movieName = t1.getText();
                int runningTime = Integer.parseInt(t2.getText());
                String genre = t3.getText();
                String screenDate = t4.getText();
                MovieDao dao = new MovieDao();
                dao.insertMovie(movieName, runningTime, genre, screenDate);
                JOptionPane.showMessageDialog(null, "저장 완료");
                displayAll();
            }
        });

        //삭제하기
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movieName = t1.getText();
                //MemberDAO 클래스를 객체생성해서 해당 메소드로 호출하기
                MovieDao dao = new MovieDao();
                dao.delete(movieName);
                JOptionPane.showMessageDialog(null, "삭제 완료");
                displayAll();

            }
        });

        //지우기
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //지우기 버튼 코드 구현
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
            }
        });

        //수정하기
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String movieName = t1.getText();
                int runningTime = Integer.parseInt(t2.getText());
                String genre = t3.getText();
                String screenDate = t4.getText();
                MovieDao dao = new MovieDao();
                dao.update(movieName, runningTime, genre, screenDate);
                JOptionPane.showMessageDialog(null, "수정 완료");
                displayAll();

            }
        });
        //검색하기
        search.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                //검색하기
                String movieName = t1.getText();
                MovieDao dao = new MovieDao();
                MovieDto dto = dao.search(movieName);
                t1.setText(dto.getMovieName());
                t2.setText(String.valueOf(dto.getRunningTime()));
                t3.setText(String.valueOf(dto.getGenre()));
                t4.setText(String.valueOf(dto.getScreenDate()));
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ManagerPage managerPage = new ManagerPage();
            }
        });



    }

    private void displayAll() {
        list.removeAll();
        MovieDao dao = new MovieDao();
        dao.select();
        ArrayList<MovieDto> allData = dao.select();
        for(MovieDto dto : allData) {
            String movieName = dto.getMovieName();
            int runningTime = dto.getRunningTime();
            String genre = dto.getGenre();
            String screenDate = dto.getScreenDate();
            list.add(movieName+"                             "
                    +runningTime+"                                               "
                    +genre+"                          "
                    +screenDate);
        }

    }

}
