# straight-forward implementation of Mastermind
# no object-orientation here :-)

import simplegui

BLACK = "Black"
WHITE = "White"
RED = "Red"
YELLOW = "Yellow"
GREEN = "Green"
BLUE = "Blue"
ORANGE = "Orange"
BROWN = "Brown"

MAX_GUESSES = 12

secret = []
guess  = []
guess_history = []



    
    
def check_pattern(secret, guess):
    black_pegs = 0
    white_pegs = 0
    
    secret_clone = list(secret)
    guess_clone = list(guess)
    
    index = 0
    for color in guess:
        if guess[index] == secret[index]:
            black_pegs += 1
            guess_clone.remove(color)
            secret_clone.remove(color)
        index += 1
    
    for color in guess_clone:
        if color in secret_clone:
            white_pegs += 1
            secret_clone.remove(color)
    
    return create_result(black_pegs, white_pegs)
    
   
    
def create_result(black_pegs, white_pegs):
    blacks = [BLACK for i in range(black_pegs)]
    whites = [WHITE for i in range(white_pegs)]
    return blacks + whites



def btn_new_game():
    global secret, guess_history
    
    # TODO: randomize!
    secret = [RED, YELLOW, GREEN, RED]
    guess_history = []


def btn_ok(): 
    
    guess = [YELLOW, ORANGE, GREEN, RED]
    
    print secret
    print guess
    print check_pattern(secret, guess)



def draw(canvas):
    pass
    
    

frame = simplegui.create_frame("Mastermind", 400, 600)
frame.add_button("new game", btn_new_game, 100)
frame.add_label("")
frame.add_button("Ok", btn_ok, 100)
frame.set_draw_handler(draw)

# start the show
btn_new_game()
frame.start()