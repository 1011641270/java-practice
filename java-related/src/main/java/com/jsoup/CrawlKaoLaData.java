/**
 * @Title CrawlKaoLaData.java
 * @Project Java
 * @Package com.jsoup
 * @author hztianduoduo
 * @Date 2016年10月9日
 * @version V1.0
 *
 * Copyright 2016 Netease, Inc. All rights reserved.
 */
package com.jsoup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.file.PoiUtil;
import com.file.PoiUtil.SpreadsheetWriter;

/**
 * @author hztianduoduo
 *
 */
public class CrawlKaoLaData {

	private static final String base_url = "http://www.kaola.com";
	private static final String pinpai_base_url = "http://www.kaola.com/pinpai.html";

	public static void main(String[] args) throws Exception {

		List<CateGoryItem> list = new CrawlKaoLaData().getCateGoryItemList();

		for (int i = 1; i < list.size(); i++) {

			String pinpaiUrl = base_url + list.get(i).getCateGoryUrl();
			String cateGoryName = list.get(i).getCateGoryName();
		
			List<BrandInfo> brandInfosList = new ArrayList<>();
			List<BrandItem> brandItemsList = new CrawlKaoLaData().getBrandItemList(pinpaiUrl);

			for (BrandItem brandItem : brandItemsList) {

				String brandName = brandItem.getBrandName();
				String brandUrl = base_url + brandItem.getBrandUrl();

				BrandInfo brandInfo = new CrawlKaoLaData().getBrandInfo(brandUrl, brandName, cateGoryName);

				brandInfosList.add(brandInfo);

			}

			new CrawlKaoLaData().createTxt(brandInfosList, cateGoryName);

		}

	}

	private void createTxt(List<BrandInfo> brandInfosList, String cateGoryName) throws Exception {

		String fileName = "D:\\" + cateGoryName + ".txt";

		FileOutputStream fos = new FileOutputStream(new File(fileName));
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		for (BrandInfo brandInfo : brandInfosList) {
			bw.write(brandInfo.getBrandCountry() + "&&&" + brandInfo.getBrandInfoLogo() + "&&&"
					+ brandInfo.getBrandInfoName() + "&&&" + brandInfo.getCateGoryName() + "&&&"
					+ brandInfo.getBrandInfoText() + "\t\n");
		}

		bw.close();
		osw.close();
		fos.close();

	}

	private void creatExcel(List<BrandInfo> brandInfosList, String cateGoryName) throws Exception {

		String fileName = cateGoryName;

		String tempFilePath = "";
		String distinctionFilePath = "";

		tempFilePath = "D:\\temp.xls";
		distinctionFilePath = "D:\\";

		FileOutputStream os = new FileOutputStream(tempFilePath); // 存放临时文件
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("品牌");
		wb.write(os);
		os.close();

		Map<String, XSSFCellStyle> styles = PoiUtil.createStyles(wb);
		String sheetRef = sheet.getPackagePart().getPartName().getName();

		// 生成xml数据临时文件o
		File tmp = File.createTempFile("sheet", ".xml");
		Writer fw = new OutputStreamWriter(new FileOutputStream(tmp), "UTF-8");

		// 创建表格
		SpreadsheetWriter sw = new SpreadsheetWriter(fw);
		sw.beginSheet();

		sw.insertRow(0);
		int styleIndex = styles.get("header").getIndex();
		sw.createCell(0, "国家", styleIndex);
		sw.createCell(1, "LOGO", styleIndex);
		sw.createCell(2, "品牌名称", styleIndex);
		sw.createCell(3, "类目", styleIndex);
		sw.createCell(4, "品牌描述", styleIndex);
		sw.endRow();

		// 每次都读取数据库里的新一页数据
		for (int rownum = 0; rownum < brandInfosList.size(); rownum++) {
			
			System.err.println(rownum+1);
			
			sw.insertRow(rownum + 1);
			sw.createCell(0, brandInfosList.get(rownum).getBrandCountry());
			sw.createCell(1, brandInfosList.get(rownum).getBrandInfoLogo());
			sw.createCell(2, brandInfosList.get(rownum).getBrandInfoName());
			sw.createCell(3, brandInfosList.get(rownum).getCateGoryName());
			sw.createCell(4, brandInfosList.get(rownum).getBrandInfoText());
			sw.endRow();
		}
		sw.endSheet();
		fw.close();

		// 创建ZIP输出流，将xml数据临时文件数据写入到ZIP文件中。
		FileOutputStream out = new FileOutputStream(distinctionFilePath + fileName + ".xlsx");
		PoiUtil.substitute(new File(tempFilePath), tmp, sheetRef.substring(1), out);
		out.close();

	}

