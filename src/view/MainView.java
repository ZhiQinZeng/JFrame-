package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import bean.Emp;
import bean.EmpTableModel;
import bean.FindTable;
import dao.EmpDao;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable tablefind;
	
//	页面的起始值   查询、添加、删除、修改
	private int start;
	private int number = 5;
	static List<Emp> yesfind;
	static List<Emp>bumenfind;
	static List<Emp>bumenvalue;
	
	private EmpTableModel tableModel;
	private JPanel panel_left;
	private JButton btnAdd;
	private JButton btnBaoCun;
	private JButton btnDelete;
	private JLabel label;
	private JPanel panel_search;
	private JTextField textField_1;
	private JLabel label_1;
	private JPanel panel_acdu;
	private JPanel panel;
	private JButton btnFirst;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnLast;
	private JButton btnSearch;
	private JPanel panel_1;
	private JButton btnNewButton;
	private JPanel panel_2;
	private JLabel label_2;
	private JComboBox comboBox;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public static List<Emp> getYesfind() {
		return yesfind;
	}

	public void setYesfind(List<Emp> yesfind) {
		this.yesfind = yesfind;
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//以二维数组的方式提供数据
		/*
		String[][] data = {
				{"张三","22","男"},
				{"张三","22","男"},
				{"张三","22","男"}
		};
		//表头
		String[] titles = {"姓名","年龄","性别"};
		table = new JTable(data,titles);
		*/
//		编辑单元格后，点击页面的其他位置，结束编辑状态
//		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		
		tableModel = new EmpTableModel();
		
		
		
		//标题
		label = DefaultComponentFactory.getInstance().createTitle("员工管理系统");
		label.setIcon(new ImageIcon("image/人副本.png"));
		label.setFont(new Font("黑体", Font.PLAIN, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label, BorderLayout.NORTH);
		
	
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnFirst = new JButton("first");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = 0;
				updateTable();
//				判断按钮是否可以让用户点击
				updateButtons();
			}
		});
		panel.add(btnFirst);
		
		
		
	//   每页5条         start，number   start - number
		btnPrevious = new JButton("previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start -= number;
				updateTable();
				updateButtons();
			}
		});
		panel.add(btnPrevious);
		// start + number
		btnNext = new JButton("next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start += number;
				updateTable();
				updateButtons();
			}
		});
		panel.add(btnNext);
		
	
		
		
		btnLast = new JButton("last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = last();
				updateTable();
				updateButtons();
			}
		});
		panel.add(btnLast);
		
		//显示数据的表格
		
		table = new JTable(tableModel);
		table.setFont(new Font("宋体", Font.PLAIN, 14));
		
		/*
		 * 上面的方式只是测试，我们的数据应该来自于数据库
		 * 尽量不要在数据展示的代码中掺杂数据库操作的代码
		 * 数据库操作的代码应该放入到dao层
		 *
		 *
			如果把table直接放在面板上，默认没有表头，我们需要把table先
			放到一个滚动面板上，再将滚动面板放到contentPane
		*/
		
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		panel_left = new JPanel();
		contentPane.add(panel_left, BorderLayout.WEST);
		panel_left.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel_search = new JPanel();
		panel_left.add(panel_search);
		panel_search.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		
		//查询功能
		label_1 = new JLabel("员工姓名：");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_search.add(label_1);
		
		textField_1 = new JTextField();
		panel_search.add(textField_1);
		textField_1.setColumns(10);
		
		
		
		btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon("image/查找1.png"));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//根据姓名查找，先要拿到姓名
				//yesfind是返回的找到的人
				 yesfind=tableModel.find(textField_1.getText());
				//接下来应该是把此设置到table中
				tableModel.setList(yesfind);
				table.updateUI();
				
			}
		});
		panel_search.add(btnSearch);
		
		panel_2 = new JPanel();
		panel_left.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label_2 = new JLabel("部门：");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_2.add(label_2);
		
		
		//下拉列表，根据部门得到平均薪水
		comboBox = new JComboBox();
		//1.先把所有部门加到下拉列表框
		bumenfind=tableModel.bumenfind();
		for(int i=0;i<bumenfind.size();i++){
			comboBox.addItem(bumenfind.get(i).getJob().toString()); 
		}
		panel_2.add(comboBox);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setColumns(14);
		panel_2.add(textArea);
		
		
		
		//为下拉列表框增加点击事件
		comboBox.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						//找到所点击的部门的记录
						bumenvalue=tableModel.bumenvalue(comboBox.getSelectedItem().toString());
						//取出集合中的部门人数
						int count=bumenvalue.size();
						//取出集合中的薪水放入一个新的数组集合
						List bumensal=new ArrayList<>();
						for(int j=0;j<bumenvalue.size();j++){
							bumensal.add(bumenvalue.get(j).getSal());
						}
						//得到此部门的最大最小值,平均值
						float val=0;
						float minsal=Collections.min(bumensal);
						float maxsal=Collections.max(bumensal);
						for(int k=0;k<bumensal.size();k++){ 
					            val=val+(Float)bumensal.get(k); 
					       } 
						val=val/bumensal.size();
						//System.out.println("val="+val);
						//System.out.println(""+bumenvalue);
						textArea.setText("此部门人数:"+count+"   "+"最高薪水:"+maxsal+"   "+"最低薪水:"+minsal+"   "+"平均薪水:"+val);
					}
				});
				
		
		
		//增删改功能
		panel_acdu = new JPanel();
		panel_left.add(panel_acdu);
		panel_acdu.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		btnDelete = new JButton("delete");
		panel_acdu.add(btnDelete);
		btnDelete.setHorizontalAlignment(SwingConstants.LEADING);
		
		btnAdd = new JButton("add");
		panel_acdu.add(btnAdd);
		
		panel_1 = new JPanel();
		panel_left.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		btnBaoCun = new JButton("save");
		btnBaoCun.setVerticalAlignment(SwingConstants.TOP);
		btnBaoCun.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(btnBaoCun);
		
		btnNewButton = new JButton("all");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击显示所有
				List<Emp> list = new EmpDao().getEmps(start, number);
				tableModel.setList(list);
				//		通知table更新一下界面,此方法会使table重写去找model要数据
				table.updateUI();
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(btnNewButton);
		btnBaoCun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				将用户的更改都同步到数据库
				
				tableModel.save();
				table.updateUI();
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				给table增加一行
				tableModel.addRow();
				table.updateUI();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				获取用户选中的行
				int index = table.getSelectedRow();
				tableModel.delete(index);
				table.updateUI();
			}
		});
		
