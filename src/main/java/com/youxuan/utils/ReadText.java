package com.youxuan.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReadText {

		public static List<String> readTxtFile(String filePath,String encoding){
			List<String> lines = new ArrayList<String>();
		    try {
		        //String encoding="utf-8"; 
		        File file=new File(filePath); 
		        if(file.isFile() && file.exists()){ //判断文件是否存在 
			        InputStreamReader read = new InputStreamReader( 
			        new FileInputStream(file),encoding);//考虑到编码格式
			        BufferedReader bufferedReader = new BufferedReader(read); 
			        String lineTxt = null;
			        while((lineTxt = bufferedReader.readLine()) != null){
			        	lines.add(lineTxt); //先放到List中方便后续操作
			        }
			        read.close();
			    }else{
			    	System.out.println("找不到指定的文件");
			    } 
		    } catch (Exception e) { 
			    System.out.println("读取文件内容出错"); 
			    e.printStackTrace(); 
		    } 
		    return lines;
		}

	    /**
	     * 根据读取的数据字典解析cspro文件后生成拆解后的文件
	     * @param dictionaryPath 数据字典路径
	     * @param dictionaryEncoding 数据字典文件编码
	     * @param filePath cspro数据文件路径
	     * @param fileEncoding cspro数据文件编码
	     * @param targetPath 拆解后的文件路径
	     */
	    public static void writeCSVFile(String dictionaryPath,String dictionaryEncoding,String filePath,String fileEncoding,String targetPath){
	    	List<String> lines = readTxtFile(dictionaryPath,dictionaryEncoding);
	    	DictionaryBean dic = new DictionaryBean();
	        RecordBean record = null;
	        ItemBean item = null;
	        List<RecordBean> records = new ArrayList<RecordBean>();
	        List<ItemBean> iditems = new ArrayList<ItemBean>();
	        for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				if("﻿[Dictionary]".equals(line)){
					for (int j = i+1; j < i+10; j++) {
						if("".equals(lines.get(j))){
							break;
						}else{
							String left = lines.get(j).split("=")[0];
							String right = lines.get(j).split("=")[1];
							if("Label".equals(left)){
								dic.setLabel(right);
							}else if("Name".equals(left)){
								dic.setName(right);
							}else if("Version".equals(left)){
								dic.setVersion(right);
							}else if("Note".equals(left)){
								dic.setNote(right);
							}else if("RecordTypeStart".equals(left)){
								dic.setRecordTypeStart(right);
							}else if("RecordTypeLen".equals(left)){
								dic.setRecordTypeLen(right);
							}else if("Positions".equals(left)){
								dic.setPositions(right);
							}else if("ZeroFill".equals(left)){
								dic.setZeroFill(right);
							}else if("DecimalChar".equals(left)){
								dic.setDecimalChar(right);
							}
						}
					}
				}
				if("[Record]".equals(line)){ //如果是record
					record = new RecordBean();
					for (int j = i+1; j < (i+10<=lines.size()?i+10:lines.size()); j++) { //根据文档，record最多有7个属性
						if("".equals(lines.get(j))){ //如果是空串，说明这个record已经结束
							break;
						}else{ //不是空串的时候，根据属性名判断并赋值
							String left = lines.get(j).split("=")[0];
							String right = lines.get(j).split("=")[1];
							if("Label".equals(left)){
								record.setLabel(right);
							}else if("Name".equals(left)){
								record.setName(right);
							}else if("RecordTypeValue".equals(left)){
								record.setRecordTypeValue(right);
							}else if("Required".equals(left)){
								record.setRequired(right);
							}else if("MaxRecords".equals(left)){
								record.setMaxRecords(right);
							}else if("RecordLen".equals(left)){
								record.setRecordLen(right);
							}else if("Note".equals(left)){
								record.setNote(right);
							}
						}
					}
					records.add(record);
				}
				if("[Item]".equals(line)){ //数据项是item
					item = new ItemBean();
					for (int j = i+1; j < (i+15<=lines.size()?i+15:lines.size()); j++) { //item�?多有11个属性
						if("".equals(lines.get(j))){ //如果是空串，说明这个item结束了，跳出循环
							break;
						}else{ //不是空串，就可以根据属性名判断并赋值
							String left = lines.get(j).split("=")[0];
							String right = lines.get(j).split("=")[1];
							if("Label".equals(left)){
								item.setLabel(right);
							}else if("Name".equals(left)){
								item.setName(right);
							}else if("Note".equals(left)){
								item.setNote(right);
							}else if("Start".equals(left)){
								item.setStart(right);
							}else if("Len".equals(left)){
								item.setLen(right);
							}else if("DataType".equals(left)){
								item.setDataType(right);
							}else if("ItemType".equals(left)){
								item.setItemType(right);
							}else if("Occurrences".equals(left)){
								item.setOccurrences(right);
							}else if("Decimal".equals(left)){
								item.setDecimal(right);
							}else if("DecimalChar".equals(left)){
								item.setDecimalChar(right);
							}else if("ZeroFill".equals(left)){
								item.setZeroFill(right);
							}
						}
					}
					if(records.size()>0){ //如果records中有了record，说明此时的item是record中的
						records.get(records.size()-1).getItems().add(item);
					}else{ //此时的item是iditems中的
						iditems.add(item);
					}
				}
			}
	        for (int i = 0; i < records.size(); i++) {
	        	FileWriter fw;
				try {
					fw = new FileWriter(targetPath+records.get(i).getLabel()+".CVS");
					StringBuffer bf = new StringBuffer();
					List<ItemBean> items = records.get(i).getItems();
					for (int j = 0; j < iditems.size(); j++) {
						bf.append(iditems.get(j).getName());
						bf.append("	");
					}
					for (int j = 0; j < items.size(); j++) {
						bf.append(items.get(j).getName());
						if(j<items.size()-1){
							bf.append("	");
						}
					}
					fw.write(bf.toString()+"\r\n");
					
					//开始写表数据
					List<String> lines2 = readTxtFile(filePath,fileEncoding);
					for (int j = 0; j < lines2.size(); j++) {
						int length = Integer.parseInt(dic.getRecordTypeLen());
						int start = Integer.parseInt(dic.getRecordTypeStart());
						if(!"".equals(lines2.get(i))){
							
							String type = "'"+lines2.get(j).substring(start-1, start+length-1)+"'";
							if(type.equals(records.get(i).getRecordTypeValue())){
								StringBuffer sf = new StringBuffer();
								for (int k = 0; k < iditems.size(); k++) {
									ItemBean item1 = iditems.get(k);
									int length1 = Integer.parseInt(item1.getLen());
									int start1 = Integer.parseInt(item1.getStart());
									sf.append(lines2.get(j).substring(start1-1, start1+length1-1).trim());
									sf.append("	");
								}
								for (int k = 0; k < records.get(i).getItems().size(); k++) {
									ItemBean item1 = records.get(i).getItems().get(k);
									int length1 = Integer.parseInt(item1.getLen());
									int start1 = Integer.parseInt(item1.getStart());
									String value = lines2.get(j).substring(start1-1, start1+length1-1).trim();
									if("Yes".equals(dic.getZeroFill())){
										if("".equals(value)){
											value = "0";
										}
									}
									sf.append(value);
									sf.append("	");
								}
								fw.write(sf.toString()+"\r\n");
							}
						}
					}
					
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	    }

		public static void main(String argv[]){ 
		    String filePath1 = "C:\\Users\\36138\\Desktop\\三农普CsPro标准数据包\\三农普CsPro标准数据包\\341502-A-1-2016-00(601).dcf";
		    String filePath2 = "C:\\Users\\36138\\Desktop\\三农普CsPro标准数据包\\三农普CsPro标准数据包\\341502-A-1-2016-00(601).dat";
		    System.out.println(new Date().getTime());
		    writeCSVFile(filePath1,"utf-8",filePath2,"GBK","E:\\files\\");
		    System.out.println(new Date().getTime());
		}

}
