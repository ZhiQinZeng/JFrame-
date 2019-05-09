package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.EmpDao;

public class EmpTableModel extends AbstractTableModel{
//	private String[][] data = {
//			{"张三","22","男"},
//			{"张三","22","男"},
//			{"张三","22","男"}
//	};
	private int number = 5;
//	//表头
	private String[] titles = {"员工姓名","职务","入职时间","薪资"};
	private List<Emp> list = new EmpDao().getEmps(0,number);
	
//	临时的集合，存储用户更改的信息
	private List<Emp> tempList = new ArrayList<>();
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

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

	@Override
	public String toString() {
		return "EmpTableModel [number=" + number + ", titles="
				+ Arrays.toString(titles) + ", list=" + list + "]";
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
	
//	重写生成列标题的方法
	@Override
	public String getColumnName(int column) {
		return titles[column];
	}
	
//	重写单元格是否可以编辑的方法
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return true;
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
		//this.fireTableCellUpdated(rowIndex, columnIndex);  
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
		if(!tempList.contains(emp)){
			tempList.add(emp);
		}
		
	}

	//此方法会调用多次，每次传入一个行索引和列索引，索引从0开始
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Emp emp = list.get(rowIndex);
		switch (columnIndex) {
		case 0:
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
	
//	添加一行
	public void addRow() {
		Emp emp = new Emp();
//		为了区分哪些员工是新添加的，将员工的员工号临时改为-1
		emp.setEmpno(-1);
		list.add(emp);
	}

	public void save() {
		for (Emp emp : tempList) {
//			需要判断是要做添加还是做更新
			if(emp.getEmpno() == -1){
				//添加
				new EmpDao().add(emp);
			}else{
				//更新
				new EmpDao().update(emp);
			}
		}
//		清空临时表，保证数据不会被重复添加
		tempList.clear();
	}
	
	//删除
	public void delete(int index) {
		//获取到对应的员工对象
		Emp emp = list.get(index);
		list.remove(index);
		//调用dao的方法删除数据
		new EmpDao().delete(emp.getEmpno());
	}
	
	
	//查询
	public List<Emp> find(String findname){
		List<Emp> yesfind=new EmpDao().search(findname);
		return yesfind;
	}

	//查找部门
	public List<Emp> bumenfind(){
		List<Emp> bumenfind=new EmpDao().bumenfind();
		return bumenfind;
	}
	
	//查找所选部门的各项参数
	public List<Emp>bumenvalue(String bumenvalue){
		List<Emp>bumenvalues=new EmpDao().bumenvalue(bumenvalue);
		return bumenvalues;
	}

}
