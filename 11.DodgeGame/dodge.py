'''
----------------------------------------------------------------------------------------------------
Author:       Alden, Nathan, Barry
Created:      December 6, 2017
Title:        Dodge Game.py
Purpose:      A program that runs a game where a user has to dodge the falling "balloons", if  they hit a balloon, they lose the game. If they successfully dodge 150 balloons, they win the game. There are 5 levels of difficulty that vary the speed of the falling balloons.
----------------------------------------------------------------------------------------------------
'''
from math import sqrt
from random import randint
import pygame
import time
pygame.init()

# Set screen
WIDTH = 800
HEIGHT = 600
TOP = 0
gameWindow = pygame.display.set_mode((WIDTH,HEIGHT))

# Define colours & fonts
WHITE = (255,255,255)
GREEN = (0,255,0)
BLACK = (0,0,0)
font = pygame.font.SysFont("Perpetua",36)
font2 = pygame.font.SysFont("Perpetua",150)
font3 = pygame.font.SysFont("Perpetua",72)
font4 = pygame.font.SysFont("Perpetua",56)

# Import Photos
background = pygame.image.load("dodgeBG.png")
background2 = pygame.image.load("dodgeBG2.png")

# Load music/sounds
pygame.mixer.music.load("wii music.mp3")
pygame.mixer.music.set_volume(0.5)
pygame.mixer.music.play(-1)

gameOver = pygame.mixer.Sound("Game Over.mp3")
gameOver.set_volume(0.8)

'''
--------------------------------------------------
                    Functions
--------------------------------------------------
'''
def startScreen():
    # Countdown "3"
    gameWindow.blit(background,(0,0))
    graphics = font2.render("3...",1,WHITE)
    gameWindow.blit(graphics,(325,200))
    pygame.display.update()

    # Countdown "2"
    gameWindow.blit(background,(0,0))
    graphics = font2.render("2...",1,WHITE)
    gameWindow.blit(graphics,(325,200))
    time.sleep(0.5)
    pygame.display.update()

    # Countdown "1" 
    gameWindow.blit(background,(0,0))
    graphics = font2.render("1...",1,WHITE)
    gameWindow.blit(graphics,(325,200))
    time.sleep(0.5)
    pygame.display.update()
    time.sleep(0.5)

def endScreen(messageY):
    while messageY > -600:
        # Draw background 
        gameWindow.blit(background,(0,0))
        
        # Game Over message
        graphics = font3.render("Game Over!",1,WHITE)
        gameWindow.blit(graphics,(245,messageY))

        # Thank you message
        graphics1 = font3.render("Thank you for playing!",1,WHITE)
        gameWindow.blit(graphics1,(110,messageY+100))
        
        # Authors 
        graphics2 = font4.render("Made by: Alden, Nathan, Barry",1,WHITE)
        gameWindow.blit(graphics2,(90,messageY+225))
        
        # Time played 
        graphics3 = font4.render("Time played: "+str(round(elapsed/1000.0-1.5,1))+" seconds",1,WHITE)
        gameWindow.blit(graphics3,(135,messageY+325))
        
        # End message
        if offScreen == 150:
            graphics4 = font3.render("Final score: "+str(offScreen),1,WHITE)
            gameWindow.blit(graphics4,(215,messageY+415))
            graphics5 = font3.render("You Beat the game!!!",1,WHITE)
            gameWindow.blit(graphics5,(150,messageY+515))
        else:
            graphics4 = font3.render("Final score: "+str(offScreen),1,WHITE)
            gameWindow.blit(graphics4,(215,messageY+415))

        # Update display, time delay 
        pygame.display.update()
        pygame.time.delay(10)

        # Move text up
        messageY -= shift
    
def distance(x1,y1,x2,y2):           
    return sqrt((x1-x2)**2 + (y1-y2)**2)

def drawMessages():
    # Render texts
    elapsedGraphics = font.render("Time Played: "+str(round(elapsed/1000.0-1.5,1)),1,WHITE)
    scoreGraphics = font.render("Score: "+str(offScreen),1,WHITE)

    # Draw timer and timer border
    gameWindow.blit(elapsedGraphics,(10,10))
    gameWindow.blit(scoreGraphics,(10,50))
    pygame.draw.rect(gameWindow,WHITE,(0,100,275,10))
    pygame.draw.rect(gameWindow,WHITE,(275,109,10,-109))

