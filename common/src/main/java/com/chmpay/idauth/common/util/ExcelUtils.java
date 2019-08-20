package com.chmpay.idauth.common.util;

import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExcelUtils {


    private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);


    public synchronized static void downLoad(Map<String, List<String[]>> params, ServletOutputStream fos) throws Exception {

        try {
            // 普通格式
            WritableFont wfc = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false,
                    UnderlineStyle.NO_UNDERLINE);
            WritableCellFormat wcfFC = new WritableCellFormat(wfc);
            wcfFC.setAlignment(Alignment.LEFT); // 设置对齐方式
            wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);


            // 标题格式
            WritableFont wfc2 = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE);
            WritableCellFormat wcfFC2 = new WritableCellFormat(wfc2);
            wcfFC2.setAlignment(Alignment.LEFT); // 设置对齐方式
            wcfFC2.setVerticalAlignment(VerticalAlignment.CENTRE);


            //普通内容格式
            WritableFont wfc3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
                    UnderlineStyle.NO_UNDERLINE);
            WritableCellFormat wcfFC3 = new WritableCellFormat(wfc3);
            wcfFC3.setAlignment(Alignment.LEFT); // 设置对齐方式
            wcfFC3.setVerticalAlignment(VerticalAlignment.CENTRE);

            NumberFormat fivedps1 = new NumberFormat("0.00");
            WritableCellFormat fivedpsFormat1 = new WritableCellFormat(fivedps1);
            fivedpsFormat1.setAlignment(jxl.format.Alignment.RIGHT);

            WritableWorkbook wwb;
            wwb = jxl.Workbook.createWorkbook(fos);
            
            List<String[]>	professisonList=params.get("专用发票");
        	List<String[]>	normalList=params.get("普通发票");
            // 添加第一个工作表并设置第一个Sheet的名字
            int sheetIndex = 0;
            for (Iterator<Map.Entry<String, List<String[]>>> it = params.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, List<String[]>> entry = it.next();
                WritableSheet sheet = wwb.createSheet(entry.getKey(), sheetIndex);
             
                Label label;
                int r = 0;
                String[] title = entry.getValue().get(0);
                for (int i = 0; i < title.length; i++) {
                    label = new Label(i, r, title[i], wcfFC2);
                    sheet.addCell(label);
                    sheet.setColumnView(i, 26);// 根据内容自动设置列宽
                }
                r++;
                for (int i = 1; i < entry.getValue().size(); i++) {
                    String[] value = entry.getValue().get(i);

                    for (int j = 0; j < value.length; j++) {
                    	boolean isFasle=false;
                    	if(!professisonList.isEmpty()&&professisonList.size()>1&&j==7){
                    		if(sheetIndex==0){
                    			isFasle=true;
                    		}
                    		
                    	}else{
                    		if(!normalList.isEmpty()&&normalList.size()>1&&j==3){
                    			if(sheetIndex==1){
                    				isFasle=true;
                    			}
                        		
                        	}
                    	}
           
                    	

                        if (isFasle) {
                            jxl.write.Number doubleNum1 = new jxl.write.Number(j, r, Double.valueOf(
                                    value[j]).doubleValue(), fivedpsFormat1);
                            sheet.addCell(doubleNum1);
                        } else {
                            label = new Label(j, r, value[j], wcfFC3);
                            sheet.addCell(label);

                        }
                        sheet.setColumnView(j, 26);// 根据内容自动设置列宽
                    }
                    r++;
                }
                sheetIndex++;

            }
            wwb.write();
            // 关闭文件
            wwb.close();
            if (fos != null) {
                fos.close();
            }

        } catch ( IOException e ) {
            e.printStackTrace();
            throw e;

        }
    }


}
