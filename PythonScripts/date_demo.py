"""
python -m pip install -U pip setuptools
python -m pip install matplotlib
================
Date tick labels
================

Show how to make date plots in matplotlib using date tick locators and
formatters.  See major_minor_demo1.py for more information on
controlling major and minor ticks

All matplotlib date plotting is done by converting date instances into
days since the 0001-01-01 UTC.  The conversion, tick locating and
formatting is done behind the scenes so this is most transparent to
you.  The dates module provides several converter functions date2num
and num2date

"""
import datetime
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.dates as mdates
import matplotlib.cbook as cbook

years = mdates.YearLocator()   # every year
months = mdates.MonthLocator()  # every month
yearsFmt = mdates.DateFormatter('%Y')

"""
# load a numpy record array from yahoo csv data with fields date,
# open, close, volume, adj_close from the mpl-data/example directory.
# The record array stores python datetime.date as an object array in
# the date column
datafile = cbook.get_sample_data('goog.npy')
try:
    # Python3 cannot load python2 .npy files with datetime(object) arrays
    # unless the encoding is set to bytes. However this option was
    # not added until numpy 1.10 so this example will only work with
    # python 2 or with numpy 1.10 and later.
    r = np.load(datafile, encoding='bytes').view(np.recarray)
except TypeError:
    r = np.load(datafile).view(np.recarray)

fig, ax = plt.subplots()
ax.plot(r.date, r.adj_close)
"""
thingy_dates = np.array([
    datetime.date(2013, 11, 17),
    datetime.date(2013, 11, 18), datetime.date(2013, 12, 18),
    datetime.date(2013, 12, 19), datetime.date(2014, 1, 26),
    datetime.date(2014, 1, 27), datetime.date(2014, 5, 8),
    datetime.date(2014, 5, 9), datetime.date(2014, 7, 6),
    datetime.date(2014, 7, 7), datetime.date(2015, 2, 20),
    datetime.date(2015, 2, 19), datetime.date(2015, 3, 29),
    datetime.date(2015, 3, 30), datetime.date(2015, 5, 7),
    datetime.date(2015, 5, 8), datetime.date(2015, 7, 6),
    datetime.date(2015, 7, 6), datetime.date(2017, 2, 15),
    datetime.date(2017, 2, 16), datetime.date(2017, 5, 29),
    datetime.date(2017, 5, 30), datetime.date(2017, 6, 3)
])
thingy = np.array([
    0,
    50, 50,
    0, 0,
    75, 75,
    0, 0,
    120, 120,
    0, 0,
    125, 125,
    0, 0,
    225, 225,
    0, 0,
    304, 304
])

fig, ax = plt.subplots()
ax.plot(thingy_dates, thingy)

# format the ticks
ax.xaxis.set_major_locator(years)
ax.xaxis.set_major_formatter(yearsFmt)
ax.xaxis.set_minor_locator(months)

datemin = datetime.date(thingy_dates.min().year, 1, 1)
datemax = datetime.date(thingy_dates.max().year + 1, 1, 1)
ax.set_xlim(datemin, datemax)


# format the coords message box
def price(x):
    return '$%1.2f' % x
ax.format_xdata = mdates.DateFormatter('%Y-%m-%d')
ax.format_ydata = price
ax.grid(True)

# rotates and right aligns the x labels, and moves the bottom of the
# axes up to make room for them
fig.autofmt_xdate()

plt.show()
