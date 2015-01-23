package org.fionaarmstrong;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer 
extends Reducer<Text, IntWritable, Text, IntWritable>{
	// The input key and value data type should match the mapper's
	
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int sum = 0;
		// Almost all processing logic use this for loop
		for (IntWritable value : values) {
			sum += value.get();
		}
		context.write(key, new IntWritable(sum));
	}
}
