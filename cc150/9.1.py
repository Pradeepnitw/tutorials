
"""
    A child is running up a staircase with n steps, and can hope either 1 step
    2 steps, or 3 steps at a time. Implement a method to count how many possible
    ways the child can run up the stairs.
"""

def ways_to_run_upstairs(n):
    solutions = []
    if n == 0:
        return 1
    solutions.append(1) # 1
    
    if n == 1:
        return 2
    solutions.append(2) # 11; 2

    if n == 2:
        return 3
    solutions.append(3) # 12; 111; 21; 3

    i = 2
    while i <= n:
        solutions.append(
            solutions[i-1] + solutions[i-2] + solutions[i-3]
        )
        i += 1
    return solutions[-1]


if __name__ == "__main__":
    print ways_to_run_upstairs(10)
