
"""
    Imagine a robot sitting on the upper left corner of an X by Y grid. The
    robot can only move in two directions: right and down. How many possible
    paths are there for the robot to go from (0, 0) to (X, Y)?
"""

def dp(x, y):
    """
    :param x int: x is the maximum step the robot can take for the x axis
    :param y int: y is the maximum step the robot can take for the y axis
    :return: number of possible paths for the robot to take go from (0, 0) to
    (x, y)
    """
    if not x and not y:
        return 0

    aux_2d = [[0]*(x+1)] * (y+1)  # (X + 1) by (Y + 1) 2d array

    # Initialize bottom row of aux_2d
    for i in reversed(range(x+1)):
        aux_2d[y][i] = 1
    # Initialize right col of aux_2d
    for j in reversed(range(y+1)):
        aux_2d[j][x] = 1

    for j in reversed(range(y)):
        for i in reversed(range(x)):
            aux_2d[j][i] = aux_2d[j+1][i] + aux_2d[j][i+1]
    return aux_2d[0][0]


def test_dp():
    assert dp(0, 0) == 0
    assert dp(0, 1) == 1
    assert dp(5, 0) == 1
    assert dp(1, 1) == 2
    assert dp(2, 2) == 6
    assert dp(3, 3) == 20
    assert dp(3, 4) == 35

"""
    Imagine certain spots are off limits, such that the robot cannot step on
    them. Design an alogrithm to find a path for the robot from the top left to
    the bottom right.
"""

def dp_limited(a):
    """
    :param a boolean[][]: a is the map, each step if True can be accessed by
    the robot, if False then it's off limits for the robot
    :return: number of possible paths for the robot to take go from (0, 0) to
    (x, y)
    """
    if not a or not a[0]:
        return 0

    aux_2d = []
    for _ in a:
        aux_2d.append([0]*len(a[0]))
    # (X + 1) by (Y + 1) 2d array
    assert len(aux_2d) == len(a)
    assert len(aux_2d[0]) == len(a[0])

    # Initialize bottom right
    if a[-1][-1]:
        aux_2d[-1][-1] = 1
    print aux_2d

    # Initialize right col of aux_2d
    for i in range(len(a) - 2, -1 , -1):
        if a[i][-1]:
            aux_2d[i][-1] = aux_2d[i][-1]
    print aux_2d

    # Initialize bottom row of aux_2d
    for j in range(len(a[-1]) - 2, -1, -1):
        if a[-1][j]:
            aux_2d[-1][j] = aux_2d[-1][j+1]
    print aux_2d

    # The rest of the aux array
    for j in reversed(range(len(a[-1])-1)):
        for i in reversed(range(len(a)-1)):
            aux_2d[i][j] = aux_2d[i+1][j] + aux_2d[i][j+1]
    print aux_2d
    return aux_2d[0][0]


def test_dp_limited():
    a = [[True, True, False], [True, True, True]]
    assert dp_limited(a) == 2


def run_tests():
    test_dp()
    test_dp_limited()
    print "All tests passed"

if __name__ == "__main__":
    run_tests()
