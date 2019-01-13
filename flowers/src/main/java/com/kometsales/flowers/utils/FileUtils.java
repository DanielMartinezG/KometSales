package com.kometsales.flowers.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class FileUtils {

	
	public static <T> List<T> convertCsvToObject(Class<T> objectClass, InputStream inputStream) throws IOException{
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(objectClass).withoutEscapeChar().withColumnReordering(true);
		ObjectReader reader = mapper.readerFor(objectClass).with(schema);
		return reader.<T>readValues(inputStream).readAll();
	}
}
