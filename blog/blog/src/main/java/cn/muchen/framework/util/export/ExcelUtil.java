package cn.muchen.framework.util.export;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName:：ExcelUtil @Description： excel操作工具类
 * @author ：柯雷
 * @date ：2018年11月16日 下午3:35:40
 *
 */
public class ExcelUtil {

	/** 日志打印对象 */
	static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	/**
	 * 导出Excel
	 * 
	 * @param sheetName sheet名称
	 * @param title     标题
	 * @param values    内容
	 * @param wb        HSSFWorkbook对象
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbook(String sheetName, String headTitle, String[] title, String[][] values,
			HSSFWorkbook workbook) {

		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if (workbook == null) {
			workbook = new HSSFWorkbook();
		}

		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = workbook.createSheet(sheetName); // 创建工作表

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		HSSFRow rowm = sheet.createRow(0);
		HSSFCell cellTiltle = rowm.createCell(0);

		// sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面 - 可扩展】
		HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook); // 获取列头样式对象
		HSSFCellStyle style = getStyle(workbook); // 单元格样式对象

		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (title.length - 1)));
		cellTiltle.setCellStyle(columnTopStyle);
		cellTiltle.setCellValue(headTitle);

		// 定义所需列数
		int columnNum = title.length;
		HSSFRow rowRowName = sheet.createRow(2); // 在索引2的位置创建行(最顶端的行开始的第二行)

		// 将列头设置到sheet的单元格中
		for (int n = 0; n < columnNum; n++) {
			HSSFCell cellRowName = rowRowName.createCell(n); // 创建列头对应个数的单元格
			cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置列头单元格的数据类型
			HSSFRichTextString text = new HSSFRichTextString(title[n]);
			cellRowName.setCellValue(text); // 设置列头单元格的值
			cellRowName.setCellStyle(columnTopStyle); // 设置列头单元格样式
		}

		// 将查询出的数据设置到sheet对应的单元格中
		for (int i = 0; i < values.length; i++) {

			Object[] obj = values[i];// 遍历每个对象
			HSSFRow row = sheet.createRow(i + 3);// 创建所需的行数

			for (int j = 0; j < obj.length; j++) {
				HSSFCell cell = null; // 设置单元格的数据类型
				if (j == 0) {
					cell = row.createCell(j, HSSFCell.CELL_TYPE_NUMERIC);
					cell.setCellValue(i + 1);
				} else {
					cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
					if (!"".equals(obj[j]) && obj[j] != null) {
						cell.setCellValue(obj[j].toString()); // 设置单元格的值
					}
				}
				cell.setCellStyle(style); // 设置单元格样式
			}
		}
		// 让列宽随着导出的列长自动适应
		for (int colNum = 0; colNum < columnNum; colNum++) {
			int columnWidth = sheet.getColumnWidth(colNum) / 256;
			for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
				HSSFRow currentRow;
				// 当前行未被使用过
				if (sheet.getRow(rowNum) == null) {
					currentRow = sheet.createRow(rowNum);
				} else {
					currentRow = sheet.getRow(rowNum);
				}
				if (currentRow.getCell(colNum) != null) {
					HSSFCell currentCell = currentRow.getCell(colNum);
					if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						int length = currentCell.getStringCellValue().getBytes().length;
						if (columnWidth < length) {
							columnWidth = length;
						}
					}
				}
			}
			if (colNum == 0) {
				sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
			} else {
				sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
			}
		}
		return workbook;
	}

	/*
	 * 列头单元格样式
	 */
	public static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

		// 设置字体
		HSSFFont font = workbook.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 11);
		// 字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("Courier New");
		// 设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		return style;

	}

	/*
	 * 列数据信息单元格样式
	 */
	public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
		// 设置字体
		HSSFFont font = workbook.createFont();
		// 设置字体大小
		// font.setFontHeightInPoints((short)10);
		// 字体加粗
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("Courier New");
		// 设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;
	}
}
