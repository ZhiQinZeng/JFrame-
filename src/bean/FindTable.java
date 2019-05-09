package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.EmpDao;
import view.MainView;

public class FindTable extends AbstractTableModel {
	//表头
	private String[] titles = {"员工姓名","职务","入职时间","薪资"};
	private List<Emp> list ;
	
	
	public String[] getTitles() {
		return titles;
	}

	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	public List<Emp> getList() {
		return list;
	}

	public void setList(List<Emp> list) {
		this.list = list;
	}

	
	
	
//	重写生成列标题的方法
	@Override
	public String getColumnName(int column) {
		
		return titles[column];
	}
	
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return titles.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Emp emp = list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			System.out.println("get到了个屁"+emp.getEname());
			return emp.getEname();
		case 1:
			return emp.getJob();
		case 2:
			return emp.getHireDate();
		case 3:
			return emp.getSal();			
		}
		return null;
	}
	
	
//	当单元格被编辑时，用户输入的数据会传递到此方法
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		//用户在页面上做的更改，默认不会保留，只有将用户的更改作用到list上
		//界面才会变化
		//根据传入行索引，找到list中对应的Emp对象
		Emp emp = list.get(rowIndex);
		
//		通过判断列，来绝对更改对象哪个属性
		switch (columnIndex) {
		case 0:
			emp.setEname((String) aValue);
			System.out.println("设置了"+aValue);
			break;
		case 1:
			emp.setJob((String)aValue);
			break;
		case 2:
//			此处传递过来的日期是字符串类型，无法强制转换为日期
//			字符串 --》 日期   '1999-02-03' -> Date对象
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = simpleDateFormat.parse((String) aValue);
				emp.setHireDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			float sal = Float.parseFloat((String) aValue);
			emp.setSal(sal);	
			break;
		}
		
		/*
		 * 	两个问题
		 * 		用户如果更改了新增的数据，会调用此方法
		 * 		用户如果更改了已有的数据，会调用此方法
		 * 	1、更新数据库的时候，需要判断是插入还是更新
		 *  2、 当用户更改了一个单元格的内容，此方法就会调用，
		 *     此方法调用的过于频繁，不应该在此处直接访问数据库
		 * 
		 */
//		用户如果更新了某条记录的值，我们就把对应的emp对象装入到临时的list中
		
		
	}
	
	
	public void loadList(List<Emp>emps){
		this.list=MainView.getYesfind();
	}

}
