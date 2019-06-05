package com.boram.member.view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.boram.member.controller.MemberController;
import com.boram.member.vo.Member;
import com.boram.member.vo.MemberDao;
import com.boram.myPage.controller.MyCart;
import com.boram.shopping.view.FixedMainMenu;
import com.boram.shopping.view.MainPanel;
import com.boram.shopping.view.MainView;

public class MemberView1 {

	private JPanel Login;
	private JTextField id;
	private JPasswordField pwd;

	// 메인 페이지 호출을 위한 JPanel 반환
	public JPanel getLoginView() {
		return this.Login;
	}

	/**
	 * Create the application.
	 */
	public MemberView1() {
		MemberDao md = new MemberDao();
		ArrayList<Member> mArr = md.fileRead();

		Login = new JPanel();
		Login.setBackground(new Color(255, 255, 255));
		Login.setBounds(550, 0, 647, 596);
		Login.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(73, 40, 515, 528);
		panel.setLayout(null);
		Login.add(panel);

		JLabel BORAMLABEL = new JLabel("BORAM");
		BORAMLABEL.setBounds(40, 75, 234, 83);
		BORAMLABEL.setFont(new Font("굴림", Font.BOLD, 54));
		panel.add(BORAMLABEL);

		JLabel MEMLABEL = new JLabel("MEMBERSHIP LOGIN");
		MEMLABEL.setFont(new Font("굴림", Font.BOLD, 23));
		MEMLABEL.setBounds(42, 147, 340, 27);
		panel.add(MEMLABEL);

		JLabel IDLABEL = new JLabel("ID*");
		IDLABEL.setFont(new Font("굴림", Font.PLAIN, 26));
		IDLABEL.setBounds(46, 227, 109, 44);
		panel.add(IDLABEL);

		id = new JTextField();
		id.setBounds(208, 227, 303, 39);
		panel.add(id);
		id.setColumns(10);

		JLabel PWDLABEL = new JLabel("Password*");
		PWDLABEL.setFont(new Font("굴림", Font.PLAIN, 26));
		PWDLABEL.setBounds(48, 302, 207, 39);
		panel.add(PWDLABEL);

		pwd = new JPasswordField();
		pwd.setBounds(208, 302, 303, 39);
		panel.add(pwd);
		pwd.setColumns(10);

		/*
		 * 로그인 버튼
		 */
		JButton logIn = new JButton("\uB85C\uADF8\uC778");
		logIn.setFont(new Font("굴림", Font.PLAIN, 18));
		logIn.setForeground(Color.WHITE);
		logIn.setBackground(Color.BLACK);
		logIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 로그인 버튼 클릭하면
				String userId = id.getText();
				String userPwd = "";
				char[] pwd1 = pwd.getPassword();
				for (int i = 0; i < pwd1.length; i++) {
					userPwd += pwd1[i];
				}

				// 아이디나 비밀번호 입력 안헀을때
				if (userId.length() == 0 || userPwd.length() == 0) {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력 하셔야 됩니다.", "입력을 확인하세요!",
							JOptionPane.DEFAULT_OPTION); // 입력확인 팝업창
					return;
				}

				MemberController mc = new MemberController();
				Member result = mc.logIn(userId, userPwd); // 멤버컨트롤러에서 저장되어있는 아이디 패스워드인지 확인

