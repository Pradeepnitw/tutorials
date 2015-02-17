package org.fionaarmstrong;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper 
extends Mapper<LongWritable, Text, Text, IntWritable> {
	// Mapper<inputKey, inputValue, outputKey, outputValue
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		StringTokenizer iter = new StringTokenizer(line);
		// tokenized the string into words, and count it... i guess
		while (iter.hasMoreTokens()) {
			word.set(iter.nextToken());
			context.write(word, one);
			// context writes to the intermediate key-value pair
			// that's going to be processed by the reducer
		}
	}
	
}
