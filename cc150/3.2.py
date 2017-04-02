
"""
    How would you design a stack which, in addition to push and pop, also has
    a function min which returns the minimum element? Push pop and min should
    all operate in O(1) time
"""

class Stack(object):
    def __init__(self):
        self._mins = []
        self._vals = []

    def push(self, var):
        self._vals.append(var)
        if not self._mins or var < self._mins[-1]:
            self._mins.append(var)
        elif var < self._mins[-1]:
            self._mins.append(var)
        else:
            self._mins.append(self._mins[-1])

    def pop(self):
        if self._vals:
            val = self._vals[-1]
            self._vals = self._vals[:-1]
            self._mins = self._mins[:-1]
            return val

    def min(self):
        if self._mins:
            return self._mins[-1]


if __name__ == "__main__":
    stack = Stack()
    stack.push(3)
    stack.push(2)
    stack.push(1)
    stack.push(3)
    print stack.min()
    print stack._mins, stack._vals
    print stack.pop()
    print stack._mins, stack._vals
    print stack.min()
    print stack._mins, stack._vals
    print stack.pop()
    print stack._mins, stack._vals
    print stack.min()
    print stack._mins, stack._vals