				// 로그인 실패하면 확인 팝업창 뜨고 다시 로그인하는 창 돌아가기
				if (result == null) {
					JOptionPane.showMessageDialog(null, "잘못입력하셨습니다. 다시 입력해주세요.");
					Login.setVisible(true);
					MainView.setMainPage(Login);
					Login.add(panel);
				}
				// 로그인 성공시
				else {
					MyCart mct = new MyCart();
					mct.loadCart(); // 로그인하면 전에 장바구니에 저장했던 물건 불러오기
					Login.setVisible(false); // 로그인 창 끄기
					// 메인창 띄우기
					MainView.setMainPage(new MainPanel(1).getMainPanel());
					// 아이콘 바꾸기
					MainView.setMainMenu(new FixedMainMenu().getMainMenu());
				}

			}
		});

		logIn.setBounds(66, 370, 189, 44);
		panel.add(logIn);

		/*
		 * 회원가입 버튼
		 */
		JButton join = new JButton("\uD68C\uC6D0\uAC00\uC785");
		join.setFont(new Font("굴림", Font.PLAIN, 18));
		join.setForeground(Color.WHITE);
		join.setBackground(Color.BLACK);
		join.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 회원가입 버튼 클릭하면
				Login.setVisible(false); // 로그인 창 끄기

				JPanel joinPanel = new JPanel();

				joinPanel.setBackground(Color.WHITE);
				joinPanel.setLayout(null);

				JLabel memberjoinLb = new JLabel("MEMBER JOIN");
				memberjoinLb.setFont(new Font("굴림", Font.BOLD, 28));
				memberjoinLb.setHorizontalAlignment(SwingConstants.CENTER);
				memberjoinLb.setBounds(155, 39, 222, 72);
				joinPanel.add(memberjoinLb);

				JLabel idLb = new JLabel("* \uC544\uC774\uB514");
				idLb.setFont(new Font("굴림", Font.BOLD, 18));
				idLb.setBounds(41, 164, 102, 28);
				joinPanel.add(idLb);

				JLabel pwdLb = new JLabel("* \uBE44\uBC00\uBC88\uD638");
				pwdLb.setFont(new Font("굴림", Font.BOLD, 18));
				pwdLb.setBounds(40, 217, 103, 36);
				joinPanel.add(pwdLb);

				JLabel nameLb = new JLabel("* \uC774\uB984");
				nameLb.setFont(new Font("굴림", Font.BOLD, 18));
				nameLb.setBounds(41, 287, 62, 18);
				joinPanel.add(nameLb);

				JLabel birthLb = new JLabel("\uC0DD\uB144\uC6D4\uC77C ");
				birthLb.setFont(new Font("굴림", Font.PLAIN, 18));
				birthLb.setBounds(41, 342, 191, 28);
				joinPanel.add(birthLb);

				JLabel addressLb = new JLabel("* \uC8FC\uC18C");
				addressLb.setFont(new Font("굴림", Font.BOLD, 18));
				addressLb.setBounds(41, 409, 62, 18);
				joinPanel.add(addressLb);

				JLabel phoneLb = new JLabel("* \uD578\uB4DC\uD3F0 \uBC88\uD638");
				phoneLb.setFont(new Font("굴림", Font.BOLD, 18));
				phoneLb.setBounds(41, 463, 150, 28);
				joinPanel.add(phoneLb);

				JLabel emailLb = new JLabel("\uC774\uBA54\uC77C ");
				emailLb.setFont(new Font("굴림", Font.PLAIN, 18));
				emailLb.setBounds(41, 526, 80, 28);
				joinPanel.add(emailLb);

				JLabel starLb = new JLabel("'*'는 모두 입력해 주세요.");
				starLb.setFont(new Font("굴림", Font.BOLD, 15));
				starLb.setBounds(160, 560, 200, 80);
				joinPanel.add(starLb);

				JTextField idText = new JTextField(); // 아이디입력
				idText.setBounds(301, 162, 176, 36);
				joinPanel.add(idText);
				idText.setColumns(10);

				JTextField pwdText = new JTextField(); // 비밀번호입력
				pwdText.setColumns(10);
				pwdText.setBounds(301, 225, 176, 36);
				joinPanel.add(pwdText);

				JTextField nameText = new JTextField(); // 이름입력
				nameText.setColumns(10);
				nameText.setBounds(301, 286, 176, 36);
				joinPanel.add(nameText);

				Choice year = new Choice(); // 태어난 연도
				year.setBounds(300, 345, 80, 36);
				int y = 0;
				for (y = 1900; y < 2011; y++) {
					year.add(String.valueOf(y));
				}
				joinPanel.add(year);

				Choice month = new Choice(); // 태어난 달
				month.setBounds(400, 345, 45, 36);
				int m = 0;
				for (m = 0; m < 13; m++) {
					month.add(String.valueOf(m));
				}
				joinPanel.add(month);

				Choice day = new Choice(); // 태어난 일
				day.setBounds(460, 345, 45, 36);
				int d = 0;
				for (d = 0; d < 32; d++) {
					day.add(String.valueOf(d));
				}
				joinPanel.add(day);

				JTextField phoneText = new JTextField(); // 폰번호 입력
				phoneText.setColumns(10);
				phoneText.setBounds(301, 463, 176, 36);
				joinPanel.add(phoneText);

				JTextField emailText = new JTextField(); // 이메일 입력
				emailText.setColumns(10);
				emailText.setBounds(301, 518, 176, 36);
				joinPanel.add(emailText);

				JTextField addressText = new JTextField(); // 주소입력
				addressText.setBounds(301, 399, 377, 43);
				joinPanel.add(addressText);
				addressText.setColumns(10);

				/*
				 * 회원가입 확인 버튼
				 */
				JButton joinBtn = new JButton("\uD68C\uC6D0\uAC00\uC785");
				joinBtn.setForeground(Color.WHITE);
				joinBtn.setBackground(Color.BLACK);
				joinBtn.setFont(new Font("굴림", Font.PLAIN, 22));
				joinBtn.setEnabled(false);
				joinBtn.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						// 정보입력후 회원가입 버튼 누르면
						String name = nameText.getText();
						String id = idText.getText();
						String pwd = pwdText.getText();
						String phone = phoneText.getText();
						String address = addressText.getText();
						String email = emailText.getText();

						if (name.equals("") || id.equals("") || pwd.equals("") || phone.equals("") || address.equals("")) {
							JOptionPane.showMessageDialog(null, "정보를 모두 입력하세요.");// 하나라도 입력안하고 가입누르면 뜨는 팝업창

						} else {

							MemberController mc = new MemberController();
							mc.join(name, year.getSelectedItem(), month.getSelectedItem(), day.getSelectedItem(), id,
									pwd, phone, address, email); // 입력한거 멤버컨트롤러 회원리스트에 추가

							JFrame frame = new JFrame();
							JOptionPane.showMessageDialog(frame, "회원가입이 완료 되었습니다."); // 완료되면 완료 팝업창
							Login.setVisible(true);

							// 회원가입창 끄기
							joinPanel.setVisible(false);

							// 로그인창 뜨기
							MainView.setMainPage(Login);
							Login.add(panel);
						}
					}
				});
				joinBtn.setBounds(252, 650, 160, 52);
				joinPanel.add(joinBtn);

				/*
				 * 아이디 체크 버튼
				 */
				JButton idcheckBtn = new JButton("ID CHECK");
				idcheckBtn.setForeground(Color.WHITE);
				idcheckBtn.setBackground(Color.BLACK);
				idcheckBtn.setFont(new Font("굴림", Font.PLAIN, 20));

				idcheckBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						// 아이디 입력후 사용가능한 아이디인지 확인하는 버튼
						// 아이디 입력후 아이디체크버튼 누르면 저장된 멤버 아이디 검색
						MemberController mc = new MemberController();
						int result = mc.idCheck(idText);

						// 같은 아이디가 존재하면
						if (result == 1) {
							JFrame frame = new JFrame();
							JOptionPane.showMessageDialog(frame, "같은 아이디가 존재합니다. 다른ID를 사용하세요."); // 다른아이디 사용하라는 팝업창
							joinBtn.setEnabled(false); // 회원가입버튼 안눌리게하기
						}

						// 저장된 아이디랑 다르면 사용가능
						else if (result == 0) {
							JFrame frame2 = new JFrame();
							JOptionPane.showMessageDialog(frame2, "사용가능한 ID입니다."); // 사용할수 있다는 팝업창
							joinBtn.setEnabled(true); // 회원가입버튼 눌림
						}

					}
				});

				idcheckBtn.setBounds(500, 164, 150, 25);
				joinPanel.add(idcheckBtn);

				MainView.setMainPage(joinPanel);
			}
		});

		join.setBounds(301, 370, 199, 44);
		panel.add(join);

		/*
		 * 아이디 찾기 버튼
		 */
		JButton searchId = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
		searchId.setFont(new Font("Gulim", Font.PLAIN, 15));
		searchId.setForeground(Color.WHITE);
		searchId.setBackground(Color.BLACK);
		searchId.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 아이디찾기 버튼 클릭하면
				panel.setVisible(false); // 로그인 창 끄기

				JPanel idSearchPanel = new JPanel();

				idSearchPanel.setBackground(Color.WHITE);
				idSearchPanel.setLayout(null);

				JLabel lblIdpwFind = new JLabel("ID/PW FIND");
				lblIdpwFind.setFont(new Font("굴림", Font.BOLD, 38));
				lblIdpwFind.setHorizontalAlignment(SwingConstants.CENTER);
				lblIdpwFind.setBounds(133, 55, 288, 54);
				idSearchPanel.add(lblIdpwFind);

				JLabel searchLb = new JLabel("\uC544\uC774\uB514 \uCC3E\uAE30");
				searchLb.setFont(new Font("굴림", Font.BOLD, 17));
				searchLb.setBounds(64, 155, 98, 31);
				idSearchPanel.add(searchLb);

				JLabel nameLb = new JLabel("\uC774\uB984 : ");
				nameLb.setFont(new Font("굴림", Font.PLAIN, 19));
				nameLb.setBounds(100, 214, 62, 49);
				idSearchPanel.add(nameLb);

				JLabel phoneLb = new JLabel("핸드폰 번호('-'빼고 입력) : ");
				phoneLb.setFont(new Font("굴림", Font.PLAIN, 19));
				phoneLb.setBounds(100, 275, 250, 30);
				idSearchPanel.add(phoneLb);

				JTextField nametext = new JTextField(); // 이름입력
				nametext.setBounds(350, 218, 248, 38);
				idSearchPanel.add(nametext);
				nametext.setColumns(10);

				JTextField phonetext = new JTextField(); // 폰번호입력
				phonetext.setColumns(10);
				phonetext.setBounds(350, 273, 248, 38);
				idSearchPanel.add(phonetext);

				/*
				 * 아이디 찾기 내용 입력후 확인 버튼
				 */
				JButton confirmBtn = new JButton("\uD655  \uC778");
				confirmBtn.setFont(new Font("굴림", Font.PLAIN, 18));
				confirmBtn.setForeground(Color.WHITE);
				confirmBtn.setBackground(Color.BLACK);
				confirmBtn.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						// 아이디 찾기 확인 버튼 누르면
						MemberController mc = new MemberController();

						// 찾는 이름과 번호가 없으면
						if (mc.searchId(nametext, phonetext) == null) {
							JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다"); // 불일치 팝업창

						// 이름과 번호에 맞는 저장된 아이디를 찾으면
						} else {
							String id = mc.searchId(nametext, phonetext);
							// 찾은 id 팝업창뜨기
							String i = (nametext.getText() + "님의 아이디 : " + id);
							JOptionPane.showMessageDialog(null, i);

							// 아이디찾기창 끄기
							idSearchPanel.setVisible(false);
							panel.setVisible(true);

							// 로그인창 뜨기
							MainView.setMainPage(Login);
							Login.add(panel);

						}

					}
				});
				confirmBtn.setBounds(280, 342, 119, 54);
				idSearchPanel.add(confirmBtn);

				MainView.setMainPage(idSearchPanel);

			}

		});
		searchId.setBounds(130, 496, 130, 27);
		panel.add(searchId);

		/*
		 * 비밀번호 찾기 버튼
		 */
		JButton searchPwd = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		searchPwd.setFont(new Font("Gulim", Font.PLAIN, 15));
		searchPwd.setBackground(Color.BLACK);
		searchPwd.setForeground(Color.WHITE);
		searchPwd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 비밀번호찾기 버튼 클릭하면
				panel.setVisible(false); // 로그인 창 끄기

				JPanel pwdSearchPanel = new JPanel();

				pwdSearchPanel.setBackground(Color.WHITE);
				pwdSearchPanel.setLayout(null);

				JLabel lblIdpwFind = new JLabel("ID/PW FIND");
				lblIdpwFind.setFont(new Font("굴림", Font.BOLD, 38));
				lblIdpwFind.setHorizontalAlignment(SwingConstants.CENTER);
				lblIdpwFind.setBounds(133, 55, 288, 54);
				pwdSearchPanel.add(lblIdpwFind);

				JLabel searchLb = new JLabel("비밀번호 찾기");
				searchLb.setFont(new Font("굴림", Font.BOLD, 17));
				searchLb.setBounds(64, 155, 150, 31);
				pwdSearchPanel.add(searchLb);

				JLabel nameLb = new JLabel("\uC774\uB984 : ");
				nameLb.setFont(new Font("굴림", Font.PLAIN, 19));
				nameLb.setBounds(100, 214, 62, 49);
				pwdSearchPanel.add(nameLb);

				JLabel phoneLb = new JLabel("핸드폰 번호('-'빼고 입력) : ");
				phoneLb.setFont(new Font("굴림", Font.PLAIN, 19));
				phoneLb.setBounds(100, 275, 250, 30);
				pwdSearchPanel.add(phoneLb);

				JTextField nametext = new JTextField(); // 이름입력
				nametext.setBounds(350, 218, 248, 38);
				pwdSearchPanel.add(nametext);
				nametext.setColumns(10);

				JTextField phonetext = new JTextField(); // 폰번호입력
				phonetext.setColumns(10);
				phonetext.setBounds(350, 273, 248, 38);
				pwdSearchPanel.add(phonetext);

				/*
				 * 비밀번호찾기 이름과 폰번호 입력후 확인버튼
				 */
				JButton confirmBtn = new JButton("\uD655  \uC778");
				confirmBtn.setFont(new Font("굴림", Font.PLAIN, 18));
				confirmBtn.setForeground(Color.WHITE);
				confirmBtn.setBackground(Color.BLACK);
				confirmBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 비밀번호 찾기 확인버튼 누르면
						MemberController mc = new MemberController();

						// 이름과 번호를 찾을수 없으면
						if (mc.searchId(nametext, phonetext) == null) {
							JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다"); // 불일치 팝업창

							// 이름과 번호가 일치하는 비밀번호찾으면
						} else {
							String pwd = mc.searchId(nametext, phonetext);
							// 찾은 pwd 팝업창뜨기
							String i = (nametext.getText() + "님의 비밀번호 : " + pwd);
							JOptionPane.showMessageDialog(null, i);

							// 비밀번호찾기창 끄기
							pwdSearchPanel.setVisible(false);
							panel.setVisible(true);

							// 로그인창 뜨기
							MainView.setMainPage(Login);
							Login.add(panel);
						}
					}
				});
				confirmBtn.setBounds(290, 342, 119, 54);
				pwdSearchPanel.add(confirmBtn);

				MainView.setMainPage(pwdSearchPanel);
			}
		});
		searchPwd.setBounds(300, 496, 130, 27);
		panel.add(searchPwd);

	}
}
