# straight-forward implementation of Mastermind
# no object-orientation here :-)

import simplegui
import random

PEG_PADDING = 10
PEG_SIZE_GUESS = 30
PEG_SIZE_RESULT = 20

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
    update_color_buttons()
    
    
    
def update_color_buttons():
    btn_color0.set_text(guess[0])
    btn_color1.set_text(guess[1])
    btn_color2.set_text(guess[2])
    btn_color3.set_text(guess[3])
    
    
    
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
    
    # create result list containing black and white pegs
    blacks = [BLACK for i in range(black_pegs)]
    whites = [WHITE for i in range(white_pegs)]
    return blacks + whites



# click handler for "new game" button
def btn_new_game():
    global guess, secret, guess_history, game_over
    
    # TODO: randomize!
    l = len(GUESS_COLORS)
    c0 = GUESS_COLORS[random.randrange(l)]
    c1 = GUESS_COLORS[random.randrange(l)]
    c2 = GUESS_COLORS[random.randrange(l)]
    c3 = GUESS_COLORS[random.randrange(l)]
    secret = [c0, c1, c2, c3]
    
    guess = [RED, YELLOW, GREEN, ORANGE]
    guess_history = []
    game_over = False
    
    update_color_buttons()



# click handler for OK button
def btn_ok(): 
    global game_over
    
    if game_over:
        return
    
    result = check_pattern(secret, guess)
    guess_history.append([list(guess), result])
       
    if result.count(BLACK) == 4 or len(guess_history) >= MAX_GUESSES:
        game_over = True



# click handlers for color switching buttons  
def btn_color0(): 
    adjust_guess_color(0)
    
def btn_color1():
    adjust_guess_color(1)
    
def btn_color2(): 
    adjust_guess_color(2)

def btn_color3(): 
    adjust_guess_color(3)
    
    

# draw handler of the canvas
def draw(canvas):
    x = PEG_PADDING + PEG_SIZE_GUESS/2
    y = PEG_PADDING
    
    # draw the entire history
    for entry in guess_history:
        # draw guess
        for color in entry[0]:
            canvas.draw_line([x, y], [x, y + PEG_SIZE_GUESS], PEG_SIZE_GUESS, color)
            x += PEG_SIZE_GUESS + PEG_PADDING
        
        # draw result
        x+= PEG_SIZE_GUESS + PEG_SIZE_RESULT
        
        for color in entry[1]:
            canvas.draw_line([x, y], [x, y + PEG_SIZE_RESULT], PEG_SIZE_RESULT, WHITE)
            if (color == BLACK):
                 canvas.draw_line([x, y+2], [x, y-2 + PEG_SIZE_RESULT], PEG_SIZE_RESULT-2, BLACK)
            x += PEG_SIZE_RESULT + PEG_PADDING
        
        
        # reset values for next line
        x = PEG_PADDING + PEG_SIZE_GUESS/2
        y += PEG_SIZE_GUESS + PEG_PADDING
     
    # draw the guess in the next line
    if not game_over:
        for color in guess:
            canvas.draw_line([x, y], [x, y + PEG_SIZE_GUESS], PEG_SIZE_GUESS, color)
            x += PEG_SIZE_GUESS + PEG_PADDING
    
    # reset values for next line
    x = PEG_PADDING + PEG_SIZE_GUESS/2
    y += PEG_SIZE_GUESS + PEG_PADDING
    
    # draw empty spots
    for i in range (MAX_GUESSES - len(guess_history) - 1):
        for color in guess:
            canvas.draw_line([x, y], [x, y + PEG_SIZE_GUESS], PEG_SIZE_GUESS, WHITE)
            canvas.draw_line([x, y+1], [x, y-1 + PEG_SIZE_GUESS], PEG_SIZE_GUESS-2, BLACK)
            x += PEG_SIZE_GUESS + PEG_PADDING
    
        # reset values for next line
        x = PEG_PADDING + PEG_SIZE_GUESS/2
        y += PEG_SIZE_GUESS + PEG_PADDING
    
    # reveal secret code
    if game_over:
        canvas.draw_text("G A M E   O V E R  !!!", [x, y + PEG_SIZE_GUESS], PEG_SIZE_GUESS, WHITE)
        y += 3 * PEG_PADDING
        
        # draw secret code
        for color in secret:
            canvas.draw_line([x, y], [x, y + PEG_SIZE_GUESS], PEG_SIZE_GUESS, color)
            x += PEG_SIZE_GUESS + PEG_PADDING
    
    
    
# build a simple UI
frame = simplegui.create_frame("Mastermind", 400, 600)
frame.add_button("new game", btn_new_game, 200)
frame.add_label("")
frame.add_label("")
frame.add_label("")
btn_color0 = frame.add_button("color 0", btn_color0, 100)
btn_color1 = frame.add_button("color 1", btn_color1, 100)
btn_color2 = frame.add_button("color 2", btn_color2, 100)
btn_color3 = frame.add_button("color 3", btn_color3, 100)
frame.add_label("")
frame.add_button("Ok", btn_ok, 100)
frame.set_draw_handler(draw)

# start the show
btn_new_game()
frame.start()