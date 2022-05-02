
def hanoi(n, a, b, c, moves):
    '''(int, string, string, string, list)->None
    Solves the Towers of Hanoi puzzle
    for n disks and 3 pins recursively.
    
    Every move is represented by a
    tuple of two values. The first is
    the pin to move the top disk of,
    the second is the pin to move it to.
    '''
    if n == 1:
        moves.append((a, c))
    elif n > 1:
        hanoi(n-1, a, c, b, moves)
        hanoi(1, a, b, c, moves)
        hanoi(n-1, b, a, c, moves)
    else:
        raise Exception("Recursed below 1!")

run = True
while run:
    moves = []
    n = int(input("Input number of disks (integers only please).\n> ").strip())

    hanoi(n, "A", "B", "C", moves)

    for move in moves:
        print("Move the top disk on pin "+move[0]+" to pin "+move[1]+".")