def redrawGameWindow():
    # Draw background 
    gameWindow.blit(background2,(0,0))
    
    # Draw player's "paddle"
    pygame.draw.rect(gameWindow,WHITE,(paddleX,paddleY,paddleW,paddleW))
    
    # Draw balloons
    for i in range(numBalloons):
        if balloonVisible[i]:
            pygame.draw.circle(gameWindow, balloonColour[i], (balloonX[i], balloonY[i]), balloonR[i], 0)
    drawMessages()
    pygame.display.update()
    pygame.time.delay(5)

'''
--------------------------------------------------
                    Main Program
--------------------------------------------------
'''
# Have the user choose the level of difficulty
difficulty = raw_input("Choose a level of difficulty (1, 2, 3, 4, or 5): ").upper()

# Declare variables
numBalloons = 150
offScreen = 0    
elapsed = 0
paddleX = 400
paddleY = 525
paddleW = 40
speedX = 10
messageY = 600
shift = 2.55
loopCount = 0

# Set lists
balloonX = []
balloonY = []
balloonR = []
balloonSpeed = []
balloonColour = []
balloonVisible = []

# Set time
BEGIN = pygame.time.get_ticks()
clock = pygame.time.Clock()
FPS = 52

# Generate balloons
for i in range(numBalloons):           
    balloonX.append(randint(0,WIDTH))
    balloonY.append(randint(-5500,TOP))
    balloonR.append(randint(20,50))

    # Easy difficulty, slow speed
    if difficulty == "1":
        balloonSpeed.append(randint(2,3))

    # Medium difficulty, medium speed
    elif difficulty == "2":
        balloonSpeed.append(randint(4,5))

    # Hard difficulty, fast speed
    elif difficulty == "3":
        balloonSpeed.append(randint(6,7))

    # Very Hard difficulty, very fast speed
    elif difficulty == "4":
        balloonSpeed.append(randint(8,9))

    # Impossible difficulty, extremely fast speed
    elif difficulty == "5":
        balloonSpeed.append(randint(10,11))
        
    balloonColour.append((randint(0,255), randint(0,255), randint(0,255)))
    balloonVisible.append(True)

# Game loop
inPlay = True
while inPlay and (offScreen < numBalloons):

    # Opening Screen
    if loopCount < 1:
        startScreen()
        loopCount += 1

    # Call function to draw game 
    redrawGameWindow()

    # Set fps 
    clock.tick(FPS)

    # Timer
    elapsed = pygame.time.get_ticks() - BEGIN

    # Get user input
    pygame.event.get()

    # Keyboard input          
    keys = pygame.key.get_pressed()

    # ESC to quit
    if keys[pygame.K_ESCAPE]:           
        inPlay = False

    # Press right to go right
    if keys[pygame.K_LEFT]:
        paddleX -= speedX
        # Stops at the left edge of the screen 
        if paddleX < 0:
            paddleX = 0

    # Press left to go left 
    if keys[pygame.K_RIGHT]:            
        paddleX += speedX
        # Stops at the right edge of the screen 
        if paddleX > 760:
            paddleX = 760
        
    # Move the falling balloons
    for i in range(numBalloons):
        balloonY[i] += balloonSpeed[i]
        if balloonVisible[i] and (balloonY[i]> 700 - balloonR[i]):
            offScreen = offScreen + 1
            balloonVisible[i] = False

        # If a balloon touches the top, game over
        for xValue in range(paddleX,paddleX+paddleW):
            if balloonVisible[i] and distance(xValue,paddleY,balloonX[i],balloonY[i]) <= balloonR[i]:
                inPlay = False

        # If a balloon touches the bottom, game over
        for xValue in range(paddleX,paddleX+paddleW):
            if balloonVisible[i] and distance(xValue,paddleY+paddleW,balloonX[i],balloonY[i]) <= balloonR[i]:
                inPlay = False

        # If a balloon touches the left side, game over
        for yValue in range(paddleY,paddleY+paddleW):
            if balloonVisible[i] and distance(paddleX,yValue,balloonX[i],balloonY[i]) <= balloonR[i]:
                inPlay = False

        # If a balloon touches the right side, game over
        for yValue in range(paddleY,paddleY+paddleW):
            if balloonVisible[i] and distance(paddleX+paddleW,yValue,balloonX[i],balloonY[i]) <= balloonR[i]:
                inPlay = False

# End messages
while True:
    pygame.mixer.music.stop()
    gameOver.play()
    endScreen(messageY)
    break
            
# Quit game when loop ends 
pygame.quit()

# Game summary
print " Game Over!"
print " Time played:",str(round(elapsed/1000.0-1.5,1)),"seconds"
if offScreen == 150:
    print " Final score:",offScreen,"\n You beat the game!!!"
else:
    print " Final score:",offScreen


