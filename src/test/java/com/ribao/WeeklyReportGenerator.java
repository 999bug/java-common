package com.ribao;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeeklyReportGenerator {
    public static void main(String[] args) {
        // 定义日期范围
        LocalDate startDate = LocalDate.of(2024, 10, 7);
        LocalDate endDate = LocalDate.of(2025, 12, 8);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy年M月d日");

        // 定义每周的结束日期
        LocalDate currentStart = startDate;

        while (!currentStart.isAfter(endDate)) {
            // 计算当前周的结束日期
            LocalDate currentEnd = currentStart.plusDays(4); // 周一到周五

            // 创建 Excel 文件
            String reportName = String.format("李石岩-日报%s-%s.xlsx",
                    currentStart.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                    currentEnd.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));

            try (Workbook workbook = new XSSFWorkbook()) {
                for (int i = 0; i < 5; i++) {
                    // 为每一天创建一个工作表
                    LocalDate currentDay = currentStart.plusDays(i);
                    String sheetName = currentDay.format(DateTimeFormatter.ofPattern("yyyy-M-d"));

                    Sheet sheet = workbook.createSheet(sheetName);
                    CellStyle headerStyle = workbook.createCellStyle();
                    Font headerFont = workbook.createFont();
                    headerFont.setBold(true); // 设置加粗
                    headerFont.setFontHeightInPoints((short) 12); // 设置字体大小
                    headerStyle.setFont(headerFont);
                    headerStyle.setAlignment(HorizontalAlignment.CENTER); // 设置居中
                    headerStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直居中
                    headerStyle.setBorderTop(BorderStyle.THIN);
                    headerStyle.setBorderBottom(BorderStyle.THIN);
                    headerStyle.setBorderLeft(BorderStyle.THIN);
                    headerStyle.setBorderRight(BorderStyle.THIN);

                    CellStyle headerStyle1 = workbook.createCellStyle();
                    headerStyle1.setBorderTop(BorderStyle.THIN);
                    headerStyle1.setBorderBottom(BorderStyle.THIN);
                    headerStyle1.setBorderLeft(BorderStyle.THIN);
                    headerStyle1.setBorderRight(BorderStyle.THIN);

                    // 设置表头
                    Row headerRow = sheet.createRow(0);
                    Cell cell = headerRow.createCell(0);
                    cell.setCellValue("本日工作内容");
                    headerRow.createCell(1).setCellValue("");
                    Cell cell5 = headerRow.createCell(2);
                    cell5.setCellValue(currentDay.format(dateFormatter));
                    headerRow.createCell(3).setCellValue("");
                    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
                    sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
                    // 设置第二行内容
                    Row progressRow = sheet.createRow(1);
                    Cell cell4 = progressRow.createCell(0);
                    cell4.setCellValue("今日进展");
                    Cell cell1 = progressRow.createCell(1);
                    cell1.setCellValue("工作内容");
                    Cell cell2 = progressRow.createCell(2);
                    cell2.setCellValue("完成情况");
                    Cell cell3 = progressRow.createCell(3);
                    cell3.setCellValue("备注");

                    cell.setCellStyle(headerStyle);
                    cell1.setCellStyle(headerStyle);
                    cell2.setCellStyle(headerStyle);
                    cell3.setCellStyle(headerStyle);
                    cell4.setCellStyle(headerStyle);
                    cell5.setCellStyle(headerStyle);

                    // 添加空行
                    for (int j = 2; j <= 10; j++) {
                        Row emptyRow = sheet.createRow(j);
                        for (int k = 0; k < 4; k++) {
                            Cell cell6 = emptyRow.createCell(k);
                            cell6.setCellValue("");
                            cell6.setCellStyle(headerStyle1);// 创建空单元格
                        }
                    }

                    // 设置“明日计划”行
                    Row planRow = sheet.createRow(11);
                    Cell cell6 = planRow.createCell(0);
                    cell6.setCellValue("明日计划");
                    Cell cell8 = planRow.createCell(1);
                    cell8.setCellValue("");
                    Cell cell9 = planRow.createCell(2);
                    cell9.setCellValue("");
                    Cell cell10 = planRow.createCell(3);
                    cell10.setCellValue("");

                    cell6.setCellStyle(headerStyle);

                    cell8.setCellStyle(headerStyle1);
                    cell9.setCellStyle(headerStyle1);
                    cell10.setCellStyle(headerStyle1);
                    // 添加空行
                    for (int j = 12; j <= 17; j++) {
                        Row emptyRow = sheet.createRow(j);
                        for (int k = 0; k < 4; k++) {
                            Cell cell7 = emptyRow.createCell(k);
                            cell7.setCellValue(""); // 创建空单元格
                            cell7.setCellStyle(headerStyle1);
                        }
                    }

                    // 合并单元格：从第2行第1列到第17行第1列

                    sheet.addMergedRegion(new CellRangeAddress(1, 10, 0, 0));
                    sheet.addMergedRegion(new CellRangeAddress(11, 17, 0, 0));

                    sheet.setColumnWidth(0, 2500); // 设置列宽
                    sheet.setColumnWidth(1, 15000); // 设置列宽
                    sheet.setColumnWidth(2, 12500); // 设置列宽
                    sheet.setColumnWidth(3, 2500); // 设置列宽
                }

                // 将工作簿写入文件
                try (FileOutputStream fileOut = new FileOutputStream(reportName)) {
                    workbook.write(fileOut);
                }

                System.out.println("生成日报: " + reportName);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 更新当前周的开始日期
            currentStart = currentStart.plusWeeks(1);
        }
    }
}
