package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.faces.event.AbortProcessingException;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bean.User;
import dao.UserDao;

public class JFrameDemo extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameDemo frame = new JFrameDemo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setBounds(105, 62, 54, 15);
		contentPane.add(label);
		
		final JLabel userTip = new JLabel("");
		userTip.setBounds(303, 62, 121, 15);
		contentPane.add(userTip);
		
		userField = new JTextField();
		userField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//当用户名输入框失去焦点时会调用此方法
//				System.out.println("用户名输入框失去焦点");
				String username = userField.getText();
				if (username== null || username.trim().length() == 0) {
//					System.out.println("用户名必须大于6位");
					userTip.setText("用户名不能为空");
				}else{
					userTip.setText("");
				}
			}
		});
		userField.setBounds(161, 59, 140, 21);
		contentPane.add(userField);
		userField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(105, 141, 54, 15);
		contentPane.add(label_1);
		
		final JLabel loginTip = new JLabel("");
		loginTip.setForeground(Color.red);
		loginTip.setBounds(131, 34, 167, 15);
		contentPane.add(loginTip);
		
		//记录一下，登录frame对象
		final JFrameDemo self = this;
		//登录
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//创建dao对象，用于操作数据库
				UserDao dao1 = new UserDao();
				//dao.findUser();
				//1.获取到用户输入的密码和用户名验证数据库
				//2.获取输入框中的内容
				String username = userField.getText();
				//getPassword()返回的是C的字符串 char[],通过new String()将C的字符串转为String
				String password = new String(passwordField.getPassword());
				 //3.查询用户是否存在
				User user = dao1.findUser(username, password);
				if (user == null) {
//					System.out.println("登录失败");
					loginTip.setText("用户或密码错误");
				}else{
//					System.out.println("登录成功");
					//销毁登录页
					self.dispose();
					//跳转到MainView
					MainView frame = new MainView();
					//显示
					frame.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(107, 210, 80, 23);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(161, 138, 140, 21);
		contentPane.add(passwordField);
		
		JButton button = new JButton("重置");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userField.setText("");
				passwordField.setText("");
			}
		});
		button.setBounds(268, 210, 80, 23);
		contentPane.add(button);
		
		
		
		
	}
}