//		页面加载以后，先判断一次按钮的状态，防止用户直接点击上一页
		updateButtons();
		
		
	}
	
	protected void updateButtons() {
//		判断四个按钮是否可以点击
//		当前页是首页，first，previous禁用
//		当前页不是首页，first，previous恢复
//		当前是尾页，last，next禁用
//		当前不是尾页，last，next恢复
		
		if(start == 0){
//			禁用首页按钮
			btnFirst.setEnabled(false);
			btnPrevious.setEnabled(false);
		}else{
			btnFirst.setEnabled(true);
			btnPrevious.setEnabled(true);
		}
		
		if(start == last()){
			btnNext.setEnabled(false);
			btnLast.setEnabled(false);
		}else{
			btnNext.setEnabled(true);
			btnLast.setEnabled(true);
		}
		
		
	}
	
	

	//判断最后一页的起始值
	private int last() {
	//		判断最后一页的起始值
	//		total - number   
	//	整除的情况	    20条数据    5条   最后页面 索引从15开始 
	//	无法整除的情况	17条数据    5条  最后页面  索引从15开始 
		int last = 0;
		int total = new EmpDao().getCount();
		if(total % number == 0){
			last = total - number;
		}else{
			last = total - total % number;
		}
		return last;
	}

	private void updateTable() {
		
		List<Emp> list = new EmpDao().getEmps(start, number);
		
		tableModel.setList(list);
		
//		通知table更新一下界面,此方法会使table重写去找model要数据
		table.updateUI();
	}
	
	

}
