package br.ufrn.imd.modelo;

import com.opencsv.CSVReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class BoatosReader {
	
	/*private static final String SAMPLE_CSV_FILE_PATH = "csv/boatos.csv";
	
	public List<String[]> oneByOne(Reader reader) throws Exception {
	    List<String[]> list = new ArrayList<>();
	    CSVReader csvReader = new CSVReader(reader);
	    String[] line;
	    while ((line = csvReader.readNext()) != null) {
	        list.add(line);
	    }
	    reader.close();
	    csvReader.close();
	    return list;
	}
	
	public String oneByOneExample() {
	    Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
	    return oneByOne(reader).toString();
	}*/
}