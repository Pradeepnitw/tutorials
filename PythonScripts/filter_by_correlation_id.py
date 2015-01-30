import cPickle as pickle
import csv
import glob

CORRELATION_ID_FILE = "corrid_data.csv";
CORRELATION_IDS = [];
CORRELATION_IDS_AS_DICTIONARY = {};
OUTPUT = {}

def load_correlation_ids():
	fd = open(CORRELATION_ID_FILE, 'rU');
	reader = csv.reader(fd, dialect=csv.excel_tab);
	for row in reader:
		if isinstance(row, list):
			if len(row) > 0:
				CORRELATION_IDS.append(row[0]);

def preprocess_corrids_to_dictionary():
	for corr_id in CORRELATION_IDS:
		CORRELATION_IDS_AS_DICTIONARY[corr_id] = corr_id;


def filter_pickle_file_on_correlation_ids():
	for filename in glob.iglob('*.data'):
		fd = open(filename, 'r');
		print "Running File: %s" % filename
		while True:
			try:
				dictionary_of_data = pickle.load(fd);
				if 'corr_id' in dictionary_of_data and 'cctrans_id' in dictionary_of_data:
					if dictionary_of_data['corr_id'] in CORRELATION_IDS_AS_DICTIONARY:
						corr_id = dictionary_of_data['corr_id'];
						print 'Hey Found one correlation id %s with cc_trans %s' % (corr_id, dictionary_of_data['cctrans_id'])
						if corr_id not in OUTPUT:
							OUTPUT[corr_id] = [];
						OUTPUT[corr_id].append(dictionary_of_data['cctrans_id']);

				elif 'corr_id_' in dictionary_of_data and 'cctrans_id' in dictionary_of_data:
					if dictionary_of_data['corr_id_'] in CORRELATION_IDS_AS_DICTIONARY:
						corr_id = dictionary_of_data['corr_id_'];
						print 'Hey Found one correlation id %s with cc_trans %s' % (corr_id, dictionary_of_data['cctrans_id'])
						if corr_id not in OUTPUT:
							OUTPUT[corr_id] = [];
						OUTPUT[corr_id].append(dictionary_of_data['cctrans_id']);
			except:
				break;

def print_formatted_data():
	fd = open('output.csv','w+');
	for key in OUTPUT:
		fd.write(key + ',')
		for cctrans_id in OUTPUT[key]:
			fd.write(cctrans_id + ',')
		fd.write('\n')


def main():
	load_correlation_ids();
	preprocess_corrids_to_dictionary();
	filter_pickle_file_on_correlation_ids();
	print_formatted_data();

if __name__ == "__main__":
	main();
