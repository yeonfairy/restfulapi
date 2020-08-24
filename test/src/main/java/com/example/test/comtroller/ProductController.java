package com.example.test.comtroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test.dto.ProductDTO;
import com.example.test.service.ProductService;


@Controller
public class ProductController {
	@Autowired
	private ProductService bservice;

	@RequestMapping(value = "restfulapi.do")
	@ResponseBody
	public JSONObject vue_restApi() {
		List<ProductDTO> list = bservice.getSelectMember();
		JSONObject obj = new JSONObject(); // JSONObject에 배열을 넣고싶어 생성

		try {
			JSONArray jArray = new JSONArray();// JsonArray에 넣는다.
			for (int i = 0; i < list.size(); i++)// 배열 개수 대로 반복문.
			{
				JSONObject vo = new JSONObject();// JSONObject에 데이터를 다시 넣는다.
				list.get(i).setNo(i + 1);
				vo.put("no", list.get(i).getNo());
				vo.put("id", list.get(i).getId());
				vo.put("productName", list.get(i).getProductName());
				vo.put("amount", list.get(i).getAmount());
				vo.put("price", list.get(i).getPrice());
				if (list.get(i).getRemark().equals("null")) {
					vo.put("remark", "-");
				} else {
					vo.put("remark", list.get(i).getRemark());
				}

				jArray.add(vo); // JSONObject를 여러개 갖고 싶어 JSONArray 안에 다시 넣는다
			}

			obj.put("item", jArray);// 배열을 넣음

			System.out.println(obj.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return obj;
	}

	@RequestMapping(value = "insert.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void restApi_post(ProductDTO vo) {
		if (vo.getRemark() == null) {
			vo.setRemark("-");
		}
		bservice.insertMember(vo);
		System.out.println(vo.getProductName());
		System.out.println(vo.getAmount());
		System.out.println(vo.getPrice());
		System.out.println(vo.getRemark());
	}

	@RequestMapping(value = "list.do")
	public String select(HttpServletResponse response, Model model, HttpServletRequest request) {
		List<ProductDTO> list = bservice.getSelectMember();

		model.addAttribute("list", list);

		return "list";

	}
	
	// 엑셀 파일 다운로드
	@RequestMapping(value = "excelDown.do")
	public void excelDown(HttpServletResponse response) throws Exception {
		List<ProductDTO> list = bservice.getSelectMember();

		// 워크북 생성
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("Inventory시스템");
		Row row = null;
		Cell cell = null;
		int rowNo = 0;
		// 테이블 헤더용 스타일
		CellStyle headStyle = wb.createCellStyle();
		// 가는 경계선을 가집니다.
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		// 배경색은 노란색입니다.
		headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// 데이터는 가운데 정렬합니다.
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		// 데이터용 경계 스타일 테두리만 지정
		CellStyle bodyStyle = wb.createCellStyle();
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);
		// 헤더 생성
		row = sheet.createRow(rowNo++);
		cell = row.createCell(0);
		cell.setCellStyle(headStyle);
		cell.setCellValue("순번");
		cell = row.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("제품명");
		cell = row.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("수량");
		cell = row.createCell(3);
		cell.setCellStyle(headStyle);
		cell.setCellValue("금액");
		cell = row.createCell(4);
		cell.setCellStyle(headStyle);
		cell.setCellValue("비고");

		// 데이터 부분 생성
		for (ProductDTO vo : list) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getId());
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getProductName());
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getAmount());
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getPrice());
			cell = row.createCell(4);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getRemark());
		}
		// 컨텐츠 타입과 파일명 지정
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=ExcelFormDown.xls");
		// 엑셀 출력
		wb.write(response.getOutputStream());
		wb.close();
	}
}
