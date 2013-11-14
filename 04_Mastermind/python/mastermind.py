import simplegui



def new_game():
    print "Good job!"


    
def draw(canvas):
    pass
    

frame = simplegui.create_frame("Mastermind", 400, 600)
frame.add_button("new game", new_game)
frame.set_draw_handler(draw)

# start the show
new_game()
frame.start()