package com.ntc.paas.plat.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

@SuppressWarnings("rawtypes")
public class OrderTypeConverter implements Converter<Integer> {
	@Override
	public Class supportJavaTypeKey() {
		return null;
	}

	@Override
	public CellDataTypeEnum supportExcelTypeKey() {
		return null;
	}

	@Override
	public Integer convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty,
			GlobalConfiguration globalConfiguration) throws Exception {
		return null;
	}

	@Override
	public CellData convertToExcelData(Integer integer, ExcelContentProperty excelContentProperty,
			GlobalConfiguration globalConfiguration) throws Exception {

		if (integer == 1) {
			return new CellData("信托业务");
		} else if (integer == 2) {
			return new CellData("非信托业务");
		} else {
			return new CellData("");
		}
	}
}
