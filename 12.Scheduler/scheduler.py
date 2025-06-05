"""
Author: Alden Shin-Culhane
Date: 9/10/25
Class: CPS 109-061

Problem:
As a full-time student who also participates in extracurricular activities, time management and organization
are essential to being able to uphold my responsibilities and commitments. Although physical schedules or
planners would facilitate this, they have never appealed to me as they can be easily damaged, lost, or permeated
which will require a new physical schedule to be purchased. I need a digital schedule that I can easily save to my
computer which has simple features for adding, removing and editing events I have coming up in the next calender
week.

Description:
A python program that can create, export, import, modify and print a comprehensive weekly schedule of events
to stay on top of class, homework, activities and any other scheduled events. This program is able to export
a schedule of events to a text file. The information required for this includes event days, event names, and
event start and end times. From this information, the program is also able to import a previously exported
schedule for display or modification. This allows the user to essentially save a completed schedule to their
computer. Modification of the schedule includes adding events, removing events, and editing an event's start
and end times.
"""
def initSchedule(): # Creates an empty 2d list with 7 indices for each day of the week
    schedule = [[], [], [], [], [], [], []]
    return schedule

def getField(string, number): # Parses an event's information string
    field = "" 
    count = 0
    for i in string:
        if (i == "/"): # Check for field delimeter
            count += 1
        elif (count > number-1): # Build string from specified field
            field += i
        if (count > number): # Quit when field is over
            break
    return field

def getDay(string): # Finds and returns a number corresponding to the day of the week from an event's information
    return getField(string, 0)

def getName(string): # Finds and returns the name of an event from its information
    return getField(string, 1)

def getStartTime(string): # Finds and returns the start time of an event from its information
    return getField(string, 2)

def getEndTime(string): # Finds and returns the end time of an event from its information
    return getField(string, 3)

def userDayInput(): # Asks the user for a day to modify and returns the corresponding schedule index for that day
    day = str(input("Enter the day of the week you would like to modify (e.g. Monday): ")).lower()
    daysOfWeek = ["sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"] 
    for i in range(0, len(daysOfWeek)): # Search for day of week
        if (day == daysOfWeek[i]):
            return i # Return schedule index for the day
    print("Unrecognized day.")
    return 0 # Default to Sunday

def addEvent(schedule, day): # Adds event(s) to a specific day in the schedule
    numEvents = int(input("Enter the number of events you would like to add: "))
    for i in range(0, numEvents):
        # Prompts user for event information
        name = str(input("Enter a name for event " + str(i+1) + ": "))
        start = str(input("Enter a start time for event " + str(i+1) + " (e.g. 11:00am): "))
        end = str(input("Enter an end time for event " + str(i+1) + " (e.g. 2:00pm): "))      
        schedule[day].append(str(day) + "/" + name + "/" + start + "/" + end + "/")
    return schedule

def removeEvent(schedule, day): # Removes an event using the day and name of the event
    name = str(input("Enter the name of the event you want to remove: "))
    # Searches for the event to remove
    for i in range(0, len(schedule[day])):
        if (getName(schedule[day][i]).lower() == name.lower()):
            del schedule[day][i] # If found, remove event
            break
    return schedule

def editEvent(schedule, day): # Edits the start and end time of an event using the day and name of the event
    name = str(input("Enter the name of the event you want to edit: "))
    newStart = str(input("Enter a new start time for the event (e.g. 11:00am): "))
    newEnd = str(input("Enter a new end time for the event (e.g. 2:00pm): "))
    # Searches for the event to edit
    for i in range(0, len(schedule[day])):
        if (getName(schedule[day][i]).lower() == name.lower()):
            schedule[day][i] = (str(day) + "/" + name + "/" + newStart + "/" + newEnd + "/") # If found, update start and end times
            break
    return schedule

def printEvent(eventNum, name, start, end): # Prints event information to console
    print("     Event " + eventNum + ":")
    print("      -> Name: " + name)
    print("      -> Start Time: " + start)
    print("      -> End Time: " + end)

def printHeader(dayNum): # Prints day of week
    daysOfWeek = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"]
    print("________________________________________________________________")
    print(daysOfWeek[int(dayNum)] + ":")

