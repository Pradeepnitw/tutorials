
from random import random


class Board(object):

    def __init__(self):
        self._board = [[None, None, None], [None, None, None], [None, None, None]]
        self._result = None  # Could be the marker of winning side or None

    def __str__(self):
        return str(self._board)

    def is_empty(self, row_idx, col_idx):
        """ Checks if the cell is empty """
        return self._board[row_idx][col_idx] is None

    def empty_cells(self):
        """ Returns list of tuples """
        pass

    def _check_winner(self):
        # Check horizontal
        for l in self._board:
            if len(set(l)) == 1:
                self._result = l[0]
                if self._result is not None:
                    return

        # Check vertical
        for j in range(3):
            s = set()
            for i in range(3):
                s.add(self._board[i][j])
            if len(s) == 1:
                self._result = s.pop()
                if self._result is not None:
                    return

        # Check diagonal
        if len(set(
                [self._board[0][0], 
                self._board[1][1], 
                self._board[2][2]])) == 1:
            self._result = self._board[0][0]
            if self._result is not None:
                return
        if len(set(
                [self._board[2][0], 
                self._board[1][1], 
                self._board[0][2]])) == 1:
            self._result = self._board[2][0]
            if self._result is not None:
                return
        if not empty_cells:
            self._result = "T"


    def place(self, row_idx, col_idx, mark):
        """
            :return: return True if this move wins the game, False if lost the game,
            None for noop
        """
        if not self.is_empty(row_idx, col_idx):
            raise InvalidArgument("cell {} {} is pre-occupied".format(row_idx, col_idx))
        self._board[row_idx][col_idx] = mark
        self._check_winner()

    def winner(self):
        """
            :return: string value of the marker "X" or "O"
        """
        return self._result

MARKER_O = "O"
MARKER_X = "X"


def _get_random_empty_cell(b):
    i = int(random()*3)
    j = int(random()*3)
    while not b.is_empty(i, j):
        i = int(random()*3)
        j = int(random()*3)
    return i, j


def run_game():
    """ Plays with two random players """
    b = Board()
    current_player = MARKER_O
    while not b.winner():
        i, j = _get_random_empty_cell(b)
        print i, j
        b.place(i, j, current_player)
        if current_player == MARKER_O:
            current_player = MARKER_X
        else:
            current_player = MARKER_O
    print b
    print "{} wins".format(b.winner())


if __name__ == "__main__":
    b = Board()
    run_game()