	private List<CateGoryItem> getCateGoryItemList() throws IOException {

		Document document = getDocument(pinpai_base_url);
		Elements elements = document.getElementsByClass("m-category-tab");
		Elements elementsResult = elements.select("ul.m-ct-ul>li");

		List<CateGoryItem> list = new ArrayList<>();

		for (Element element : elementsResult) {

			CateGoryItem cateGoryItem = new CateGoryItem();

			String cateGoryName = element.select("a[href]").select("span").text().toString();
			String cateGoryUrl = element.select("a[href]").attr("href").toString();

			cateGoryItem.setCateGoryName(cateGoryName);
			cateGoryItem.setCateGoryUrl(cateGoryUrl);

			list.add(cateGoryItem);

		}

		return list;
	}

	private List<BrandItem> getBrandItemList(String pinpaiUrl) throws Exception {

		List<BrandItem> list = new ArrayList<>();

		Document document = getDocument(pinpaiUrl);
		Elements elements = document.getElementsByClass("m-brands-list");
		Elements elementsResult = elements.select("ul.m-bl-ul>li");

		for (Element element : elementsResult) {

			BrandItem brandItem = new BrandItem();

			String brandName = element.select("a[href]").select("span").text().toString();
			String brandUrl = element.select("a[href]").attr("href").toString();

			brandItem.setBrandName(brandName);
			brandItem.setBrandUrl(brandUrl);

			list.add(brandItem);
		}

		return list;
	}

	private BrandInfo getBrandInfo(String brandUrl, String brandName, String cateGoryName) throws Exception {

		Document document = getDocument(brandUrl);
		BrandInfo brandInfo = new BrandInfo();
		brandInfo.setCateGoryName(cateGoryName);

		Elements elements_from = document.getElementsByClass("from");

		for (Element element : elements_from) {
			brandInfo.setBrandCountry(element.select("span").text().toString());
		}

		Elements elements_intro = document.getElementsByClass("intro");

		for (Element element : elements_intro) {
			brandInfo.setBrandInfoText(element.select("p").text());
		}

		Elements elements_info = document.getElementsByClass("info");

		for (Element element : elements_info) {
			brandInfo.setBrandInfoLogo(element.select("img[src]").attr("src").toString());
		}

		brandInfo.setBrandInfoName(brandName);
		return brandInfo;
	}

	private Document getDocument(String url) throws IOException {
		return org.jsoup.Jsoup.connect(url).get();
	}

	private class BrandInfo {

		private String cateGoryName; // 类目名称
		private String brandInfoName; // 品牌名称
		private String brandInfoLogo; // 品牌LOGO [文件，保存链接]
		private String brandCountry; // 品牌国家
		private String brandInfoText; // 品牌介绍

		public BrandInfo() {

		}

		public String getCateGoryName() {
			return cateGoryName;
		}

		public void setCateGoryName(String cateGoryName) {
			this.cateGoryName = cateGoryName;
		}

		public String getBrandInfoName() {
			return brandInfoName;
		}

		public void setBrandInfoName(String brandInfoName) {
			this.brandInfoName = brandInfoName;
		}

		public String getBrandInfoLogo() {
			return brandInfoLogo;
		}

		public void setBrandInfoLogo(String brandInfoLogo) {
			this.brandInfoLogo = brandInfoLogo;
		}

		public String getBrandCountry() {
			return brandCountry;
		}

		public void setBrandCountry(String brandCountry) {
			this.brandCountry = brandCountry;
		}

		public String getBrandInfoText() {
			return brandInfoText;
		}

		public void setBrandInfoText(String brandInfoText) {
			this.brandInfoText = brandInfoText;
		}

	}

	private class BrandItem {

		private String brandName;
		private String brandUrl;

		public String getBrandName() {
			return brandName;
		}

		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}

		public String getBrandUrl() {
			return brandUrl;
		}

		public void setBrandUrl(String brandUrl) {
			this.brandUrl = brandUrl;
		}

	}

	private class CateGoryItem {

		private String cateGoryName;
		private String cateGoryUrl;

		public String getCateGoryName() {
			return cateGoryName;
		}

		public void setCateGoryName(String cateGoryName) {
			this.cateGoryName = cateGoryName;
		}

		public String getCateGoryUrl() {
			return cateGoryUrl;
		}

		public void setCateGoryUrl(String cateGoryUrl) {
			this.cateGoryUrl = cateGoryUrl;
		}

	}

}
