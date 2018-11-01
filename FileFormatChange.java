package package2;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class FileFormatChange {
	 public static void fileformatchange(String inputfilepath, String outputfilepath) throws IOException{
		 LinkedList<String[]> text_lines = new LinkedList<>();
		 try (BufferedReader br = new BufferedReader(new FileReader(inputfilepath))) {
		        String curLine;
		        while ((curLine = br.readLine()) != null) {
		            text_lines.add(curLine.replaceAll("( )+", " ").trim().split(" "));                 
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		 String [] fr ={"Date","Time","Temp Out","Hi Temp","Low Temp","Out Hum","Dew Pt.","Wind Speed","Wind Dir","Wind Run",
				 		"Hi Speed","Hi Dir","Wind Chill","Heat Index", "THW Index","THSW Index","Bar","Rain","Rain Rate",
				 		"Solar Rad.","Solar Energy","Hi Solar Rad.","UV Index","UV Dose","Hi UV","Heat D-D","Cool D-D",
				 		"In Temp","In Hum","In Dew","In Heat","In EMC","In Air Density","Temp 2nd","Hum 2nd","ET",
				 		"Soil 1 Moist.","Leaf Wet 1","Wind Samp","Wind Tx","ISS Recept","Arc. Int."};
		text_lines.removeFirst();
		text_lines.removeFirst();
		text_lines.removeFirst();
		text_lines.addFirst(fr);
		    
		
		    Workbook workbook = new HSSFWorkbook();
		    Sheet sheet = workbook.createSheet("Teste");
		    int row_num = 0;
		    for(String[] line : text_lines){
		        Row row = sheet.createRow(row_num++);
		        int cell_num = 0;
		        for(String value : line){
		            Cell cell = row.createCell(cell_num++);
		            cell.setCellValue(value);
		        }
		        
		    }

		    FileOutputStream fileOut = new FileOutputStream(outputfilepath);
		    workbook.write(fileOut);
		    fileOut.close();
		    workbook.close();
	 }
	 public static void main(String [] args) throws IOException{
		 String filepath = "C:\\Users\\Administrator\\Desktop\\test.txt";
		 String outputfilepath = "C:\\Users\\Administrator\\Desktop\\Testing.xls";
		 fileformatchange(filepath, outputfilepath);
	 }
}
