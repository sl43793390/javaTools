package com.rquest.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.rquest.bootdemo.riskmaster.entity.ReportHeader;
import com.rquest.bootdemo.riskmaster.enums.DataTypeEnums;
import com.rquest.bootdemo.riskmaster.enums.FrozenGridEnums;
import com.rquest.bootdemo.riskmaster.enums.MarketDataEnums;
import com.rquest.bootdemo.riskmaster.enums.WeeklyConstantEnums;
import com.rquest.bootdemo.riskmaster.util.Util;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Table;
import com.vaadin.ui.TreeTable;

/**
 * @author Administrator How to use this class 
 * 1.setHeader()
 * 2.setTableContent()
 * 3.initLayout()
 * 4.initContent()
 */

public class RquestTreeTable extends TreeTable{

	private static final long serialVersionUID = 262524689303658676L;
	/**
	 * 报表冻结列及单层多层横向表头通用组件；
	 */

	private List<ReportHeader> tableHeaders;
	private List<Map<String, Object>> tableContents;
	private List<ReportHeader> lowestHeaders; // lowest level header
	private Object itemId;

	// Constructor
	public RquestTreeTable() {

	}
	

	public List<ReportHeader> getTableHeaders() {
		return tableHeaders;
	}


	public void setTableHeaders(List<ReportHeader> tableHeaders) {
		this.tableHeaders = tableHeaders;
	}


	public List<Map<String, Object>> getTableContents() {
		return tableContents;
	}


	public void setTableContents(List<Map<String, Object>> tableContents) {
		this.tableContents = tableContents;
	}


	/**
	 * Function Name : initLayoutTable Description : 当点击左侧菜单时调用加载报表Layout；
	 * 
	 * @author : Administrator
	 */
	public void initLayout() {
		initContentTableHeader();
		this.setSizeFull();
		this.setEnabled(true);
	}

	// Assume setTableHeader() and setTableConents() has already been done
	public void initContent() {
		initContentTableRecords();
	}

	/**
	 * Function Name : initContentTableHeader Description : 当点击左侧菜单时调用加载报表表头信息；
	 * 
	 * @author : Administrator
	 */
	private void initContentTableHeader() {
		this.lowestHeaders = getHeadersByLevel(WeeklyConstantEnums.WEEKLY_TABLELEVELONE);
		setTreeTableHeader();
	}