def printFooter(): # Prints dashes
    print("________________________________________________________________")

def printSchedule(schedule): # Prints the current schedule to the console
    print("\n                        EVENT SCHEDULE:")
    for i in range(0, len(schedule)):
        for j in range(0, len(schedule[i])):
            currentEvent = schedule[i][j] # Event string
            currentDay = getDay(currentEvent) # Day of week as an integer (0-6, Sun-Sat)
            if (j < 1):
                printHeader(currentDay) # Print day of week
            printEvent(str(j+1), getName(currentEvent), getStartTime(currentEvent), getEndTime(currentEvent)) # Print event details
            if (j == len(schedule[i])-1):
                printFooter() # Print dashes
            print()

def importSchedule(schedule): # Imports a schedule from a file of the user's choice (load feature)
    schedule = initSchedule() # Reset schedule to an empty 2d list with 7 indicies
    fileName = str(input("Enter the name of the text file to import from (e.g. Calendar): ")) # Prompts the user to select a file
    fileContents = open(fileName + ".txt").readlines() # Creates a list of strings from a file, each string being a distinct event and its information
    for line in fileContents: # Creates a schedule from the event information in a file
        day = getDay(line)
        name = getName(line)
        start = getStartTime(line)
        end = getEndTime(line)
        schedule[int(day)].append(day + "/" + name + "/" + start + "/" + end + "/")
    return schedule

def exportSchedule(schedule): # Prints the current schedule to a file of the user's choice (save feature)
    fileName = str(input("Enter a name for the text file to export to (e.g. Calendar): ")) # Prompts the user to select a file
    fileContents = open(fileName + ".txt", "w") # Overwrides any text from a prexisting file or creates a new file if needed
    for i in range(0, len(schedule)): # Prints the event information from a schedule to a file
        for j in range(0, len(schedule[i])):
            currentEvent = schedule[i][j]
            fileContents.write(getDay(currentEvent) + "/" + getName(currentEvent) + "/" + getStartTime(currentEvent) + "/" + getEndTime(currentEvent) + "/\n")

def printOptions():
    print("\n----------------------------------------------------------------")
    print("                            OPTIONS:")
    print("1: Add event(s)")
    print("2: Remove an event")
    print("3: Edit an event")
    print("4: Print event schedule to console")
    print("5: Import an event schedule")
    print("6: Export event details to file")
    print("7: Exit")
    print("----------------------------------------------------------------\n")

def main():
    schedule = initSchedule() # Creates a 2d list for the current schedule
    inSchedule = True # Boolean used to quit the program when prompted
    while (inSchedule == True):
        printOptions()
        action = int(input("Enter a number for the corresponding action: ")) # Asks for user options in console
        if (action == 1): # If the user selects to add an event, do so accordingly with their inputted info
            dayOfWeek = userDayInput()
            schedule = addEvent(schedule, dayOfWeek)
            print("EVENT(S) ADDED!")
        elif (action == 2): # If the user selects to remove an event, do so accordingly with their inputted info
            dayOfWeek = userDayInput()
            schedule = removeEvent(schedule, dayOfWeek)
            print("EVENT REMOVED!")
        elif (action == 3): # If the user selects to edit an event, do so accordingly with their inputted info
            dayOfWeek = userDayInput()
            schedule = editEvent(schedule, dayOfWeek)
            print("EVENT EDITED!")
        elif (action == 4): # If the user selects to print the schedule, print the current schedule to console
            printSchedule(schedule)
        elif (action == 5): # If the user selects to import an event schedule, load the schedule from their specified file
            schedule = importSchedule(schedule)
            print("SCHEDULE IMPORTED FROM FILE.")
        elif (action == 6): # If the user selects to export an event schedule, write the schedule to their specified file
            exportSchedule(schedule)
            print("SCHEDULE EXPORTED TO FILE.")
        elif (action == 7): # Ends the schedule program
            print("EXITED SCHEDULE.")
            inSchedule = False
        else:
            print("Please enter a number from 1-7!")

main() # Initializes schedule program

