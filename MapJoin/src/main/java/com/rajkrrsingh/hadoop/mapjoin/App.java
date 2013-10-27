package com.rajkrrsingh.hadoop.mapjoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class App extends Configured implements Tool
{
    public static void main( String[] args ) throws Exception
    {
    	int exitCode = ToolRunner.run(new Configuration(),new App(), args);
    	System.exit(exitCode);
	}
    @Override
    public int run(String[] args) throws Exception {
    	if(args.length !=2 ){
    		System.err.println("Usage : App -files <location-to-cust-id-and-order-file> <input path> <output path>");
    		System.exit(-1);
    	}
    	Job job = new Job(getConf());
    	job.setJobName("Map Side Join");
    	job.setJarByClass(App.class);
    	FileInputFormat.addInputPath(job,new Path(args[0]) );
    	FileOutputFormat.setOutputPath(job, new Path(args[1]));
    	job.setMapperClass(MapSideJoinMapper.class);
    	job.setNumReduceTasks(0);
    	
    	boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;
    	
    }
}
