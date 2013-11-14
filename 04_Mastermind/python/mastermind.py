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

GUESS_COLORS = (RED, YELLOW, GREEN, BLUE, ORANGE, BROWN)
MAX_GUESSES = 12

secret = []
guess  = []
guess_history = []
game_over = False


# helper function to adjust the color of the current guess
def adjust_guess_color(guess_index):
    if game_over:
        return
    color = guess[guess_index]
    index = GUESS_COLORS.index(color)
    guess[guess_index] = GUESS_COLORS[(index + 1) % len(GUESS_COLORS)]
    update_color_labels()
    
    
def update_color_labels():
    lbl_color0.set_text(guess[0])
    lbl_color1.set_text(guess[1])
    lbl_color2.set_text(guess[2])
    lbl_color3.set_text(guess[3])
    
    
    
def check_pattern(secret, guess):
    black_pegs = 0
    white_pegs = 0
    
    secret_clone = list(secret)
    guess_clone = list(guess)
    
    # remove all position matches
    index = 0
    for color in guess:
        if guess[index] == secret[index]:
            black_pegs += 1
            guess_clone.remove(color)
            secret_clone.remove(color)
        index += 1
    
    #remove all color matches
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
    global guess, secret, guess_history, game_over
    
    # TODO: randomize!
    secret = [RED, YELLOW, GREEN, RED]
    
    guess = [RED, YELLOW, GREEN, ORANGE]
    guess_history = []
    game_over = False
    
    update_color_labels()


def btn_ok(): 
    global game_over
    
    if game_over:
        return
    
    result = check_pattern(secret, guess)
    guess_history.append(list(guess, result))
    print guess, result
    
    if len(guess_history) >= MAX_GUESSES or result.count(BLACK) == 4:
        game_over = True
        print
        print "G A M E   O V E R   ! ! !"
        print 
        
        
def btn_color0(): 
    adjust_guess_color(0)
            
def btn_color1():
    adjust_guess_color(1)
        
def btn_color2(): 
    adjust_guess_color(2)
        
def btn_color3(): 
    adjust_guess_color(3)
    

    
def draw(canvas):
    
    pass
    
    


# build a simple UI
frame = simplegui.create_frame("Mastermind", 400, 600)
frame.add_button("new game", btn_new_game, 200)
frame.add_label("")
frame.add_label("")
frame.add_label("")
frame.add_button("color 0", btn_color0, 100)
lbl_color0 = frame.add_label("")
frame.add_button("color 1", btn_color1, 100)
lbl_color1 = frame.add_label("")
frame.add_button("color 2", btn_color2, 100)
lbl_color2 = frame.add_label("")
frame.add_button("color 3", btn_color3, 100)
lbl_color3 = frame.add_label("")
frame.add_label("")
frame.add_button("Ok", btn_ok, 100)
frame.set_draw_handler(draw)

# start the show
btn_new_game()
frame.start()