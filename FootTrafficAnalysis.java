import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.log4j.Logger;

public class FootTrafficAnalysis {

	private static String outputPath = "";
	private static Logger log = Logger.getLogger(FootTrafficAnalysis.class
			.getName());

	/*
	 * 
	 * In this method used to read input file data and add list of foot traffic
	 * 
	 * @param fileName contains input file return string
	 */
	private static boolean readFileSetMap(String inputFile) {
		List<FootTrafficVO> listFootTraffic = new ArrayList<FootTrafficVO>();
		TreeMap<Integer, List<FootTrafficVO>> treeMap = new TreeMap<Integer, List<FootTrafficVO>>();
		try {
			BufferedReader bufferReader;
			bufferReader = new BufferedReader(new FileReader(inputFile)); // BufferReader using  read  the input  file
			String line = bufferReader.readLine(); // read line by line
			while (line != null) { // check line value not equal null or not
				if(line.trim().length()==0){
					line = bufferReader.readLine();
					continue;
				}
				String[] data = line.split(" ");// remove space read line to assign array of String using  String.split method
				FootTrafficVO footTrafficVO = new FootTrafficVO();
				if (data.length != 0 && data.toString().length() != 0) { // Check String array length
					footTrafficVO.setVisitorNo(Integer.parseInt(data[0])); // visitor value
					footTrafficVO.setRoom(Integer.parseInt(data[1]));// room
					footTrafficVO.setVisitorStatus(data[2]);// visitor in / out status
					footTrafficVO.setInOutTime(Integer.parseInt(data[3]));// visitor  in  out  time
					listFootTraffic.add(footTrafficVO); // add list
					treeMap.put(footTrafficVO.getRoom(), listFootTraffic); // key room,value list of FootVisitorInfo
				} else {
					log.warn("There is no data in this file");

				}
				line = bufferReader.readLine();
			}
			bufferReader.close();
			getValueFromMap(treeMap);// In this method use to get value from map
			return true;
		} catch (IOException io) {
			log.error(io.getMessage());
			throw new FootTrafficCustomException("No File found ", io);
		} catch (FootTrafficCustomException e) {
			log.error(e.getMessage());
			log.error("File Contains Empty line");
			return false;
		}
	}

	/*
	 * In this method used to iterate output map to add room list details
	 */

	private static boolean getValueFromMap(
			TreeMap<Integer, List<FootTrafficVO>> insideTreeMap) {
		List<String> listOutput = new ArrayList<>();
		String outPut = "";
		List<FootTrafficVO> listFootVO = null;
		int visitor = -1;
		try {
			if (insideTreeMap.size() > 0) {// Check map size
				for (Map.Entry<Integer, List<FootTrafficVO>> entry : insideTreeMap
						.entrySet()) {
					listFootVO = new ArrayList<FootTrafficVO>();
					listFootVO = entry.getValue();// particular key list of foot
													// traffic value
					if (listFootVO != null) {
						Collections.sort(listFootVO, new CompareRoomVisitor());
					}// In this line used to ascending sort room,visitor wise
						// status
					double duration = 0;
					int count = 0;
					int inTime = 0;
					double avg = 0;
					if (listFootVO.size() > 0) {
						for (FootTrafficVO footTraffic : listFootVO) {
							if (footTraffic.getRoom() == entry.getKey()
									&& footTraffic.getVisitorNo() == visitor) {
								duration += footTraffic.getInOutTime() - inTime; // If same room and same visitor calculate duration for this room
								count++; // no of visitor for particular room
							}
							visitor = footTraffic.getVisitorNo();// assign visitor value to get previous visitor no for above if loop
							inTime = footTraffic.getInOutTime();// assign particular visitor in time to calculate duration purpose
						}
						avg = (duration / count); // calculate average of visitor spend for particular room
						outPut = "Room " + entry.getKey() + ","
								+ Math.round(avg) + " minute average visit,"
								+ count + " visitor(s) total";
						listOutput.add(outPut);// in this list used to write the  output file
						writeOutputFile(listOutput);// in this method write the out put file given output file path

					} else {
						log.warn("Foot Traffic list not in this room"
								+ entry.getKey());
						return false;
					}

				}
				openOutPutFile();// After write the file directly open output  file purpose
			}
		} catch (FootTrafficCustomException fe) {
			log.error("Tree Map Contains Error..");
			return false;
		}
		return true;
	}

	/*
	 * In this method used to write output value from list
	 * 
	 * @param listOutPut in this list contains room foot details return
	 * List<String>
	 */

	private static boolean writeOutputFile(List<String> listOutPut) {
		FileWriter writer;
		BufferedWriter buffer;
		try {
			writer = new FileWriter(outputPath); // write the file in this given path using FileWriter
			buffer = new BufferedWriter(writer);
			for (String output : listOutPut) {
				buffer.write(output);// iterate write line by line
				buffer.newLine();// create new line
				buffer.newLine();// create new line
				buffer.newLine();// create new line
				buffer.newLine();// create new line

			}
			buffer.close();
			log.info("Output File write Successfully");
			return true;
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return false;
		} catch (FootTrafficCustomException e) {
			log.error(e.getMessage());
			return false;
		}

	}

	/*
	 * In this method used to open output file
	 */
	private static boolean openOutPutFile() {
		if (!Desktop.isDesktopSupported()) {
			log.error("Desktop not support in this Platform");
			return false;
		}
		File file = new File(outputPath);
		Desktop desktop = Desktop.getDesktop();
		if (file.exists())
			try {
				desktop.open(file);
				return true;
			} catch (IOException e) {
				log.error(e.getMessage());
				e.printStackTrace();
				return false;
			} catch (FootTrafficCustomException e) {
				log.error("OS Enviorment not supported", e);
			}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("Enter a Input File Path :");
		Scanner scanner = new Scanner(System.in);
		String inputFile = scanner.nextLine();// input file path
		System.out.println("Enter a Output File Path :");
		outputPath = scanner.nextLine();// out put file path
		readFileSetMap(inputFile);

	}

}
