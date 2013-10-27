package com.rajkrrsingh.hadoop.mapjoin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
 
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
 
public class MapSideJoinMapper extends Mapper<LongWritable, Text, Text, Text> {
 
	private static HashMap<String, String> CustIdOrderMap = new HashMap<String, String>();
	private BufferedReader brReader;
	private String orderNO = "";
	private Text outKey = new Text("");
	private Text outValue = new Text("");
 
	enum MYCOUNTER {
		RECORD_COUNT, FILE_EXISTS, FILE_NOT_FOUND, SOME_OTHER_ERROR
	}
 
	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
 
		Path[] cacheFilesLocal = DistributedCache.getLocalCacheFiles(context
				.getConfiguration());
 
		for (Path eachPath : cacheFilesLocal) {
			if (eachPath.getName().toString().trim().equals("order_custid.txt")) {
				context.getCounter(MYCOUNTER.FILE_EXISTS).increment(1);
				setupOrderHashMap(eachPath, context);
			}
		}
 
	}
 
	private void setupOrderHashMap(Path filePath, Context context)
			throws IOException {
 
		String strLineRead = "";
 
		try {
			brReader = new BufferedReader(new FileReader(filePath.toString()));
 
			while ((strLineRead = brReader.readLine()) != null) {
				String custIdOrderArr[] = strLineRead.toString().split("\\s+");
				CustIdOrderMap.put(custIdOrderArr[0].trim(),	custIdOrderArr[1].trim());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			context.getCounter(MYCOUNTER.FILE_NOT_FOUND).increment(1);
		} catch (IOException e) {
			context.getCounter(MYCOUNTER.SOME_OTHER_ERROR).increment(1);
			e.printStackTrace();
		}finally {
			if (brReader != null) {
				brReader.close();
 
			}
 
		}
	}
 
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
 
		context.getCounter(MYCOUNTER.RECORD_COUNT).increment(1);
 
		if (value.toString().length() > 0) {
			String custDataArr[] = value.toString().split("\\s+");
 
			try {
				orderNO = CustIdOrderMap.get(custDataArr[0].toString());
			} finally {
				orderNO = ((orderNO.equals(null) || orderNO
						.equals("")) ? "NOT-FOUND" : orderNO);
			}
 
			outKey.set(custDataArr[0].toString());
 
			outValue.set(custDataArr[1].toString() + "\t"
					+ custDataArr[2].toString() + "\t"
					+ custDataArr[3].toString() + "\t" + orderNO);
 
		}
		context.write(outKey, outValue);
		orderNO = "";
	}
}