	/**
	 * Function Name : initContentTableRecords Description : 当点击左侧菜单时调用加载报表内容信息；
	 * 
	 * @author : Administrator
	 */
	private void initContentTableRecords() {
		try {
			setTreeTableRecords();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Function Name : setthisHeader Description : 此方法了用于给 单层表头的报表添加表头；
	 * 
	 * @author : Administrator
	 */
	private void setTreeTableHeader() {

		// 设置表头名称，字符型数据左对齐，数值型数据右对齐；
//		int iwidth = 120;// 1300/tableheaders.length;
		for (int i = 0; i < this.lowestHeaders.size(); i++) {
			if (this.lowestHeaders.get(i).getFlagDisplay().equals("Y")) {
				if (this.lowestHeaders.get(i).getCdDataType().equals(DataTypeEnums.STRING)) {
					this.addContainerProperty(this.lowestHeaders.get(i).getIdHeader(), String.class, null);
					this.setColumnAlignment(this.lowestHeaders.get(i).getIdHeader(), Table.Align.CENTER);
				} else if (this.lowestHeaders.get(i).getCdDataType().equals(DataTypeEnums.NUMBER)) {
					this.addContainerProperty(this.lowestHeaders.get(i).getIdHeader(), String.class, null);
					this.setColumnAlignment(this.lowestHeaders.get(i).getIdHeader(), Table.Align.RIGHT);
				} else if (this.lowestHeaders.get(i).getCdDataType().equals(DataTypeEnums.CHECKBOX)) { // Checkbox
					this.addContainerProperty(this.lowestHeaders.get(i).getIdHeader(), CheckBox.class, null);
					this.setColumnAlignment(this.lowestHeaders.get(i).getIdHeader(), Table.Align.RIGHT);
				} else if (this.lowestHeaders.get(i).getCdDataType().equals(DataTypeEnums.BUTTON)) { // Button
					this.addContainerProperty(this.lowestHeaders.get(i).getIdHeader(), Button.class, null);
					this.setColumnAlignment(this.lowestHeaders.get(i).getIdHeader(), Table.Align.RIGHT);
				} else {
					this.addContainerProperty(this.lowestHeaders.get(i).getIdHeader(), String.class, null);
					this.setColumnAlignment(this.lowestHeaders.get(i).getIdHeader(), Table.Align.RIGHT);
				}
				if (i == 0) {
					this.setColumnAlignment(this.lowestHeaders.get(i).getIdHeader(), Table.Align.LEFT);
				}
				this.setColumnHeader(this.lowestHeaders.get(i).getIdHeader(), this.lowestHeaders.get(i).getNameHeader());
//				if (i == 0) {
//					this.setColumnWidth(this.lowestHeaders.get(i).getIdHeader(), iwidth * 2);
//				} else {
//					this.setColumnWidth(this.lowestHeaders.get(i).getIdHeader(), iwidth);
//				}
			}
		}
	}

	/**
	 * Function Name : setthisRecords Description : 此方法了用于给树形table加载数据；
	 * 
	 * @author : Administrator
	 */
	private void setTreeTableRecords() throws SQLException {
		this.removeAllItems();
		// 读取要查询的日期；
		if (tableContents.size() > 0) {
			int i = 0;
			String itemID;
			String itemPID;
			NumberFormat nf = NumberFormat.getInstance(Locale.CHINA);
			NumberFormat nf2 = NumberFormat.getPercentInstance(Locale.CHINA);
			nf2.setMinimumFractionDigits(2);
			int iSize = this.getColumnHeaders().length;// 报表实际列数

			do {
				Object[] obj = new Object[iSize];// object长度为报表实际列数
				HashMap map = (HashMap) tableContents.get(i);
				// 根据报表表头设定的列，读取相应的数据
				int k = 0;// 报表实际列数与tableheaders的总列数可能不一样多,所以用另一变量k来计数
				for (int j = 0; j < lowestHeaders.size(); j++) {
					if (lowestHeaders.get(j).getFlagDisplay().equals("Y")) {
						if (map.get(lowestHeaders.get(j).getIdHeader()) != null)
							if (lowestHeaders.get(j).getCdDataType().equals(DataTypeEnums.NUMBER)) {
								if (!map.get(lowestHeaders.get(j).getIdHeader()).equals("-")) {
									String bigDecimal = new BigDecimal(Double.parseDouble(map.get(lowestHeaders.get(j).getIdHeader()).toString()))
											.toPlainString();
									obj[k] = Util.NumberFormatSeparate(Double.parseDouble(bigDecimal), 0);
									// obj[k]=nf.format(Double.parseDouble(data));
								} else {
									obj[k] = map.get(lowestHeaders.get(j).getIdHeader()).toString();
								}
							} else if (lowestHeaders.get(j).getCdDataType().equals(DataTypeEnums.NUMBER_2)) {
								if (!map.get(lowestHeaders.get(j).getIdHeader()).equals("-")) {
									String bigDecimal = new BigDecimal(Double.parseDouble(map.get(lowestHeaders.get(j).getIdHeader()).toString()))
											.toPlainString();
									obj[k] = Util.NumberFormatSeparate(Double.parseDouble(bigDecimal), 2);
									// obj[k]=nf.format(Double.parseDouble(data));
								} else {
									obj[k] = map.get(lowestHeaders.get(j).getIdHeader()).toString();
								}
							} else if (lowestHeaders.get(j).getCdDataType().equals(DataTypeEnums.NUMBER_4)) {
								if (!map.get(lowestHeaders.get(j).getIdHeader()).equals("-")) {
									String bigDecimal = new BigDecimal(Double.parseDouble(map.get(lowestHeaders.get(j).getIdHeader()).toString()))
											.toPlainString();
									obj[k] = Util.NumberFormatSeparate(Double.parseDouble(bigDecimal), 4);
									// obj[k]=nf.format(Double.parseDouble(data));
								} else {
									obj[k] = map.get(lowestHeaders.get(j).getIdHeader()).toString();
								}
							} else if (lowestHeaders.get(j).getCdDataType().equals(DataTypeEnums.NUMBER_6)) {
								if (!map.get(lowestHeaders.get(j).getIdHeader()).equals("-")) {
									String bigDecimal = new BigDecimal(Double.parseDouble(map.get(lowestHeaders.get(j).getIdHeader()).toString()))
											.toPlainString();
									obj[k] = Util.NumberFormatSeparate(Double.parseDouble(bigDecimal), 6);
									// obj[k]=nf.format(Double.parseDouble(data));
								} else {
									obj[k] = map.get(lowestHeaders.get(j).getIdHeader()).toString();
								}
							} else if (lowestHeaders.get(j).getCdDataType().equals(DataTypeEnums.PERCENT)) {
								String bigDecimal = new BigDecimal(String.valueOf(map.get(lowestHeaders.get(j).getIdHeader()))).toPlainString();
								obj[k] = Util.NumberFormatSeparate(Double.parseDouble(bigDecimal) * 100, 0);
							} else if (lowestHeaders.get(j).getCdDataType().equals(DataTypeEnums.PERCENT_2)) {
								String bigDecimal = new BigDecimal(String.valueOf(map.get(lowestHeaders.get(j).getIdHeader()))).toPlainString();
								obj[k] = Util.NumberFormatSeparate(Double.parseDouble(bigDecimal) * 100, 2);
							} else if (lowestHeaders.get(j).getCdDataType().equals(DataTypeEnums.STRING)) {
								obj[k] = map.get(lowestHeaders.get(j).getIdHeader()).toString();
							} else {
								// 如果数据类型有匹配数字的不保留小数
								if (map.get(lowestHeaders.get(j).getIdHeader()).toString().matches("^-?\\d+(\\.\\d{1,6})?$")) {
									String bigDecimal = new BigDecimal(Double.parseDouble(map.get(lowestHeaders.get(j).getIdHeader()).toString()))
											.toPlainString();
									obj[k] = Util.NumberFormatSeparate(Double.parseDouble(bigDecimal), 0);
								} else {

									obj[k] = map.get(lowestHeaders.get(j).getIdHeader()).toString();
								}
							}
						else {
							obj[k] = "";
						}
						k = k + 1;
					}
				}
				// 如果rpt表中只有id_instrument ,而没有id_parent 和id_portfolio 时
				if (map.containsKey(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_INSTRUMENT)
						&& !map.containsKey(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_PARENT)
						&& !map.containsKey(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_PORTFOLIO)) {
					itemID = map.get(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_INSTRUMENT).toString();
					// 如果有id_instrument ,有id_parent 没有id_portfolio 时
				} else if (map.containsKey(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_INSTRUMENT)
						&& !map.containsKey(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_PORTFOLIO)) {
					itemID = map.get(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_INSTRUMENT).toString();
					// 如果有id_instrument ,有id_parent 和id_portfolio 时
				} else if (map.containsKey(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_INSTRUMENT)) {
					if (map.get(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_PORTFOLIO).toString().equalsIgnoreCase("none")) {
						itemID = map.get(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_PARENT).toString() + "_"
								+ map.get(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_INSTRUMENT).toString();
					} else {
						itemID = map.get(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_PORTFOLIO).toString();
					}
					// 如果没有id_instrument 时，有id_portfolio使用id_portfolio
				} else if (!map.containsKey(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_INSTRUMENT)
						&& map.containsKey(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_PORTFOLIO)) {
					itemID = map.get(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_PORTFOLIO).toString();
				} else if (map.containsKey(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ITEMS)) {
					itemID = map.get(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ITEMS).toString();
//					以下这个else if 专门用于生成对手组织架构而写的，如果有发现影响到某个grid的正常加载请及时反馈-sunlong；
				} else  if (map.get(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_PARENT)!=null
						&&map.get(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_INSTRUMENT)==null
						&&map.get(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_PORTFOLIO)==null) {
					itemID = map.get(MarketDataEnums.COUNTERPARTYID).toString();
				}else{
					itemID = String.valueOf(i);
				}

				if (map.containsKey(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_PARENT)) {
					if (map != null && map.get(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_PARENT) != null) {
						itemPID = map.get(lowestHeaders.get(0).getIdReport() + FrozenGridEnums.ID_PARENT).toString();
						this.addItem(obj, itemID);
						this.setChildrenAllowed(itemID, false);
						// 如果有父节点
						if (!itemPID.equals("0")) {
							this.setChildrenAllowed(itemPID, true);
							this.setParent(itemID, itemPID);
							this.setCollapsed(itemPID, false);
						}
						i++;
					} else {
						this.addItem(obj, itemID);
						i++;
					}
				} else {
					this.addItem(obj, itemID);
					i++;
				}
			} while (i < tableContents.size());
		}
	}

	public List<ReportHeader> getHeadersByLevel(Integer level) {
		List<ReportHeader> reportHeaders = new ArrayList<ReportHeader>();
		for (ReportHeader reportHeader : tableHeaders) {
			if (reportHeader.getNbrLevel() == level) {
				reportHeaders.add(reportHeader);
			}
		}
		return reportHeaders;
	}

	public String getItemId() {
		if (itemId == null)
			return null;
		else
			return itemId.toString();
	}

	public void setItemId(Object itemId) {
		this.itemId = itemId;
	}

}
