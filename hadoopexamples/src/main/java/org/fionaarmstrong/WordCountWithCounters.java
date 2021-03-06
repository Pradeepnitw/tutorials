package org.fionaarmstrong;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/*
 * Usage: hadoop jar xx.jar WordCountWithCounters inputpath outpath
 * 
 * in the log, this will show up:
 * 	bad words counter
 * 		BadRecord = 2
 */
public class WordCountWithCounters  extends Configured implements Tool {

	public int run(String[] arg0) throws Exception {
		Configuration conf = new Configuration();
		Job job = new Job(conf);
		
		job.setJarByClass(WordCountWithCounters.class);
		job.setJobName("Word Count with Counters");
		
		job.setMapperClass(WordCountMapperWithCounters.class);
		job.setCombinerClass(WordCountReducer.class);
		job.setReducerClass(WordCountReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setInputFormatClass(TextInputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path(arg0[0]));
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
		
		return job.waitForCompletion(true) ? 0 : 1;
	}
	
	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Configuration(), new WordCountWithCounters(), args);
		System.exit(exitCode);
	}

}
