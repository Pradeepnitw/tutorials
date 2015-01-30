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
			// If we replace this line with
			// sum += 1;
			// Will this logic qualify as a Reducer? Nope
			// a Commutative/Associative operation should always 
			// return same result if called multiple times, sum += 1 doesn't
			// Will writing reducer like this, what type of combiner we would have used.
		}
		context.write(key, new IntWritable(sum));
	}
}
